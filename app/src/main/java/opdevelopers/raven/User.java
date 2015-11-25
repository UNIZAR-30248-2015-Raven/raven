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

    public void setNombre(String nombre) throws ErrorException {
        if (nombre == null || nombre.equals("")) {
            throw new ErrorException();
        }
        else {
            this.nombre = nombre;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws ErrorException {
        if (apellido == null || apellido.equals("")) {
            throw new ErrorException();
        }
        else {
            this.apellido = apellido;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ErrorException {
        if (email == null || email.equals("") || !email.contains("@") || !email.contains(".")) {
            throw new ErrorException();
        }
        else {
            this.email = email;
        }
    }

    public String getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(String anyoNacimiento) throws ErrorException {
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
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ErrorException {
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
    }

    public String getInfoMedica() {
        return infoMedica;
    }

    public void setInfoMedica(String infoMedica) throws ErrorException {
        if (infoMedica == null || infoMedica.equals("")) {
            throw new ErrorException();
        }
        else {
            this.infoMedica = infoMedica;
        }
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) throws ErrorException {
        if (residencia == null || residencia.equals("")) {
            throw new ErrorException();
        } else {
            this.residencia = residencia;
        }
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) throws ErrorException {
        if (contrasenya == null || contrasenya.equals("")) {
            throw new ErrorException();
        }
        else {
            this.contrasenya = contrasenya;
        }
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) throws ErrorException {
        if (nombreContacto == null || nombreContacto.equals("")) {
            throw new ErrorException();
        }
        else {
            this.nombreContacto = nombreContacto;
        }
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) throws ErrorException {
        if (apellidoContacto == null || apellidoContacto.equals("")) {
            throw new ErrorException();
        }
        else {
            this.apellidoContacto = apellidoContacto;
        }
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) throws ErrorException {
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

    @Override
    public String toString() {
        String cadena = "INFO USUARIO\n";
        cadena += "===========\n";
        if (this.nombre != null) {
            cadena += "nombre -> " + this.nombre + "\n";
        }
        if (this.apellido != null) {
            cadena += "apellido -> " + this.apellido + "\n";
        }
        cadena += "email -> " + this.email + "\n";
        if (this.anyoNacimiento != null) {
            cadena += "anyoNacimiento -> " + this.anyoNacimiento + "\n";
        }
        if (this.telefono != null) {
            cadena += "telefono -> " + this.telefono + "\n";
        }
        if (this.infoMedica != null) {
            cadena += "infoMedica -> " + this.infoMedica + "\n";
        }
        if (this.residencia != null) {
            cadena += "residencia -> " + this.residencia + "\n";
        }
        cadena += "contrasenya -> " + this.contrasenya + "\n";
        if (this.nombreContacto != null) {
            cadena += "nombreContacto -> " + this.nombreContacto + "\n";
        }
        if (this.apellidoContacto != null) {
            cadena += "apellidoContacto -> " + this.apellidoContacto + "\n";
        }
        if (this.telefonoContacto != null) {
            cadena += "telefonoContacto -> " + this.telefonoContacto + "\n";
        }
        return cadena;
    }
}