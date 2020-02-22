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
public class TokenER {
    
    enum TipoER{
        ASTERISCO,
        OR,
        MAS,
        INTERROGACION,
        CADENA,
        SIMBOLO,
        IDENTIFICADOR,
        PUNTO,
        NUMERAL;
        
        
        
    }
    TipoER tipo;
    String lexema;
    String nExpresion;
    
    public TokenER(TipoER tipo, String lexema,String nExpresion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.nExpresion=nExpresion;
    }

    
    
    
    
}
