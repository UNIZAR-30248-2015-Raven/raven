package opdevelopers.raven;

import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        // no mostramos el action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_alarm);

        TextView titulo = (TextView) findViewById(R.id.titulo);

        String extra = getIntent().getStringExtra("info");

        String[] info = extra.split(":");

        titulo.setText(info[0]);

        TextView cancelarAlarma = (TextView) findViewById(R.id.cancelButton);

        cancelarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                finish();
            }
        });

        // Clear notificaction
        NotificationManager nMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancel(Integer.parseInt(info[1]));

        empezarSonar();
    }


    private void empezarSonar() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.sonido_alarma);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
}