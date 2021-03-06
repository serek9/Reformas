public class Main {

    private static ClienteList misClientes;
    private static Fichero miFichero;
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
                        altaPresupuesto();
                        break;
                    case 3:
                        presPendientes();
                        break;
                    case 4:
                        presCliente();
                        break;
                    case 5:
                        presRechazados();
                        break;
                    case 6:
                        showClientes();
                        break;
                    case 7:
                        cambiarEstado();
                        break;
                    case 8:
                        System.out.println("Has salido.");
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
        System.out.println("ALTA CLIENTE.");
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
        boolean telexis = misClientes.existeCliente(telefono);
        if (!telexis){
            Cliente c = new Cliente(nombre, apellido, telefono, vip);
            misClientes.alta(c);
            miFichero.grabar(misClientes);
            System.out.println("Cliente dado de alta.");
        }
        if (telexis){
            System.out.println("Error. El telefono ya existe.");
        }
    }

    public static void altaPresupuesto(){
        System.out.println("REGISTRAR PRESUPUESTO.");
        String numCliente  = EntradaDatos.pedirCadenaNoVacia("Introduce telefono del cliente: ");
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
            boolean codexis = cliente.getPresupuestos().existePresupuesto(codigo);
            if (!codexis){
                Presupuesto p = new Presupuesto(codigo, concepto, precio, estado);
                cliente.getPresupuestos().alta(p);
                miFichero.grabar(misClientes);
                System.out.println("Presupuesto registrado.");
            }
            if (codexis){
                System.out.println("Error. El codigo del presupuesto ya existe.");
            }
        }else {
            System.out.println("El cliente no existe, no se puede añadir presupuesto.");
        }
    }

    public static void presPendientes(){
        System.out.println("PRESUPUESTOS PENDIENTES.");
        for (Cliente c:misClientes.getLista()) {
            for (Presupuesto p : c.getPresupuestos().getLista()) {
                if (p.getEstado().equalsIgnoreCase("pendiente")) {
                    System.out.println(c.getNombre()+" "+c.getApellidos()+" --> "+p);
                }
            }
        }
    }

    public static void presCliente(){
        System.out.println("PRESUPUESTOS.");
        String numCliente  = EntradaDatos.pedirCadenaNoVacia("Introduce telefono del cliente: ");
        boolean existe = misClientes.existeCliente(numCliente);
        Cliente cliente = misClientes.obtenerClientePorTelefono(numCliente);

        if (existe){
            System.out.println("Presupuestos de "+cliente.getNombre()+"("+cliente.getTelefono()+")");
            System.out.println(cliente.getPresupuestos().getLista());
        }else {
            System.out.println("El cliente no existe.");
        }
    }

    public static void presRechazados(){
        System.out.println("PRESUPUESTOS RECHAZADOS.");
        boolean entr = false;
        for (Cliente c:misClientes.getLista()) {
            for (Presupuesto p : c.getPresupuestos().getLista()) {
                if (p.getEstado().equalsIgnoreCase("rechazado")) {
                    System.out.println(c.getNombre()+" "+c.getApellidos()+" --> "+p);
                    entr = true;
                }
                if (!entr){
                    System.out.println("No hay presupuestos rechazados.");
                }
            }
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

    private static void cambiarEstado(){
        System.out.println("CAMBIAR ESTADO PRESUPUESTO.");
        String estado;
        boolean pendiente = false;
        boolean valido = false;
        String numCliente  = EntradaDatos.pedirCadenaNoVacia("Introduce telefono del cliente: ");
        boolean existe = misClientes.existeCliente(numCliente);
        Cliente cliente = misClientes.obtenerClientePorTelefono(numCliente);
        if (existe){
            int numPres  = EntradaDatos.pedirEntero("Introduce codigo del presupuesto: ");
            boolean existe1 = cliente.getPresupuestos().existePresupuesto(numPres);
            Presupuesto presupuesto = cliente.getPresupuestos().obtenerPresupuestoPorCodigo(numPres);
            if (existe1){
                do{
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
                }while (!valido && !pendiente);
                    presupuesto.setEstado(estado);
                    miFichero.grabar(misClientes);
                System.out.println("Estado del presupuesto modificado.");
            }else {
                System.out.println("No existe el presupuesto.");
            }
        }else{
            System.out.println("No existe el cliente.");
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
