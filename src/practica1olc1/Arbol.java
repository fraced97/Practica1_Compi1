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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author USUARIO
 */
public class Arbol {

    class Nodo {

        public LinkedList<Integer> getPrimeros2() {
            return primeros2;
        }

        public void setPrimeros2(int a) {
            this.primeros2.add(a);
        }

        public LinkedList<Integer> getUltimos2() {
            return ultimos2;
        }

        public void setUltimos2(int a) {
            this.ultimos2.add(a);
        }

        //String nombreArchivo;
        TokenER tipoToken;
        int contador;
        Nodo hojaIzquierda, hojaDerecha;
        LinkedList<Integer> primeros2;
        boolean anulable;
        int numeracion;
        LinkedList<Integer> ultimos2;
        public LinkedList<Integer> siguientes = new LinkedList<>();

        public Nodo(int contador, TokenER tipoToken) {
            //this.nombreArchivo = u;
            this.contador = contador;
            this.tipoToken = tipoToken;
            this.hojaDerecha = null;
            this.hojaIzquierda = null;
            primeros2 = new LinkedList();
            ultimos2 = new LinkedList();
        }

        public void verificarDuplicado() {
            Set<Integer> name = new HashSet<>(this.primeros2);
            Set<Integer> name2 = new HashSet<>(this.ultimos2);
            this.primeros2.clear();
            this.primeros2.addAll(name);
            this.ultimos2.clear();
            this.ultimos2.addAll(name2);
        }
    }
    Nodo auxiliar ;
    public Nodo raiz = null;
    int indice = 0;
    int index2 = 0;
    int hojasArbolContador = -1;
    int contadorAsterisco = 0;
    int contadorSuma = 0;
    int contadorInterrog = 0;
    int contadorOr = 0;
    int contadorId = 0;
    int contadorCadena = 0;
    LinkedList<TokenER> copiaLista;
    LinkedList<Nodo> listaDeNodos = new LinkedList();
    LinkedList<LinkedList<Integer>> hojasArbol = new LinkedList();
    //LinkedList<Follows> follows = new LinkedList();
    
    public Boolean esta_vacio() {
        return raiz == null;
    }

    public void siguientesOrdenar( ) {
        /*for (int i = 0; i <ht.size(); i++) {
            for (int j = 0; j < s.size(); j++) {
                if(!listNode.get(ht.get(i)).siguientes.contains(s.get(j))){
                    listNode.get(ht.get(i)).siguientes.add(s.get(j));
                }
            }
        }*/
                        for(LinkedList<Integer> x: hojasArbol){
         Collections.sort(x); 
                }
    }

