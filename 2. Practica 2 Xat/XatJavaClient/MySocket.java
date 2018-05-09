/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class MySocket extends Socket {

    public MySocket(InetAddress address, int port) {
        super();
        InetSocketAddress addr = new InetSocketAddress(address, port);
        try {
            this.connect(addr);
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return super.getOutputStream(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return super.getInputStream(); //To change body of generated methods, choose Tools | Templates.
    }

    public BufferedReader getBufferedReader() {
        BufferedReader b = null;
        try {
            b = new BufferedReader(new InputStreamReader(this.getInputStream()));

        } catch (IOException e) {
            System.err.print("Error!");
        }
        return b;
    }

    public PrintWriter getPrintWriter() {
        PrintWriter p = null;
        try {
            p = new PrintWriter(new OutputStreamWriter(this.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print("Error!");
        }
        return p;
    }

}
