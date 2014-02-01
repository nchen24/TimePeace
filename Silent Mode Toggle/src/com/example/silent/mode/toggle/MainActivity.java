package com.example.silent.mode.toggle;



import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.widget.Button;

public class MainActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	Button normal, silent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        super.onCreate(savedInstanceState);
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        checkIfPhoneIsSilent();
        setButtonClickListener();
        Log.d("SilentModeApp", "This is a test");
        
    }    
        
    // Does not detect phones current status and just displays to activate Silent Mode 
    
        private void setButtonClickListener() {
        final TextView txt = (TextView) findViewById(R.id.txt1);	
        final Button toggleButton = (Button)findViewById(R.id.silent);
        toggleButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		
        	if (mPhoneIsSilent) {
        		//change back to normal mode
        		
        		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        		mPhoneIsSilent = false;
        		toggleButton.setText("Activate Silent Mode");
        		txt.setText("Mobile Is In Vibration Mode");
        		Toast.makeText(getBaseContext(), "Vibration Mode Activated", 
               		 Toast.LENGTH_LONG).show();
        	}
        		else
        		{
        			//change to silent mode
        			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        			mPhoneIsSilent = true;
        			toggleButton.setText("Activate Vibration Mode");
        			txt.setText("Mobile Is In Silent Mode");
        			Toast.makeText(getBaseContext(), "Silent Mode Activated",
                            Toast.LENGTH_LONG).show();
        		}
        	// Now toggle the UI again
        	toggleUi();	
        	}
        	});
    }
        private void checkIfPhoneIsSilent() {
        	int ringermode = mAudioManager.getRingerMode();
        	if (ringermode == AudioManager.RINGER_MODE_SILENT) {
        		mPhoneIsSilent = true;
        	}
        	else
        	{
        		mPhoneIsSilent = false;
        	}
        }
        private void toggleUi() {
        	ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        	Drawable newPhoneImage;
        	if (mPhoneIsSilent){
        		newPhoneImage = 
        		getResources().getDrawable(R.drawable.phone_silent);
        }
        else
        {
        		newPhoneImage = 
        			getResources().getDrawable(R.drawable.vibration_on);
        
        }
        	imageView.setImageDrawable(newPhoneImage);
    
}
@Override      
protected void onResume() {
	super.onResume();
	checkIfPhoneIsSilent();
	toggleUi();
	
}
}
