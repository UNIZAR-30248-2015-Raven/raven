package opdevelopers.raven.simonGame;

import android.app.Activity;
import android.app.AlertDialog;

import android.os.Bundle;

import android.content.SharedPreferences;

import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;

import android.media.AudioManager;

import opdevelopers.raven.R;

public class SimonActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = SimonActivity.class.getSimpleName();
	
	private static final int LEVEL_DIALOG = 1;
	private static final int GAME_DIALOG = 2;
	private static final int ABOUT_DIALOG = 3;
	private static final int HELP_DIALOG = 4;
	
	private SimonClone model;
	private Menu mMenu;
	private AlertDialog levelDialog;
	private AlertDialog gameDialog;
	private AlertDialog aboutDialog;
	private AlertDialog helpDialog;
	private TextView levelDisplay;
	private TextView gameDisplay;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        model = new SimonClone(this);
                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        ButtonGridView grid = (ButtonGridView) this.findViewById(R.id.button_grid);
        grid.setSimonCloneModel(model);



        
        Button startButton = (Button)findViewById(R.id.start);
        startButton.setOnClickListener(new OnClickListener() {        	
        	public void onClick(View v) {
        		model.gameStart();
        	}
        });
        
        /* Change the default vol control of app to what is SHOULD be. */
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  
        
        /* After all initialization, we set up our save/restore InstanceState Bundle. */
        if (savedInstanceState == null) {		// Just launched.  Set initial state.
        	SharedPreferences settings = getPreferences (0); // Private mode by default.
        	model.setLevel(settings.getInt(SimonClone.KEY_GAME_LEVEL, 1));	// Game Level
        	model.setGame(settings.getInt(SimonClone.KEY_THE_GAME, 1)); 	// The Game
        	model.setLongest(settings.getString(SimonClone.KEY_LONGEST_SEQUENCE, "")); 	// String Rep of Longest
        } else {
        	/* If I understand the activity cycle, I can put this here and not override
        	 * onRestoreInstanceState */
        	model.restoreState(savedInstanceState);
        }
    }
        
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	model.saveState(outState);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
    	mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    protected void onPause () {
    	super.onPause();
    	SharedPreferences settings = getPreferences (0); // Private mode by default.
    	SharedPreferences.Editor editor = settings.edit();


    	editor.commit();
    }



}