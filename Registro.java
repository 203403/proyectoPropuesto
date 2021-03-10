import java.io.*;
import java.util.ArrayList;

public class Registro{
    //Atributos necesarios para llevar el registro y guardarse en un archivo txt
    private String fecha;
    private String nombreCliente;
    private long numeroTelefono;
    private String tipoServicio;
    private double costoTotal;

    //Contructor vacio
    public Registro(){
    }

    //Contructor con atributos
    public Registro(String fecha, String nombreCliente, long numeroTelefono, String tipoServicio, double costoTotal) {
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.numeroTelefono = numeroTelefono;
        this.tipoServicio = tipoServicio;
        this.costoTotal = costoTotal;
    }

    //Modificacion del metodo toString
    @Override
    public String toString(){
        return ("La fecha fue: "+fecha+"\t|El nombre del(la) cliente es: "+nombreCliente+"\t|Su numero de telefono es: "+numeroTelefono+"\t|El servicio realizado fue "+tipoServicio+"\t|El costo por el servicio fue de: "+costoTotal+"\n");
    }

    //Metodo para guardar los datos
    public void guardarDatos(String nombreArchivo, ArrayList<String> re){
        File f = new File(nombreArchivo);//Intancia para poder crear o abrir un archivo txt
        String temp="";//Variable temporal

        //Se realiza un ciclo para llenar la variable temp con el contenido del ArrayList
        for(int i=0; i<re.size(); i++){
            temp += re.get(i);
        }

        try{
            //Instancias para poder acceder, modificar y almacenar datos en el archvo txt
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            wr.append(temp);//Aqui se agrega la informacion al archivo txt
            wr.close();
            bw.close();
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        };
    }

    public ArrayList<String> leerArchivo(String n, ArrayList<String> re){
        re.clear();//Se vacia el ArrayList para volverlo a llenar con el contenido del txt (Para evitar que se esten escribiendo multiples veces un mismo datos)
        try{
            FileReader r = new FileReader(n);//Instancia para poder leer un archivo txt
            BufferedReader buffer = new BufferedReader(r);

            String temp="";//Variable temporal

            //Ciclo para que lea cada linea de texto del archivo txt
            while(temp!=null){
                temp = buffer.readLine();//Almacena la linea de texto leida en la variable temp
                if(temp==null){
                    break;
                }
                re.add(temp);//Se añade el contenido en temp al ArrayList
            };
            buffer.close();
        }catch(Exception e){};
        return re;
    }
}