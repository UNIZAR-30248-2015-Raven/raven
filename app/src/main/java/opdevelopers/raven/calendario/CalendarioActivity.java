package opdevelopers.raven.calendario;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import opdevelopers.raven.Constants;
import opdevelopers.raven.CreateEventActivity;
import opdevelopers.raven.Event;
import opdevelopers.raven.EventAdapter;
import opdevelopers.raven.R;


/**
 * Created by Daniel on 21/11/2015 based on https://github.com/SundeepK/CompactCalendarView
 */
public class CalendarioActivity extends ActionBarActivity {
    private static int ACTIVITY_CREAR_EVENTO = 1;

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private Map<Date, List<Event>> events = new HashMap<>();

    private int dia = currentCalender.get(Calendar.DAY_OF_MONTH);
    private int mes = currentCalender.get(Calendar.MONTH);
    private int anno = currentCalender.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final ActionBar actionBar = getSupportActionBar();
        final List<String> mutableEvents = new ArrayList<>();

        final ListView bookingsListView = (ListView) findViewById(R.id.bookings_listview);

        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mutableEvents);
        bookingsListView.setAdapter(adapter);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);

        //calendario en español
        Locale spanish = new Locale("es", "ES");
        compactCalendarView.setLocale(spanish);

        compactCalendarView.setUseThreeLetterAbbreviation(true);

        // below allows you to configure color for the current day in the month
        compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.colorPrimary));
        // below allows you to configure colors for the current day the user has selected
        compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        addEvents(compactCalendarView, -1);
        addEvents(compactCalendarView, Calendar.DECEMBER);
        addEvents(compactCalendarView, Calendar.AUGUST);
        compactCalendarView.invalidate();

        //set initial title
        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));


        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateClicked);

                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anno = calendar.get(Calendar.YEAR);


                List<Event> eventsFromMap = events.get(dateClicked);
                Log.d("MainActivity", "inside onclick " + dateClicked);
                if(eventsFromMap != null) {
                    Log.d("MainActivity", eventsFromMap.toString());
                    mutableEvents.clear();
                    for(Event event : eventsFromMap) {
                        mutableEvents.add(event.getMensaje());
                    }
                    // below will remove events
                    compactCalendarView.removeEvent(new CalendarDayEvent(dateClicked.getTime(), Color.argb(255, 169, 68, 65)), true);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        Button crearEvento = (Button) findViewById(R.id.boton_crear_evento);
        crearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarioActivity.this, CreateEventActivity.class);
                intent.putExtra("dia", dia);
                intent.putExtra("mes", mes + 1);
                intent.putExtra("anno", anno);
                CalendarioActivity.this.startActivityForResult(intent, ACTIVITY_CREAR_EVENTO);
            }
        });

        /**
         * Obetener eventos de un usurio por su correo elctrónico
         */
        EventAdapter adaptadorEventos = new EventAdapter(Constants.FETCH_EVENTS, "rgcmb@hotmail.com");
        ArrayList<Event> listEvents = (ArrayList) adaptadorEventos.peticionFetchEventos();
        for(Event ev : listEvents) {
            System.out.println(ev);
        }
    }

    private void addEvents(CompactCalendarView compactCalendarView, int month) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for(int i = 0; i < 6; i++){
            currentCalender.setTime(firstDayOfMonth);
            if(month > -1){
                currentCalender.set(Calendar.MONTH, month);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(),  Color.argb(255, 169, 68, 65)), false);
            events.put(currentCalender.getTime(), createEvents());
        }
    }

    private List<Event> createEvents() {
        return Arrays.asList(
                new Event("id1","email1","mensaje1","date1","time1","periodicidad1"),
                new Event("id2","email2","mensaje2","date2","time2","periodicidad2"),
                new Event("id3","email3","mensaje3","date3","time3","periodicidad3"));
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
