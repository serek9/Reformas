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

    public boolean existeCliente(String num){
        boolean existe = false;
        for (Cliente c:lista){
            if (c.getTelefono().equalsIgnoreCase(num)){
                existe = true;
            }
        }
        return existe;
    }
    
    public Cliente obtenerClientePorTelefono(String telefono) {
        for (Cliente c : lista) {
            if (c.getTelefono().equalsIgnoreCase(telefono)) {
                return c;
            }
        }
        return null;
    }
    
    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

}
