/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;
import java.util.*;
import java.io.*;

/**
 *
 * @author jhonger
 */
public class Sudoku {
    
  static List<Solucion> soluciones=new ArrayList<Solucion>();
          
    public static int casilla(int x){
    
        int k;
         k= x / 3;
         switch(k){
         
             case 0:return 0;
             case 1:return 3;
             default:return 6;
         
         }
  
    }
    
    public static void sudoku(int matriz[][]){
        
    boolean inicial[][]= new boolean[matriz.length][matriz.length];
    
    for(int i=0; i<matriz.length;i++){
        
        for(int j=0;j<matriz[i].length;j++)
            
            inicial[i][j]= (matriz[i][j]==0);
    }
    
   solucion(0,0,matriz,inicial);
    }
    
    
    public static void solucion(int i,int j,int matrizS[][],boolean inicial[][]){
        if(inicial[i][j]){
          
            for (int k = 1; k <= 9; k++) {
                matrizS[i][j]=k;
                if(SePuede(i, j, matrizS)){
                   
                    if(i==8 && j==8){
                        int aux[][]=matrizS;
                        mostrar(matrizS);
                    }
                   if(i<8 && j==8)
                        solucion(i+1, 0, matrizS, inicial);
                   
                    if(i<=8 && j<8)
                        solucion(i,j+1, matrizS, inicial);      
                }
                matrizS[i][j]=0;
            }
        } else {
           
              if(i==8 && j==8){
                  mostrar(matrizS);
              }
              if(i<8 && j==8)
                  solucion(i+1, 0, matrizS, inicial);
              if(i<=8 && j<8)
                  solucion(i,j+1, matrizS, inicial);    
          
        }
   }
    
    
    public static void mostrar(int matriz[][]){
        int aux[][]=new int[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%d ", matriz[i][j]);
                aux[i][j]=matriz[i][j];
            }
        System.out.print("\n");
        }
         System.out.print("\n");
          System.out.print("\n");
          
        soluciones.add(new Solucion(aux));
    }
    
    
    public static boolean SePuede(int i,int j,int matriz[][]){
        boolean valido=true;
        int k=0,l=0;
        
        while(k<8 && valido){       //revisa la columna
            if(matriz[i][j] == matriz[k][j] && i!=k)
                valido=false;
            k++;
                
        }
        while(l<8 && valido){       //revisa la columna
            if(matriz[i][j] == matriz[i][l] && j!=l)
                valido=false;
            l++;
                
        }
        
        k=casilla(i);
        while(k<(casilla(i)+3) && valido){
           
            while(l<(casilla(j)+3) && valido){
                
                if(matriz[i][j]==matriz[k][l] && k != i && l != j)
                    valido=false;
                l++;
            
            }
            l=casilla(j);
            k++;

        }
        return valido;
    
    
    }

    private static void guardar(List<Solucion> soluciones) {
       FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("soluciones.txt");
            pw = new PrintWriter(fichero);
            
            
             pw.println("Sudoku\n");
                pw.println(soluciones.get(0));
                 pw.println("\n----------------------------------");
                 
            for (int i = 1; i < soluciones.size() ; i++){
                 pw.println("solucion n°"+i+" \n");
                pw.println(soluciones.get(i));
                 pw.println("\n----------------------------------");
                
            }
            
            pw.println("numero de soluciones totales ="+soluciones.size()+" ");
            
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
  
      private static int[][] leerArchivo() {
        int sudoku[][]=new int [9][9];
        
         File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("sudokus.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;int i=0;
         while((linea=br.readLine())!=null){
              int j=0;
          
             for (StringTokenizer stringTokenizer = new StringTokenizer(linea," \n,."); stringTokenizer.hasMoreTokens();) {
                 String token = stringTokenizer.nextToken();
                 sudoku[i][j]=Integer.parseInt(token);
                 j++;
             }
             i++;
         }
      }
      catch(Exception e){
         e.printStackTrace();
         System.out.print("error al cargar");
      }finally{
         try{                   
            if( null != fr ){  
               fr.close();    
            }                 
         }catch (Exception e2){
            e2.printStackTrace();
         }
      }
        return sudoku;
    }

      
   
    public static void main(String[] args) {
     
     
      int prueba[][]=leerArchivo();
        System.out.print("sudoku original \n"); 
       mostrar(prueba);
       System.out.print("sudoku lleno \n"); 
       sudoku(prueba);
       System.out.print("Numero de soluciones: "+soluciones.size()+" \n"); 
       guardar(soluciones);
    }

  
}
