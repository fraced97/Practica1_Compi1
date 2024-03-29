/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static practica1olc1.VentanaPrincipal.analizar;
import static practica1olc1.VentanaPrincipal.listaJlist;

/**
 *
 * @author USUARIO
 */
public class AnalizadoLexico {
    public  LinkedList<Token> listaTokens = new LinkedList<>();
    public static LinkedList<ErroresLexicos> listaErrores = new LinkedList<>();
    public static LinkedList<TokenER> listaTokensER = new LinkedList<>();
    public void Analizar(String x){
        String aux="";
        char variable;
        String lexema="";
        int fila = 1;
        int columna = 0;
        int estadoActual =0;
        limpiarLista();
        
        for (int i = 0; i < x.length(); i++) {
            variable = x.charAt(i);
            columna++;
            switch(estadoActual){
                case 0:
                    //System.out.println("ENTRO A");
                    if(variable =='{'){
                        //estadoActual=1;
                        estadoActual=0;
                        lexema= lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.LLAVE_ABIERTA,lexema,"Llave Abierta",fila,columna));
                        lexema="";
                        //lexema= lexema + variable;
                        //listaTokens.add(new Token(Token.Tipo.LLAVE_ABIERTA,lexema,"Llave Abierta",fila,columna));
                    }else if(variable == '}'){
                        //estadoActual=1;
                        estadoActual=0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.LLAVE_CERRADA,lexema,"Llave Cerrada",fila,columna));
                        lexema ="";
                        //lexema = lexema + variable;
                        //listaTokens.add(new Token(Token.Tipo.LLAVE_CERRADA,lexema,"Llave Cerrada",fila,columna));
                        //lexema="";
                        
                    
                    }else if(variable == '%'){
                        estadoActual = 2;
                        lexema = lexema + variable;
                    
                    }else if(variable == '/'){
                        //System.out.println(variable+"  +++++++");
                        estadoActual =4;
                        lexema = lexema + variable;
                    
                    }else if(variable == '<'){
                        estadoActual = 6;
                        lexema = lexema + variable;
                        
                    }else if(Character.isLetter(variable)){
                        
                         estadoActual =10;
                         lexema = lexema+variable;
                    
                    }else if(variable == '\n'){
                        columna=0;
                        estadoActual=0;
                        fila++;
                        
                    }else if(variable == ' ' || variable == '\t'){
                        estadoActual=0;
                    }else if(variable=='#'){
                        estadoActual=0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.NUMERAL,lexema,"Numeral",fila,columna));
                        lexema ="";
                    
                    }else{
                        lexema=lexema+variable;
                        estadoActual=0;
                        listaErrores.add(new ErroresLexicos(lexema,"Lexico","Elemento desconocido",fila,columna));
                        lexema="";
                                
                    }
                    break;
                /*case 1:
                    if(variable== '{'){
                        estadoActual=0;
                        lexema= lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.LLAVE_ABIERTA,lexema,"Llave Abierta",fila,columna));
                        lexema="";
                    }else if(variable=='}'){
                        estadoActual=0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.LLAVE_CERRADA,lexema,"Llave Cerrada",fila,columna));
                        lexema ="";
                    }
                    break;*/
                case 2:
                    if(variable=='%'){
                        estadoActual=0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.PORCENTAJE,lexema,"Porcentaje",fila,columna));
                        lexema ="";
                        
                    }
                    break;
                /*case 3:
                    estadoActual=0;
                    lexema = lexema + variable;
                    listaTokens.add(new Token(Token.Tipo.PORCENTAJE,lexema,"Porcentaje",fila,columna));
                    lexema ="";
                    break;*/
                case 4:
                    if(variable=='/'){
                       estadoActual=5;
                       lexema = lexema + variable;
                      //System.out.println(variable + "   ... ENTRO EN OTRO SLASH");
                    }
                    break;
                case 5:
                    if(variable == '\n'){
                        //System.out.println("ENTRO AQUI EN SALTO DE LINEA");
                        
                        estadoActual=0;
                        lexema = lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.COMENTARIO,lexema,"Comentario",fila,columna));
                        //System.out.println(lexema);
                        lexema ="";
                        fila++;
                        columna =0;
                    }else {
                        //System.out.println("CONCATENANDO LETRAS EN COMENTARIO DE LINEA");
                        
                        estadoActual= 5;
                        lexema = lexema+ variable;
                        //System.out.println(lexema);
                        //listaTokens.add(new Token(Token.Tipo.PORCENTAJE,lexema,"Llave Cerrada",fila,columna));
                        //lexema ="";
                    }
                    break;
                case 6:
                    if(variable=='!'){
                        estadoActual = 7;
                        lexema = lexema + variable;
                    }
                    break;
                case 7:
                    if(variable=='!'){
                        estadoActual = 8;
                        lexema = lexema + variable;
                    }else if(variable=='\n'){
                        estadoActual = 7;
                        fila++;
                        lexema = lexema + variable;
                    }else{
                        estadoActual = 7;
                        lexema= lexema + variable;
                    }
                    break;
                    
                case 8:
                    if(variable == '>'){
                        estadoActual=0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.COMENTARIO,lexema,"Comentario",fila,columna));
                        lexema="";
                    }
                    break;
                    
                /*case 9:
                    listaTokens.add(new Token(Token.Tipo.COMENTARIO,lexema,"Comentario",fila,columna));
                    lexema="";
                    estadoActual=0;
                    break;*/
                            
                case 10:
                    if(Character.isDigit(variable)){
                       estadoActual = 10;
                       lexema = lexema + variable;
                    }else if(Character.isLetter(variable)){
                        lexema = lexema + variable;
                        if(buscarPR(lexema,fila,columna)){
                            estadoActual =20;
                            //lexema=lexema+variable;
                        }else{
                            estadoActual = 10;
                        }
                        
                        //lexema = lexema + variable;
                    }else if(variable == '_'){
                        estadoActual = 10;
                        lexema = lexema + variable;
                    }else if(variable==':'){
                        listaTokens.add(new Token(Token.Tipo.IDENTIFICADOR,lexema,"Identificador",fila,columna-1));
                        lexema="";
                        estadoActual = 14;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.DOS_PUNTOS,lexema,"Dos puntos",fila,columna));
                        lexema="";
                    }else if(variable == '-'){
                        listaTokens.add(new Token(Token.Tipo.IDENTIFICADOR,lexema,"Identificador",fila,columna-1));
                        lexema="";
                        estadoActual =11;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.GUION,lexema,"Guion",fila,columna));
                        lexema="";
                        
                    }
                    break;
                case 11:
                    if(variable == '>'){
                        estadoActual=12;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.MAYOR_QUE,lexema,"Mayor Que",fila,columna));
                        lexema="";
                        
                    }
                    break;
                case 12:
                    if(variable==';'){
                        listaTokens.add(new Token(Token.Tipo.EXPRESION_REGULAR,lexema,"Expresion Regular",fila,columna-1));
                        lexema="";
                        estadoActual =0;
                        lexema = lexema + variable;
                        listaTokens.add(new Token(Token.Tipo.PUNTO_COMA,lexema,"Punto y Coma",fila,columna));
                        lexema="";
                    }else{
                        estadoActual =12;
                        lexema = lexema + variable;
                    }
                    break;
                /*case 13:
                    listaTokens.add(new Token(Token.Tipo.CONJUNTO,lexema,"Conjuntos",fila,columna));
                    lexema="";
                    estadoActual=0;
                    break;*/
                case 14:
                    if(variable=='"'){
                        estadoActual = 15;
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.COMILLAS,lexema,"Comillas",fila,columna));
                        lexema="";
                    }/*else if(Character.isLetter(variable)){
                        estadoActual=10;
                        lexema=lexema+variable;
                    }*/
                    break;
                case 15:
                    if(variable=='"'){
                        listaTokens.add(new Token(Token.Tipo.EJEMPLO_EXPRESION,lexema,"Ejemplo Expresion",fila,columna-1));
                        lexema="";
                        estadoActual=0;
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.COMILLAS,lexema,"Comillas",fila,columna));
                        lexema="";
                        //lexema=lexema+variable;
                    }else{
                        estadoActual=15;
                        lexema= lexema+variable;
                    }
                    break;
                /*case 16:
                    listaTokens.add(new Token(Token.Tipo.EXPRESION_REGULAR,lexema,"Expresion Regular",fila,columna));
                    lexema="";
                    estadoActual=0;
                    break;*/
                /*case 17:
                    if(variable=='-'){
                        lexema="";
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.GUION,lexema,"Guion",fila,columna));
                        lexema="";
                        estadoActual = 18;
                        
                    }*/
                    //break;
                case 18:
                    if(variable=='>'){
                       lexema=lexema+variable;
                       listaTokens.add(new Token(Token.Tipo.MAYOR_QUE,lexema,"Mayor que",fila,columna));
                       lexema="";
                       estadoActual=19;
                    }
                    break;
                    
                case 19:
                    if(variable==';'){
                        listaTokens.add(new Token(Token.Tipo.CONJUNTO,lexema,"Conjunto",fila,columna-1));
                        lexema="";
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.PUNTO_COMA,lexema,"Punto y coma",fila,columna));
                        lexema="";
                        estadoActual=0;
                    }else{
                       lexema=lexema+variable;
                       estadoActual=19;
                    }
                    break;
                case 20:
                    if(variable==':'){
                        lexema="";
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.DOS_PUNTOS,lexema,"Dos puntos",fila,columna));
                        lexema="";
                        estadoActual=21;
                    }
                    break;
                case 21:
                    if(Character.isLetter(variable)){
                        estadoActual=21;
                        lexema=lexema+variable;
                    }else if(Character.isDigit(variable)){
                        estadoActual=21;
                        lexema=lexema+variable;
                    }else if(variable=='_'){
                        estadoActual=21;
                        lexema=lexema+variable;
                    }else if(variable=='-'){
                        listaTokens.add(new Token(Token.Tipo.IDENTIFICADOR_CONJ,lexema,"Identificador Conj",fila,columna-1));
                        lexema="";
                        lexema=lexema+variable;
                        listaTokens.add(new Token(Token.Tipo.GUION,lexema,"Guion",fila,columna));
                        lexema="";
                        estadoActual = 18;
                    }
                    break;
                    
            }   
            
            
        }
        
        /*for(int i=0;i<listaTokens.size();i++ ){
            System.out.println(listaTokens.get(i));
        }*/
        for (Token i : listaTokens) {
            System.out.println(i.lexema+"   #-#-#-#-#  "+i.tipo1+"   #-#-#-#  "+String.valueOf(i.fila)+"   #-#-#-#  "+String.valueOf(i.columna)+"   #-#-#-#  "+String.valueOf(i.token)+"\n");
        }
        
        
        
        
    }
    
    public void AnalizarER() throws InterruptedException{
        String lexemaLetra="";
        String lexema="";
        String lexemaAux="";
        int nExpresion=0;
        int estado=0;
        int auxContador=0;
        int contador=0;
        //Token auxContador;
        for (Token i : listaTokens) {
            if(String.valueOf(i.token).equals("EXPRESION_REGULAR")){
                
                nExpresion++;
                lexemaAux="."+i.lexema+"#";
                for (int j = 0; j < lexemaAux.length(); j++) {
                    auxContador=j;
                    char c = lexemaAux.charAt(j);
                    switch(estado){
                        case 0:
                            if(c=='*'){
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.ASTERISCO,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else if(c=='+'){
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.MAS,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else if(c=='|'){
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.OR,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else if(c=='?'){
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.INTERROGACION,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else if(c=='.'){
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.PUNTO,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            
                            }else if(c=='{'){
                                estado=2;
                            }else if(c==' '||c=='\t'){
                                estado=0;
                            }else if(c=='"'){
                                estado=1;
                            }else if(c=='#'){
                            
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.NUMERAL,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                        try {
                            Arbol arbol = new Arbol(analizar.listaTokensER);
                            int numero = 0;
                            contador++;
                            numero = (int) (Math.random() * 100) + 1;
                            if((numero%2)==0){
                                JOptionPane.showMessageDialog(null, "Expresion "+String.valueOf(contador)+" Correcta");
                            }else{
                                JOptionPane.showMessageDialog(null, "Expresion "+String.valueOf(contador)+" InCorrecta");
                            }
                           //VentanaPrincipal ventana = new VentanaPrincipal();
                            //ventana.obtenerEjemploLexema(numero, contador);
                            //ventana.setVisible(true);
                            
                            arbol.tablaSiguientes();
                            analizar.listaTokensER.clear();
                        } catch (IOException ex) {
                            Logger.getLogger(AnalizadoLexico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            }/*else if(c=='a'||c=='A'){
                                for (int k = auxContador; k < lexemaAux.length(); k++) {
                                    lexemaLetra=lexemaLetra+lexemaAux.charAt(k);
                                    for (Token n : listaTokens) {
                                        if(lexemaLetra.equals(n.lexema)){                                           
                                            listaTokensER.add(new TokenER(TokenER.TipoER.IDENTIFICADOR,lexemaLetra));
                                            lexemaLetra="";
                                            estado=0;
                                            break;
                                        }
                                    }
                                    
                                }
                                
                            }*/else{
                                lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.SIMBOLO,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }
                        break;
                        case 1:
                            if(c=='"'){
                                //lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.CADENA,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else{
                                lexema=lexema+c;
                                estado=1;
                            }
                            break;
                        case 2:
                            if(c=='}'){
                                //lexema=lexema+c;
                                listaTokensER.add(new TokenER(TokenER.TipoER.IDENTIFICADOR,lexema,"Expresion "+String.valueOf(nExpresion)));
                                lexema="";
                                estado=0;
                            }else{
                                lexema=lexema+c;
                                estado=2;
                            }
                            break;
                    }
                    ////////ME QUEDE AQUI
                    
                    
                }
            }
        }
        System.out.println("----------------------LISTA EXPRESION REGULARES ----------------------");
        
        for (TokenER i : listaTokensER) {
            System.out.println(i.lexema+"  ===============   "+String.valueOf(i.tipo)+"    ====================    "+i.nExpresion+"\n");
        }
    }
    
    
    public void limpiarLista(){
        listaTokens.clear();
        listaErrores.clear();
        listaTokensER.clear();
        listaJlist.clear();
        System.out.println("ESTA LIMPIO");
    }
    public boolean buscarPR(String lexema, int fila, int columna){
        boolean devolver=false;
        switch(lexema){
            case "CONJ":
                listaTokens.add(new Token(Token.Tipo.RESERVADA_CONJ,lexema,"Reservada",fila,columna));
                devolver=true;
        }
        return devolver;
    }
    
}
