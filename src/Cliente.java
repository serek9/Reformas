import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable {
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean vip;
    private List presupuestos;

    public Cliente() {

    }

    public Cliente(String nombre, String apellidos, String telefono, boolean vip) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.vip = vip;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return apellidos;}

    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public boolean isVip() {return vip;}

    public void setVip(boolean vip) {this.vip = vip;}

    public List getPresupuestos() {return presupuestos;}

    public void setPresupuestos(List presupuestos) {this.presupuestos = presupuestos;}

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", vip=" + vip +
                ", presupuestos=" + presupuestos +
                '}';
    }
}
