package com.example.android.miwok;

import android.widget.ImageView;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceid=NO_IMAGE_PROVIDED;
    private int mAudioResourceid;
    private static final int NO_IMAGE_PROVIDED=-1;

    public Word (String defaultTranslation,String miwokTranslation,int audioResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation= miwokTranslation;
        mAudioResourceid=audioResourceId;
    }

    public Word (String defaultTranslation,String miwokTranslation,int resourceid,int audioResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation= miwokTranslation;
        mImageResourceid= resourceid;
        mAudioResourceid=audioResourceId;
    }

    public String getDefaultTranslation()
    {
        return mDefaultTranslation;

    }

    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    public int getResourceid()
    {
        return mImageResourceid;
    }

    public boolean hasImage()
    {
        return mImageResourceid != NO_IMAGE_PROVIDED;
    }

    public int getAudioId()
    {
      return mAudioResourceid;
    }
}
