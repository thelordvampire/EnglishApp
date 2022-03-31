package com.tiennb.btl.model;



import java.util.ArrayList;

public class ColectionWord {
    private String name;
    private ArrayList<Word> words;

 

    public ColectionWord(){

    }

    public ColectionWord(String name, ArrayList<Word> words) {
        this.name = name;
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}
