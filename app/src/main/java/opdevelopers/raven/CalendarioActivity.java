package opdevelopers.raven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Daniel on 15/11/2015.
 */
public class CalendarioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendario);
        setTitle(R.string.calendario);
    }
}
