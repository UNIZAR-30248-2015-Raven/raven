package opdevelopers.raven;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Eduardo on 18/11/2015.
 */
public class CreateEventActivity extends AppCompatActivity {

    private static final int ACTIVITY_CLIENTE = 1;
    private static final String USUARIO = "CorreoUsuario";

    private EditText mMensajeText;           // Mensaje del evento
    private EditText mFechaText;             // Fecha del evento
    private EditText mHoraText;              // Hora del evento

    private boolean lunes = false;
    private boolean martes = false;
    private boolean miercoles = false;
    private boolean jueves = false;
    private boolean viernes = false;
    private boolean sabado = false;
    private boolean domingo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int dia = intent.getIntExtra("dia", 0);
        int mes = intent.getIntExtra("mes", 0);
        int anno = intent.getIntExtra("anno", 0);

        setContentView(R.layout.activity_create_event);
        setTitle(R.string.crear_evento_label);

        mMensajeText = (EditText) findViewById(R.id.mensaje);
        mFechaText = (EditText) findViewById(R.id.date);

        String ceroMes = "";
        String ceroDia = "";
        if (mes < 10) {
            ceroMes = "0";
        }
        if (dia < 10) {
            ceroDia = "0";
        }
        mFechaText.setText(anno + "-" + ceroMes + mes + "-" + ceroDia + dia);

        mHoraText = (EditText) findViewById(R.id.time);

        mFechaText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(Date.valueOf(mFechaText.getText().toString()));
                DatePickerDialog mDatePicker = new DatePickerDialog(CreateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth,
                                                  int selectedDay) {
                                selectedMonth = selectedMonth + 1;
                                String zeroMonth = "";
                                String zeroDay = "";
                                if (selectedMonth < 10) {
                                    zeroMonth = "0";
                                }
                                if (selectedDay < 10) {
                                    zeroDay = "0";
                                }
                                mFechaText.setText(selectedYear + "-" + zeroMonth + selectedMonth + "-" + zeroDay + selectedDay);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Seleccionar fecha");
                mDatePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar", mDatePicker);
                mDatePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", mDatePicker);
                mDatePicker.show();
            }
        });
        mFechaText.setFocusable(false);
        mFechaText.setClickable(true);

        mHoraText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (!mHoraText.getText().toString().equals("")) {
                    Time time = Time.valueOf(mHoraText.getText().toString() + ":00");
                    calendar.setTime(time);
                }
                TimePickerDialog mTimePicker = new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                String zeroHour = "";
                                String zeroMinute = "";
                                if (selectedHour < 10) {
                                    zeroHour = "0";
                                }
                                if (selectedMinute < 10) {
                                    zeroMinute = "0";
                                }
                                mHoraText.setText(zeroHour + selectedHour + ":" + zeroMinute + selectedMinute);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Seleccionar hora");
                mTimePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar", mTimePicker);
                mTimePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", mTimePicker);
                mTimePicker.show();
            }
        });
        mHoraText.setFocusable(false);
        mHoraText.setClickable(true);

        Button botonSeguir = (Button) findViewById(R.id.seguir);

        botonSeguir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean guardado = guardarEvento();
                if (guardado) {
                    Toast.makeText(getApplicationContext(), R.string.exito_datos,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });
    }

    private String obtenerEmailUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        String email = prefsCorreo.getString("email", "");
        return email;
    }

    private String getPeriodicidad() {
        String periodicidad = "";

        if (lunes) periodicidad += "L ";
        if (martes) periodicidad += "M ";
        if (miercoles) periodicidad += "X ";
        if (jueves) periodicidad += "J ";
        if (viernes) periodicidad += "V ";
        if (sabado) periodicidad += "S ";
        if (domingo) periodicidad += "D";
        return periodicidad.trim(); // quita espacios al principio y al final de la cadena
    }

    /*
     * Envía una petición http con los datos introducidos por el usuario para guardarlos en la
     * base de datos del servidor.
     */
    public boolean guardarEvento() {
        boolean peticionAceptada = false;
        try {
            String error = "";
            String errorFechaPeriodicidad = "";
            String email = obtenerEmailUsuario();
            String mensaje = mMensajeText.getText().toString();
            if (mensaje == null || mensaje.equals("")) {
                error += "Mensaje, ";
            }
            String fecha = "";
            if (mFechaText.isEnabled()) {
                fecha = mFechaText.getText().toString();
            }
            String hora = mHoraText.getText().toString();
            if (hora == null || hora.equals("") || !hora.contains(":")) {
                error += "Hora, ";
            }
            String periodicidad = getPeriodicidad();
            if ((fecha.equals("") && periodicidad.equals("")) || (!fecha.equals("") && !periodicidad.equals(""))) {
                errorFechaPeriodicidad += "Falta por seleccionar fecha o periodicidad";
            }

            if (error.length() > 0) {
                String advertencia = "Error en el campo: " + error.substring(0, error.length()-2) + ".";
                Toast.makeText(getApplicationContext(), advertencia + errorFechaPeriodicidad, Toast.LENGTH_SHORT).show();
            }

            EventAdapter adaptadorEventos = new EventAdapter(Constants.CREATE_EVENT);
            Event evento = new Event("", email, mensaje, fecha, hora, periodicidad);
            peticionAceptada = adaptadorEventos.enviarPeticionCrearEvento(evento);
        }
        catch (ErrorException e) {
            e.printStackTrace();
        }
        return peticionAceptada;
    }

    private void periodityHab() {
        boolean habilitar = true;

        if (lunes || martes || miercoles || jueves || viernes || sabado || domingo) {
            habilitar = false;
        }

        if (habilitar) {
            mFechaText.setEnabled(true);
        } else {
            mFechaText.setEnabled(false);
        }
    }

    public void onCheckboxClicked(View view) {
        // ¿Está el objeto View ahora chequeado?
        boolean checked = ((CheckBox) view).isChecked();

        // Comprueba qué checkbox ha sido clicado
        switch(view.getId()) {
            case R.id.chLunes:
                lunes = checked ? true : false;
                periodityHab();
                break;
            case R.id.chMartes:
                martes = checked ? true : false;
                periodityHab();
                break;
            case R.id.chMiercoles:
                miercoles = checked ? true : false;
                periodityHab();
                break;
            case R.id.chJueves:
                jueves = checked ? true : false;
                periodityHab();
                break;
            case R.id.chViernes:
                viernes = checked ? true : false;
                periodityHab();
                break;
            case R.id.chSabado:
                sabado = checked ? true : false;
                periodityHab();
                break;
            case R.id.chDomingo:
                domingo = checked ? true : false;
                periodityHab();
                break;
        }
    }
}