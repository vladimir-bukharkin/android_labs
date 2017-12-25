package com.example.vbukharkin.l01;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
	public final static String ACTION_PLAYESERVICE = "com.samples.app.playservice.ACTION_PLAYESERVICE";
	public final static String EXTRA_STATUS = "com.samples.app.playservice.EXTRA_STATUS";
	public final static int EXTRA_STATUS_START = 0;
	public final static int EXTRA_STATUS_STOP = 1;
	public final static int EXTRA_STATUS_COMPLETE = 2;
	public final static int EXTRA_STATUS_ERROR = 3;
	
    private MediaPlayer player;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        //Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show(); 
        player = MediaPlayer.create(this, R.raw.test_cbr);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);       
        player.setLooping(false);        
    }

    @Override
    public void onDestroy() {
        player.stop();
        sendAction(EXTRA_STATUS_STOP);
    }
    
    @Override
    public void onStart(Intent intent, int startid) {
        player.start();
        sendAction(EXTRA_STATUS_START);
    }

	@Override
	public void onCompletion(MediaPlayer mp) {
		sendAction(EXTRA_STATUS_COMPLETE);
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		sendAction(EXTRA_STATUS_ERROR);
		return false;
	}
	
	private void sendAction(int event) {
        Intent intent = new Intent(ACTION_PLAYESERVICE);
        intent.putExtra(EXTRA_STATUS, event);
        sendBroadcast(intent);		
	}
}