    public Arbol(LinkedList<TokenER> lista) throws IOException, InterruptedException {
        while (indice < lista.size()) {
            this.copiaLista = lista;
            raiz = ArbolySiguientes();
            graficarInorden();
            //index++;
            //raiz = null;
            //this.lista = null;

        }

        //abb_report();
    }
    Nodo ArbolySiguientes(){                
        switch(copiaLista.get(indice).tipo){
            case CADENA:
                    Nodo Cadena_Nodo = new Nodo(indice,copiaLista.get(indice));
                Cadena_Nodo.anulable=(false);
                     indice++;
                      hojasArbolContador++;
             Cadena_Nodo.numeracion=(hojasArbolContador);
                    Cadena_Nodo.setPrimeros2(Cadena_Nodo.numeracion);
                        Cadena_Nodo.setUltimos2(Cadena_Nodo.numeracion);
                hojasArbol.add(new LinkedList<Integer>()); //*
                        Cadena_Nodo.verificarDuplicado();
            listaDeNodos.add(Cadena_Nodo);
                        return Cadena_Nodo;
            case ASTERISCO:
               
               
          Nodo nodoAsterisco = new Nodo(indice,copiaLista.get(indice));
                indice++;
                        Nodo izqPor = ArbolySiguientes();
           nodoAsterisco.hojaIzquierda=izqPor;
                nodoAsterisco.anulable=true;
                         nodoAsterisco.primeros2.addAll(izqPor.primeros2);
             nodoAsterisco.ultimos2.addAll(izqPor.ultimos2);
                       for (int aux: izqPor.ultimos2) {
                            for (int aux2: izqPor.primeros2) {
               hojasArbol.get(aux).add(aux2);
                }
                }
                
                siguientesOrdenar();
                return nodoAsterisco;
                
            case IDENTIFICADOR:
                Nodo IdentificadorNodo = new Nodo(indice,copiaLista.get(indice));
                             IdentificadorNodo.anulable=(false);
                      hojasArbolContador++;
                IdentificadorNodo.numeracion=(hojasArbolContador);
            IdentificadorNodo.setPrimeros2(IdentificadorNodo.numeracion);
                                        IdentificadorNodo.setUltimos2(IdentificadorNodo.numeracion);
              indice++;
                      IdentificadorNodo.verificarDuplicado();
                                   hojasArbol.add(new LinkedList<Integer>()); //*
                    listaDeNodos.add(IdentificadorNodo);
            return IdentificadorNodo;    
            
            case PUNTO:
                
                             Nodo puntoNodo = new Nodo(indice,copiaLista.get(indice));
                         indice++;
                     Nodo puntoIzquierda = ArbolySiguientes();
                            Nodo puntoDerecha = ArbolySiguientes();
                  puntoNodo.hojaIzquierda=puntoIzquierda;
                         puntoNodo.hojaDerecha=puntoDerecha;
            if (puntoIzquierda.anulable && puntoDerecha.anulable) {
                    puntoNodo.anulable=(true);
                         }else{
               puntoNodo.anulable=(false);
                         }
                            if (puntoIzquierda.anulable) {
              puntoNodo.primeros2.addAll(puntoIzquierda.primeros2);
                              puntoNodo.primeros2.addAll(puntoDerecha.primeros2);
            }else{
                puntoNodo.primeros2.addAll(puntoIzquierda.primeros2);
                        }
                            if (puntoDerecha.anulable) {
          puntoNodo.ultimos2.addAll(puntoIzquierda.ultimos2);
                                           puntoNodo.ultimos2.addAll(puntoDerecha.ultimos2);
                         }else{
       puntoNodo.ultimos2.addAll(puntoDerecha.ultimos2);
                           }
                                for (int aux: puntoIzquierda.ultimos2) {
              for (int aux2: puntoDerecha.primeros2) {
                                   //follows.get(x).getSiguientes().add(y);
             hojasArbol.get(aux).add(aux2);
                                       }
         }
                                puntoNodo.verificarDuplicado();
                                    return puntoNodo;
            case NUMERAL:
                                Nodo nodoHashtag = new Nodo(indice,copiaLista.get(indice));
                         nodoHashtag.anulable=(false);
                
             indice++;
                           hojasArbolContador++;
           nodoHashtag.numeracion=(hojasArbolContador);
                        nodoHashtag.setPrimeros2(nodoHashtag.numeracion);
            nodoHashtag.setUltimos2(nodoHashtag.numeracion);
                                 hojasArbol.add(new LinkedList<Integer>()); //*
                //Follows f = new Follows();
                //f.setNodo(nodoHashtag);
                //follows.add(f);
                            nodoHashtag.verificarDuplicado();
                                    listaDeNodos.add(nodoHashtag);
        return nodoHashtag;    
            case INTERROGACION:
                
                              Nodo InterrogacionNodo = new Nodo(indice,copiaLista.get(indice));
         indice++;
                         Nodo InterrogacionIzquierda = ArbolySiguientes(); 
                InterrogacionNodo.hojaIzquierda=(InterrogacionIzquierda);
                                                    InterrogacionNodo.anulable=(true);
        InterrogacionNodo.primeros2.addAll(InterrogacionIzquierda.primeros2);
                          InterrogacionNodo.ultimos2.addAll(InterrogacionIzquierda.ultimos2);
           return InterrogacionNodo;
                
            case OR:
                                        Nodo or_Nodo = new Nodo(indice,copiaLista.get(indice));
                indice++;
                Nodo orIzquierda = ArbolySiguientes();
                                                     Nodo orDerecha = ArbolySiguientes();
        or_Nodo.hojaIzquierda=(orIzquierda);
                                 or_Nodo.hojaDerecha=(orDerecha);
         if (orIzquierda.anulable || orDerecha.anulable) {
                                       or_Nodo.anulable=(true);
                            }else{
                                 or_Nodo.anulable=(false);
           }
                                         or_Nodo.primeros2.addAll(orIzquierda.primeros2);
                         or_Nodo.primeros2.addAll(orDerecha.primeros2);
                                                or_Nodo.ultimos2.addAll(orIzquierda.ultimos2);
          or_Nodo.ultimos2.addAll(orDerecha.ultimos2);
                                       or_Nodo.verificarDuplicado();
          return or_Nodo;
                
            
            case MAS:
                
                             Nodo signoMasNodo = new Nodo(indice,copiaLista.get(indice));
                             indice ++;
            Nodo nodoMasIzquierda = ArbolySiguientes();
                             signoMasNodo.hojaIzquierda=(nodoMasIzquierda);
                                                 if(nodoMasIzquierda.anulable){
                    signoMasNodo.anulable=(true);
                         }else{
                    signoMasNodo.anulable=(false);
                }
                                 signoMasNodo.primeros2.addAll(nodoMasIzquierda.primeros2);
                         signoMasNodo.ultimos2.addAll(nodoMasIzquierda.ultimos2);
            for (int x: nodoMasIzquierda.ultimos2) {
                                 for (int y: nodoMasIzquierda.primeros2) {
                hojasArbol.get(x).add(y);
                            }
         }
                                                       siguientesOrdenar();
                return signoMasNodo;
            
                
            
                
            default:
                return null;
        }
        
    }

    

