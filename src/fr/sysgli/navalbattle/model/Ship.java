package fr.sysgli.navalbattle.model;

public final class Ship {

	private ShipType type;
	private ShipOrientation orientation;
	private int x,y;
	private int health;

	public Ship(ShipType type) {
		this.type = type;
		health = type.getSize();
	}

	public final ShipType getType() {
		return type;
	}

	public final boolean isDestroyed() {
		return health <= 0;
	}

	public final void damage() {
		if(health > 0)
			health--;
	}
	
	public final ShipOrientation getOrientation() {
		return orientation;
	}

	public final void setOrientation(ShipOrientation orientation) {
		this.orientation = orientation;
	}

	public final void setPosition(int x, int y) {
		if(x < 0 || x > 9)
			throw new IllegalArgumentException("X coordinate must be bewteen 0 and 9, " + x + "given.");
		if(y < 0 || y > 9)
			throw new IllegalArgumentException("Y coordinate must be bewteen 0 and 9, " + y + "given.");
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean validateCoordinates() {
		if(orientation.equals(ShipOrientation.HORIZONTAL))
			return x <= 9 - type.getSize();
		else if(orientation.equals(ShipOrientation.VERTICAL))
			return y <= 9 - type.getSize();
		else
			return false;
	}
	
}
