/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class InputThreadClient implements Runnable{
    private Client c;
    private PrintWriter p;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public InputThreadClient(Client c) {
        this.c = c;
        this.p = this.c.getS().getPrintWriter();        
    }

    @Override
    public void run() {
        String linia = null;
        try {
            while ((linia = in.readLine()) != null){
                p.print(linia);
                System.out.println(linia);
                p.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(InputThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
