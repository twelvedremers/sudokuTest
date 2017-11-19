/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author jhonger
 */
public class Solucion {
    
    int elemento[][];

    public Solucion(int[][] elemento) {
       
        this.elemento = elemento;
    }

    @Override
    public String toString() {
       String palabra="";
        for (int i = 0; i < elemento.length; i++) {
            for (int j = 0; j < elemento[i].length; j++) 
            palabra += " "+elemento[i][j];
        palabra+="\n\n";
            
        }
        return palabra; 
    }
    
    
    
}
