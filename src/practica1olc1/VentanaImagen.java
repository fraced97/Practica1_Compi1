/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

import java.awt.Frame;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author USUARIO
 */
public class VentanaImagen extends Frame{
    JLabel imagen;
    JScrollPane scroll;
    public VentanaImagen(String ruta) throws InterruptedException{
        
        super("Ventana Reporte");
        Thread.sleep(1000);
        setSize(900,600);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        imagen= new JLabel(new ImageIcon(ruta));
        scroll = new JScrollPane();
        scroll.setViewportView(imagen);
        add(scroll);
        setVisible(true);
        File archivo1 = new File(ruta);
        archivo1.delete();
        
    }
}
