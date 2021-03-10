public class Cliente{
    //Atributos String nombre(el nombre del cliente), long telefono(el numero de telefono del cliente)
    private String nombre;
    private long telefono;

    //Constructor vacio
    public Cliente() {
    }

    //Constructor con atributos
    public Cliente(String nombre, long telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}