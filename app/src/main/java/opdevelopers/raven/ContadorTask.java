package opdevelopers.raven;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * @author Daniel Uroz
 *
 * Contador que se lanza en otro hilo para notificar más adelante cuando finalice
 */
public class ContadorTask extends TimerTask {
    private Handler handler;
    private String mensaje;

    public ContadorTask(Handler handler, String mensaje) {
        this.handler = handler;
        this.mensaje = mensaje;
    }


    /**
     * Se ejecuta cuando el contador haya acabado
     */
    public void run() {
        // envía un mensaje a la vista
        Message message = Message.obtain();
        message.obj = mensaje;

        handler.sendMessage(message);
    }
}

