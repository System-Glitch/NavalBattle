/*
MIT License

Copyright (c) 2018 SystemGlitch

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
