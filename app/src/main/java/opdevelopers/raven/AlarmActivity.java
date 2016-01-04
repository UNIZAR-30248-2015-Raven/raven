package opdevelopers.raven;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * @author Daniel Uroz
 *
 * Pantalla de notificación cuando un contador ha acabado
 */
public class AlarmActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        setTitle(getIntent().getStringExtra("titulo"));

        TextView cancelarAlarma = (TextView) findViewById(R.id.cancelButton);

        cancelarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                finish();
            }
        });

        empezarSonar();
    }


    private void empezarSonar() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.sonido_alarma);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
}