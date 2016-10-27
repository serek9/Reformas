import java.io.Serializable;
//TODO existePresupuesto y obtenerPresupuestoPorCodigo
public class Presupuesto implements Serializable{
    private int codigo;
    private String concepto;
    private double precio;
    private String estado;

    public Presupuesto() {

    }

    public Presupuesto(int codigo, String concepto, double precio, String estado) {
        this.codigo = codigo;
        this.concepto = concepto;
        this.precio = precio;
        this.estado = estado;
    }

    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getConcepto() {return concepto;}
    public void setConcepto(String concepto) {this.concepto = concepto;}

    public double getPrecion() {return precio;}
    public void setPrecion(double precion) {this.precio = precion;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "Presupuesto{" +
                "codigo=" + codigo +
                ", concepto='" + concepto + '\'' +
                ", precion=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }
}
