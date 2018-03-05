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
