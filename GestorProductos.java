package tarea;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GestorProductos {

	private static final String ARCHIVO_JSON = "base_de_datos_productos.json";
    private static final Gson gson = new Gson();
    private static final Scanner entrada = new Scanner(System.in);

    public void ejecutar() {
        try {
            while (true) {
                System.out.print("Elige una opción:");
                System.out.println();
                System.out.println("1. Escribir en archivo JSON");
                System.out.println("2. Leer archivo JSON");
                System.out.println("3. Salir");
                int opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1:
                        escribirEnJson();
                        break;
                    case 2:
                        leerDeJson();
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        } finally {
            entrada.close(); // Cerrar Scanner
        }
    }
    
    
    // Escribir en Archivo
    private void escribirEnJson() {
        List<Producto> productos = leerProductosExistentes();
        boolean agregarOtro = true;

        while (agregarOtro) {
            Producto producto = crearProducto();
            productos.add(producto);

            System.out.println("¿Deseas agregar otro producto? (s/n)");
            String respuesta = entrada.nextLine();
            agregarOtro = respuesta.equalsIgnoreCase("s");
        }

        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(productos, writer);
            System.out.println("Productos guardados en " + ARCHIVO_JSON);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    

    private List<Producto> leerProductosExistentes() {
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaProductos = new TypeToken<List<Producto>>() {}.getType();
            List<Producto> productos = gson.fromJson(reader, tipoListaProductos);
            return productos != null ? productos : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    
    private Producto crearProducto() {
        String id = obtenerEntradaValida("ID (solo números): ", "\\d+");
        String nombre = obtenerEntradaValida("Nombre: ", "[a-zA-Z]+");
        String estado = obtenerEntradaValida("Estado (produccion o terminado): ", "produccion|terminado");
        String origen = obtenerEntradaValida("Origen (local o importado): ", "local|importado");
        double precio = Double.parseDouble(obtenerEntradaValida("Precio (Q): ", "\\d+(\\.\\d+)?"));

        return new Producto(id, nombre, estado, origen, precio);
    }

    
    private String obtenerEntradaValida(String mensaje, String regex) {
        String entradaValida;
        while (true) {
            System.out.print(mensaje);
            String entrada = GestorProductos.entrada.nextLine().toLowerCase(); // Convertir a minúsculas
            if (entrada.matches(regex)) {
                entradaValida = entrada;
                break;
            } else {
                System.out.println("Entrada no válida. Inténtalo de nuevo.");
            }
        }
        return entradaValida;
    }
    
    // Leer Archivo
    private void leerDeJson() {
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaProductos = new TypeToken<List<Producto>>() {}.getType();
            List<Producto> productos = gson.fromJson(reader, tipoListaProductos);

            if (productos != null) {
                for (Producto producto : productos) {
                    System.out.println(producto);
                }
            } else {
                System.out.println("No hay productos en la base de datos.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}