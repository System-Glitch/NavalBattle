# NavalBattle
A graphical solo vs AI battleship game made in Java and using JavaFX.

## Install
The project uses maven 2. You can build using `mvn install`.  

## Getting started
Maven should have built a runnable jar file in the `target` folder. Simply double click it to run or use `java -jar file.jar`.  

The board will appear. Your boats and the enemy's ones are placed randomly.  

![Fresh start screenshot](https://i.imgur.com/sRerG9W.png)  

Your board is on the left. The enemy's one is on the right.  
You're first to play. In order to fire, click the tile you want in your enemy's grid. If a red square appears, you hit a ship. 
However if a red cross appears, it means that you fired in the water.  
  
Once your turn is finished, the AI will play. Its actions are represented the same way on your grid.  
  
![Some turns later screenshot](https://i.imgur.com/t78Ow8J.png)  

Destroy all the enemy's ships before it manages to destroy all yours!  
Once the game is over, the enemy's grid is revealed.  

![Game ended screenshot](https://i.imgur.com/cyEv29Q.png)  
  
You can start a new game at any time by pressing the "Play again" button in the bottom right corner of the window.  Have fun!
