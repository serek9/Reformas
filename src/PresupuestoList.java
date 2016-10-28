import java.io.Serializable;
import java.util.ArrayList;

public class PresupuestoList implements Serializable {
    private ArrayList<Presupuesto> lista;

    public PresupuestoList(){ lista = new ArrayList<>();}

    public void alta(Presupuesto p){lista.add(p);}

    public ArrayList<Presupuesto> getLista() {return lista;}

    public void setLista(ArrayList<Presupuesto> lista) {this.lista = lista;}

    public Presupuesto obtenerPresupuestoPorCodigo(int codigo){
        for (Presupuesto p:lista){
            if (p.getCodigo()==codigo){
                return p;
            }
        }
        return null;
    }

    public boolean existePresupuesto(int num){
        boolean existe = false;
        for (Presupuesto p:lista){
            if (p.getCodigo()==num){
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public String toString() {
        return "PresupuestoList{" +
                "lista=" + lista +
                '}';
    }
}
