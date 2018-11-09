package com.example.consultants.week3_daily4.UI.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.consultants.week3_daily4.Constants;
import com.example.consultants.week3_daily4.R;
import com.example.consultants.week3_daily4.UI.main.MainPresenter;

import static com.example.consultants.week3_daily4.UI.App.CHANNEL_ID;


public class MyMusicService extends Service {

    public static final String TAG = MyMusicService.class.getSimpleName() + "_TAG";
    private MediaPlayer mediaPlayer;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.like_a_boss);
        mediaPlayer.start();
//        String intentString = intent.getAction();
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            Intent notificationIntent = new Intent(this, MainPresenter.class);
            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            playAction();
            stopAction();
            nextAction();
            previousAction();
            notificationView(notificationIntent);
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            playAction();
        } else if (intent.getAction().equals(Constants.ACTION.STOP_ACTION)) {
            stopAction();
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            nextAction();
        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            previousAction();
        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            stopForeground(true);
            onDestroy();
            stopSelf();
        }

//        String input = intent.getStringExtra("musicInput");
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.like_a_boss);
//        mediaPlayer.start();
//
//        Test this -> pass intent and string
//        works
//        notificationView(intent, input);

        //TODO look at other possibilities with this pass -> need to pause
        return START_NOT_STICKY;
    }

    public void playAction() {
        Log.d(TAG, "playAction: ");
        Intent playIntent = new Intent(this, MyMusicService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

//        notificationView(playIntent, pplayIntent);
    }

    public void stopAction() {
        Log.d(TAG, "stopAction: ");
        Intent stopIntent = new Intent(this, MyMusicService.class);
        stopIntent.setAction(Constants.ACTION.STOP_ACTION);
        PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                stopIntent, 0);

//        notificationView(stopIntent, pstopIntent);
    }

    public void nextAction() {
        Log.d(TAG, "nextAction: ");
        Intent nextIntent = new Intent(this, MyMusicService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

//        notificationView(nextIntent, pnextIntent);
    }

    public void previousAction() {
        Log.d(TAG, "previousAction: ");
        Intent previousIntent = new Intent(this, MyMusicService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

//        notificationView(previousIntent, pplayIntent);
    }

    //TODO need to fix the channel -> add the application extension
    //original notification method
//    public void notificationView(Intent intent, PendingIntent pendingIntent) {
    public void notificationView(Intent notificationIntent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Service")
                .setContentText("My Music")
                .setSmallIcon(R.drawable.ic_music_note)
                .setContentIntent(pendingIntent)
//                test this
//                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous,
                        "Previous",
                        pendingIntent)
                .addAction(android.R.drawable.ic_media_play,
                        "Play",
                        pendingIntent)
                .addAction(android.R.drawable.ic_media_next,
                        "Next",
                        pendingIntent)
                .build();

        //        start the intent -> promote to foreground
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        //release the song when user interacts
        mediaPlayer.release();
    }
}
