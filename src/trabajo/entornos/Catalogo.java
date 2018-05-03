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
    private ArrayList<JLabel[]> alCarrito;
    private ArrayList<Producto> carro;
    private int x0 = 10;
    private int x1 = 20;
    private int y0 = 10;
    private int y1 = 20;
    private int aumentoX1 = 75;
    private int aumentoX2 = 110;
    private int aumentoY = 20;
    private JLabel[] labels;
    private JLabel total;

    public Catalogo() {

        this.setLayout(null);

        alCarrito = new ArrayList<JLabel[]>();
        jt1 = new JTextArea();
        jt1.setEditable(false);
        compra = new JButton("Compra");
        quitar = new JButton("Quitar Producto");
        carrito = new JButton("Ver carrito");
        volver = new JButton("Volver");
        jTabla1 = new javax.swing.JTable();
        jScrollPanel1 = new javax.swing.JScrollPane(jTabla1);
        pnCarrito = new JPanel();
        pnCarrito.setLayout(null);
        jScrollPanel2 = new JScrollPane(pnCarrito);
        carro = new ArrayList<Producto>();
        labels = new JLabel[3];
        total = new JLabel("Total: 0");

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

        pnCarrito.add(lbProducto);
        pnCarrito.add(lbUnid);
        pnCarrito.add(lbDinero);
        pnCarrito.add(total);

        lbProducto.setBounds(x1, y1, 80, 20);
        x1 += aumentoX1;
        lbUnid.setBounds(x1, y1, 80, 20);
        x1 += aumentoX1;
        lbDinero.setBounds(x1, y1, 80, 20);
        x1 = x0;
        total.setBounds(x0, y1 + 30, 200, 20);
        y1 += aumentoY;

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
                        productoAux[i] = jTabla1.getModel().getValueAt(row, i).toString();
                        System.out.println(jTabla1.getModel().getValueAt(row, i).toString());
                    }

                }

                if (encuentraProducto(productoAux)) {

                } else {
                    meteCarrito(productoAux);
                    JLabel jlAux = new JLabel(carro.get(carro.size() - 1).getNombre());
                    pnCarrito.add(jlAux);
                    jlAux.setBounds(x1, y1, 120, 20);
                    x1 += aumentoX2;
                    labels[0] = jlAux;

                    JLabel jlAux1 = new JLabel(String.valueOf(carro.get(carro.size() - 1).getUnidades()));
                    pnCarrito.add(jlAux1);
                    jlAux1.setBounds(x1, y1, 80, 20);
                    x1 += aumentoX1;
                    labels[1] = jlAux1;

                    JLabel jlAux2 = new JLabel(String.valueOf(carro.get(carro.size() - 1).getPpu() * carro.get(carro.size() - 1).getUnidades()));
                    pnCarrito.add(jlAux2);
                    jlAux2.setBounds(x1, y1, 80, 20);
                    y1 += aumentoY;
                    labels[2] = jlAux2;
                    alCarrito.add(labels);
                    x1 = x0;

                    double auxD = carro.get(carro.size() - 1).getPpu();
                    total.setText("Total: " + String.valueOf(Double.valueOf(total.getText().substring(6)) + auxD));
                    total.setBounds(x0, y1 + 30, 200, 20);

                }

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
        //jTabla1.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbCategorias));
        //jTabla1.getModel().addTableModelListener(eventomodelo);
        //clases = MySQL.getClases(tabla);

        //MySQL.cierra();
    }

    private void meteCarrito(String[] nuevoProducto) {
        Producto p = new Producto(nuevoProducto[1], 1, Double.valueOf(nuevoProducto[3]));
        carro.add(p);

    }

    private boolean encuentraProducto(String[] productoAux) {
        for (int i = 0; i < carro.size(); i++) {
            //if (carro.get(i).getNombre().equals(s[1])) {
            if (carro.get(i).getNombre().compareToIgnoreCase(productoAux[1]) == 0) {
                carro.get(i).setUnidades(carro.get(i).getUnidades() + 1);
                for (int j = 0; j < alCarrito.size(); j++) {
                    if (alCarrito.get(j)[0].getText().equals(productoAux[1])) {
                        alCarrito.get(j)[1].setText(String.valueOf(carro.get(i).getUnidades()));
                        alCarrito.get(j)[2].setText(String.valueOf(carro.get(i).getUnidades() * carro.get(i).getPpu()));
                        double auxD = carro.get(i).getPpu();
                        total.setText("Total: " + String.valueOf(Double.valueOf(total.getText().substring(6)) + auxD));
                        total.setBounds(x0, y1 + 30, 200, 20);
                        return true;
                    }
                }

            }
        }
        return false;

    }

}
