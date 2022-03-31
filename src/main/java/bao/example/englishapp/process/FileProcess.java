/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import java.io.File;

/**
 *
 * @author Bao
 */
public class FileProcess {
    
    public static boolean checkFileExit(String url)
    {
        File file = new File("src/main/resources"+url);
        return file.exists();
    }
    
    public static boolean checkFileCanRead(String url)
    {
        File file = new File("src/main/resources"+url);
        return file.canRead();
    }
    
    public static boolean checkFileCanExecute(String url)
    {
        File file = new File("src/main/resources"+url);
        return file.canExecute();
    }
    
}
