package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

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
                player.pause();
                // play the word from the beginning when we resume playback.
                player.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                //Permanent loss of audio focus
                //Stop MediaPlayer and release resources
                releaseMediaPlayer();
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //Gain audio focus back again (after having lost it earlier)
                //Resume playing the audio file
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



    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup link to AudioManger to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Words array
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("weṭeṭṭi", "red", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("chokokki", "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("ṭakaakki", "brown", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("ṭopoppi", "gray", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("kululli", "black", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("kelelli", "white", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Создаем {@link AndroidFlavorAdapter}, источником данных которого является список
        // {@link AndroidFlavor} s. Адаптер знает, как создавать представления элементов списка для каждого элемента
        // в списке.
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // Получить ссылку на ListView и прикрепить адаптер к спискуView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

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
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //we have focus

                    //create media player and pass to it current word audio res
                    //
                    player = MediaPlayer.create(getActivity(), audioResId.getAudio());

                    //start plying
                    player.start();

                    //when complete playing release media player
                    player.setOnCompletionListener(completionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the Fragment is stopped, release the media player resources because we won't
        // be playing any more sounds.
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
