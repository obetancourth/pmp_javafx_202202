package com.pmp.fx101;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Scene modalScene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("categorias"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    //------------------- MODAL IMPLEMENTATION
    public static FXMLLoader getFXMLLoader( String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;  
    }
    
    public static void loadFXML(Parent fxmlToShow) throws IOException {
        scene.setRoot(fxmlToShow);
    }
    
    public static void loadFXMLModal(Parent fxmlToShow) throws IOException {
        modalScene = new Scene(fxmlToShow,  640, 480);
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }
     
    public static void closeModal(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    // -----------------------------------
    public static void main(String[] args) {
        launch();
    }

}