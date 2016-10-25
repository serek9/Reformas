import java.io.Serializable;
import java.util.ArrayList;

public class PresupuestoList implements Serializable {
    private ArrayList<Presupuesto> lista;

    public PresupuestoList(){ lista = new ArrayList<>();}

    public void alta(Presupuesto p){lista.add(p);}

    public ArrayList<Presupuesto> getLista() {return lista;}

    public void setLista(ArrayList<Presupuesto> lista) {this.lista = lista;}
}
