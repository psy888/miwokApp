package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //Words array
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("әpә","father"));
        words.add(new Word("әṭa","mother"));
        words.add(new Word("angsi","son"));
        words.add(new Word("tune","daughter"));
        words.add(new Word("taachi","older brother"));
        words.add(new Word("chalitti","younger brother"));
        words.add(new Word("teṭe","older sister"));
        words.add(new Word("kolliti","younger sister"));
        words.add(new Word("ama","grandmother"));
        words.add(new Word("paapa","grandfather"));


        // Создаем {@link AndroidFlavorAdapter}, источником данных которого является список
        // {@link AndroidFlavor} s. Адаптер знает, как создавать представления элементов списка для каждого элемента
        // в списке.
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Получить ссылку на ListView и прикрепить адаптер к спискуView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
