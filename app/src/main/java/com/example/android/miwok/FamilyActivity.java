package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Words array
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("әpә", "father", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("әṭa", "mother", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("angsi", "son", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("tune", "daughter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("taachi", "older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("chalitti", "younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("teṭe", "older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("kolliti", "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("ama", "grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("paapa", "grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));


        // Создаем {@link AndroidFlavorAdapter}, источником данных которого является список
        // {@link AndroidFlavor} s. Адаптер знает, как создавать представления элементов списка для каждого элемента
        // в списке.
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

        // Получить ссылку на ListView и прикрепить адаптер к спискуView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // Устанавливаем соответствующий категории цвет для WordList
        // listView.setBackgroundColor(getResources().getColor(R.color.category_family));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (player != null) {
                    player.release();
                }
                int audioResId = words.get(i).getAudio();
//                Toast toast = Toast.makeText(NumbersActivity.this, audioResId, Toast.LENGTH_LONG);
//                toast.show();
                player = MediaPlayer.create(FamilyActivity.this, audioResId);
                player.start();

            }
        });
    }
}
