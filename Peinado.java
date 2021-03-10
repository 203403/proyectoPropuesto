//Subclase de cabello
public class Peinado extends Cabello{
    //Contructor heredado(contructor de la superclase)
    public Peinado(String tipo, double costo, int cantidad, int largo) {
        super(tipo, costo, cantidad, largo);
    }

    //Modificacion y utilizacion del metodo abstracto asignarCoste
    @Override
    public double asignarCoste(String n){
        double coste;//Variable local coste
        //Coste dependiendo de la cantidad (volumen) del cabello (poco o mucho)
        if(n=="Peinados"){
            //Coste dependiendo de la cantidad (volumen) del cabello (poco o mucho)
            if(cantidad==1)
                coste = 50;
            else
                coste = 100;
            //Coste dependiendo del largo del cabello (corto, mediano o largo)
            switch(largo){
                case 1:
                    coste+=50;
                break;
                case 2:
                    coste+=125;
                break;
                case 3:
                    coste+=200;
                break;
            }
        }else{//Si el tipo de servicio no es un Peinado se toman los siguientes criterios
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
}