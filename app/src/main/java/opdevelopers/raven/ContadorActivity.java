package opdevelopers.raven;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Timer;


/**
 * @author Daniel Uroz
 *
 * Pantalla que se encarga de establecer un contador
 */
public class ContadorActivity extends AppCompatActivity {
    private static final int HOURS_TO_MIN = 60;
    private static final int MIN_TO_MILISEC = 60000;

    private static final int MAX_HOUR_VALUE = 24;
    private static final int MAX_MIN_VALUE = 60;
    private static final int MIN_HOUR_VALUE = 0;
    private static final int MIN_MIN_VALUE = 0;

    private EditText titulo;
    private NumberPicker hourPicker;
    private NumberPicker minPicker;
    private Button crearContadorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        setTitle();
        findViewsById();

        setPickerValues(hourPicker, MIN_HOUR_VALUE, MAX_HOUR_VALUE);
        setPickerValues(minPicker, MIN_MIN_VALUE, MAX_MIN_VALUE);

        crearContadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearContador();
            }
        });
    }


    /**
     * Establece el título del Activity
     */
    private void setTitle() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.contador);
        }
    }


    /**
     * Consigue los elementos de la vista
     */
    private void findViewsById() {
        titulo = (EditText) findViewById(R.id.titulo);

        hourPicker = (NumberPicker) findViewById(R.id.hourPicker);
        minPicker = (NumberPicker) findViewById(R.id.minPicker);

        // evita que se puedan editar directamente con el teclado
        hourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        minPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        crearContadorButton = (Button) findViewById(R.id.boton_crear_contador);
    }


    /**
     * Establece el rango de los NumberPickers
     *
     * @param picker (hour o min)
     * @param minValue del rango
     * @param maxValue del rango
     */
    private void setPickerValues(NumberPicker picker, int minValue, int maxValue) {
        String[] nums = new String[maxValue];

        for(int i = 0; i < nums.length; i++) {
            nums[i] = Integer.toString(i);
        }

        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue - 1);
        picker.setWrapSelectorWheel(true);
        picker.setDisplayedValues(nums);
        picker.setValue(minValue);
    }


    /**
     * Crea un contador en un hilo aparte para que notifique cuando acabe
     */
    private void crearContador() {
        if (sonDatosValidos()) {
            // tiempo en que sonará el contador
            int millis = ((hourPicker.getValue() * HOURS_TO_MIN) + minPicker.getValue())
                    * MIN_TO_MILISEC;


            Handler handler = new Handler() {
                // cuando haya acabado el contador se empieza otra Acivity
                public void handleMessage(Message msg) {
                    String message = (String) msg.obj; //Extract the string from the Message
                    Intent viewTargetActivity = new Intent(ContadorActivity.this, AlarmActivity.class);
                    viewTargetActivity.putExtra("titulo", message);
                    startActivity(viewTargetActivity);
                }
            };

            Timer timer = new Timer();
            timer.schedule(new ContadorTask(handler, titulo.getText().toString()), millis);

            Toast.makeText(this, getResources().getString(R.string.sonara_en) + " "
                    + hourPicker.getValue() + " " + getResources().getString(R.string.horas)
                    + " " + minPicker.getValue() + " " + getResources().getString(R.string.minutos),
                    Toast.LENGTH_SHORT).show();

            finish();
        }
    }


    /**
     * Comprueba que los datos introducidos son correctos, sino muestra al usuario los errores
     */
    private boolean sonDatosValidos() {
        // comprueba que ha introducido un título
        if (titulo.getText().toString().length() == 0) {
            Toast.makeText(this, R.string.no_titulo, Toast.LENGTH_SHORT).show();

            return false;
        }

        int hours = hourPicker.getValue();
        int mins = minPicker.getValue();

        // compureba que ha introducido un tiempo
        if ((hours == MIN_HOUR_VALUE) && (mins == MIN_MIN_VALUE)) {
            Toast.makeText(this, R.string.no_tiempo, Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }
}