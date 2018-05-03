package trabajo.entornos;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    private ArrayList<JLabel[]> alEtiquetas;
    private ArrayList<Producto> carro;
    private int x0 = 10;
    private int x1 = 20;
    private int y0 = 10;
    private int y1 = 20;
    private int aumentoX1 = 75;
    private int aumentoX2 = 110;
    private int aumentoY = 40;
    //private JLabel[] labels;
    private JLabel total;
    private int altoSizeCarrito = 170;

    public Catalogo() {

        this.setLayout(null);

        alEtiquetas = new ArrayList<JLabel[]>();
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
        pnCarrito.setBounds(0, 0, 280, altoSizeCarrito);
        pnCarrito.setPreferredSize(new Dimension(300, altoSizeCarrito));
        JPanel xapu = new JPanel();
        xapu.setLayout(new BorderLayout());
        xapu.add(pnCarrito);
        jScrollPanel2 = new JScrollPane(xapu, 20, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        carro = new ArrayList<Producto>();
        //labels = new JLabel[3];
        total = new JLabel("Total: 0");

        jScrollPanel1.setBounds(30, 50, 600, 450);
        jScrollPanel2.setBounds(625, 50, 330, 450);
        compra.setBounds(20, 600, 100, 40);
        quitar.setBounds(120, 600, 100, 40);
        volver.setBounds(220, 600, 100, 40);
        total.setBounds(320,600,100,40);

        this.add(compra);
        this.add(quitar);
        this.add(carrito);
        this.add(volver);
        this.add(jScrollPanel1);
        this.add(jScrollPanel2);
        this.add(total);

        rellenaTabla();

        //Panel del Carrito
        JLabel lbProducto = new JLabel("Producto");
        JLabel lbUnid = new JLabel("Unidades");
        JLabel lbDinero = new JLabel("Dinero");

        pnCarrito.add(lbProducto);
        pnCarrito.add(lbUnid);
        pnCarrito.add(lbDinero);

        lbProducto.setBounds(x1, y1, 80, 20);
        x1 += aumentoX1;
        lbUnid.setBounds(x1 + 50, y1, 80, 20);
        x1 += aumentoX1;
        lbDinero.setBounds(x1 + 50, y1, 80, 20);
        x1 = x0;
        y1 += aumentoY;

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Catalogo.this.setVisible(false);

            }
        });

        jTabla1.setEnabled(false);
        jTabla1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent mouseEvent) {

                String[] productoAux = new String[jTabla1.getColumnCount()];
                int row = jTabla1.rowAtPoint(mouseEvent.getPoint());
                int col = jTabla1.columnAtPoint(mouseEvent.getPoint());
                if (row >= 0 && col >= 0) {
                    for (int i = 0; i < jTabla1.getColumnCount(); i++) {
                        productoAux[i] = jTabla1.getModel().getValueAt(row, i).toString();
                        //System.out.println(productoAux[i]);
                    }

                }

//                while (alEtiquetas.size() != 0){
//                System.out.println("PRIMER OBJETO DEL ARRAY ETIQUETAS: "+alEtiquetas.get(0)[0].getText());
//                System.out.println("PRIMER OBJETO DEL ARRAY ETIQUETAS: "+alEtiquetas.get(0)[1].getText());
//                System.out.println("PRIMER OBJETO DEL ARRAY ETIQUETAS: "+alEtiquetas.get(0)[2].getText());
//                break;
//                }
                int posicionAuxCarro = encuentraProducto(productoAux);
                if (posicionAuxCarro != -1) {

                    //System.out.println("productoAux[0]= " + productoAux[0] + "==carro.get(id).getId()=" + carro.get(posicionAuxCarro).getId());
                    carro.get(posicionAuxCarro).setUnidades(carro.get(posicionAuxCarro).getUnidades() + 1);

                    if (carro.get(posicionAuxCarro).getNombre().compareToIgnoreCase(alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[0].getText()) == 0) {
                        System.out.println(carro.get(posicionAuxCarro).getNombre() + " es igual a == " + alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[0].getText());

                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[1].setText(String.valueOf(carro.get(posicionAuxCarro).getUnidades()));
                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[2].setText(String.valueOf(carro.get(posicionAuxCarro).getUnidades() * carro.get(posicionAuxCarro).getPpu()));
                        double ppuAux = carro.get(posicionAuxCarro).getPpu();
                        total.setText("Total: " + String.valueOf(Double.valueOf(total.getText().substring(6)) + ppuAux));

                    }

                } else {
                    JLabel[] labels = new JLabel[3];
                    System.out.println("ha entrao");
                    meteCarrito(productoAux);
                    JLabel jlAux = new JLabel(carro.get(carro.size() - 1).getNombre());
                    pnCarrito.add(jlAux);
                    jlAux.setBounds(x1, y1, 170, 20);
                    x1 += aumentoX2;
                    labels[0] = jlAux;

                    JLabel jlAux1 = new JLabel(String.valueOf(carro.get(carro.size() - 1).getUnidades()));
                    pnCarrito.add(jlAux1);
                    jlAux1.setBounds(x1 + 40, y1, 80, 20);
                    x1 += aumentoX1;
                    labels[1] = jlAux1;

                    JLabel jlAux2 = new JLabel(String.valueOf(carro.get(carro.size() - 1).getPpu() * carro.get(carro.size() - 1).getUnidades()));
                    pnCarrito.add(jlAux2);
                    jlAux2.setBounds(x1 + 40, y1, 80, 20);
                    y1 += aumentoY;
                    labels[2] = jlAux2;
                    alEtiquetas.add(labels);

                    carro.get(encuentraProducto(productoAux)).setFilaCarrito(alEtiquetas.size() - 1);

                    x1 = x0;

                    double auxD = carro.get(carro.size() - 1).getPpu();
                    total.setText("Total: " + String.valueOf(Double.valueOf(total.getText().substring(6)) + auxD));
                    //total.setBounds(x0, y1 + 30, 200, 20);
                    aumentaTamañoCarrito();
                }

//                System.out.println("==toString completo de ArrayList Carro: ===");
//                for (Producto x : carro) {
//                    System.out.println(x.toString());
//                }
//                jTabla1.clearSelection();
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

//        for (int i = 0; i < datos.length; i++) {
//            for (int j = 0; j < datos[i].length; j++) {
//                System.out.print(datos[i][j] + ",");
//            }
//            System.out.println("\n");
//        }
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
        Producto p = new Producto(Integer.parseInt(nuevoProducto[0]), nuevoProducto[1], 1, Double.valueOf(nuevoProducto[3]));
        carro.add(p);
    }

    private int encuentraProducto(String[] productoAux) {

        for (int i = 0; i < carro.size(); i++) {
            //if (carro.get(i).getNombre().equals(s[1])) {
            if (Integer.parseInt(productoAux[0]) == carro.get(i).getId()) {
                return i;
            }
        }
        return -1;

    }

    private void aumentaTamañoCarrito() {
        altoSizeCarrito += 40;
        pnCarrito.setBounds(0, 0, 280, altoSizeCarrito);
        pnCarrito.setPreferredSize(new Dimension(300, altoSizeCarrito));
    }
    
    private void actualizaTotal (double totalSinTruncar){
        double truncado = (double) Math.round(totalSinTruncar * 100d) / 100d;
        total.setText(String.valueOf(truncado));
    }

}
