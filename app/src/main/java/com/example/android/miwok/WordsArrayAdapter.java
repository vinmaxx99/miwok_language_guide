package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsArrayAdapter extends ArrayAdapter<Word> {

   private int mColorResourseid;

    public WordsArrayAdapter(Context context, ArrayList<Word> words,int color)
    {
        super (context,0,words);
        mColorResourseid = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokTranslationtv);
        miwokTextView.setText(currentWord.getMiwokTranslation());


        TextView defaultTextview = (TextView) listItemView.findViewById(R.id.defaultTranslationtv);
        defaultTextview.setText(currentWord.getDefaultTranslation());

        ImageView image= (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage())
        {
            image.setImageResource(currentWord.getResourceid());
        }
        else
        {
            image.setVisibility(View.GONE);
        }

    View textcontainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourseid);
        textcontainer.setBackgroundColor(color);

return listItemView;
    }
}
