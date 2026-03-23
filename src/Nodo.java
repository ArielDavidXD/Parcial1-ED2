import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String nombre;
    private String tipoArchivo;
    private Nodo padre;
    private List<Nodo> hijos;

    public Nodo (String nombre, String tipoArchivo){
        this.nombre = nombre;
        this.tipoArchivo= tipoArchivo;
        this.hijos = new ArrayList<>();
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }
    public void agregarHijo(String nombre, String tipoArchivo){
        Nodo nuevo = new Nodo(nombre, tipoArchivo);
        nuevo.setPadre(this);
        hijos.add(nuevo);
    }
}
