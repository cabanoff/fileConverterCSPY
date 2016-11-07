/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileconverter;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author WIN7x64
 */
public class FileConverter extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private FXMLDocumentController controller;
    
    @Override
     public void start(Stage primaryStage) {
       this.primaryStage = primaryStage;
       this.primaryStage.setTitle("File Converter");
       
       initRootLayout();
       
            
    }
    
    /**
     * Initialize root layout
     */
    public void initRootLayout(){
        try{
            //load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FileConverter.class.getResource("FXMLDocument.fxml"));
            rootLayout = (AnchorPane)loader.load();
            
            //Show scene, containing root layout 
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //give controller access to the main application
            this.controller = loader.getController();
            this.controller.setMainApp(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop() throws Exception{
        controller.close();
    }
    
    /**
     * Returns main stage
     * @return
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
