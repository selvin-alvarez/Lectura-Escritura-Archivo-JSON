package tarea;

class Producto {
	
	// Atributos
	private String id;
    private String nombre;
    private String estado;
    private String origen;
    private double precio;

    // Constructor
    public Producto(String id, String nombre, String estado, String origen, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.origen = origen;
        this.precio = precio;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", origen='" + origen + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
