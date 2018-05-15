/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo.entornos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prg
 */
public class MySQLTest {
    
    public MySQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of conecta method, of class MySQL.
     */
    @Test
    public void testConecta() {
        System.out.println("conecta");
        String user = "";
        String pass = "";
        MySQL.conecta(user, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cierra method, of class MySQL.
     */
    @Test
    public void testCierra() {
        System.out.println("cierra");
        MySQL.cierra();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cierraRs method, of class MySQL.
     */
    @Test
    public void testCierraRs() {
        System.out.println("cierraRs");
        MySQL.cierraRs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejecutaConsulta method, of class MySQL.
     */
    @Test
    public void testEjecutaConsulta() {
        System.out.println("ejecutaConsulta");
        String sql = "";
        boolean expResult = false;
        boolean result = MySQL.ejecutaConsulta(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejecutaConsultaAccion method, of class MySQL.
     */
    @Test
    public void testEjecutaConsultaAccion() {
        System.out.println("ejecutaConsultaAccion");
        String sql = "";
        int expResult = 0;
        int result = MySQL.ejecutaConsultaAccion(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of seleccionaBD method, of class MySQL.
     */
    @Test
    public void testSeleccionaBD() {
        System.out.println("seleccionaBD");
        String baseDatos = "";
        MySQL.seleccionaBD(baseDatos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBDs method, of class MySQL.
     */
    @Test
    public void testGetBDs() {
        System.out.println("getBDs");
        String[] expResult = null;
        String[] result = MySQL.getBDs();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTablas method, of class MySQL.
     */
    @Test
    public void testGetTablas() {
        System.out.println("getTablas");
        String database = "";
        String[] expResult = null;
        String[] result = MySQL.getTablas(database);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultSet method, of class MySQL.
     */
    @Test
    public void testGetResultSet() {
        System.out.println("getResultSet");
        ResultSet expResult = null;
        ResultSet result = MySQL.getResultSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnas method, of class MySQL.
     */
    @Test
    public void testGetColumnas() {
        System.out.println("getColumnas");
        String[] expResult = null;
        String[] result = MySQL.getColumnas();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClases method, of class MySQL.
     */
    @Test
    public void testGetClases() {
        System.out.println("getClases");
        String[] expResult = null;
        String[] result = MySQL.getClases();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCabecera method, of class MySQL.
     */
    @Test
    public void testGetCabecera() {
        System.out.println("getCabecera");
        String[] expResult = null;
        String[] result = MySQL.getCabecera();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatos method, of class MySQL.
     */
    @Test
    public void testGetDatos() {
        System.out.println("getDatos");
        String sql = "";
        Object[][] expResult = null;
        Object[][] result = MySQL.getDatos(sql);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUltimoDatoIndividual method, of class MySQL.
     */
    @Test
    public void testGetUltimoDatoIndividual() {
        System.out.println("getUltimoDatoIndividual");
        String sql = "";
        Object expResult = null;
        Object result = MySQL.getUltimoDatoIndividual(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilaDatos method, of class MySQL.
     */
    @Test
    public void testGetFilaDatos() {
        System.out.println("getFilaDatos");
        MySQL instance = new MySQL();
        Object[] expResult = null;
        Object[] result = instance.getFilaDatos();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertaFilaDatos method, of class MySQL.
     */
    @Test
    public void testInsertaFilaDatos() {
        System.out.println("insertaFilaDatos");
        Object[] datosFila = null;
        MySQL.insertaFilaDatos(datosFila);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of borraFilaDatos method, of class MySQL.
     */
    @Test
    public void testBorraFilaDatos() {
        System.out.println("borraFilaDatos");
        int nFila = 0;
        MySQL.borraFilaDatos(nFila);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaValor method, of class MySQL.
     */
    @Test
    public void testModificaValor() {
        System.out.println("modificaValor");
        int nFila = 0;
        int nColumna = 0;
        Object nuevoValor = null;
        MySQL.modificaValor(nFila, nColumna, nuevoValor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeta method, of class MySQL.
     */
    @Test
    public void testGetMeta() {
        System.out.println("getMeta");
        ResultSetMetaData expResult = null;
        ResultSetMetaData result = MySQL.getMeta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dameCategorias method, of class MySQL.
     */
    @Test
    public void testDameCategorias() {
        System.out.println("dameCategorias");
        String[] expResult = null;
        String[] result = MySQL.dameCategorias();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
