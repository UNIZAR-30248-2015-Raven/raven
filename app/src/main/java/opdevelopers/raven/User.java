package opdevelopers.raven;

/**
 * Created by Eduardo on 30/10/2015.
 */
public class User {

    private String nombre;
    private String apellido;
    private String email;
    private String anyoNacimiento;
    private String telefono;
    private String infoMedica;
    private String residencia;
    private String contrasenya;
    private String nombreContacto;
    private String apellidoContacto;
    private String telefonoContacto;

    public User(String email, String contrasenya) throws ErrorException {
        if (email == null || email.equals("") || !email.contains("@") || !email.contains(".")) {
            throw new ErrorException();
        }
        else {
            this.email = email;
        }
        if (contrasenya == null || contrasenya.equals("")) {
            throw new ErrorException();
        }
        else {
            this.contrasenya = contrasenya;
        }
    }

    public User(String nombre, String apellido, String email, String anyoNacimiento, String telefono,
                String infoMedica, String residencia, String contrasenya, String nombreContacto,
                String apellidoContacto, String telefonoContacto) throws ErrorException {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.anyoNacimiento = anyoNacimiento;
        this.telefono = telefono;
        this.infoMedica = infoMedica;
        this.residencia = residencia;
        this.contrasenya = contrasenya;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(String anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getInfoMedica() {
        return infoMedica;
    }

    public void setInfoMedica(String infoMedica) {
        this.infoMedica = infoMedica;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}