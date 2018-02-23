package fr.sysgli.navalbattle.controller;

import fr.sysgli.navalbattle.model.Player;

public interface Observable_ {

	public void addObserver(Observer_ obs);
	public void updateObservers(Player player); //Called when a tile is clicked
	public void deleteObserver(Observer_ obs);

}
