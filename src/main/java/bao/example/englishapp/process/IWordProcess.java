/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import bao.example.englishapp.model.Word;
import java.util.List;

/**
 *
 * @author Bao
 */
public interface IWordProcess {
    
    public List<Word> selectAll();
    public void add(Word word);
    public void update(Word word);
    public void remove(Word word);
    
}
