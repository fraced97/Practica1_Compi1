/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author USUARIO
 */
public class Arbol {

    class Nodo {

        //String nombreArchivo;
        TokenER tipoToken;
        int contador;
        Nodo hojaIzquierda, hojaDerecha;
        ArrayList<Integer> primeros;
        ArrayList<Integer> ultimos;
        LinkedList<Integer>primeros2;
        boolean anulable;
        LinkedList<Integer>ultimos2;

        public Nodo(int contador, TokenER tipoToken) {
            //this.nombreArchivo = u;
            this.contador = contador;
            this.tipoToken = tipoToken;
            this.hojaDerecha = null;
            this.hojaIzquierda = null;
            primeros= new ArrayList<>();
            ultimos= new ArrayList<>();
            primeros2 = new LinkedList();
            ultimos2 = new LinkedList();
        }
    }
    Nodo auxiliar = null;
    public Nodo raiz = null;
    int indice = 0;
    int index2=0;
    int contadorPunto=0;
    int contadorAsterisco=0;
    int contadorSuma=0;
    int contadorInterrog=0;
    int contadorOr=0;
    int contadorId=0;
    int contadorCadena=0;
    LinkedList<TokenER> lista;

    public Boolean esta_vacio() {
        return raiz == null;
    }

    public Arbol(LinkedList<TokenER> lista) throws IOException, InterruptedException {
        while(indice<lista.size()){
            this.lista = lista;
            raiz = ordenarNodos();
            graficarInorden();
            //index++;
            raiz=null;
            this.lista=null;
            
        }
        
        //abb_report();
    }

    Nodo ordenarNodos() {
        //System.out.println(lista.get(index).tipo+"-------");
        //System.out.println(lista.get(index+1).tipo+"==========");
        //System.out.println(lista.get(index+2).tipo+"==========");
        switch (lista.get(indice).tipo) {
            case ASTERISCO:
                   Nodo and_nodo = new Nodo(indice, lista.get(indice));
                indice++;
                   Nodo and_izquierda = ordenarNodos();
                 and_nodo.hojaIzquierda = and_izquierda;
                     return and_nodo;
            case MAS:
                  Nodo masNodo = new Nodo(indice, lista.get(indice));
                 indice++;
                          Nodo masIzquierda = ordenarNodos();
                   masNodo.hojaIzquierda = masIzquierda;
                     return masNodo;
            case PUNTO:
                         Nodo puntoNodo = new Nodo(indice, lista.get(indice));
                   indice++;
                Nodo puntoIzquierda = ordenarNodos();
                   Nodo puntoDerecha = ordenarNodos();
                 puntoNodo.hojaIzquierda = puntoIzquierda;
                     puntoNodo.hojaDerecha = puntoDerecha;
                   return puntoNodo;
            case INTERROGACION:
                 System.out.println("ENTRO EN INTERROGACION");
                   Nodo interrogacionNodo = new Nodo(indice, lista.get(indice));
                   indice++;
                  Nodo interrogacionIzquierda = ordenarNodos();
                     interrogacionNodo.hojaIzquierda = interrogacionIzquierda;
                       return interrogacionNodo;
            case OR:
                System.out.println("ENTRO EN OR");
                   Nodo nodoOr = new Nodo(indice, lista.get(indice));
                  indice++;
                 Nodo izquierdaOr = ordenarNodos();
                       Nodo derechaOr = ordenarNodos();
                nodoOr.hojaIzquierda = izquierdaOr;
                        nodoOr.hojaDerecha = derechaOr;
                   return nodoOr;
            case IDENTIFICADOR:
                   Nodo id_nodo = new Nodo(indice, lista.get(indice));
                indice++;
                     return id_nodo;
            case CADENA:
                  Nodo cadena_nodo = new Nodo(indice, lista.get(indice));
                 indice++;
                       return cadena_nodo;
            default:
                   return null;
        }
        
        
    }
    
    public void graficarArbol2(Nodo raiz7,PrintWriter escribir){
        if (raiz7 !=null) {
             escribir.println("\"" + raiz7.tipoToken.lexema+","+String.valueOf(raiz7.contador) +"\";");
        if (raiz7.hojaIzquierda != null) {
             escribir.println("\"" + raiz7.tipoToken.lexema+","+String.valueOf(raiz7.contador) + "\"->\"" + raiz7.hojaIzquierda.tipoToken.lexema+","+String.valueOf(raiz7.hojaIzquierda.contador) +  "\";");
        }
        
               
        if (raiz7.hojaDerecha!=null) {
             escribir.println("\"" + raiz7.tipoToken.lexema+","+String.valueOf(raiz7.contador) +"\"->\"" + raiz7.hojaDerecha.tipoToken.lexema+","+String.valueOf(raiz7.hojaDerecha.contador) +  "\";");
        }
 
        graficarArbol2(raiz7.hojaIzquierda, escribir);
        graficarArbol2(raiz7.hojaDerecha, escribir);
        }
        


       

    }

    public void inorden(Nodo aux, PrintWriter escribir) {
        //System.out.println("Inorden");
        if (aux != null) {
            inorden(aux.hojaIzquierda, escribir);
            escribir.println("\"" + aux.tipoToken.lexema+","+String.valueOf(aux.contador) +","+aux.tipoToken.nExpresion+","+String.valueOf(aux.contador)+ "\"->");
            //System.out.println(aux.archivo.getNombre());
            inorden(aux.hojaDerecha, escribir);

        }
    }

    public void graficarInorden() {
        //System.out.println(this.raiz.tipoToken.lexema+"********");
        
        
        //String ruta= "C:\\Users\\USUARIO\\Desktop\\GraficaPruebaProyecto\\ArbolAVL.";
        
        File punto = new File(".");
        String nombreArchivo="";
        PrintWriter escribir=null;
        
        try{
            Thread.sleep(1000);
            escribir = new PrintWriter(punto.getAbsolutePath()+"//Arboles"+"//"+"Arbol.dot","UTF-8");
            //escribir.println("Arbol.dot", "w")
            escribir.println("digraph G {\n");
            Thread.sleep(1000);
            //#f.write("rankdir = LR \n node [shape = square];\n")
            graficarArbol2(this.raiz, escribir);
            Thread.sleep(1000);
            //inorden(this.raiz, escribir);
            escribir.println("}");

            escribir.close();
            Thread.sleep(1000);
            int numero=0;
            numero = (int) (Math.random() * 1000000000) + 1;
            ProcessBuilder abrir;
            Thread.sleep(1000);
            String rutaImagen=punto.getAbsolutePath()+"//Arboles"+"//"+"ArbolAvlImagen"+String.valueOf(numero)+".jpg";
            String rutaDot= punto.getAbsolutePath()+"//Arboles"+"//"+"Arbol.dot";
            abrir = new ProcessBuilder("dot", "-Tjpg", "-o",rutaImagen,rutaDot);
            Thread.sleep(1000);
            //abrir = new ProcessBuilder("dot -Tjpg Arbol.dot -o imagenArbol.jpg");
            //system("dot -Tjpg Arbol.dot -o imagenArbol.jpg")
            //os.system("imagenArbol.jpg")
            abrir.redirectErrorStream(true);
            abrir.start();
            ///////////////////////////// Creo una ventana nueva con la imagen 
            //ScrollPaneReport temporal3= new ScrollPaneReport(rutaImagen);
            //VentanaReporte3 temporal4 = new VentanaReporte3(rutaImagen);
            //temporal4.abrirVentana(rutaImagen);
            
            ////////////////////////////////////////////////////////////
            
        }catch(Exception e){
            System.out.println("NO HAY NADA");
        }

    }
 
}
