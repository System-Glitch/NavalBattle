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

package fr.sysgli.navalbattle;
	
import fr.sysgli.navalbattle.resources.ResourcesLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(ResourcesLoader.class.getResource("GameView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(ResourcesLoader.class.getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Naval battle");
			primaryStage.setResizable(false);
			
			primaryStage.getIcons().add(new Image(ResourcesLoader.class.getResource("icon_16.png").toExternalForm()));
			primaryStage.getIcons().add(new Image(ResourcesLoader.class.getResource("icon_32.png").toExternalForm()));
			primaryStage.getIcons().add(new Image(ResourcesLoader.class.getResource("icon_64.png").toExternalForm()));
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
