/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;

import XatJavaClient.MySocket;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class Server implements Runnable {
    private ConcurrentHashMap<String, MySocket> mapa = new ConcurrentHashMap<String, MySocket>();
    private MyServerSocket serverSocket;
    
    public Server(int port){
        try {
            this.serverSocket = new MyServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run() {
        
    }
    
}

