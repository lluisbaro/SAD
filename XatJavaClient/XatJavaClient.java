/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

/**
 *
 * @author jordimaripare
 */
public class XatJavaClient {
    public static void main(String[] args){
        Client c = new Client();
        c.getOutput().run();
        c.getInput().run();
    }
}
