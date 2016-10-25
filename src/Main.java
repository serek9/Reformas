import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

    private static ClienteList misClientes;
    private static  Fichero miFichero;
    private static String[] estados = {"aceptado","pendiente","rechazado"};

    public static void main(String[] args) {
        int option = 0;

        miFichero = new Fichero("datos.xml");

        // Cargamos los datos del fichero
        misClientes = (ClienteList) miFichero.leer();
        // Si no existiera el fichero pq no hay datos misClientes es null
        if(misClientes == null) {
            misClientes = new ClienteList();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        mostrarMenu();
        try {
            while (option != 8) {
                option = EntradaDatos.pedirEntero("Elige opcion:");
                mostrarMenu();
                switch (option) {
                    case 1:
                        altaCliente();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        showClientes();
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Opción incorrecta, de 1 a 8.");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void altaCliente(){
        String nombre = EntradaDatos.pedirCadenaNoVacia("Nombre del Cliente: ");
        String apellido = EntradaDatos.pedirCadenaNoVacia("Apellido del Cliente: ");
        String telefono;
        do{
            telefono = EntradaDatos.pedirCadenaNoVacia("Telefono: ");
            if (telefono.length()!=9){
                System.out.println("El telefono debe tener 9 cifras.");
            }
        }while (telefono.length()!=9);
        String respuesta;
        boolean vip = false;
        do {
            respuesta = EntradaDatos.pedirCadena("¿Es VIP (SI/NO)?");
            if (respuesta.equalsIgnoreCase("si")){
                vip = true;
            }
            else if (respuesta.equalsIgnoreCase("no")){
                vip = false;
            }
            else{
                System.out.println("Respuesta no valida, escribe si o no.");
            }
        }while(!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO"));
        Cliente c = new Cliente(nombre, apellido, telefono, vip);
        misClientes.alta(c);
        miFichero.grabar(misClientes);
        System.out.println("Cliente dado de alta.");
    }

    public static void altaPresupuesto(){
        System.out.println("ALTA PRESUPUESTO:");
        String numCliente  = EntradaDatos.pedirCadenaNoVacia("Introduce telefono del cliente: ");
        //PARANOIA "misClientes", "ClienteList".
        boolean existe = misClientes.existeCliente(numCliente);
        Cliente cliente = misClientes.obtenerClientePorTelefono(numCliente);

        if (existe){
            int codigo = EntradaDatos.pedirEntero("Introduce el codigo del presupuesto:");
            String concepto = EntradaDatos.pedirCadenaNoVacia("Introduce el concepto");
            double precio = EntradaDatos.pedirDouble("Introduce el precio neto del presupuesto");
            String estado;
            boolean valido = false, pendiente = false;

            do {
                estado = EntradaDatos.pedirCadenaNoVacia("Estado del presupuesto: [aceptado] [pendiente] [rechazado]");
                for (String a:estados){
                    if (a.equalsIgnoreCase(estado)){
                        valido = true;
                    }
                }
                if (!valido){
                    pendiente = SoN("Estado no valido. ¿Dejar pendiente?");
                    if (pendiente){
                        estado = "pendiente";
                    }
                }
            }while(!valido && !pendiente);

            Presupuesto presupuesto = new Presupuesto(codigo, concepto, precio, estado);
            //TODO Añadir presupuesto y grabar(misPresupuestos??).
        }else {
            System.out.println("El cliente no existe, no se puede añadir presupuesto.");
        }
    }

    public static boolean SoN(String mensaje){
        String respuesta;
        do{
            respuesta = EntradaDatos.pedirCadenaNoVacia(mensaje+" [si][no]");
            if(!respuesta.equalsIgnoreCase("si")&&!respuesta.equalsIgnoreCase("no")){
                System.out.println("Por favor, introduce un valor valido");
            }
        }while(!respuesta.equalsIgnoreCase("si")&&!respuesta.equalsIgnoreCase("no"));
        return respuesta.equals("si");
    }

    private static void showClientes(){
        System.out.println("Listado de clientes en el sistema.");
        for (Cliente c : misClientes.getLista()){
            System.out.println(c);
        }
    }

    public static void mostrarMenu(){
        System.out.println("Opción 1. Alta cliente.");
        System.out.println("Opción 2. Nuevo presupuesto.");
        System.out.println("Opción 3. Presupuestos pendientes.");
        System.out.println("Opción 4. Listado de presupuestos.");
        System.out.println("Opción 5. Listado de presupuestos rechazados.");
        System.out.println("Opción 6. Listados clientes.");
        System.out.println("Opción 7. Cambiar estado de un presupuesto.");
        System.out.println("Opción 8. Salir.");
    }
}
