
package trabajo.entornos;


public class Producto {
    private int id;
    private String nombre;
    private int unidades;
    private double ppu;
    private int filaCarrito;

    public Producto(int id, String nombre, int unidades, double ppu) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
        this.ppu = ppu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "ID: "+id+"\tNombre: "+nombre+"\t Unidades: "+unidades+"\tPPU: "+ppu;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public int getFilaCarrito() {
        return filaCarrito;
    }

    public void setFilaCarrito(int filaCarrito) {
        this.filaCarrito = filaCarrito;
    }
    
}
