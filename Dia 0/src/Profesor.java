public class Profesor {
    private int id;
    private String nombres;
    private String apellidos;
    private String departamento;
    private String telefono;
    private String correo;
    
    public Profesor(int id, String nombres, String apellidos, String departamento, String telefono, String correo){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.departamento = departamento;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    @Override 
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", departamento='" + departamento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
