package trabajo.entornos;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Catalogo extends JDialog {

    private JTextArea jt1;
    private JButton compra, quitar, carrito, volver;
    private MySQL db;
    private JTable jTabla1;
    private JScrollPane jScrollPanel1, jScrollPanel2;
    private JPanel pnCarrito;
    private DefaultTableModel modelo;
    private ArrayList alCarrito;
    //

    public Catalogo() {

        this.setLayout(null);

        alCarrito = new ArrayList();
        jt1 = new JTextArea();
        jt1.setEditable(false);
        compra = new JButton("Compra");
        quitar = new JButton("Quitar Producto");
        carrito = new JButton("Ver carrito");
        volver = new JButton("Volver");
        jTabla1 = new javax.swing.JTable();
        jScrollPanel1 = new javax.swing.JScrollPane(jTabla1);
        pnCarrito = new JPanel();
        pnCarrito.setLayout(new GridLayout(0, 4,10,10));
        jScrollPanel2 = new JScrollPane(pnCarrito);

        jScrollPanel1.setBounds(30, 50, 600, 450);
        jScrollPanel2.setBounds(650, 50, 300, 450);
        //jTabla1.setEnabled(false);
        compra.setBounds(20, 600, 100, 40);
        quitar.setBounds(120, 600, 100, 40);
        volver.setBounds(220, 600, 100, 40);

        this.add(compra);
        this.add(quitar);
        this.add(carrito);
        this.add(volver);
        this.add(jScrollPanel1);
        this.add(jScrollPanel2);

        rellenaTabla();
        
        //Panel del Carrito
        JLabel lbProducto = new JLabel("Producto");
        JLabel lbUnid = new JLabel("Unidades");
        JLabel lbDinero = new JLabel("Dinero");
        JLabel lbVacio = new JLabel("");
        alCarrito.add(lbProducto);
        alCarrito.add(lbUnid);
        alCarrito.add(lbDinero);
        alCarrito.add(lbVacio);
        
        for (Object x:alCarrito)
            pnCarrito.add((JLabel)x);
        
        for (int i = 0; i < 400; i++) {
            JLabel aux = new JLabel ("xdd");
            pnCarrito.add(aux);
        }
        

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Catalogo.this.setVisible(false);

            }
        });

        jTabla1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent mouseEvent) {
                
                String[] productoAux = new String[jTabla1.getColumnCount()];
                int row = jTabla1.rowAtPoint(mouseEvent.getPoint());
                int col = jTabla1.columnAtPoint(mouseEvent.getPoint());
                if (row >= 0 && col >= 0) {
                    for (int i = 0; i < jTabla1.getColumnCount(); i++) {
                        productoAux[i]=jTabla1.getModel().getValueAt(row, i).toString();
                    }
                    
                }
                
                meteCarrito(productoAux);
            }
        });

        this.setTitle("Papelería - Catálogo");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        this.setVisible(true);
    }

    private void rellenaTabla() {

//        boolean salida = MySQL.ejecutaConsulta("select idProducto as Codigo,nombre,cantidad"
//                + "ppu,categoria  from producto;");
//        System.out.println("EjecutaConsulta: " + salida);
        //devuelve los datos de la consulta y la cabecera de la tabla
        Object[][] datos = MySQL.getDatos("select idProducto,nombre,cantidad,"
                + "ppu,categoria  from producto;");
        Object[] cabecera = MySQL.getCabecera();

        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {
                System.out.print(datos[i][j] + ",");
            }
            System.out.println("\n");
        }

        JComboBox<String> cbCategorias = new JComboBox<String>();
        for (String x : MySQL.dameCategorias()) {
            cbCategorias.addItem(x);
        }

        modelo = new DefaultTableModel(datos, cabecera) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        jTabla1.setModel(modelo);
        
        //Hay que crear clase Producto + Su contenedor y pasar los datos del
        //JTabla1 a esa clase cada vez que se actualice el JTable.
        
        //jTabla1.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbCategorias));
        //jTabla1.getModel().addTableModelListener(eventomodelo);
        //clases = MySQL.getClases(tabla);

        //MySQL.cierra();
    }
    
    private void meteCarrito(String[] nuevoProducto){
        alCarrito.add(nuevoProducto);
        
        
        
    }

}
