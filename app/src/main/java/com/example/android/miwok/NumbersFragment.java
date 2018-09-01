package com.example.android.miwok;

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

import static android.content.Context.AUDIO_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releasemediaplayer();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.start();
            }
            else if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
              releasemediaplayer();
            }
        }
    };

    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    };


    ArrayList<Word> words = new ArrayList<Word>();
    ListView list;

    @Override
    public void onStop() {
        super.onStop();
        releasemediaplayer();
    }

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(AUDIO_SERVICE);
//        Word word;

        final WordsArrayAdapter numbersAdapter = new WordsArrayAdapter(getActivity(),words,R.color.category_numbers);

        list=rootView.findViewById(R.id.list);

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        list.setAdapter(numbersAdapter);
//        Log.v("NumbersActivity", "Word at index 0: " + numberslist.get(0));
//        Log.v("NumbersActivity", "Word at index 1: " + numberslist.get(1));
//        Log.v("NumbersActivity", "Word at index 2: " + numberslist.get(2));
//        Log.v("NumbersActivity", "Word at index 3: " + numberslist.get(3));
//        Log.v("NumbersActivity", "Word at index 4: " + numberslist.get(4));
//        Log.v("NumbersActivity", "Word at index 5: " + numberslist.get(5));
//        Log.v("NumbersActivity", "Word at index 6: " + numberslist.get(6));
//        Log.v("NumbersActivity", "Word at index 7: " + numberslist.get(7));
//        Log.v("NumbersActivity", "Word at index 8: " + numberslist.get(8));
//        Log.v("NumbersActivity", "Word at index 9: " + numberslist.get(9));


        list.setAdapter(numbersAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releasemediaplayer();

                int res = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                Word word = words.get(i);

                if(res==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });



        return rootView ;
    }

    private void releasemediaplayer()
    {
        if (mediaPlayer !=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }

    }

}
