/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo.entornos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author prg
 */
public class IO {

    public static String leeFichero(String nomFichero) throws FileNotFoundException, IOException {

        FileReader input = new FileReader(nomFichero);
        int FIN_FICHERO = -1;

        int n;
        char c;
        String cadena = "";

        while ((n = input.read()) != FIN_FICHERO) {
            c = (char) n;
            cadena += c;
        }

        return cadena;

    }

    public static void escribe( String texto) throws IOException {

        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == jF1.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath()+".txt";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
        File archivo = new File(ruta);
        BufferedWriter output;
        output = new BufferedWriter(new FileWriter(ruta));

        output.write(texto);

        output.close();

    }

}
