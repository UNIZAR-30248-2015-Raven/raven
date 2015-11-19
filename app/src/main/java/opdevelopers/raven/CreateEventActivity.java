package opdevelopers.raven;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Eduardo on 18/11/2015.
 */
public class CreateEventActivity extends AppCompatActivity {

    private static final int ACTIVITY_CLIENTE = 1;
    private static final String USUARIO = "CorreoUsuario";

    private EditText mMensajeText;           // Mensaje del evento
    private EditText mFechaText;             // Fecha del evento
    private EditText mHoraText;              // Hora del evento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int dia = intent.getIntExtra("dia", 0);
        int mes = intent.getIntExtra("mes", 0);
        int anno = intent.getIntExtra("anno", 0);

        setContentView(R.layout.activity_create_event);
        setTitle(R.string.crear_evento);

        mMensajeText = (EditText) findViewById(R.id.mensaje);
        mMensajeText.setText(dia + "/" + (mes + 1) + "/" + anno);
        mFechaText = (EditText) findViewById(R.id.date);
        mHoraText = (EditText) findViewById(R.id.time);

        mFechaText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (!mFechaText.getText().toString().equals("")) {
                    calendar.setTime(Date.valueOf(mFechaText.getText().toString()));
                }
                DatePickerDialog mDatePicker = new DatePickerDialog(CreateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth,
                                                  int selectedDay) {
                                selectedMonth = selectedMonth + 1;
                                mFechaText.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Seleccionar fecha");
                mDatePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar", mDatePicker);
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
                    calendar.setTime(Date.valueOf(mHoraText.getText().toString()));
                }
                TimePickerDialog mTimePicker = new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                mHoraText.setText(selectedHour + ":" + selectedMinute);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Seleccionar hora");
                mTimePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar", mTimePicker);
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
                    Intent i = new Intent(CreateEventActivity.this, CalendarioActivity.class);
                    CreateEventActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.error_datos,
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private String obtenerEmailUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        String email = prefsCorreo.getString("email", "");
        return email;
    }

    /*
     * Envía una petición http con los datos introducidos por el usuario para guardarlos en la
     * base de datos del servidor.
     */
    public boolean guardarEvento() {
        boolean peticionAceptada = false;
        String email = obtenerEmailUsuario();
        String mensaje = mMensajeText.getText().toString();
        String fecha = mFechaText.getText().toString();
        String hora = mHoraText.getText().toString();

        EventAdapter adaptadorEventos = new EventAdapter();
        Event evento = new Event("", email, mensaje, fecha, hora, "");
        peticionAceptada = adaptadorEventos.enviarPeticionCrearEvento(evento);
        return peticionAceptada;
    }

}