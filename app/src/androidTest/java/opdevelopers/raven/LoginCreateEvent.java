package opdevelopers.raven;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class LoginCreateEvent extends ActivityInstrumentationTestCase2<LoginActivity> {
  	private Solo solo;
  	
  	public LoginCreateEvent() {
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
		solo.waitForActivity(opdevelopers.raven.LoginActivity.class, 2000);
        //Set default small timeout to 12830 milliseconds
		Timeout.setSmallTimeout(12830);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.email));
        //Enter the text: 'rgcmb@hotmail.com'
		solo.clearEditText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.email));
		solo.enterText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.email), "rgcmb@hotmail.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.contrasenya));
        //Enter the text: '123456'
		solo.clearEditText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.contrasenya));
		solo.enterText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.contrasenya), "123456");
        //Click on INICIAR SESIÓN
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.iniciar_sesion));
        //Wait for activity: 'opdevelopers.raven.MainActivity'
		//assertTrue("opdevelopers.raven.MainActivity is not found!", solo.waitForActivity(opdevelopers.raven.MainActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.botonCalendario));
        //Wait for activity: 'opdevelopers.raven.calendario.CalendarioActivity'
		//assertTrue("opdevelopers.raven.calendario.CalendarioActivity is not found!", solo.waitForActivity(opdevelopers.raven.calendario.CalendarioActivity.class));
        //Click on CREAR EVENTO
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.boton_crear_evento));
        //Wait for activity: 'opdevelopers.raven.CreateEventActivity'
		//assertTrue("opdevelopers.raven.CreateEventActivity is not found!", solo.waitForActivity(opdevelopers.raven.CreateEventActivity.class));
        //Set default small timeout to 13050 milliseconds
		Timeout.setSmallTimeout(13050);
        //Enter the text: 'Soy una prueba de aceptacion'
		solo.clearEditText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.mensaje));
		solo.enterText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.mensaje), "Soy una prueba de aceptacion");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.time));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Aceptar
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '21:32'
		solo.clearEditText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.time));
		solo.enterText((android.widget.EditText) solo.getView(opdevelopers.raven.R.id.time), "21:32");
        //Click on SEGUIR
		solo.clickOnView(solo.getView(opdevelopers.raven.R.id.seguir));
	}
}