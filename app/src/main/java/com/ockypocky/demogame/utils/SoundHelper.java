package com.ockypocky.demogame.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View;

import com.ockypocky.demogame.R;

public class SoundHelper {

    private MediaPlayer musicPlayer;
    private SoundPool mSoundPool;
    private int mSoundID;
    private boolean mLoaded;
    private float mVolume;

    public SoundHelper(Activity activity) {

        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        float actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mVolume = actVolume / maxVolume;

        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        /**
         *    Build the SoundPool object
         */

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        } else {
            /**
             *  noinspection deprecation
             */

            mSoundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                mLoaded = true;
            }
        });
        mSoundID = mSoundPool.load(activity, R.raw.ballon_pop, 1);
    }

    public void playSound(View v) {

        /**
         *        Is the sound loaded does it already play?

         */
        if (mLoaded) {
            mSoundPool.play(mSoundID, mVolume, mVolume, 1, 0, 1f);
        }
    }

    public void prepareMusicPlayer(Context context) {
        musicPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.background_music);
        musicPlayer.setVolume(.5f, .5f);
        musicPlayer.setLooping(true);
    }

    public void playMusic() {
        if (musicPlayer != null) {
            musicPlayer.start();
        }
    }

    public void stopMusic() {
        if (musicPlayer != null && musicPlayer.isPlaying()) {
            musicPlayer.pause();
        }
    }

}
