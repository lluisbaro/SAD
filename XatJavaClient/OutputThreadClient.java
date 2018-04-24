/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class OutputThreadClient implements Runnable{
    private Client c;
    private BufferedReader r;
    public OutputThreadClient(Client c){
        this.c = c;
        this.r = this.c.getS().getBufferedReader();
    }
    @Override
    public void run() {
        try {
            while(r.ready()){
                System.out.println(r.readLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(OutputThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
