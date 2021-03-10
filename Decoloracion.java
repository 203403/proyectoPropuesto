//Subclase de cabbello
public class Decoloracion extends Cabello{
    //Atributos int tonoBase(tonalidad base de la decoloraion), int capaAdicional(tonalidad aparte del tonoBase, ya sea reflejos o matices)
    private int tonoBase;
    private int capaAdicional;

    //Contructor heredado(atributos de la subclase y contructor de la superclase)
    public Decoloracion(String tipo, double costo, int cantidad, int largo, int tonoBase, int capaAdicional) {
        super(tipo, costo, cantidad, largo);
        this.tonoBase = tonoBase;
        this.capaAdicional = capaAdicional;
    }
    
    //Getters y Setters de los atributos
    public int getTonoBase() {
        return tonoBase;
    }

    public void setTonoBase(int tonoBase) {
        this.tonoBase = tonoBase;
    }

    public int getCapaAdicional() {
        return capaAdicional;
    }

    public void setCapaAdicional(int capaAdicional) {
        this.capaAdicional = capaAdicional;
    }

    //Modificacion y utilizacion del metodo abstracto asignarCoste
    @Override
    public double asignarCoste(String n){
        double coste;//Variable local coste
        if(n=="Tinte"){//Si el tipo de servicio es tinte se toman los siguientes criterios
            //Coste dependiendo de la cantidad (volumen) del cabello (poco o mucho)
            if(cantidad==1)
                coste = 100;
            else
                coste = 150;
            //Coste dependiendo del largo del cabello (corto, mediano o largo)
            switch(largo){
                case 1:
                    coste+=50;
                break;
                case 2:
                    coste+=100;
                break;
                case 3:
                    coste+=150;
                break;
            }
        }else{//Si el tipo de servicio no es un tinte se toman los siguientes criterios
            //Coste dependiendo de la cantidad (volumen) del cabello (poco o mucho)
            if(cantidad==1)
                coste = 160;
            else
                coste = 280;
            //Coste dependiendo del largo del cabello (corto, mediano o largo)
            switch(largo){
                case 1:
                    coste+=140;
                break;
                case 2:
                    coste+=230;
                break;
                case 3:
                    coste+=320;
                break;
            }
        }
        return coste;
    }

    //Metodo que agrega un costo adicional a los servicios de decoloracion en el que se le añade el servicio de tinte
    public double costoAdicionalTinte(double costo){
        double costoFinal = costo + 200;
        return costoFinal;
    }
}