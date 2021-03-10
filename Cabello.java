//clase abstacta Cabello subclase de Servicio
public abstract class Cabello extends Servicio {
    protected int cantidad;
    protected int largo;

    //Contructor heredado(atributos de la subclase y contructor de la superclase)
    public Cabello(String tipo, double costo, int cantidad, int largo) {
        super(tipo, costo);
        this.cantidad = cantidad;
        this.largo = largo;
    }
    
    //Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    //Metddo abstracto asignarCoste
    public abstract double asignarCoste(String n);
}