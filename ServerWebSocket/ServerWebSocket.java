/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerWebSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class ServerWebSocket {
    public static void main(String[] args){
        try {
            MyServerSocket server = new MyServerSocket(5000);
            MySocket s = server.accept();
            BufferedReader r = s.getBufferedReader();
            System.out.println(r.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ServerWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
