package opdevelopers.raven;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

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
        EventAdapter adaptadorEventos = new EventAdapter(Constants.FETCH_EVENTS, "rgcmb@hotmail.com");
        try {
            JSONArray jsonObject = new JSONArray(adaptadorEventos.peticionFetchEventos());
            for (int i = 0; i < jsonObject.length(); i++) {
                System.out.println(jsonObject.get(i));
            }
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println((String)adaptadorEventos.peticionFetchEventos());

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
                // nombre descriptivo premoh what is i, better intent
                i.putExtra("dia", dia);
                i.putExtra("mes", mes + 1);
                i.putExtra("anno", anno);

                CalendarioActivity.this.startActivityForResult(i, ACTIVITY_CREAR_EVENTO);
            }
        });
    }
}