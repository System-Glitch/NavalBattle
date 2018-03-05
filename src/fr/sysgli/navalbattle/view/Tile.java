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

package fr.sysgli.navalbattle.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.sysgli.navalbattle.controller.Observable_;
import fr.sysgli.navalbattle.controller.Observer_;
import fr.sysgli.navalbattle.model.Player;
import fr.sysgli.navalbattle.model.Ship;
import fr.sysgli.navalbattle.model.ShipOrientation;
import fr.sysgli.navalbattle.resources.ResourcesLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class Tile extends Pane implements Observable_ {

	private static Image waterTexture;
	private static Image waterTextureHit;
	
	private int x, y;
	private TileType type;
	private boolean unknown;
	private boolean listen;
	private int index;
	private Ship ship;
	

	private ArrayList<Observer_> listObservers = new ArrayList<Observer_>();
	
	public Tile(int x, int y, boolean listen, final Player enemy) {
		super();
		this.x = x;
		this.y = y;
		this.type = TileType.WATER;
		this.unknown = true;
		this.listen = listen;
		this.index = 0;
		this.setStyle("-fx-background-color: #CCCCCC;");

		if(listen)
			this.setOnMouseClicked((event) -> handleClick(enemy));

	}
	

	@Override
	public void addObserver(Observer_ obs) {
		listObservers.add(obs);
	}

	@Override
	public void updateObservers(Player player) {
		for(Observer_ obs : listObservers)
			obs.update(player);
	}

	@Override
	public void deleteObserver(Observer_ obs) {
		listObservers.remove(obs);
	}

	public void handleClick(Player whoClicked) {
		if(!unknown) return;
		if(type.equals(TileType.SHIP)) {
			if(ship != null)
				ship.damage();
			else
				Logger.getGlobal().warning("Tile is of type SHIP but has no ship linked");
			setType(TileType.SHIP_DETROYED);
		} else if(type.equals(TileType.WATER)) {
			setType(TileType.WATER_HIT);
		}
		setUnknown(false);
		update();
		updateObservers(whoClicked);
	}
	

	public final Ship getShip() {
		return ship;
	}

	public final void setShip(Ship ship) {
		this.ship = ship;
	}

	public final TileType getType() {
		return type;
	}

	public final void setType(TileType type) {
		this.type = type;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final boolean isUnknown() {
		return unknown;
	}

	public final void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}

	public final int getIndex() {
		return index;
	}

	public final void setIndex(int index) {
		if(index < 0) throw new IllegalArgumentException("Tile index must be positive, " + index + " given.");
		this.index = index;
	}

	public final void update() {
		this.setStyle("");
		if(!unknown || !listen) {
			switch(type) {
			case SHIP:
				if(ship != null) {
					Image image = ship.getType().getTextures()[index];
					if(image != null) {
						this.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100.0, 100.0, true, true, true, false))));

						if(ship.getOrientation().equals(ShipOrientation.VERTICAL))
							this.setRotate(90);
					}
				} else {
					Logger.getGlobal().warning("Tile of type ship trying to render without ship reference.");
					this.setStyle("-fx-background-color: #00FF00;");
				}
				break;
			case SHIP_DETROYED:
				this.setStyle("-fx-background-color: #FF0000;");
				break;
			case WATER:
				this.setBackground(new Background(new BackgroundImage(waterTexture, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100.0, 100.0, true, true, true, false))));
				break;
			case WATER_HIT:
				this.setBackground(new Background(new BackgroundImage(waterTextureHit, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100.0, 100.0, true, true, true, false))));
				break;
			default:
				this.setStyle("-fx-background-color: #CCCCCC;");
				break;		
			}
		} else {
			this.setStyle("-fx-background-color: #CCCCCC;");
		}
	}

	static {		
		try {
			URL url = ResourcesLoader.class.getResource("water.jpg");
			waterTexture = new Image(url.openStream());
			url = ResourcesLoader.class.getResource("water_hit.jpg");
			waterTextureHit = new Image(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