    public void graficarArbol2(Nodo raiz7, PrintWriter escribir) {
        if (raiz7 != null) {
            escribir.println("\"" + raiz7.tipoToken.lexema + "," + String.valueOf(raiz7.contador) + "\";");
            if (raiz7.hojaIzquierda != null) {
                escribir.println("\"" + raiz7.tipoToken.lexema + "," + String.valueOf(raiz7.contador) + "\"->\"" + raiz7.hojaIzquierda.tipoToken.lexema + "," + String.valueOf(raiz7.hojaIzquierda.contador) + "\";");
            }

            if (raiz7.hojaDerecha != null) {
                escribir.println("\"" + raiz7.tipoToken.lexema + "," + String.valueOf(raiz7.contador) + "\"->\"" + raiz7.hojaDerecha.tipoToken.lexema + "," + String.valueOf(raiz7.hojaDerecha.contador) + "\";");
            }

            graficarArbol2(raiz7.hojaIzquierda, escribir);
            graficarArbol2(raiz7.hojaDerecha, escribir);
        }

    }

    public void inorden(Nodo aux, PrintWriter escribir) {
        //System.out.println("Inorden");
        if (aux != null) {
            inorden(aux.hojaIzquierda, escribir);
            escribir.println("\"" + aux.tipoToken.lexema + "," + String.valueOf(aux.contador) + "," + aux.tipoToken.nExpresion + "," + String.valueOf(aux.contador) + "\"->");
            //System.out.println(aux.archivo.getNombre());
            inorden(aux.hojaDerecha, escribir);

        }
    }

    public void graficarInorden() {
        //System.out.println(this.raiz.tipoToken.lexema+"********");

        //String ruta= "C:\\Users\\USUARIO\\Desktop\\GraficaPruebaProyecto\\ArbolAVL.";
        File punto = new File(".");
        String nombreArchivo = "";
        PrintWriter escribir = null;

        try {
            Thread.sleep(1000);
            escribir = new PrintWriter(punto.getAbsolutePath() + "//Arboles" + "//" + "Arbol.dot", "UTF-8");
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
            int numero = 0;
            numero = (int) (Math.random() * 1000000000) + 1;
            ProcessBuilder abrir;
            Thread.sleep(1000);
            String rutaImagen = punto.getAbsolutePath() + "//Arboles" + "//" + "ArbolAvlImagen" + String.valueOf(numero) + ".jpg";
            String rutaDot = punto.getAbsolutePath() + "//Arboles" + "//" + "Arbol.dot";
            abrir = new ProcessBuilder("dot", "-Tjpg", "-o", rutaImagen, rutaDot);
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
        } catch (Exception e) {
            System.out.println("NO HAY NADA");
        }

    }

    public void tablaSiguientes() {
        
        
        PrintWriter escribir = null;
        try {

            File punto = new File(".");

            escribir = new PrintWriter(punto.getAbsolutePath()+"//"+"Siguientes" + "//" + "Siguiente.dot", "UTF-8");

            escribir.println("digraph G {\n"
                    + "    graph [ratio=fill];\n"
                    + "    node [label=\"\\N\", fontsize=20, shape=plaintext];\n"
                    + "    arset [label=<\n"
                    + "        <TABLE ALIGN=\"LEFT\">\n"
                    + "            <TR>\n"
                    + "                <TD>Hoja</TD>\n"
                    + "                <TD>Numero de Hoja</TD>\n"
                    + "\t      <TD>Siguientes</TD>"
                    + "            </TR>");

            int contadorColumna = -1;
            for (LinkedList<Integer> u : this.hojasArbol) {
                contadorColumna++;
                if (u != null) {
                    escribir.println("<TR>");escribir.println("<TD>");escribir.println(listaDeNodos.get(contadorColumna).tipoToken.lexema.replaceAll(">", "&#62;").replaceAll("<", "&#60;"));
           escribir.println("</TD>"); escribir.println("<TD>"); escribir.println(String.valueOf(contadorColumna));
 escribir.println("</TD>");escribir.println("<TD>"); escribir.println(u.toString()); escribir.println("</TD>");escribir.println("</TR>");
                }//else{
                //escribir.println("<TR>");escribir.println("<TD>");escribir.println(String.valueOf(contadorColumna));escribir.println("</TD>");escribir.println("<TD>");escribir.println();
                //escribir.println("</TD>");escribir.println("<TD>");escribir.println();escribir.println("</TD>");escribir.println("<TD>");escribir.println();escribir.println("</TD>");
                // escribir.println("</TR>");
                // }

            }

                            escribir.println(" </TABLE>\n"
                        + "    >, ];\n"
                + "}");

            escribir.close();
            int numero = 0;
            numero = (int) (Math.random() * 1000000000) + 1;
            ProcessBuilder abrir;
            String rutaImagen = punto.getAbsolutePath()+"//"+"Siguientes" + "//" + "TablaSiguiente" + String.valueOf(numero) + ".jpg";
            String rutaDot = punto.getAbsolutePath()+"//"+"Siguientes" + "//" + "Siguiente.dot";
            abrir = new ProcessBuilder("dot", "-Tjpg", "-o", rutaImagen, rutaDot);

            abrir.redirectErrorStream(true);
            abrir.start();

            //Ventana temporal4 = new VentanaReporte3(rutaImagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Llamar a la funcion que sacara la grafica del codigo ejecutado
        //creacionDibujo(fileName);
    }

}
