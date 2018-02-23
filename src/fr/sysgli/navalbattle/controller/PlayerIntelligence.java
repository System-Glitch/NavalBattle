package fr.sysgli.navalbattle.controller;

import java.util.Random;

import fr.sysgli.navalbattle.model.Player;
import fr.sysgli.navalbattle.model.ShipOrientation;
import fr.sysgli.navalbattle.view.Tile;
import fr.sysgli.navalbattle.view.TileType;

public final class PlayerIntelligence {

	private Player player;
	private boolean shipFound;
	private boolean triedLeft;
	private boolean triedRight;
	private boolean triedDown;
	private boolean triedUp;
	private ShipOrientation orientation;
	private int direction;
	private int x, y;

	public PlayerIntelligence(Player player) {
		this.player = player;
		reset();
	}

	public void play(Player enemy) {

		if( ( triedLeft && triedRight && orientation != null && orientation.equals(ShipOrientation.HORIZONTAL)	) ||
			( triedUp   && triedDown  && orientation != null && orientation.equals(ShipOrientation.VERTICAL)    ) ||
			( triedLeft && triedRight && triedUp && triedDown && orientation == null 							) )
			reset();
		
		if(!shipFound) {
			Random random = new Random();
			Tile tile;
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
				tile = enemy.getTile(x, y);
			} while(!tile.isUnknown());

			fire(tile);

			if(tile.getType().equals(TileType.SHIP_DETROYED))
				shipFound = true;
		} else {
			
			if(!triedLeft) { //Left
				
				if(x+direction != 0) { 				
					direction -= 1;
					
					Tile tile = enemy.getTile(x+direction, y);
					if(!tile.isUnknown()) {
						direction = 0;
						triedLeft = true;
						play(enemy);
						return;
					}
					fire(tile);
					if(!tile.getType().equals(TileType.SHIP_DETROYED)) {
						triedLeft = true;
						direction = 0;
					} else {
						orientation = ShipOrientation.HORIZONTAL;
					}
					
				} else {
					direction = 0;
					triedLeft = true;
					play(enemy);
				}
				
			} else if(!triedRight) { //Right
				
				if(x+direction != 9) { 				
					direction += 1;
					
					Tile tile = enemy.getTile(x+direction, y);
					if(!tile.isUnknown()) {
						direction = 0;
						triedRight = true;
						play(enemy);
						return;
					}
					fire(tile);
					if(!tile.getType().equals(TileType.SHIP_DETROYED)) {
						triedRight = true;
						direction = 0;
					} else {
						orientation = ShipOrientation.HORIZONTAL;
					}
					
				} else {
					direction = 0;
					triedRight = true;
					play(enemy);
				}
				
			} else if(!triedUp && (orientation == null || orientation.equals(ShipOrientation.VERTICAL))) { //Up
				
				if(y+direction != 0) { 				
					direction -= 1;
					
					Tile tile = enemy.getTile(x, y+direction);
					if(!tile.isUnknown()) {
						direction = 0;
						triedUp = true;
						play(enemy);
						return;
					}
					fire(tile);
					if(!tile.getType().equals(TileType.SHIP_DETROYED)) {
						triedUp = true;
						direction = 0;
					} else {
						orientation = ShipOrientation.VERTICAL;
					}
					
				} else {
					direction = 0;
					triedUp = true;
					play(enemy);
				}
				
			} else if(!triedDown && (orientation == null || orientation.equals(ShipOrientation.VERTICAL))) { //Up
				
				if(y+direction != 0) { 				
					direction += 1;
					
					Tile tile = enemy.getTile(x, y+direction);
					if(!tile.isUnknown()) {
						direction = 0;
						triedDown = true;
						play(enemy);
						return;
					}
					fire(tile);
					if(!tile.getType().equals(TileType.SHIP_DETROYED)) {
						triedDown = true;
						direction = 0;
					} else {
						orientation = ShipOrientation.VERTICAL;
					}
					
				} else {
					direction = 0;
					triedDown = true;
					play(enemy);
				}
				
			}
		}

		//Debug
		//System.out.println("found : " + shipFound + " | triedLeft : " + triedLeft + " | triedRight : " + triedRight + " | triedDown : " + triedDown + " | triedUp : " + triedUp + " | orientation : " + orientation + " | direction : " + direction);

	}

	private void reset() {
		shipFound = false;
		triedLeft = triedRight = triedDown = triedUp = false;
		direction = 0;
		orientation = null;
	}

	private void fire(Tile tile) {
		tile.handleClick(player);
	}

}
