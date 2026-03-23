import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GestorDeArchivos gestor = new GestorDeArchivos("C:", "carpeta");

        gestor.insertar("C:", "Documentos", "carpeta");
        gestor.insertar("C:", "Imagenes", "carpeta");
        gestor.insertar("Documentos", "tarea.txt", "archivo");
        gestor.insertar("Documentos", "proyecto.docx", "archivo");

        gestor.insertar("Imagenes", "foto.png", "archivo");
        gestor.insertar("Imagenes", "wallpaper.jpg", "archivo");

        System.out.println("----- ARBOL DE ARCHIVOS -----");
        gestor.dfsRe(gestor.getRaiz());

        // modificar archivo
        System.out.println("\n----- MODIFICAR -----");
        gestor.modificar("tarea.txt", "tarea_final.txt", "archivo");
        gestor.dfsRe(gestor.getRaiz());

        // eliminar archivo
        System.out.println("\n------ ELIMINAR -------");
        gestor.eliminar("foto.png");
        gestor.dfsRe(gestor.getRaiz());

        // mostrar direccion
        System.out.println("\n----- DIRECCION ARCHIVO -----");
        System.out.println(gestor.mostrarDireccionArchivo("tarea_final.txt").reversed());

        gestor.MoverArchivo("wallpaper.jpg", "Documentos");
        gestor.dfsRe(gestor.getRaiz());

        gestor.CopiarArchivo("wallpaper.jpg", "Imagenes");
        gestor.dfsRe(gestor.getRaiz());
    }

}