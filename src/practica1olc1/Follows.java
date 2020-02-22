/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1olc1;

import java.util.LinkedList;
import practica1olc1.Arbol.Nodo;

/**
 *
 * @author USUARIO
 */
public class Follows {
    private Nodo nodo;
    private LinkedList<Integer> siguientes;

    Nodo getNodo() {
        return nodo;
    }

    void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public LinkedList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(LinkedList<Integer> siguientes) {
        this.siguientes = siguientes;
    }

    public Follows() {
    }

    
    
    
}
