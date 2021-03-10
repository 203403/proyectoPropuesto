public class Servicio{
    //Atributos String tipo(el nombre del servicio + informacion adicional si asi lo requiere el registro del servicio), double costo(costo por el servicio)
    protected String tipo;
    protected double costo;

    //Contructor vacio
    public Servicio(){
    }

    //Constructor con atributos
    public Servicio(String tipo, double costo) {
        this.tipo = tipo;
        this.costo = costo;
    }
    
    //Cambio para GitHub
    //Getters y Setters de los atributos
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    //Modificacion del metodo toString
    @Override
    public String toString(){
        return ("El servicio fue "+tipo+" y tuvo un costo de: "+costo);
    }
}