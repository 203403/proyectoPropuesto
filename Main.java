import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main{
    static Scanner entrada = new Scanner(System.in);//Entrada me permitira la captura de informacion del teclado
    static ArrayList<String> regis = new ArrayList<String>();//ArrayList tipo String para almacenar temporalmente los registros para poder modificar la informacion ya existente en el archivo txt o añadir nueva
    public static void main(String [] args) {
        boolean validar=false;//Variable que servira para validar las opciones asi como condicion del ciclo
        int opcion;//VAriable que almacena las opciones escogidas por el usuario
        Registro r = new Registro();//Objeto de tipo registro
        regis = r.leerArchivo("ControlDeResgistros.txt", regis);//Se almacena en el ArrayList el contenido del archivo txt
        do{
            //Despliega un menu de opciones
            System.out.println("Bienvenido al sistema de registros de servicios\nSeleccione que desea hacer\n1. Mostrar registros\n2. Agregar un nuevo registro\n3. Eliminar registro\n4. Salir");
            opcion = entrada.nextInt();
            //Evalua y ejecuta el proceso segun la opcion escogida por el usuario
            switch(opcion){
                case 1:
                    //Valida si el ArrayList contiene informacion
                    if(regis.isEmpty()){
                        System.out.println("No existen registros para mostrar");//Si no contiene informacion muestra el mensaje
                    }else{
                        //Si contiene informacion ejecuta el metodo mostrarRegistros
                        System.out.println("Estos son los registros:");
                        mostrarRegistros();
                    }
                    validar=false;
                break;
                case 2:
                    Servicio servicio = selecionarServicio();//Se crea un objeto de tipo Servicio que alamcenara lo que retorne seleccionarServicio
                    Cliente cliente = resgistarDatosCliente(validar);
                    Registro registro = guardarDatosServicio(servicio, cliente);
                    String mensaje = registro.toString();
                    regis.add(mensaje);
                    registro.guardarDatos("ControlDeResgistros.txt", regis);
                    System.out.println(mensaje);
                    validar = false;
                break;
                case 3:
                    if(regis.isEmpty()){
                        System.out.println("No existen registros para eliminar");
                    }else{
                        do{
                            System.out.println("Que registro desea eliminar:");
                            mostrarRegistros();
                            opcion = entrada.nextInt();
                            if(regis.size()>1)
                                validar = validarOpcion(validar, (regis.size()-1), opcion);
                            else
                                validar = validarOpcion(validar, (regis.size()), opcion);
                            if(validar==true){
                                regis.remove(opcion-1);
                                r.guardarDatos("ControlDeResgistros.txt", regis);
                            }
                        }while(validar!=true);
                    }

                    validar=false;
                break;
                case 4:
                    validar = true;
                break;
                default:
                    System.out.println("Opcion invalida, por favor vuelva a intentarlo\n");
                    validar=false;
                break;
            }
        }while(validar!=true);
    }

    //Metodo que permite selecionar el servicio realizado
    public static Servicio selecionarServicio(){
        Servicio servicio = new Servicio();//Se crea un objeto Servicio vacio
        int opcion;
        boolean validar = false;
        String servicios[] = {"Corte", "Tinte", "Rayitos", "Mechas", "Permanentes", "Alaciados", "Peinados", "Depilacion", "Planchado de cejas", "Rizado de pestanias", "Extension de pestanias", "Maquillaje"};
        //Ciclo que valida y permite seleccionar el servicio
        do{
            System.out.println("Por favor seleccione el servicio realizado");
            //Impresion de los servicios
            for(int i=0; i<servicios.length; i++){
                System.out.println((i+1)+". "+servicios[i]);
            }
            opcion = entrada.nextInt();
            validar = validarOpcion(validar, servicios.length, opcion);
            if(validar==true)
                servicio = selecionarExtrasServicio(servicios[opcion-1], opcion);//El objeto servicio almacenara lo que retorne el metodo seleccionarExtrasServicio
        }while(validar!=true);
        return servicio;//Retorna servicio
    }

    //Metodo que permite seleccionar caracteristicas adicionales para ciertos servicios asi como el calculo del costo
    public static Servicio selecionarExtrasServicio(String nombreServicio, int opcionT){
        Servicio servicio = new Servicio();//Se crea un objeto Servicio vacio
        int opcionCabC=0, opcionCabL=0, fase, tono=0, capa=0;//Variables locales
        boolean validar = false;
        double costo=0;
        String mens1="", mens2="";
        String tonoBase[] = {"Negro", "Negro Azulado", "Castanio Oscuro", "Castanio Medio", "Castanio Claro", "Rubio Oscuro", "Rubio Medio", "Rubio Claro", "Rubio Clarisimo", "Rubio Extra Claro"};
        String capaAdicional[] = {"Cenizo", "Nacarado", "Dorado", "Cobrizo", "Caoba", "Rojo", "Marron"};
        if(opcionT >=2 && opcionT<=4){//Evalua si la opcion de servicio seleccionado por el usuario se encuentra entre algunas de las opciones que implican una decoloracion
            Decoloracion deco = new Decoloracion(nombreServicio, costo, opcionCabC, opcionCabL, tono, capa);//Se crea un objeto tipo Decoloracion
            do{//Ciclo que captura informacion proveniente de evaluarCabello, esta informacion se requiere para el calculo del costo total
                opcionCabC = evaluarCabello(nombreServicio, 0, fase=1, validar);//Almacena la cantidad de cabello
                opcionCabL = evaluarCabello(nombreServicio, opcionCabC, fase=2, validar);//Almacena el largo del cabello
                validar = true;
            }while(validar!=true);
            //Se almacenan los datos en el objeto deco
            deco.setCantidad(opcionCabC);
            deco.setLargo(opcionCabL);
            if(nombreServicio=="Tinte"){//Si se escoge como servicio Tinte, se almacenan ciertas caracteristicas 
                fase=1;
                tono = caracteristicasServicio(fase, tonoBase, tonoBase.length, validar, nombreServicio);
                fase=3;
                capa = caracteristicasServicio(fase, capaAdicional, capaAdicional.length, validar, nombreServicio);
                deco.setCosto(costo=deco.asignarCoste(nombreServicio));//El calculo del coste es en funcion al tipo de servicio
                //Almacenamiento de mensajes en variable String para su posterior concatenacion
                mens1 = " Tonalidad usada: ";
                mens2= ".";
            }else{//Si no se escogio tinte, pero sigue siendo una decoloracion se pregunta si aunado a ese servicio se realiza un tinte
                System.out.println("Se realizo "+nombreServicio+" con un tinte\n1. Si\n2. No");
                opcionT = entrada.nextInt();
                validar = validarOpcion(validar, 2, opcionT);
                if(opcionT==1){//Segun la opcion de si se realizo o no un tinte se capturan los siguientes datos
                    fase=1;
                    tono = caracteristicasServicio(fase, tonoBase, tonoBase.length, validar, nombreServicio);
                    fase=2;
                    capa = caracteristicasServicio(fase, capaAdicional, 3, validar, nombreServicio);
                    deco.setCosto(deco.costoAdicionalTinte(costo=deco.asignarCoste(nombreServicio)));//El calculo del coste es en funcion al tipo de servicio
                    //Almacenamiento de mensajes en variable String para su posterior concatenacion
                    mens1 = " con tinte de tonalidad ";
                    mens2 = " y un matiz del 10.";
                }else{
                    fase=2;
                    capa = caracteristicasServicio(fase, capaAdicional, 3, validar, nombreServicio);
                    tono = 10;
                    deco.setCosto(costo=deco.asignarCoste(nombreServicio));//El calculo del coste es en funcion al tipo de servicio
                    //Almacenamiento de mensajes en variable String para su posterior concatenacion
                    mens1 = " con un matiz del ";
                    mens2 = ".";
                }
            }
            deco.setTipo(nombreServicio);
            deco.setTonoBase(tono);
            deco.setCapaAdicional(capa);
            servicio = new Servicio((deco.getTipo()+mens1+deco.getTonoBase()+mens2+deco.getCapaAdicional()), deco.getCosto());
        }else if(nombreServicio=="Peinados" || nombreServicio=="Alaciados" || nombreServicio=="Permanentes"){
            Peinado peinado = new Peinado(nombreServicio, costo, opcionCabC, opcionCabL);
            do{
                opcionCabC = evaluarCabello(nombreServicio, 0, fase=1, validar);
                opcionCabL = evaluarCabello(nombreServicio, opcionCabC, fase=2, validar);
                validar = true;
            }while(validar!=true);
            peinado.setCantidad(opcionCabC);
            peinado.setLargo(opcionCabL);
            peinado.setCosto(costo=peinado.asignarCoste(nombreServicio));
            servicio = new Servicio(peinado.getTipo(), peinado.getCosto());
        }else{
            switch(nombreServicio){
                case "Corte":
                    costo = 40;
                break;
                case "Depilacion":
                    costo = 250;
                break;
                case "Planchado de cejas":
                    costo = 100;
                break;
                case "Rizado":
                    costo = 200;
                break;
                case "Extension de pestanias":
                    costo = 200;
                break;
                case "Maquillaje":
                    costo = 100;
                break;
            }
            servicio = new Servicio(nombreServicio, costo);
        }
        return servicio;
    }

    public static int evaluarCabello(String nombreServ, int opcionC, int fase, boolean validar){//Metodo que obtiene el la informacion del cabello segun lo solicitado
        int op;
        String volumen[][] = {{"Poco", "$100", "$160", "$50"}, {"Mucho", "$150", "$280", "100"}};
        String largo[][]= {{"Corto", "$50", "$140", "$50"},{"Mediano", "$100", "$230", "$125"},{"Largo", "$150", "$320", "$200"}};
        do{//Ciclo que valida y captura la opcion escogida por el usuario
            //Imprime distintos valores segun el servicio que se solicite
            if(fase==1){
                System.out.println("Que cantidad de cabello tenia el/la cliente?");
                if(nombreServ=="Tinte")
                    System.out.println("1. "+volumen[0][0]+" "+volumen[0][1]+"\n2. "+volumen[1][0]+" "+volumen[1][1]);
                else if(nombreServ=="Peinados" || nombreServ=="Alaciados")
                    System.out.println("1. "+volumen[0][0]+" "+volumen[0][3]+"\n2. "+volumen[1][0]+" "+volumen[1][3]);
                else
                    System.out.println("1. "+volumen[0][0]+" "+volumen[0][2]+"\n2. "+volumen[1][0]+" "+volumen[1][2]);
            }else{
                System.out.println("Que largo tenia el cabello el/la cliente?");
                if(nombreServ=="Tinte"){
                    if(opcionC==1)
                        System.out.println("1. "+largo[0][0]+" "+largo[0][1]+"\n2. "+largo[1][0]+" "+largo[1][1]);
                    else
                        System.out.println("1. "+largo[1][0]+" "+largo[1][1]+"\n2. "+largo[2][0]+" "+largo[2][1]);
                }else if(nombreServ=="Peinados" || nombreServ=="Alaciados"){
                    if(opcionC==1)
                        System.out.println("1. "+largo[0][0]+" "+largo[0][3]+"\n2. "+largo[1][0]+" "+largo[1][3]);
                    else
                        System.out.println("1. "+largo[1][0]+" "+largo[1][3]+"\n2. "+largo[2][0]+" "+largo[2][3]);
                }else{
                    if(opcionC==1)
                        System.out.println("1. "+largo[0][0]+" "+largo[0][2]+"\n2. "+largo[1][0]+" "+largo[1][2]);
                    else
                        System.out.println("1. "+largo[1][0]+" "+largo[1][2]+"\n2. "+largo[2][0]+" "+largo[2][2]);
                } 
            }
            op = entrada.nextInt();
            validar = validarOpcion(validar, 2, op);
            if(fase==2 && opcionC==2)
                op+=1;
        }while(validar!=true);
        return op;//Retorna la opcion escogida
    }

    public static int caracteristicasServicio(int fase, String[] tonalidad, int longitud, boolean validar, String nombreServicio){//IMprime un mensaje segun el tipo de servicio y las opciones escogidas por el usuario
        int valor, opcion;
        do{
            switch(fase){
                case 1:
                    System.out.println("Selecione el tono base del tinte para el servicio de(l@s)"+nombreServicio);
                break;
                case 2:
                    System.out.println("Selecione el matiz del(l@s) "+nombreServicio);
                break;
                case 3:
                    System.out.println("Selecione el reflejo del(l@s) "+nombreServicio);
                break;
            }
            mostrarTonalidades(tonalidad, longitud);//Se muestran las tonalidades y demas caracteristicas segun ciertos criterios
            opcion = entrada.nextInt();
            validar = validarOpcion(validar, longitud, opcion);
            valor = opcion;
        }while(validar!=true);
        return valor;//Retorna el valor de la opcion escogida simpre y cuando esta sea valida
    }

    public static Cliente resgistarDatosCliente(boolean validar){
        Cliente cliente = new Cliente();
        String nombre;
        long numeroTelefono;
        System.out.println("Registrar Datos Cliente");
        System.out.print("\nIngrese el nombre del cliente: ");
        entrada.nextLine();
        nombre = entrada.nextLine();
        do{
            System.out.print("Ingrese el numero de telefono del cliente: ");
            numeroTelefono = entrada.nextLong();
            double max = Math.pow(10, 10);
            if(numeroTelefono>999999999 && numeroTelefono<max){
                cliente = new Cliente(nombre, numeroTelefono);
                validar = true;
            }else{
                System.out.println("Numero invalido, vuelva a ingresarlo");
                validar = false;
            }
        }while(validar!=true);
        return cliente;
    }

    public static Registro guardarDatosServicio(Servicio serv, Cliente c){
        Calendar fecha = Calendar.getInstance();
        Registro registro = new Registro(fecha.getTime().toString(), c.getNombre(), c.getTelefono(), serv.getTipo(), serv.getCosto());
        return registro;
    }

    public static void mostrarTonalidades(String tonalidades[], int longitud){//Imprime las tonalidades
        for(int i=0; i<longitud; i++){
            System.out.println((i+1)+" "+tonalidades[i]);
        }
    }

    public static void mostrarRegistros(){//Metodo que visualiza los registros
        //Un ciclo que imprime el contenido del ArrayList
        for(int i=0; i<regis.size(); i++){
            System.out.println((i+1)+".- "+regis.get(i));
        }
        System.out.println();
    }

    public static boolean validarOpcion(boolean validar, int max, int opcion){
        if(opcion>=1 && opcion <=max){
            validar = true;
        }else{
            validar = false;
            System.out.println("Opcion invalida, por favor vuelva a intentarlo\n");
        }
        return validar;
    }  
}