package opdevelopers.raven.calendario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import opdevelopers.raven.Constants;
import opdevelopers.raven.CreateEventActivity;
import opdevelopers.raven.DetallesEventActivity;
import opdevelopers.raven.Event;
import opdevelopers.raven.EventAdapter;
import opdevelopers.raven.R;


/**
 * Created by Daniel on 21/11/2015 based on https://github.com/SundeepK/CompactCalendarView
 */
public class CalendarioActivity extends AppCompatActivity {
    private static int ACTIVITY_CREAR_EVENTO = 1;
    private static int ACTIVITY_DETALLAR_EVENTO = 2;
    private static final String USUARIO = "CorreoUsuario";
    private static final int EVENTO_PERIODICO = -1;

    private static final int ANNO = 0;
    private static final int MES = 1;
    private static final int DIA = 2;
    private static final int HORAS = 0;
    private static final int MINUTOS = 0;

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

    private int dia = currentCalender.get(Calendar.DAY_OF_MONTH);
    private int mes = currentCalender.get(Calendar.MONTH) + 1;
    private int anno = currentCalender.get(Calendar.YEAR);

    private List<Event> eventos = new ArrayList<>();
    private List<String> mutableEventos = new ArrayList<>();
    private ArrayAdapter adapter = null;
    private CompactCalendarView compactCalendarView = null;

    //correspondencia entre las posiciones del ListView y los objetos Event que referencian
    private List<Event> listViewEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final ActionBar actionBar = getSupportActionBar();

        final ListView eventsListView = (ListView) findViewById(R.id.events_listview);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableEventos);
        eventsListView.setAdapter(adapter);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent intent = new Intent(CalendarioActivity.this, DetallesEventActivity.class);

                intent.putExtra("id", listViewEventos.get(position).getId());
                intent.putExtra("email", listViewEventos.get(position).getEmail());
                intent.putExtra("mensaje", listViewEventos.get(position).getMensaje());
                intent.putExtra("date", listViewEventos.get(position).getDate());
                intent.putExtra("hora", listViewEventos.get(position).getTime());
                intent.putExtra("periodicidad", listViewEventos.get(position).getPeriodicidad());

                CalendarioActivity.this.startActivityForResult(intent, ACTIVITY_DETALLAR_EVENTO);
            }
        });
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);

        //calendario en español
        Locale spanish = new Locale("es", "ES");
        compactCalendarView.setLocale(spanish);

        compactCalendarView.setUseThreeLetterAbbreviation(true);

        // below allows you to configure color for the current day in the month
        compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        // below allows you to configure colors for the current day the user has selected
        compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimary));

        compactCalendarView.invalidate();

        //set initial title
        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //obtener eventos del usuario
        eventos = obetenerEventos();
        mostrarEventosEnVista();
        actualizarListaEventos();

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateClicked);

                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH) + 1;
                anno = calendar.get(Calendar.YEAR);

                actualizarListaEventos();
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
                intent.putExtra("mes", mes);
                intent.putExtra("anno", anno);
                CalendarioActivity.this.startActivityForResult(intent, ACTIVITY_CREAR_EVENTO);
            }
        });
    }

    private void actualizarListaEventos() {
        //chapuza para evitar que el día 05 no sea el mismo que el 5
        String diaString;

        if (dia < 10) {
            diaString = "0" + String.valueOf(dia);
        } else {
            diaString = String.valueOf(dia);
        }

        mutableEventos.clear();
        listViewEventos.clear();

        String[] values;

        for (Event evento : eventos) {
            if (evento.getDate().length() != 0) {
                values = evento.getDate().split("-");

                if ((values[ANNO].compareTo(String.valueOf(anno)) == 0) &&
                        (values[MES].compareTo(String.valueOf(mes)) == 0) &&
                        (values[DIA].compareTo(diaString) == 0)) {
                    mutableEventos.add(evento.getMensaje() + " a las " + evento.getTime());
                    listViewEventos.add(evento);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }


    /**
     * Obetener eventos de un usuario por su correo elctrónico
     */
    private ArrayList<Event> obetenerEventos() {
        EventAdapter adaptadorEventos = new EventAdapter(Constants.FETCH_EVENTS, obtenerEmailUsuario());
        ArrayList<Event> listEvents = (ArrayList) adaptadorEventos.peticionFetchEventos();

        return listEvents;
    }

    private String obtenerEmailUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        return prefsCorreo.getString("email", "");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        eventos = obetenerEventos();
        mostrarEventosEnVista();
        actualizarListaEventos();
    }

    private void mostrarEventosEnVista() {
        long miliseconds;
        for (Event event : eventos) {
            if ((miliseconds = eventToMiliseconds(event)) == EVENTO_PERIODICO) {
                Log.e("IEEE", "EVENTO_PERIODICO");
            } else {
                compactCalendarView.addEvent(new CalendarDayEvent(miliseconds, Color.argb(255, 169, 68, 65), event), false);
            }
        }
    }

    private long eventToMiliseconds(Event evento) {
        String[] valuesTime = evento.getTime().split(":");
        String[] valuesDate;
        if (evento.getDate().length() != 0) {
            valuesDate = evento.getDate().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(valuesDate[ANNO]), Integer.parseInt(valuesDate[MES]) - 1, Integer.parseInt(valuesDate[DIA]),
                    Integer.parseInt(valuesTime[HORAS]), Integer.parseInt(valuesTime[MINUTOS]), 0);

            return calendar.getTimeInMillis();
        }

        return EVENTO_PERIODICO;
    }
}
