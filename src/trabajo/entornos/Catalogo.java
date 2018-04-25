
package trabajo.entornos;
//pruebaa
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Catalogo extends JDialog{
    
    private JPanel jp1;
    private JTextArea jt1;
    private JButton compra,quitar,carrito,volver;
    private JLabel catalog;
    private MySQL db;
    
    public Catalogo(){
        
        this.setLayout(null);
        
        jp1 = new JPanel(new GridLayout());
        jt1 = new JTextArea();
        jt1.setEditable(false);
        catalog = new JLabel("Catálogo: ");
        compra = new JButton("Compra");
        quitar = new JButton("Quitar Producto");
        carrito = new JButton("Ver carrito");
        volver = new JButton("Volver");
        db = new MySQL();
        
        jp1.add(jt1);
        //jt1.setText(db.database());
        
        this.add(jp1);
        this.add(catalog);
        this.add(compra);
        this.add(quitar);
        this.add(carrito);
        this.add(volver);
        
        jp1.setBounds(20, 100, 400, 300);
        catalog.setBounds(20,50, 100, 20);
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
        this.setSize(500, 550);
        this.setVisible(true);
    }
}
