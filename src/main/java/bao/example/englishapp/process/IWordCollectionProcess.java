/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import bao.example.englishapp.model.WordCollection;

/**
 *
 * @author Bao
 */
public interface IWordCollectionProcess {
    
    public void add(WordCollection col);
    public void update(WordCollection col);
    public void remove(WordCollection col);
    public void merge(WordCollection col);
    
}
