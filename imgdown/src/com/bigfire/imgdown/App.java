package com.bigfire.imgdown;
import java.net.URI;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		URL url = new URI("Crawer.fxml");
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Crawer.fxml"));
        Scene mainScene = new Scene(root, 486, 358);
        mainScene.setRoot(root);
        primaryStage.setTitle("网页图片下载器1.0 by 大火");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
	}

}
