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
