package opdevelopers.raven;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import opdevelopers.raven.calendario.CalendarioActivity;

/**
 * Created by Daniel on 22/11/2015.
 */
public class DetallesEventActivity extends AppCompatActivity {
    private EditText mMensajeText;           // Mensaje del evento
    private EditText mFechaText;             // Fecha del evento
    private EditText mHoraText;              // Hora del evento

    private CheckBox mLunes;
    private CheckBox mMartes;
    private CheckBox mMiercoles;
    private CheckBox mJueves;
    private CheckBox mViernes;
    private CheckBox mSabado;
    private CheckBox mDomingo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String mensaje = intent.getStringExtra("mensaje");
        String date = intent.getStringExtra("date");
        String hora = intent.getStringExtra("hora");
        String periodicidad = intent.getStringExtra("periodicidad");

        setContentView(R.layout.activity_create_event);
        setTitle(R.string.crear_evento);

        mMensajeText = (EditText) findViewById(R.id.mensaje);
        mMensajeText.setText(mensaje, TextView.BufferType.EDITABLE);
        mMensajeText.setEnabled(false);
        mMensajeText.setFocusable(false);

        mFechaText = (EditText) findViewById(R.id.date);
        mFechaText.setText(date, TextView.BufferType.EDITABLE);
        mFechaText.setEnabled(false);
        mFechaText.setFocusable(false);

        mHoraText = (EditText) findViewById(R.id.time);
        mHoraText.setText(hora, TextView.BufferType.EDITABLE);
        mHoraText.setEnabled(false);
        mHoraText.setFocusable(false);

        Button botonSeguir = (Button) findViewById(R.id.seguir);
        botonSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLunes = (CheckBox) findViewById(R.id.chLunes);
        mLunes.setEnabled(false);

        mMartes = (CheckBox) findViewById(R.id.chMartes);
        mMartes.setEnabled(false);

        mMiercoles = (CheckBox) findViewById(R.id.chMiercoles);
        mMiercoles.setEnabled(false);

        mJueves = (CheckBox) findViewById(R.id.chJueves);
        mJueves.setEnabled(false);

        mViernes = (CheckBox) findViewById(R.id.chViernes);
        mViernes.setEnabled(false);

        mSabado = (CheckBox) findViewById(R.id.chSabado);
        mSabado.setEnabled(false);

        mDomingo = (CheckBox) findViewById(R.id.chDomingo);
        mDomingo.setEnabled(false);

        if (periodicidad.length() != 0) {
            mFechaText.setText(" ", TextView.BufferType.EDITABLE);
            checkCheckboxes(periodicidad);
        }
    }

    private void checkCheckboxes(String periodicidad) {
        String[] dias = periodicidad.split(" ");

        for (String s : dias) {
            switch (s) {
                case CalendarioActivity.LUNES:
                    mLunes.setChecked(true);
                    break;
                case CalendarioActivity.MARTES:
                    mMartes.setChecked(true);
                    break;
                case CalendarioActivity.MIERCOLES:
                    mMiercoles.setChecked(true);
                    break;
                case CalendarioActivity.JUEVES:
                    mJueves.setChecked(true);
                    break;
                case CalendarioActivity.VIERNES:
                    mViernes.setChecked(true);
                    break;
                case CalendarioActivity.SABADO:
                    mSabado.setChecked(true);
                    break;
                case CalendarioActivity.DOMINGO:
                    mDomingo.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }
}
