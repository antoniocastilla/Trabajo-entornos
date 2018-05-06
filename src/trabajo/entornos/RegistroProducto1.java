package trabajo.entornos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static trabajo.entornos.MySQL.DB_URL;

public class RegistroProducto1 {

    private Inicio Inico;
    private Catalogo catalogo;
    Statement stmt;
    private MySQL mysql;

    private JDialog ventanaDialogo;
    private JPanel panelDialog;

    private JLabel Lbid;
    private JLabel LbNombre;
    private JLabel Lbcantidad;
    private final JLabel LbPPU;
    private final JLabel Lbcategoria;

    private JTextField TxId;
    private JTextField TxNombre;
    private JTextField Txcantidad;
    private JTextField TxPPU;
    private JTextField Txcategoria;

    private JButton insertarBt;
    private JButton cerrrarBt;

    private String texto;
    private String textoGuardado;
    private String sql;
    private static Connection conexion;
    static final String user = "pepe"; //usuario        
    static final String pass = "pepa"; //contraseña

    public RegistroProducto1() {

        ventanaDialogo = new JDialog(Inico, "Dialogo de Registrar producto");
        ventanaDialogo.setSize(750, 550);
        ventanaDialogo.setLayout(null);

        insertarBt = new JButton("Insertar");
        cerrrarBt = new JButton("Cerrar");

        Lbid = new JLabel("ID Producto");
        LbNombre = new JLabel("Nombre");
        Lbcantidad = new JLabel("Cantidad");
        LbPPU = new JLabel("PPU");
        Lbcategoria = new JLabel("Categoria");

        TxId = new JTextField("");
        TxNombre = new JTextField("");
        Txcantidad  = new JTextField("");
        TxPPU = new JTextField("");
        Txcategoria  = new JTextField("");

        ventanaDialogo.add(Lbid);
        ventanaDialogo.add(LbNombre);
        ventanaDialogo.add(Lbcantidad);
        ventanaDialogo.add(LbPPU);
        ventanaDialogo.add(Lbcategoria);

        ventanaDialogo.add(insertarBt);
        ventanaDialogo.add(cerrrarBt);

        ventanaDialogo.add(TxId);
        ventanaDialogo.add(TxNombre);
        ventanaDialogo.add(Txcantidad);
        ventanaDialogo.add(TxPPU);
        ventanaDialogo.add(Txcategoria);
        

        Lbid.setBounds(100, 10, 100, 20);
        LbNombre.setBounds(100, 60, 100, 20);
        Lbcantidad.setBounds(100, 110, 100, 20);
        LbPPU.setBounds(100, 160, 100, 20);
        Lbcategoria.setBounds(100, 200, 100, 20);

        TxId.setBounds(200, 10, 100, 40);
        TxNombre.setBounds(200, 60, 100, 40);
        Txcantidad.setBounds(200, 110, 100, 40);
        TxPPU.setBounds(200, 160, 100, 40);
        Txcategoria.setBounds(200, 200, 100, 40);

        insertarBt.setBounds(350, 290, 150, 20);
        cerrrarBt.setBounds(550, 290, 150, 20);

        ventanaDialogo.setVisible(true);
        ventanaDialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
        insertarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] productos = new Object[5];

                productos[0] = (Object) TxId.getText();
                productos[1] = (Object) TxNombre.getText();
                productos[2] = (Object) Txcantidad.getText();
                productos[3] = (Object) TxPPU.getText();
                productos[4] = (Object) Txcategoria.getText();


//                MySQLBaseDatos.ejecutaConsultaAccion(sql);
                try {
                    mysql.conecta("pepe", "pepa");
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(DB_URL, user, pass);

                    stmt = conexion.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    
                    sql = "INSERT INTO producto"
                        + "(idProducto, nombre, cantidad, ppu, categoria ) VALUES" + 
                            "(?,?,?,?,?)";
                    
                    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                    preparedStatement.setString(1, TxId.getText());
                    preparedStatement.setString(2, TxNombre.getText());
                    preparedStatement.setString(3, Txcantidad.getText());
                    preparedStatement.setString(4, TxPPU.getText());
                    preparedStatement.setString(5, Txcategoria.getText());

                    
                    preparedStatement .executeUpdate();
                    
                    
                    
//                    catalogo.insertaTabla(productos);
                    
                    
            

                    System.out.println(productos);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        cerrrarBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaDialogo.dispose();

            }

        });

    }

}
