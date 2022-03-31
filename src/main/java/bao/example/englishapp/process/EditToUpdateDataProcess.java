/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import com.tiennb.btl.model.Word;

/**
 *
 * @author Bao
 */
public class EditToUpdateDataProcess {
    
    private static Word updateWord;
    
    public static void setUpdateWord(Word word)
    {
        updateWord = word;
    }
    
    public static Word getUpdateWord()
    {
        return updateWord;
    }
    
}
