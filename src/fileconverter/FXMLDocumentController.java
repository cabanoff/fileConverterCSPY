/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileconverter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

//import javafx.scene.control.Label;

/**
 *
 * @author WIN7x64
 */
public class FXMLDocumentController implements Initializable {
    
    //reference to main application
    private FileConverter mainApp;
    //private File inDirectory, outDirectory;
    private Converter converter;
    
    
    @FXML
    private void handleInDirAction(ActionEvent event) {
        converter.setInDirectory(setDirectory(converter.getInDirectory())); 
        System.out.println(converter.getInDirectory());
    }
    
    @FXML
    private void handleOutDirAction(ActionEvent event) {
        converter.setOutDirectory(setDirectory(converter.getOutDirectory()));
        System.out.println(converter.getOutDirectory());
    }
    
    @FXML
    private void handleXAction(ActionEvent event){
        converter.setFileX(setFile(converter.getInDirectory()));
    }
    
    @FXML
    private void handleYAction(ActionEvent event){
        converter.setFileY(setFile(converter.getInDirectory()));
    }
    
    @FXML
    private void handleZAction(ActionEvent event){
        converter.setFileZ(setFile(converter.getInDirectory()));
    }
    
    @FXML
    private void handleLightAction(ActionEvent event){
        converter.setFileLight(setFile(converter.getInDirectory()));
    }
    
    @FXML
    private void handleConvertAction(ActionEvent event){
        String string;
        string = converter.createFileDist(converter.getOutDirectory());
        System.out.println(string);
    }
    
    private File setFile (File initDirectory){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        if(initDirectory != null){
            fileChooser.setInitialDirectory(initDirectory);
        }
        return(fileChooser.showOpenDialog(mainApp.getPrimaryStage()));
    }
    
    private File setDirectory(File initDirectory){
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Locate Directory");
        if(initDirectory != null){
            dirChooser.setInitialDirectory(initDirectory);
        }
        return(dirChooser.showDialog(mainApp.getPrimaryStage()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        converter = new Converter();
    }    
    
    public void close(){
        converter.close();
    }
     public void setMainApp(FileConverter mainApp){
        this.mainApp = mainApp;
    }
    
}
