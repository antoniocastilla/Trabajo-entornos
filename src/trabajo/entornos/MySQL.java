
package trabajo.entornos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
    
    static final String DDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost/libreriaEntornos";
    // Database credentials
    static String USER = "pepe";
    static String PASS = "pepa";
    static Statement stmt;
    static private ResultSet rs;
    static private ResultSetMetaData meta;
    static private Connection conex;
    //int numColumnas;

    public MySQL() {

    }

    public static void conecta(String user, String pass) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(DDBC_DRIVER);
            conex = DriverManager.getConnection(DB_URL, user, pass);
            stmt = conex.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }
    
    

    public static void cierra() {
        try {
            if (conex != null) {
                conex.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    
    public static void cierraRs(){
        try {
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando RS: "+ex);
        }
    }

    public static boolean ejecutaConsulta(String sql) {
        try {
            //Statement stmt = conex.createStatement(
            //ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //String sql = "SELECT id, first, fenac, age FROM empleados2";
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            return true;
        } catch (SQLException se) {
            //Handle errors for DDBC 
            // se.printStackTrace();
            return false;
        }

    }

    public static int ejecutaConsultaAccion(String sql) {
        try {
            Statement stmt = conex.createStatement();
            int filas = 0;

            //String sql = "SELECT id, first, fenac, age FROM empleados2";
            filas = stmt.executeUpdate(sql);

            return filas;
        } catch (SQLException se) {
//Handle errors for DDBC 
            // se.printStackTrace();
            return -1;
        }

    }

    public static void seleccionaBD(String baseDatos) {
//         Statement st = null;
        String queryUse = "use " + baseDatos;
        try {
//            stmt = conex.createStatement();
            stmt.executeUpdate(queryUse);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static String[] getBDs() {

        // Connection serv = null;
//        Statement st = null;
        String[] bds = null;

        try {
            String query = "show databases";

            stmt = conex.createStatement();

            ResultSet res = stmt.executeQuery(query);

            //Obtenemos el numero de filas de la consulta
            res.last();
            int filas = res.getRow();
            bds = new String[filas];

            //Obtenemos el contenido
            int index = 0;
            res.beforeFirst();

            while (res.next()) {

                String bd_name = res.getString(1);

//                if (!bd_name.equals("information_schema")) {
                bds[index++] = bd_name;
//                }
            }

        } catch (SQLException e) {
        }

        return bds;

    }

    public static String[] getTablas(String database) {

        // Connection serv = null;
        //Statement st = null;
        String[] tablas = null;

        try {
            seleccionaBD(database);
            //String sql = "use "+ database;
            String query = "show tables";

            stmt = conex.createStatement();

            //ejecutaConsultaAccion(sql);
            ResultSet res = stmt.executeQuery(query);

            //Obtenemos el numero de filas de la consulta
            res.last();
            int filas = res.getRow();
            tablas = new String[filas];

            //Obtenemos el contenido
            int index = 0;
            res.beforeFirst();

            while (res.next()) {
                tablas[index++] = res.getString(1);
            }

        } catch (SQLException e) {
        }

        return tablas;

    }

    public static ResultSet getResultSet() {

        return rs;
    }

    public static String[] getColumnas() {
        String[] columnas = null;

        try {

            int numColumnas = meta.getColumnCount();
            columnas = new String[numColumnas];
            for (int i = 1; i <= numColumnas; i++) {
                columnas[i - 1] = meta.getColumnName(i);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return columnas;

    }

    //public Class[] getClases(){
    public static String[] getClases() {

     String[] claseColumnas = null;

        try {

            int numColumnas = meta.getColumnCount();
            claseColumnas = new String[numColumnas];
            rs.first();
            for (int i = 1; i <= numColumnas; i++) {
                //claseColumnas[i-1]=meta.getColumnClassName(i);
               claseColumnas[i - 1] = meta.getColumnClassName(i);
//                claseColumnas[i - 1] = rs.getObject(i).getClass();
                System.out.println("clase-->>" + claseColumnas[i - 1]);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return claseColumnas;
        // return claseColumnas;
    }

    public static String[] getCabecera() {

        String[] cabecera = null;
        try {
            int numColumnas = meta.getColumnCount();
            cabecera = new String[numColumnas];
            for (int i = 1; i <= numColumnas; i++) {
                cabecera[i - 1] = meta.getColumnName(i);
            }

            return cabecera;

        } catch (SQLException se) {
            se.printStackTrace();
            return cabecera;
        }

    }

    public static Object[][] getDatos(String sql) {

        Object[][] datos = null;
        int nFila = 0;

        try {
            
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            rs.last();
            int cuantasFilas = rs.getRow();
            rs.beforeFirst();
            int cuantasColumnas = meta.getColumnCount();
            datos = new Object[cuantasFilas][cuantasColumnas];
            
            while (rs.next()) {
                // Se crea y rellena la fila para el modelo de la tabla.
                for (int i = 1; i <= cuantasColumnas; i++) {
                    datos[nFila][i - 1] = rs.getObject(i);
                }

                nFila++;
            }
            //rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }
        public static Object getUltimoDatoIndividual(String sql) {

        Object dato = null;
        int nFila = 0;

        try {
            
            rs = stmt.executeQuery(sql);
            meta = rs.getMetaData();
            rs.last();
            nFila = rs.getRow();
            dato = rs.getObject(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dato;
    }
    

    public Object[] getFilaDatos() {

        Object[] filaDatos = null;

        try {
            // Para cada registro de resultado en la consulta 
            //rs.absolute(fila );
            rs.last();
            int nCol = meta.getColumnCount();
            filaDatos = new Object[nCol];
            for (int i = 1; i <= nCol; i++) {
                filaDatos[i - 1] = rs.getObject(i);
            }

            //rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filaDatos;
    }
    
    public static void insertaFilaDatos(Object [] datosFila){
        
        try {
            rs.moveToInsertRow();
            for (int i = 0; i < datosFila.length ; i++) {
                rs.updateObject(i+1, datosFila[i]);
            }
            rs.insertRow();
            
            DatabaseMetaData metaData = conex.getMetaData();
            //metaData.getPrimaryKeys(null, null, ventas);
            
        } catch (SQLException ex) {
            System.out.println("Error insertando");
        }
        
        
    }
    
    public static void borraFilaDatos(int nFila){
        
        try {
            rs.absolute(nFila+1);
            rs.deleteRow();
            
        } catch (SQLException ex) {
            System.out.println("Error borrando la Fila");
        }
        
    }
    
    public static void modificaValor (int nFila, int nColumna , Object nuevoValor){
        
        try {
            rs.absolute(nFila+1);
            rs.updateObject(nColumna+1, nuevoValor);
            rs.updateRow();
            
        } catch (SQLException ex) {
            System.out.println("Error modificando valor");
        }
        
    }

    public static ResultSetMetaData getMeta() {
        return meta;
    }
    
    public static String[] dameCategorias(){
        
        String[] categorias = null;
        
        try {
            
            ResultSet rs2 = stmt.executeQuery("select * from categoria;");
            ResultSetMetaData meta2 = rs2.getMetaData();
            rs2.last();
            categorias = new String[meta2.getColumnCount()+1];
            categorias[0]="Todas";
            
            rs2.beforeFirst();
            int i=1;
            while (rs2.next()){
                categorias[i] = rs2.getObject("nombre").toString();
                //System.out.println(categorias[i]);
            }
                
            
        } catch (SQLException ex) {
            System.out.println("Error obteniendo Categorias: "+ex);
        }
        
        return categorias;
    }
    
    
    

}
