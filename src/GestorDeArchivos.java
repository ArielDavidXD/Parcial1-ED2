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
        //creamos nuevo nodo q sera el resultado de la busqueda recursiva
        //q empezara desde la raiz hasta el padre q digamos
        Nodo nodoPadre = buscar(raiz, padre);
        //si el nodo es diferente de null,
        if (nodoPadre != null) {
            //utilizando el metodo agregarhijo de la clase nodo,
            //el cual asigna hijos a la lista de hijos del nodo seleccionado
            nodoPadre.agregarHijo(nombre, tipoArchivos);
        } else {
            System.out.println("padre no encontrado");
        }
    }

    //metodo buscar recursivamente
    public Nodo buscar(Nodo actual, String nombre){
        //si el nodo actual es null, retorna null
        if(actual==null){
            return null;
        }
        //ahora si el nombre del nodo actual es igual a pasado por
        //parametro nos retorna dicho nodo
        if(actual.getNombre().equalsIgnoreCase(nombre)){
            return actual;
        }
        //si no, vas a recorrer la lista de hijos de actual
        for(Nodo hijos: actual.getHijos()){
            //creamos un nodo q va a llamar al metodo recursivo pero en vez de
            //empezar por la raiz lo hara por los hijos de actual, y actual sera ahora (hijos)
            Nodo res = buscar(hijos, nombre);
            //si estabusqueda retorna un resultado diferente de null, lo retornamos
            if(res!=null){
                return res;
            }
        }
        //si llega al final sin encontrarlo retornara null
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
        //lo mismo, creamos nodo q tendra el resultado de la busqueda recursiva
        Nodo nodo = buscar(raiz, nombreArchivo);
        //se obtiene el padre de ese nodo
        Nodo padre = nodo.getPadre();
        if(padre != null){
            //si el padre no es null va a eliminar ese nodo de la lista de hijos
            padre.getHijos().remove(nodo);
            //y aqui el padre de ese nodo sera null (ya q el nodo no pertenece a esa carpeta)
            nodo.setPadre(null);
        }
        //creamos nuevo nodo q sera la nueva direccion del eliminado previamente
        Nodo nuevo = buscar(raiz, nombreCarpetaNueva);
        //y lo agregamos como hijo
        nuevo.getHijos().add(nodo);
        //el metodo de copiar es lo mismo pero sin el paso intermedio de eliminar el hijo del padre
    }
}
