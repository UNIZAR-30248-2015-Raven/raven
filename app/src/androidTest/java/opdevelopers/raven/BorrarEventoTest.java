package opdevelopers.raven;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class BorrarEventoTest extends ActivityInstrumentationTestCase2<LoginActivity> {
  	private Solo solo;
  	
  	public BorrarEventoTest() {
		super(LoginActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'opdevelopers.raven.LoginActivity'
		solo.waitForActivity(LoginActivity.class, 1000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.email));
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Enter the text: 'rgcmb@hotmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(R.id.email), "rgcmb@hotmail.com");
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Enter the text: '123456'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.contrasenya));
		solo.enterText((android.widget.EditText) solo.getView(R.id.contrasenya), "123456");
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Click on INICIAR SESIÓN
		solo.clickOnView(solo.getView(R.id.iniciar_sesion));
        //Wait for activity: 'opdevelopers.raven.MainActivity'
		assertTrue("opdevelopers.raven.MainActivity is not found!", solo.waitForActivity(MainActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(R.id.botonCalendario));
        //Wait for activity: 'opdevelopers.raven.calendario.CalendarioActivity'
		assertTrue("opdevelopers.raven.calendario.CalendarioActivity is not found!", solo.waitForActivity(opdevelopers.raven.calendario.CalendarioActivity.class));
        //Click on CREAR EVENTO
		solo.clickOnView(solo.getView(R.id.boton_crear_evento));
        //Wait for activity: 'opdevelopers.raven.CreateEventActivity'
		assertTrue("opdevelopers.raven.CreateEventActivity is not found!", solo.waitForActivity(CreateEventActivity.class));
        //Enter the text: 'Prueba aceptacion'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.mensaje));
		solo.enterText((android.widget.EditText) solo.getView(R.id.mensaje), "Prueba aceptacion");
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.time));
        //Wait for dialog
		solo.waitForDialogToOpen(1000);
        //Sleep for 1000 milliseconds
        solo.setTimePicker(0, 12, 23);
		solo.sleep(1000);
        //Click on Aceptar
        solo.clickOnView(solo.getView(android.R.id.button1));
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Click on SEGUIR
		solo.clickOnView(solo.getView(R.id.seguir));
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Click on Prueba aceptacion a las 19:09
		solo.clickOnView(solo.getView(android.R.id.text1, 0));
        //Wait for activity: 'opdevelopers.raven.DetallesEventActivity'
		assertTrue("opdevelopers.raven.DetallesEventActivity is not found!", solo.waitForActivity(DetallesEventActivity.class));
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        solo.clearEditText((android.widget.EditText) solo.getView(R.id.mensaje));
        solo.enterText((android.widget.EditText) solo.getView(R.id.mensaje), "Prueba aceptacion modificada");
        solo.sleep(1000);
        //Click on SEGUIR
		solo.clickOnView(solo.getView(R.id.seguir));
        //Sleep for 1000 milliseconds
		solo.sleep(1000);
        //Long click Prueba aceptacion a las 19:09
		solo.clickLongOnView(solo.getView(android.R.id.text1));
        //Wait for dialog
		solo.waitForDialogToOpen(1000);
        //Click on Borrar evento
		solo.clickInList(1, 0);
        solo.sleep(1000);
	}
}
