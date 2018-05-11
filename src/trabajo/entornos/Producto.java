
package trabajo.entornos;


public class Producto {
    private int id;
    private String nombre;
    private int unidades;
    private double ppu;
    private String categoria;
    private int filaCarrito;

    public Producto (){
        
    }
    
    public Producto(int id, String nombre, int unidades, double ppu) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
        this.ppu = ppu;
    }

    
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return nombre+"\t\tUnidades: "+unidades+"\tTotal: "+ppu*unidades;
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
        return (double) Math.round(ppu * 100d) / 100d;
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
