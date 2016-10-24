/*
 * Incluye el ArrayList<Game> para poder serializarlo
 * y grabarlo en fichero como un único objeto JavaBean
 */

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteList implements Serializable {
    
    private ArrayList<Cliente> lista;

    public ClienteList() {
        lista = new ArrayList<>();
    }
    
    public void alta(Cliente c) {
        lista.add(c);
    }
    
    public void baja(Cliente c) {
        lista.remove(c);
    }
    
    public Cliente obtenerClientePorTelefono(String telefono) {
        // REcorremos la lista
        for (Cliente c : lista) {
            // Si el telefono del cliente actual es igual al telefono q me pasan
            if (c.getTelefono().equalsIgnoreCase(telefono)) {
                // devolvemos el cliente encontrado
                return c;
            }
        }
        // Si llega a este punto es que no ha encontrado 
        // ningún cliente con el telefono pasado
        return null;
    }
    
    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

}
