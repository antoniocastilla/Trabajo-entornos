package trabajo.entornos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Printer implements Printable {

    private ArrayList<Producto> alp = new ArrayList<Producto>();
    private double total, pago, vuelta;
    private int idTicket;
    private boolean seLeHaPasadoElidTicket;

    public Printer(ArrayList<Producto> alp, double total, double pago, double vuelta) {
        this.alp = alp;
        this.total = total;
        this.pago = pago;
        this.vuelta = vuelta;
        MySQL.conecta("pepe", "pepa");
        seLeHaPasadoElidTicket = false;

    }

    public Printer(ArrayList<Producto> alp, double total, double pago, double vuelta, int idTicketUltimo) {
        this.alp = alp;
        this.total = total;
        this.pago = pago;
        this.vuelta = vuelta;
        MySQL.conecta("pepe", "pepa");
        idTicket = idTicketUltimo;
        seLeHaPasadoElidTicket = true;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int Index) throws PrinterException {
        if (Index > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        int x = 50;
        int y = 50;
        int x0 = 50;
        int iY = 20;
        
        if (!seLeHaPasadoElidTicket) {
            Object dato = MySQL.getUltimoDatoIndividual("select idTicket from ticket;");
            idTicket = (int) dato;
        }
        Object dato = MySQL.getUltimoDatoIndividual("select fecha from ticket;");
        Date f1 = (Date) dato;
        g.drawString("Factura: ", x, y);
        g.drawString("IdTicket: " + idTicket, x + 350, y);
        y += iY;
        g.drawLine(x, y, x + 500, y);
        y += iY;
        g.drawString("Productos: ", x, y);
        y += iY;
        for (int i = 0; i < alp.size(); i++) {
            g.drawString(alp.get(i).getNombre() + ":", x, y);
            y += iY;
            x += 20;
            g.drawString("Unidades: " + alp.get(i).getUnidades(), x, y);
            x += 200;
            g.drawString("PPU: " + dameTruncado(alp.get(i).getPpu()), x, y);
            x += 200;
            g.drawString("Total: " + dameTruncado((alp.get(i).getUnidades() * alp.get(i).getPpu())), x, y);
            x = x0;
            y += iY;
        }
        g.drawLine(x, y, x + 500, y);
        y += iY;
        g.drawString("Total: " + total + "€", x, y);
        y += iY;
        g.drawString("Ha pagado con: " + pago + "€", x, y);
        y += iY;
        g.drawString("Se le han devuelto: " + vuelta + "€", x, y);
        y += iY;
        g.drawString("Fecha: " + f1.toString(), x, y);

        return PAGE_EXISTS;
    }
    
    private double dameTruncado(double totalSinTruncar) {
        return (double) Math.round(totalSinTruncar * 100d) / 100d;

    }

}
