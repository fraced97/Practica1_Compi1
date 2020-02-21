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
public class ErroresLexicos {

    public ErroresLexicos(String error, String tipo, String descripcion, int fila, int columna) {
        this.error = error;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
    }
    String error;
    String tipo;
    String descripcion;
    int fila;
    int columna;
    
}
