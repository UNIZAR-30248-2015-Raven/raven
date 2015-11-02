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
        if (nombre == null || nombre.equals("")) {
            throw new ErrorException();
        }
        else {
            this.nombre = nombre;
        }
        if (apellido == null || apellido.equals("")) {
            throw new ErrorException();
        }
        else {
            this.apellido = apellido;
        }
        if (email == null || email.equals("") || !email.contains("@") || !email.contains(".")) {
            throw new ErrorException();
        }
        else {
            this.email = email;
        }
        if (anyoNacimiento == null || anyoNacimiento.equals("") || anyoNacimiento.length() != 4) {
            throw new ErrorException();
        }
        else {
            try {
                int anyo = Integer.parseInt(anyoNacimiento);
                if (anyo < 0) {
                    throw new ErrorException();
                }
            }
            catch (NumberFormatException e) {
                throw new ErrorException();
            }
            this.anyoNacimiento = anyoNacimiento;
        }
        if (telefono == null || telefono.equals("")) {
            throw new ErrorException();
        }
        else {
            try {
                int tlf = Integer.parseInt(telefono);
                if (tlf < 0) {
                    throw new ErrorException();
                }
            }
            catch (NumberFormatException e) {
                throw new ErrorException();
            }
            this.telefono = telefono;
        }
        if (infoMedica == null || infoMedica.equals("")) {
            throw new ErrorException();
        }
        else {
            this.infoMedica = infoMedica;
        }
        if (residencia == null || residencia.equals("")) {
            throw new ErrorException();
        }
        else {
            this.residencia = residencia;
        }
        if (contrasenya == null || contrasenya.equals("")) {
            throw new ErrorException();
        }
        else {
            this.contrasenya = contrasenya;
        }
        if (nombreContacto == null || nombreContacto.equals("")) {
            throw new ErrorException();
        }
        else {
            this.nombreContacto = nombreContacto;
        }
        if (apellidoContacto == null || apellidoContacto.equals("")) {
            throw new ErrorException();
        }
        else {
            this.apellidoContacto = apellidoContacto;
        }
        if (telefonoContacto == null || telefonoContacto.equals("")) {
            throw new ErrorException();
        }
        else {
            try {
                int tlfContacto = Integer.parseInt(telefonoContacto);
                if (tlfContacto < 0) {
                    throw new ErrorException();
                }
            }
            catch (NumberFormatException e) {
                throw new ErrorException();
            }
            this.telefonoContacto = telefonoContacto;
        }
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