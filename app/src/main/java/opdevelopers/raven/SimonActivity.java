package opdevelopers.raven;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class SimonActivity extends AppCompatActivity {
    Button yellow;
    Button blue;
    Button green;
    Button red;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        yellow = (Button) findViewById(R.id.btYellow);
        blue  = (Button) findViewById(R.id.btBlue);
        green = (Button) findViewById(R.id.btGreen);
        red = (Button) findViewById(R.id.btRed);
        yellow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorYellowLight));
                } else {
                    v.setBackgroundColor(getResources().getColor(R.color.colorYellowDark));
                }
                return false;
            }
        });

        blue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorBlueLight));
                } else {
                    v.setBackgroundColor(getResources().getColor(R.color.colorBlueDark));
                }
                return false;
            }
        });

        red.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorRedLight));
                } else {
                    v.setBackgroundColor(getResources().getColor(R.color.colorRedDark));
                }
                return false;
            }
        });

        green.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorGreenLight));
                } else {
                    v.setBackgroundColor(getResources().getColor(R.color.colorGreenDark));
                }
                return false;
            }
        });

    }
}
