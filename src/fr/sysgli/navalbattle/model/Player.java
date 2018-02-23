package fr.sysgli.navalbattle.model;

import java.util.ArrayList;

import fr.sysgli.navalbattle.controller.PlayerIntelligence;
import fr.sysgli.navalbattle.view.Tile;
import fr.sysgli.navalbattle.view.TileType;

public final class Player {

	private ArrayList<Ship> ships;
	private Tile tiles[][];
	private PlayerIntelligence ia;
	
	public Player() {
		ships = new ArrayList<Ship>();
		tiles = new Tile[10][10];
	}
	
	public void enableIA() {
		ia = new PlayerIntelligence(this);
	}
	
	public void addShip(Ship ship) {
		ships.add(ship);
		
		int pos = ship.getOrientation().equals(ShipOrientation.HORIZONTAL) ? ship.getX() : ship.getY();
		int j = 0;
		for(int i = pos ; i < pos + ship.getType().getSize() ; i++) {
			Tile tile = tiles[ship.getOrientation().equals(ShipOrientation.VERTICAL) ? ship.getX() : i][ship.getOrientation().equals(ShipOrientation.HORIZONTAL) ? ship.getY() : i];
			tile.setType(TileType.SHIP);
			tile.setShip(ship);
			tile.setIndex(j++);
		}
	}
	
	public boolean canPlaceShip(Ship ship) {
		int pos = ship.getOrientation().equals(ShipOrientation.HORIZONTAL) ? ship.getX() : ship.getY();
		for(int i = pos ; i < pos + ship.getType().getSize() ; i++)
			if(!tiles[ship.getOrientation().equals(ShipOrientation.VERTICAL) ? ship.getX() : i][ship.getOrientation().equals(ShipOrientation.HORIZONTAL) ? ship.getY() : i].getType().equals(TileType.WATER))
				return false;
		return true;
	}
	
	public void setTile(int x , int y , Tile tile) {
		tiles[x][y] = tile;
	}
	
	public Tile getTile(int x , int y) {
		return tiles[x][y];
	}
	
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	public boolean isDead() {
		for(Ship ship : ships)
			if(!ship.isDestroyed()) return false;
		return true;
	}
	
	public void randomPlay(Player enemy) {
		ia.play(enemy);
	}

}
