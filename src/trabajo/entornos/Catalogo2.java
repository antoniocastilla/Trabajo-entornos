/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo.entornos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prg
 */
public class Catalogo2 extends javax.swing.JDialog {

    /**
     * Creates new form Catalogo2
     */
    DefaultTableModel modelo;
    ArrayList<Producto> alProductos;
    int altoSizeCarrito;

    public Catalogo2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        alProductos = new ArrayList<Producto>();
        rellenaTabla();
        initComponents();
        
        altoSizeCarrito = jScrollPane2.getHeight();
        System.out.println("alto mide: "+altoSizeCarrito);
        
        this.setVisible(true);
        

    }

    private void actualizaPanelCarrito() {

        pnCarrito.removeAll();
        pnCarrito.setVisible(false);
        pnCarrito.setVisible(true);

        for (Producto x : alProductos) {

            String nombreTruncado = x.getNombre();
            //if (x.getNombre().length() >20)
            //nombreTruncado = x.getNombre().substring(0, 20);

            JLabel lbNombre = new JLabel(nombreTruncado);
            JLabel lbUnidades = new JLabel(String.valueOf(x.getUnidades()));
            double precio = x.getPpu() * x.getUnidades();
            precio = (double) Math.round(precio * 100d) / 100d;
            JLabel lbPrecio = new JLabel(String.valueOf(precio));

            pnCarrito.add(lbNombre);
            pnCarrito.add(lbUnidades);
            pnCarrito.add(lbPrecio);
        }
        
        if (altoSizeCarrito < 400){
        altoSizeCarrito = altoSizeCarrito+40;
        //jScrollPane2.setPreferredSize(new Dimension(300, altoSizeCarrito));
        jScrollPane2.setSize(400, altoSizeCarrito);
        } else{
            altoSizeCarrito+=20;
            pnCarrito.setPreferredSize(new Dimension(300, altoSizeCarrito));
        }

    }

    private void rellenaTabla() {

        MySQL.conecta("pepe", "pepa");
        Object[][] datos = MySQL.getDatos("select idProducto,nombre,cantidad,"
                + "ppu,categoria  from producto;");
        Object[] cabecera = MySQL.getCabecera();

        modelo = new DefaultTableModel(datos, cabecera) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //jTabla1.setModel(modelo);
        //jTabla1.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbCategorias));
        //jTabla1.getModel().addTableModelListener(eventomodelo);
        //clases = MySQL.getClases(tabla);
        //MySQL.cierra();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnCarrito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla1 = new javax.swing.JTable();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(83, 94, 165));

        jScrollPane2.setBackground(new java.awt.Color(255, 102, 102));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 100));

        pnCarrito.setBackground(new java.awt.Color(83, 94, 165));
        pnCarrito.setAutoscrolls(true);
        pnCarrito.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        pnCarrito.setMaximumSize(null);
        pnCarrito.setMinimumSize(null);
        pnCarrito.setPreferredSize(new java.awt.Dimension(400, 170));
        pnCarrito.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane2.setViewportView(pnCarrito);

        jTabla1.setAutoCreateRowSorter(true);
        jTabla1.setBackground(new java.awt.Color(129, 152, 180));
        jTabla1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTabla1.setModel(modelo);
        jTabla1.setCellSelectionEnabled(true);
        jTabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabla1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabla1MouseClicked
        // TODO add your handling code here:
        String[] productoAux = new String[jTabla1.getColumnCount()];
        int row = jTabla1.rowAtPoint(evt.getPoint());
        int col = jTabla1.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            for (int i = 0; i < jTabla1.getColumnCount(); i++) {
                productoAux[i] = jTabla1.getModel().getValueAt(row, i).toString();
            }

            System.out.println("ha entrao");

            boolean contiene = false;

            if (alProductos != null) {
                for (Producto x : alProductos) {
                    if (x.getId() == Integer.parseInt(productoAux[0].toString())) {
                        contiene = true;
                        break;
                    }
                }
            }

            for (int j = 0; j < productoAux.length; j++) {
                System.out.println("i: " + j + "\t" + productoAux[j]);
            }

            if (!contiene) {
                Producto producto = new Producto();
                producto.setId(Integer.parseInt(productoAux[0].toString()));
                producto.setNombre(productoAux[1]);
                producto.setUnidades(1);
                producto.setPpu(Double.parseDouble(productoAux[3].toString()));
                producto.setCategoria(productoAux[4].toString());
                alProductos.add(producto);
            } else {

                for (Producto x : alProductos) {
                    if (x.getId() == Integer.parseInt(productoAux[0].toString())) {
                        x.setUnidades(x.getUnidades() + 1);
                    }
                }
            }

            actualizaPanelCarrito();

            //producto.setUnidades(Integer.parseInt(productoAux[2]));
            //System.out.println(productoAux[i]);
        }
    }//GEN-LAST:event_jTabla1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Catalogo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catalogo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catalogo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catalogo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Catalogo2 dialog = new Catalogo2(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabla1;
    private javax.swing.JPanel pnCarrito;
    // End of variables declaration//GEN-END:variables
}
