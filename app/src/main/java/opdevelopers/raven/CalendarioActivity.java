package opdevelopers.raven;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Daniel on 15/11/2015.
 */
public class CalendarioActivity extends AppCompatActivity {
    private static final int ACTIVITY_CREAR_EVENTO = 1;

    private Calendar calendario = Calendar.getInstance();

    private int dia = calendario.get(Calendar.DAY_OF_MONTH);
    private int mes = calendario.get(Calendar.MONTH);
    private int anno = calendario.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendario);
        setTitle(R.string.calendario);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        Button botonCrear = (Button) findViewById(R.id.botonCrearEvento);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                dia = dayOfMonth;
                mes = month;
                anno = year;
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalendarioActivity.this, CreateEventActivity.class);
                
                i.putExtra("dia", dia);
                i.putExtra("mes", mes);
                i.putExtra("anno", anno);

                CalendarioActivity.this.startActivityForResult(i, ACTIVITY_CREAR_EVENTO);
            }
        });
    }
}