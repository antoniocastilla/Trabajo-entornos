
package trabajo.entornos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Catalogo extends JDialog{
    
    private JTextArea jt1;
    private JButton compra,quitar,carrito,volver;
    private MySQL db;
    private JTable jTabla1;
    
    public Catalogo(){
        
        this.setLayout(null);
        
        jt1 = new JTextArea();
        jt1.setEditable(false);
        compra = new JButton("Compra");
        quitar = new JButton("Quitar Producto");
        carrito = new JButton("Ver carrito");
        volver = new JButton("Volver");
        jTabla1 = new javax.swing.JTable();
        
        this.add(jt1);        
        this.add(compra);
        this.add(quitar);
        this.add(carrito);
        this.add(volver);
        
        compra.setBounds(20, 430, 100, 40);
        quitar.setBounds(120, 430, 100, 40);
        carrito.setBounds(220, 430, 100, 40);
        volver.setBounds(320, 430, 100, 40);
        
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Catalogo.this.setVisible(false);
                
                
            }
        });
        this.setTitle("Papelería - Catálogo");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        this.setVisible(true);
    }
}
