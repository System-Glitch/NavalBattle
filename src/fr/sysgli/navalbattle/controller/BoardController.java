package fr.sysgli.navalbattle.controller;

import java.util.Random;

import fr.sysgli.navalbattle.model.Player;
import fr.sysgli.navalbattle.model.Ship;
import fr.sysgli.navalbattle.model.ShipOrientation;
import fr.sysgli.navalbattle.model.ShipType;
import fr.sysgli.navalbattle.view.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BoardController implements Observer_ {

	@FXML GridPane gridPlayer;
	@FXML GridPane gridEnemy;
	@FXML Label infoLabel;
	
	private Player player;
	private Player enemy;
	
	private int turn;

	@FXML
	void initialize() {
		
		drawGraduations(gridPlayer);
		drawGraduations(gridEnemy);
		
		initGame();

	}
	
	private void initGame() {
		turn = 1;
		player = new Player();
		enemy = new Player();
		enemy.enableIA();
		
		fillGrid(gridPlayer, player, false, enemy);
		fillGrid(gridEnemy, enemy, true, player);
		
		generateRandomShips(player);
		generateRandomShips(enemy);
		
		renderGrid(player);
		renderGrid(enemy);
		
		infoLabel.setText("Turn: " + turn);
	}
	
	@Override
	public void update(Player player) {
		if(player.equals(this.player)) { //Player played
			if(enemy.isDead()) {
				revealGrid(enemy);
				infoLabel.setText("You win !");
			} else
				enemy.randomPlay(player);
		} else if(player.equals(this.enemy)) { //Enemy played
			if(this.player.isDead()) {
				revealGrid(enemy);
				infoLabel.setText("Vous lose !");
			} else {
				turn++;
				infoLabel.setText("Turn: " + turn);
			}
		}
	}
	
	private void renderGrid(Player player) {
		for(int x = 0 ; x < 10 ; x++) {
			for(int y = 0 ; y < 10 ; y++) {
				player.getTile(x, y).update();
			}
		}
	}

	private void drawGraduations(GridPane grid) {
		for(char i = 0 ; i < 10 ; i++) {
			BorderPane pane = new BorderPane();
			Label graduation = new Label(String.valueOf((char) (65+i)));
			pane.setCenter(graduation);
			grid.add(pane, i+1, 0);
		}
		for(int i = 1 ; i <= 10 ; i++) {
			BorderPane pane = new BorderPane();
			Label graduation = new Label(String.valueOf(i));
			pane.setCenter(graduation);
			grid.add(pane, 0, i);
		}

	}
	
	private void fillGrid(GridPane grid, Player player, boolean listen, Player enemy) {
		for(int x = 1 ; x <= 10 ; x++) {
			for(int y = 1 ; y <= 10 ; y++) {
				Tile pane = new Tile(x,y, listen, enemy);
				pane.setUnknown(true);
				pane.addObserver(this);
				player.setTile(x-1, y-1, pane);
				grid.add(pane, x, y);
			}
		}
	}
	
	private void generateRandomShips(Player player) {
		Random random = new Random();
		ShipOrientation orientation;
		for(ShipType type : ShipType.values()) {
			for(int i = 0 ; i < type.getQuantity() ; i++) {
				Ship ship = new Ship(type);
				
				do {
					orientation = random.nextBoolean() ? ShipOrientation.HORIZONTAL : ShipOrientation.VERTICAL;
					ship.setOrientation(orientation);
					ship.setPosition(orientation.equals(ShipOrientation.HORIZONTAL) ? random.nextInt(10-type.getSize()) : random.nextInt(10), orientation.equals(ShipOrientation.VERTICAL) ? random.nextInt(10-type.getSize()) : random.nextInt(10));
				} while(!player.canPlaceShip(ship));
				player.addShip(ship);
			}
		}
	}
	
	private void revealGrid(Player player) {
		for(int x = 0 ; x < 10 ; x++) {
			for(int y = 0 ; y < 10 ; y++) {
				Tile tile = player.getTile(x, y);
				tile.setUnknown(false);
				tile.update();
			}
		}
	}
	
	@FXML
	protected void onReplayButtonClicked(ActionEvent event) {
		initGame();
	}

}
