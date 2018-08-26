package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer player;
    private AudioManager audioManager;

    //Initialization OnAudioFocusChangeListener
    private AudioManager.OnAudioFocusChangeListener afChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                //Temporary loss of audio focus
                //in this case we need to pause audio
                //Temporary loss of audio focus, can 'duck' or lower volume if applicable
                //Pause audio file
                Toast myToast = Toast.makeText(getApplicationContext(), "AUDIOFOCUS_LOSS_TRANSIENT!", Toast.LENGTH_SHORT);
                myToast.show();
                player.pause();
                // play the word from the beginning when we resume playback.
                player.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                //Permanent loss of audio focus
                //Stop MediaPlayer and release resources
                Toast myToast = Toast.makeText(getApplicationContext(), "AUDIOFOCUS_LOSS!", Toast.LENGTH_SHORT);
                myToast.show();
                releaseMediaPlayer();
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //Gain audio focus back again (after having lost it earlier)
                //Resume playing the audio file
                Toast myToast = Toast.makeText(getApplicationContext(), "AUDIOFOCUS_LOSS!", Toast.LENGTH_SHORT);
                myToast.show();
                player.start();
            }
        }
    };
    private  MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup link to AudioManger to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    //Words array
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo’e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na’aacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        // Создаем {@link AndroidFlavorAdapter}, источником данных которого является список
        // {@link AndroidFlavor} s. Адаптер знает, как создавать представления элементов списка для каждого элемента
        // в списке.
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        // Получить ссылку на ListView и прикрепить адаптер к спискуView.
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (player != null) {
                    releaseMediaPlayer();
                }
                //Get current WORD word object
                Word audioResId = words.get(i);


                // initialization of the audio attributes and focus request
                int res = audioManager.requestAudioFocus(afChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // handling audio request results
                if(res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //we have focus

                    //create media player and pass to it current word audio res
                       //
                        player = MediaPlayer.create(getApplicationContext(), audioResId.getAudio());

                        //start plying
                        player.start();

                        //when complete playing release media player
                        player.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
        }
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            audioManager.abandonAudioFocus(afChange);

    }
}
