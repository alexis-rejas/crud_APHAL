package vallegrande.edu.pe.model;

public class Usuario {
    private int    id;
    private String nombre;
    private String correo;
    private String telefono;
    private String asunto;
    private String mensaje;

    public Usuario() {}

    public Usuario(int id, String nombre, String correo,
                   String telefono, String asunto, String mensaje) {
        this.id       = id;
        this.nombre   = nombre;
        this.correo   = correo;
        this.telefono = telefono;
        this.asunto   = asunto;
        this.mensaje  = mensaje;
    }

    // Getters y Setters
    public int    getId()       { return id; }
    public String getNombre()   { return nombre; }
    public String getCorreo()   { return correo; }
    public String getTelefono() { return telefono; }
    public String getAsunto()   { return asunto; }
    public String getMensaje()  { return mensaje; }

    public void setId(int id)             { this.id = id; }
    public void setNombre(String n)       { this.nombre = n; }
    public void setCorreo(String c)       { this.correo = c; }
    public void setTelefono(String t)     { this.telefono = t; }
    public void setAsunto(String a)       { this.asunto = a; }
    public void setMensaje(String m)      { this.mensaje = m; }
}