package com.example.android.miwok;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //Words array
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("weṭeṭṭi","red", R.drawable.color_red));
        words.add(new Word("chokokki","green", R.drawable.color_green));
        words.add(new Word("ṭakaakki","brown", R.drawable.color_brown));
        words.add(new Word("ṭopoppi","gray", R.drawable.color_gray));
        words.add(new Word("kululli","black", R.drawable.color_black));
        words.add(new Word("kelelli","white", R.drawable.color_white));
        words.add(new Word("ṭopiisә","dusty yellow", R.drawable.color_dusty_yellow));
        words.add(new Word("chiwiiṭә","mustard yellow", R.drawable.color_mustard_yellow));


        // Создаем {@link AndroidFlavorAdapter}, источником данных которого является список
        // {@link AndroidFlavor} s. Адаптер знает, как создавать представления элементов списка для каждого элемента
        // в списке.
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Получить ссылку на ListView и прикрепить адаптер к спискуView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // Устанавливаем соответствующий категории цвет для WordList
        listView.setBackgroundColor(getResources().getColor(R.color.category_colors));

    }
}
