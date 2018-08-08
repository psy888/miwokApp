package com.example.android.miwok;

public class Word {
    private String miwokWord;
    private String englishWord;
    //private Drawable img;
    private int img;

    public Word (String mi, String english, int img){
        this.miwokWord = mi;
        this.englishWord = english;
        this.img = img;
    }
    public Word (String mi, String english){
        this.miwokWord = mi;
        this.englishWord = english;
        this.img = 0;
    }

    public String getTranslationWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public int getImageResource() {
        return img;
    }
}
