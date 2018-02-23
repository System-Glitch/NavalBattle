package fr.sysgli.navalbattle.model;

import java.io.IOException;
import java.net.URL;

import fr.sysgli.navalbattle.resources.ResourcesLoader;
import javafx.scene.image.Image;

public enum ShipType {

	CARRIER(5, 1),
	BATTLESHIP(4, 2),
	CRUISER(3, 3),
	DESTROYER(2, 3);

	private int size;
	private int quantity;
	private Image[] textures;

	ShipType(int size, int quantity) {
		this.size = size;
		this.quantity = quantity;
		this.textures = new Image[size];

		try {
			for(int i = 0 ; i < textures.length ; i++) {
				URL url = ResourcesLoader.class.getResource(this.name().toLowerCase() + "_" + (i+1) + ".jpg");
				if(url != null)
					textures[i] = new Image(url.openStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getSize() {
		return size;
	}

	public int getQuantity() {
		return quantity;
	}

	public Image[] getTextures() {
		return textures;
	}

}
