package com.example.android.miwok;

public class Word {
    private String miwokWord;
    private String englishWord;
    private static final int NO_IMAGE_SET = -1;
    private int audio;
    private int img = NO_IMAGE_SET;


    public Word(String mi, String english, int img, int audioResId) {
        this.miwokWord = mi;
        this.englishWord = english;
        this.img = img;
        this.audio = audioResId;
    }

    public Word(String mi, String english, int audioResId) {
        this.miwokWord = mi;
        this.englishWord = english;
        this.audio = audioResId;
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

    public boolean hasImage() {
        return img != NO_IMAGE_SET;
    }

    public int getAudio() {
        return audio;
    }
}
