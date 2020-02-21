/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

import java.awt.Component;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author USUARIO
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
    }
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    File archivoAbrir;
    FileInputStream entrada;
    FileOutputStream salida;
    public static LinkedList<String> listaJlist = new LinkedList<>();
    public static AnalizadoLexico analizar = new AnalizadoLexico();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public String AbrirArchivo(File Archivo) {
        String documento = "";
        try {

            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8")); //UTILIZA CARACTERES ESPECIALES
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (Exception e) {
        }
        return documento;
    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo Guardado";

        } catch (Exception e) {
        }
        return mensaje;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Entrada = new java.awt.TextArea();
        botonAnalizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSalida = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abrirArchivo = new javax.swing.JMenuItem();
        guardarArchivo = new javax.swing.JMenuItem();
        guardarComo = new javax.swing.JMenuItem();
        generarXML = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setText("Archivo de Entrada");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 140, 14);
        jPanel1.add(txt_Entrada);
        txt_Entrada.setBounds(20, 30, 540, 250);

        botonAnalizar.setText("Analizar");
        botonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnalizarActionPerformed(evt);
            }
        });
        jPanel1.add(botonAnalizar);
        botonAnalizar.setBounds(70, 300, 110, 23);

        jButton2.setText("Generar Automata");
        jPanel1.add(jButton2);
        jButton2.setBounds(220, 300, 140, 23);

        jLabel2.setText("Salida");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 330, 50, 14);
        jPanel1.add(txtSalida);
        txtSalida.setBounds(30, 350, 540, 120);

        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(620, 30, 200, 430);

        jMenu1.setText("Archivo");

        abrirArchivo.setText("Abrir");
        abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(abrirArchivo);

        guardarArchivo.setText("Guardar");
        guardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(guardarArchivo);

        guardarComo.setText("Guardar Como");
        guardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(guardarComo);

        generarXML.setText("Generar XML de Salida");
        jMenu1.add(generarXML);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarComoActionPerformed
        // TODO add your handling code here:

        ////Guardar COMO
        if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("er")) {
                String Documento = txt_Entrada.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                    //listaPestanas.set(numero, archivo); // Guardo la posicion de la pestana y el archivo

                    //condicion = true; //CONDICION DEL PRIMER GUARDADO
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no Compatible");
                }
            } else if (archivoAbrir == null) {
                JOptionPane.showMessageDialog(rootPane, "Agregue .er al final");
                archivo = null;
            } else if (archivoAbrir != null) {
                JOptionPane.showMessageDialog(rootPane, "Agregue .er al final");
                archivo = archivoAbrir;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Acción de Guardado Cancelada");
        }
    }//GEN-LAST:event_guardarComoActionPerformed

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        // TODO add your handling code here:
        seleccionar.setFileFilter(new FileNameExtensionFilter("todos los archivos *.er", "er", "ER"));
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            archivoAbrir = seleccionar.getSelectedFile(); ///file auxiliar
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("er")) {

                    String documento = AbrirArchivo(archivo);
                    txt_Entrada.setText(documento);

                    // listaPestanas.add(null);
                    //condicion = false;
                    //textArea1.insert(documento);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo No Compatible");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Acción Cancelada");
        }
    }//GEN-LAST:event_abrirArchivoActionPerformed

    private void guardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarArchivoActionPerformed
        // TODO add your handling code here:
        if (archivo == null) {
            if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
                archivo = seleccionar.getSelectedFile();
                if (archivo.getName().endsWith("er")) {
                    String Documento = txt_Entrada.getText();
                    String mensaje = GuardarArchivo(archivo, Documento);
                    if (mensaje != null) {
                        JOptionPane.showMessageDialog(null, mensaje);
                        //archivo = seleccionar.getSelectedFile();

                    } else {
                        JOptionPane.showMessageDialog(null, "Archivo no Compatible");
                    }
                } else if (archivoAbrir == null) {
                    JOptionPane.showMessageDialog(rootPane, "Agregue .er al final");
                    archivo = null;
                } else if (archivoAbrir != null) {
                    JOptionPane.showMessageDialog(rootPane, "Agregue .er al final");
                    archivo = archivoAbrir;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Acción de Guardado Cancelada");
            }
        } else {
            String Documento = txt_Entrada.getText();
            String mensaje = GuardarArchivo(archivo, Documento);// mando la posicion de la Lista en donde esta el tipo Archivo
            if (mensaje != null) {

                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "Archivo no Compatible");
            }
        }
    }//GEN-LAST:event_guardarArchivoActionPerformed
    
    private void botonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnalizarActionPerformed
        // TODO add your handling code here:
        
        analizar.Analizar(txt_Entrada.getText());
        analizar.AnalizarER();
        Carpetas();
        try {
            //Thread.sleep(1000);
            Arbol arbol = new Arbol(analizar.listaTokensER);
            //Thread.sleep(1000);
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_botonAnalizarActionPerformed
    
    
    public void Carpetas(){
        listaJlist.add("");
        DefaultListModel dlm = new DefaultListModel();
        int aux=0;
       // NodoMatrizAd temp = this.father.getSiguiente();
        //rutaActual=this.father;
       
        //while(temp!=null){
            for (int i = 0; i < analizar.listaTokens.size(); i++) {
                if(analizar.listaTokens.get(i).tipo1.equals("Identificador")){
                    for (int j = 0; j < listaJlist.size(); j++) {
                        aux=j;
                        if(analizar.listaTokens.get(i).lexema.equals(listaJlist.get(j))){
                            
                           break; 
                        }
                        
                        
                    
                    
                    }
                    if(!analizar.listaTokens.get(i).lexema.equals(listaJlist.get(aux))){
                        listaJlist.add(analizar.listaTokens.get(i).lexema);
                        dlm.addElement(new entrarLista(analizar.listaTokens.get(i).lexema, new ImageIcon("carpeta2.png")));
                    }
                }
        }
               
            
            //temp = temp.getSiguiente();
            
            
        //}
        //////ARCHIVOS
         //NodoMatrizAd aux;
                /*if(this.nodoEnComunMatriz==null){
            aux = this.father;
                }else{
                    aux = this.nodoEnComunMatriz;
                }
                //DefaultListModel dlm = new DefaultListModel();
                aux.getArbol().inorden2();
                for(Archivo a: aux.getArbol().getArchivosList() ){*/
             
             //dlm.addElement(new entrarLista(a.getNombre(), new ImageIcon("archivo2.png")));
             
                    //}
            jList1.setModel(dlm);
                jList1.setCellRenderer(new imagenEnLista());
                        jList1.updateUI();
        /////ARCHIVOOOOOAAAA
        //jList1 = new JList(dlm);
        jList1.setModel(dlm);
        jList1.setCellRenderer(new imagenEnLista());
        jList1.updateUI();
        
        
        
    }
    class entrarLista
{
   private String value;
   private ImageIcon icon;
  
   public entrarLista(String value, ImageIcon icon) {
      this.value = value;
      this.icon = icon;
   }
  
   public String getValue() {
      return value;
   }
  
   public ImageIcon getIcon() {
      return icon;
   }
  
   public String toString() {
      return value;
   }
    }
    class imagenEnLista
 extends JLabel implements ListCellRenderer
{
   private JLabel label;
  
   public Component getListCellRendererComponent(JList list, Object value,  int index, boolean isSelected,  boolean cellHasFocus) {
                entrarLista entry = (entrarLista) value;
  
      setText(value.toString());
                setIcon(entry.getIcon());
   
            if (isSelected) {
         setBackground(list.getSelectionBackground());
         setForeground(list.getSelectionForeground());
                    }
                else {
         setBackground(list.getBackground());
         setForeground(list.getForeground());
                }
  
                setEnabled(list.isEnabled());
            setFont(list.getFont());
                            setOpaque(true);
  
                        return this;
   }
    }
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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirArchivo;
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JMenuItem generarXML;
    private javax.swing.JMenuItem guardarArchivo;
    private javax.swing.JMenuItem guardarComo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.TextArea txtSalida;
    private java.awt.TextArea txt_Entrada;
    // End of variables declaration//GEN-END:variables
}
