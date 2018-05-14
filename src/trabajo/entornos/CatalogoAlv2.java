package trabajo.entornos;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class CatalogoAlv2 extends JDialog {

    private int xMouse;
    private int yMouse;
    private JTextArea jt1;
    private JButton compra, carrito, volver;
    private MySQL db;
    private JTable jTabla1;
    private JScrollPane jScrollPanel1, jScrollPanel2;
    private JPanel pnContenedor, pnCarrito;
    private DefaultTableModel modelo;
    private ArrayList<JLabel[]> alEtiquetas;
    private ArrayList<Producto> carro;
    private int x0 = 10;
    private int x1 = 20;
    private int y0 = 20;
    private int y1 = 20;
    private int aumentoX1 = 75;
    private int aumentoX2 = 110;
    private int aumentoY = 40;
    private JLabel total;
    private int altoSizeCarrito = 170;
    private ArrayList<Integer> posiciones;

    //Barra superior (FrameDrag, X para cerrar)
    private JLabel FrameDrag;
    private JLabel lbX;

    public CatalogoAlv2() {

        this.setLayout(null);
        this.setBackground(new java.awt.Color(83, 94, 165));

        pnContenedor = new JPanel();
        alEtiquetas = new ArrayList<JLabel[]>();
        jt1 = new JTextArea();
        jt1.setEditable(false);
        compra = new JButton("Compra");
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
        posiciones = new ArrayList<Integer>();

        //FrameDrag y Cierre
        FrameDrag = new JLabel("Catálogo");
        FrameDrag.setBounds(10, 0, 960, 30);
        FrameDrag.setBackground(new java.awt.Color(204, 204, 204));
        FrameDrag.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        FrameDrag.setForeground(new java.awt.Color(255, 255, 255));
        FrameDrag.setText("Manejar Stock Almacén");
        FrameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                CatalogoAlv2.this.setLocation(x - xMouse, y - yMouse);
            }
        });
        compra.setBackground(new java.awt.Color(102, 102, 102));
        compra.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        compra.setForeground(new java.awt.Color(204, 204, 204));
        compra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/comprar.png")));
        compra.setBorderPainted(false);
        compra.setVerticalTextPosition(SwingConstants.BOTTOM);
        compra.setHorizontalTextPosition(SwingConstants.CENTER);
        compra.setBorder(null);
        compra.setContentAreaFilled(false);
        compra.setToolTipText("Añade articulos clickando sobre ellos. Si quieres quitarlos, pulsa con botón derecho sobre el carrito");
        compra.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] datos = MySQL.getDatos("select idProducto,cantidad from producto;");
                MySQL.cierraRs();
                for (int i = 0; i < carro.size(); i++) {
                    for (int j = 0; j < datos.length; j++) {
                        if (carro.get(i).getId() == (int)datos[j][0] ) {
                            if ((int)datos[j][1]-carro.get(i).getUnidades() < 0) {
                                JOptionPane.showMessageDialog(null, "No existen ese numero de unidades en la tienda de:  \n"+carro.get(i).getNombre()+" "
                                        + "\nSolo quedan: "+datos[j][i]+" "
                                        + "\nHas seleccionado: "+carro.get(i).getUnidades(), "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        
                    }
                }

                if (carro.isEmpty()) {
                    
                }else{
                
                new VentanaFactura(carro, Double.parseDouble(total.getText().substring(6)));
                }
            }
        });
        FrameDrag.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        lbX = new JLabel("X");
        lbX.setBounds(975, 0, 30, 30);
        lbX.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lbX.setForeground(new java.awt.Color(255, 255, 255));
        lbX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CatalogoAlv2.this.setVisible(false);
            }
        });
        
        pnContenedor.add(FrameDrag);
        pnContenedor.add(lbX);

        pnContenedor.setLayout(null);
        pnContenedor.setBackground(new Color(102, 102, 102));
        pnContenedor.setBounds(0, 0, 1000, 700);
        pnCarrito.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPanel1.setBounds(30, 50, 600, 450);
        jScrollPanel2.setBounds(625, 50, 330, 450);
        jTabla1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        compra.setBounds(30, 500, 120, 120);
        volver.setBounds(120, 570, 100, 40);
        total.setBounds(650, 600, 100, 40);
        this.setUndecorated(true);

        this.add(pnContenedor);
        pnContenedor.add(compra);
        pnContenedor.add(carrito);
        pnContenedor.add(volver);
        pnContenedor.add(jScrollPanel1);
        pnContenedor.add(jScrollPanel2);
        pnContenedor.add(total);

        rellenaTabla();

        //Panel del Carrito
        JLabel lbProducto = new JLabel("Producto");
        lbProducto.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        JLabel lbUnid = new JLabel("Unidades");
        lbUnid.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
        JLabel lbDinero = new JLabel("Dinero");
        lbDinero.setFont(new java.awt.Font("Helvetica Neue", 0, 14));

        pnCarrito.add(lbProducto);
        pnCarrito.add(lbUnid);
        pnCarrito.add(lbDinero);

        lbProducto.setBounds(x1 - 10, y1, 80, 20);
        x1 += aumentoX1;
        lbUnid.setBounds(x1 + 80, y1, 80, 20);
        x1 += aumentoX1;
        lbDinero.setBounds(x1 + 80, y1, 80, 20);
        x1 = x0;
        posiciones.add(y1);
        y1 += aumentoY;

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CatalogoAlv2.this.setVisible(false);

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
                    int posicionAuxCarro = encuentraProducto(productoAux);

                    if (posicionAuxCarro != -1) {

                        carro.get(posicionAuxCarro).setUnidades(carro.get(posicionAuxCarro).getUnidades() + 1);

                        if (carro.get(posicionAuxCarro).getNombre().compareToIgnoreCase(alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[0].getText()) == 0) {
                            alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[1].setText(String.valueOf(carro.get(posicionAuxCarro).getUnidades()));
                            alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[2].setText(String.valueOf(dameTruncado((carro.get(posicionAuxCarro).getUnidades() * carro.get(posicionAuxCarro).getPpu()))));
                            double ppuAux = carro.get(posicionAuxCarro).getPpu();
                            total.setText("Total: " + String.valueOf(dameTruncado(Double.valueOf(total.getText().substring(6)) + ppuAux)));
                        }

                    } else {
                        while (posiciones.contains(y1)) {
                            y1 += aumentoY;
                        }
                        JLabel[] labels = new JLabel[3];
                        meteCarrito(productoAux);
                        JLabel jlAux = new JLabel(carro.get(carro.size() - 1).getNombre());
                        jlAux.setToolTipText("Click con el botón derecho para quitar producto");
                        jlAux.addMouseListener(new MouseAdapter() {
                            public void mousePressed(MouseEvent mouseEvent) {
                                int posicionAuxCarro = encuentraProducto(productoAux);
                                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                                    System.out.println("Botón derecho en la etiqueta");
                                    if (posicionAuxCarro == -1) {

                                    } else if (carro.get(posicionAuxCarro).getUnidades() == 1) {
                                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[0].setText("");
                                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[1].setText("");
                                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[2].setText("");
                                        alEtiquetas.remove(carro.get(posicionAuxCarro).getFilaCarrito());
                                        double ppuAux = carro.get(posicionAuxCarro).getPpu();
                                        total.setText("Total: " + String.valueOf(dameTruncado(Double.valueOf(total.getText().substring(6)) - ppuAux)));
                                        posiciones.remove(carro.get(posicionAuxCarro).getFilaCarrito() + 1);
                                        y1 = y0;
                                        sacaCarrito(posicionAuxCarro);
                                        for (int i = posicionAuxCarro; i < carro.size(); i++) {
                                            carro.get(i).setFilaCarrito(carro.get(i).getFilaCarrito() - 1);
                                        }

                                    } else {
                                        carro.get(posicionAuxCarro).setUnidades(carro.get(posicionAuxCarro).getUnidades() - 1);
                                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[1].setText(String.valueOf(carro.get(posicionAuxCarro).getUnidades()));
                                        alEtiquetas.get(carro.get(posicionAuxCarro).getFilaCarrito())[2].setText(String.valueOf(dameTruncado((carro.get(posicionAuxCarro).getUnidades() * carro.get(posicionAuxCarro).getPpu()))));
                                        double ppuAux = carro.get(posicionAuxCarro).getPpu();
                                        total.setText("Total: " + String.valueOf(dameTruncado(Double.valueOf(total.getText().substring(6)) - ppuAux)));

                                    }
                                }
                            }
                        });

                        jlAux.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
                        jlAux.setForeground(new java.awt.Color(204, 204, 204));
                        pnCarrito.add(jlAux);
                        jlAux.setBounds(x1, y1, 170, 20);
                        x1 += aumentoX2;
                        labels[0] = jlAux;

                        JLabel jlAux1 = new JLabel(String.valueOf(carro.get(carro.size() - 1).getUnidades()));
                        jlAux1.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
                        jlAux1.setForeground(new java.awt.Color(204, 204, 204));
                        pnCarrito.add(jlAux1);
                        jlAux1.setBounds(x1 + 90, y1, 80, 20);
                        x1 += aumentoX1;
                        labels[1] = jlAux1;

                        JLabel jlAux2 = new JLabel(String.valueOf(dameTruncado(carro.get(carro.size() - 1).getPpu() * carro.get(carro.size() - 1).getUnidades())));
                        pnCarrito.add(jlAux2);
                        jlAux2.setFont(new java.awt.Font("Helvetica Neue", 0, 14));
                        jlAux2.setForeground(new java.awt.Color(204, 204, 204));
                        jlAux2.setBounds(x1 + 60, y1, 80, 20);
                        posiciones.add(y1);
                        y1 += aumentoY;

                        labels[2] = jlAux2;
                        alEtiquetas.add(labels);

                        carro.get(encuentraProducto(productoAux)).setFilaCarrito(alEtiquetas.size() - 1);

                        x1 = x0;

                        double auxD = carro.get(carro.size() - 1).getPpu();
                        total.setText("Total: " + String.valueOf(dameTruncado(Double.valueOf(total.getText().substring(6)) + auxD)));
                        aumentaTamañoCarrito();
                    }
                }
            }
        });

        this.setTitle("Papelería - Catálogo");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
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
    }

    private void meteCarrito(String[] nuevoProducto) {
        Producto p = new Producto(Integer.parseInt(nuevoProducto[0]), nuevoProducto[1], 1, Double.valueOf(nuevoProducto[3]));
        carro.add(p);
    }

    private void sacaCarrito(int i) {
        carro.remove(i);

    }

    private int encuentraProducto(String[] productoAux) {

        for (int i = 0; i < carro.size(); i++) {
            if (Integer.parseInt(productoAux[0]) == carro.get(i).getId()) {
                return i;
            }
        }
        return -1;

    }

    private void aumentaTamañoCarrito() {
        altoSizeCarrito += 30;
        pnCarrito.setBounds(0, 0, 280, altoSizeCarrito);
        pnCarrito.setPreferredSize(new Dimension(300, altoSizeCarrito));
    }

    private void disminuyeTamañoCarrito() {
        altoSizeCarrito -= 30;
        pnCarrito.setBounds(0, 0, 280, altoSizeCarrito);
        pnCarrito.setPreferredSize(new Dimension(300, altoSizeCarrito));
    }

    private double dameTruncado(double totalSinTruncar) {
        return (double) Math.round(totalSinTruncar * 100d) / 100d;

    }

}
