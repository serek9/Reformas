public class Presupuesto {
    private int codigo;
    private String concepto;
    private double precion;
    private String estado;

    public Presupuesto(int codigo, String concepto, double precion, String estado) {
        this.codigo = codigo;
        this.concepto = concepto;
        this.precion = precion;
        this.estado = estado;
    }

    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getConcepto() {return concepto;}
    public void setConcepto(String concepto) {this.concepto = concepto;}

    public double getPrecion() {return precion;}
    public void setPrecion(double precion) {this.precion = precion;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "Presupuesto{" +
                "codigo=" + codigo +
                ", concepto='" + concepto + '\'' +
                ", precion=" + precion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
