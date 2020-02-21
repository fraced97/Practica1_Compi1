/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

/**
 *
 * @author USUARIO
 */
public class Token {
    enum Tipo{
        LLAVE_ABIERTA,
        LLAVE_CERRADA,
        PORCENTAJE,
        DIAGONAL,
        MENOR_QUE,
        GUION,
        EXCLAMACION,
        MAYOR_QUE,
        DOS_PUNTOS,
        COMILLAS,
        PUNTO_COMA,
        IDENTIFICADOR,
        COMENTARIO,
        CONJUNTO,
        EXPRESION_REGULAR,
        RESERVADA_CONJ,
        EJEMPLO_EXPRESION,
        IDENTIFICADOR_CONJ;
    }
    Tipo token;
    String lexema;
    String tipo1;
    int fila;
    int columna;
    
    public Token(Tipo token, String lexema, String tipo1, int fila, int columna) {
        this.token = token;
        this.lexema = lexema;
        this.tipo1 = tipo1;
        this.fila = fila;
        this.columna = columna;
    }
    
   
    
}
