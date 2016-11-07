/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author WIN7x64
 */
public class Converter {
    private File fileX, fileY, fileZ, fileLight, fileDist;
    private File inDirectory, outDirectory;
    private final String inSave = "in.sav";
    private final String outSave = "out.sav";
     
    
    
    public File getFileX(){
        return fileX;
    }
    public File getFileY(){
        return fileY;
    }
    public File getFileZ(){
        return fileZ;
    }
    public File getFileLight(){
        return fileLight;
    }
    public File getFileDist(){
        return fileDist;
    } 
    public File getInDirectory(){
        return inDirectory;
    }
    public File getOutDirectory(){
        return outDirectory;
    }
    
    public void setFileX(File file){
        fileX = file;
    }
    public void setFileY(File file){
        fileY = file;
    }
    public void setFileZ(File file){
        fileZ = file;
    }
    public void setFileLight(File file){
        fileLight = file;
    }
    public void setFileDist(File file){
        fileDist = file;
    }
    public void setInDirectory(File file){
        if(file!=null)inDirectory = file;
    }
    public void setOutDirectory(File file){
        if(file!=null)outDirectory = file;
    }
    /**
     * loads files if exist
     */
    Converter(){
        File dir;
        try{
            FileInputStream fis = new FileInputStream(new File(inSave));
            ObjectInputStream ois = new ObjectInputStream(fis);
            dir = (File)ois.readObject();
            ois.close();
            if(dir.isDirectory()&& dir.exists())inDirectory = new File(dir.getPath());
        }catch(Exception e){System.out.println("unable to open" + inSave);}
        try{
            FileInputStream fis = new FileInputStream(new File(outSave));
            ObjectInputStream ois = new ObjectInputStream(fis);
            dir = (File)ois.readObject();
            ois.close();
            if(dir.isDirectory()&& dir.exists())outDirectory = new File(dir.getPath());
            else System.out.println("");
        }catch(Exception e){System.out.println("unable to open" + outSave);}
    }
   
    public String createFileDist(File pathName){
        if(pathName.isDirectory()){
            if((fileX != null)&&(fileY != null)&&(fileZ != null)&&(fileLight != null)){
                if(isFilesRepeated(fileX,fileY,fileZ,fileLight))return"files shouldn't be repeated";
                if(convertFiles(pathName))return "";
                return"fail to create file";
            }
            else return("not all files are chosen");
        }
        else return("not correct directory");
    }
    
    public void close(){
        SaveDir();
    }
    
    /**
     * converts input files into one output file for working with C-SPY
     * input files format -  comma separated decimal ascii
     * output - lines of hex ascii
     * @param pathName
     * @return 
     */
    private boolean convertFiles(File pathName){
        Formatter distFile;
        try{
            distFile = new Formatter(pathName.toString()+"\\inData.txt");
            return true;
        }catch(Exception e) {return false;}
    }
    
    private boolean isFilesRepeated(File file1, File file2, File file3, File file4){
        File[] compareArr = {file1, file2, file3, file4};
        for(int i = 0; i<compareArr.length;i++ ){
            for(int j = i+1; j<compareArr.length;j++){
                if(compareArr[i].equals(compareArr[j]))return true;
            }
        }
        return false;
    }
    
    private void SaveDir(){
        File inDir = new File(inSave);
        File outDir = new File(outSave);
        try{
            FileOutputStream fos = new FileOutputStream(inDir);            
            ObjectOutputStream oos = new ObjectOutputStream(fos);         
            oos.writeObject(inDirectory);
            oos.close();
        }catch(Exception e){System.out.println("file" + inDir +" is not finding");}
        try{
            FileOutputStream fos = new FileOutputStream(outDir);            
            ObjectOutputStream oos = new ObjectOutputStream(fos);         
            oos.writeObject(outDirectory);
            oos.close();
        }catch(Exception e){System.out.println("file" + outDir +" is not finding");}
        
    }
    
}
