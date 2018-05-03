
package trabajo.entornos;


public class Producto {
    private int id;
    private String nombre;
    private int unidades;
    private double ppu;

    public Producto(String nombre, int unidades, double ppu) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.ppu = ppu;
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
    
}
