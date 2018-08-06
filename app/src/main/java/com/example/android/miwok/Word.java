package com.example.android.miwok;

public class Word {
    private String miwokWord;
    private String englishWord;

    public Word (String mi,String english){
        miwokWord = mi;
        englishWord = english;
    }

    public String getTranslationWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

}
