/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MilloresMVC;

/**
 *
 * @author jordimaripare
 */
public class Utilitats {
    /*Aquesta classe d'utilitats és una classe temporal per testejar funcionalitats necessàries del programa*/
    
    public static void main(String[] args){
        int cols = this.getColumns();
        System.out.println();
    }
    
    public int getColumns(){
        String s;
        try{
            s = System.getenv("COLUMNS");
            if(s == null){
                throw new NoExportatException();
            }
        }catch (NoExportatException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return Integer.parseInt(s);
    }
    public int getLines(){
        String s;
        try{
            s = System.getenv("LINES");
            if(s == null){
                throw new NoExportatException();
            }
        }catch (NoExportatException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return Integer.parseInt(s);
    }
    
}
