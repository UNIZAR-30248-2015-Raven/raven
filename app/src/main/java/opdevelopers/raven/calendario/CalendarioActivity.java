package opdevelopers.raven.calendario;

import android.content.Context;
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

import java.text.DateFormat;
import java.text.ParseException;
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
    private static final int SEMANA_ANNO = 53;


    public static final String LUNES = "L";
    public static final String MARTES = "M";
    public static final String MIERCOLES = "X";
    public static final String JUEVES = "J";
    public static final String VIERNES = "V";
    public static final String SABADO = "S";
    public static final String DOMINGO = "D";

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

    private int dia = currentCalender.get(Calendar.DAY_OF_MONTH);
    private int diaSemana = currentCalender.get(Calendar.DAY_OF_WEEK);
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

        //cuando se pulsa sobre un item de la lista se muestra detalladamente el evento pulsado
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

        //color primario
        compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimary));

        compactCalendarView.invalidate();

        //set initial title
        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));


        //obtener eventos del usuario y actualizar vista
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
                diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
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

    /**
     * Actualiza la lista de eventos que se muestra en un día concreto
     */
    private void actualizarListaEventos() {
        //evitar que el día 05 no sea el mismo que el 5
        String diaString;

        if (dia < 10) {
            diaString = "0" + String.valueOf(dia);
        } else {
            diaString = String.valueOf(dia);
        }

        //borrar los posibles eventos que se puedan estar mostrando al usuario
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
            } else {
                //fecha seleccionada en el calendario
                Calendar calendarClicked = Calendar.getInstance();
                calendarClicked.set(anno, mes - 1, dia);
                Date dateCliked = calendarClicked.getTime();

                //fecha actual
                Date today = new Date();

                ArrayList<Integer> diasSemanaEvento
                        = (ArrayList<Integer>) transformarPeriodicidad(evento.getPeriodicidad());

                for (Integer diaSemana : diasSemanaEvento) {
                    //únicamente mostramos los eventos periódicos si el día pulsado es posterior
                    //al día actual y si es el mismo día de la semana que el que contiene el evento
                    if ((dateCliked.after(today) || dateCliked.equals(today))
                            && (diaSemana == this.diaSemana)) {
                        mutableEventos.add(evento.getMensaje() + " a las " + evento.getTime());
                        listViewEventos.add(evento);
                    }
                }
            }
        }

        //notificamos cambios
        adapter.notifyDataSetChanged();
    }

    /**
     * Obtener eventos de usuario
     *
     * @return lista de eventos asociados al usuario
     */
    private ArrayList<Event> obetenerEventos() {
        EventAdapter adaptadorEventos = new EventAdapter(Constants.FETCH_EVENTS, obtenerEmailUsuario());
        ArrayList<Event> listEvents = (ArrayList) adaptadorEventos.peticionFetchEventos();

        return listEvents;
    }

    /**
     * Obtiene el email asociado al usuario
     *
     * @return email
     */
    private String obtenerEmailUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        return prefsCorreo.getString("email", "");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_CREAR_EVENTO) {
            eventos = obetenerEventos();
            mostrarEventosEnVista();
            actualizarListaEventos();
        }
    }

    /**
     * Dibuja el indicador de eventos en el calendario
     */
    private void mostrarEventosEnVista() {
        long miliseconds;
        for (Event event : eventos) {
            if ((miliseconds = toMiliseconds(event)) == EVENTO_PERIODICO) {
                anndirEventosPeriodicos(event);
            } else {
                compactCalendarView.addEvent(new CalendarDayEvent(miliseconds, Color.argb(255, 169, 68, 65)), false);
            }
        }
    }

    /**
     * Calcula los milisegundos equivalentes a un evento
     *
     * @param evento para ser transformado en milisegundos
     * @return milisegundos
     */
    private long toMiliseconds(Event evento) {
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

    /**
     * Añade el indicador de eventos periódicos a la vista del calendario
     *
     * @param evento a añadir
     */
    private void anndirEventosPeriodicos(Event evento) {
        ArrayList<Integer> diasSemanaEvento
                = (ArrayList<Integer>) transformarPeriodicidad(evento.getPeriodicidad());

        Calendar currentCalendar;
        Date today = new Date();

        // para cada uno de los días en los cuales se repite el evento
        for (Integer diaSemana : diasSemanaEvento) {
            currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(today);

            // buscamos el primer día en el que se repita el evento. Por ejemplo, si estamos a
            // jueves y el evento se repite un miércoles tendremos que buscar el miércoles
            // más próximo
            while (currentCalendar.get(Calendar.DAY_OF_WEEK) != diaSemana) {
                // sumamos un día al calendario
                currentCalendar.add(Calendar.DATE, 1);
            }

            // una vez encontrado el día, pintamos el evento cada semana en cada iteración
            // durante un año
            for (int i = 0; i < SEMANA_ANNO; i++, currentCalendar.add(Calendar.WEEK_OF_MONTH, 1)) {
                compactCalendarView.addEvent(new CalendarDayEvent(currentCalendar.getTimeInMillis(),
                        Color.argb(255, 169, 68, 65)), false);
            }
        }
    }

    /**
     * Realiza la conversión de la codificación de la base de datos (p.ej: D) a la
     * correspondiente a la clase Calendar (p.ej: Calendar.SUNDAY)
     *
     * @param periodicidadEvento a transformar
     * @return lista con la periodicidad del evento
     */
    public List<Integer> transformarPeriodicidad(String periodicidadEvento) {
        String[] periodicidad = periodicidadEvento.split(" ");
        List<Integer> diasSemanaEvento = new ArrayList<>();

        for (String s : periodicidad) {
            switch (s) {
                case LUNES:
                    diasSemanaEvento.add(Calendar.MONDAY);
                    break;
                case MARTES:
                    diasSemanaEvento.add(Calendar.TUESDAY);
                    break;
                case MIERCOLES:
                    diasSemanaEvento.add(Calendar.WEDNESDAY);
                    break;
                case JUEVES:
                    diasSemanaEvento.add(Calendar.THURSDAY);
                    break;
                case VIERNES:
                    diasSemanaEvento.add(Calendar.FRIDAY);
                    break;
                case SABADO:
                    diasSemanaEvento.add(Calendar.SATURDAY);
                    break;
                case DOMINGO:
                    diasSemanaEvento.add(Calendar.SUNDAY);
                    break;
                default:
                    break;
            }
        }

        return diasSemanaEvento;
    }
}