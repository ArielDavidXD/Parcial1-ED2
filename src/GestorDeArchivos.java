import java.util.ArrayList;


public class GestorDeArchivos {
    private Nodo raiz;

    public GestorDeArchivos(String nombre, String tipoArchivo){
        raiz =new Nodo(nombre, tipoArchivo);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(String padre, String nombre, String tipoArchivos) {
        Nodo nodoPadre = buscar(raiz, padre);
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(nombre, tipoArchivos);
        } else {
            System.out.println("padre no encontrado");
        }
    }

    public Nodo buscar(Nodo actual, String nombre){
        if(actual==null){
            return null;
        }
        if(actual.getNombre().equalsIgnoreCase(nombre)){
            return actual;
        }
        for(Nodo hijos: actual.getHijos()){
            Nodo res = buscar(hijos, nombre);
            if(res!=null){
                return res;
            }
        }
        return null;
    }

    public boolean modificar( String nombreViejo, String datoNuevo, String tipoArchivoNuevo) {
        Nodo nodo = buscar(raiz, nombreViejo);
        if (nodo != null) {
            nodo.setNombre(datoNuevo);
            nodo.setTipoArchivo(tipoArchivoNuevo);
            return true;
        }
        return false;
    }

    public boolean eliminar(String nombre){
        Nodo nodo = buscar(raiz, nombre);
        if(nodo==null){
            return false;
        }
        if(raiz==null){
            return false;
        }
        if(raiz.getNombre().equalsIgnoreCase(nombre)){
            raiz=null;
            return true;
        }
        return eliminarRecursivo(raiz, nombre);
    }

    private boolean eliminarRecursivo(Nodo actual, String nombre) {
        if (actual == null) return false;
        for (int i = 0; i < actual.getHijos().size(); i++) {
            Nodo hijo = actual.getHijos().get(i);
            if (hijo.getNombre().equals(nombre)) {
                actual.getHijos().remove(i);
                return true;
            } else {
                if (eliminarRecursivo(hijo, nombre)) return true;
            }
        }
        return false;
    }

    public ArrayList<String> mostrarDireccionArchivo(String nombre){
        Nodo nodo = buscar(raiz, nombre);
        ArrayList<String> lista = new ArrayList<>();
        if(nodo!=null){
        while(nodo!=null){
            lista.add(nodo.getNombre());
            nodo = nodo.getPadre();
        }
        }
        return lista;
    }

    public void dfsRe(Nodo actual) {
        if (actual == null) {
            return;
        }
        System.out.println(actual.getNombre());
        for (Nodo hijo : actual.getHijos()) {
            dfsRe(hijo);
        }
    }

    public void CopiarArchivo(String nombreArchivo, String nombreCarpetaNueva){
        Nodo nodo = buscar(raiz, nombreArchivo);
        Nodo nuevo = buscar(raiz, nombreCarpetaNueva);
        nuevo.getHijos().add(nodo);
    }


    public void MoverArchivo(String nombreArchivo, String nombreCarpetaNueva){
        Nodo nodo = buscar(raiz, nombreArchivo);
        Nodo padre = nodo.getPadre();
        if(padre != null){
            padre.getHijos().remove(nodo);
            nodo.setPadre(null);
        }

        Nodo nuevo = buscar(raiz, nombreCarpetaNueva);
        nuevo.getHijos().add(nodo);
    }
}
