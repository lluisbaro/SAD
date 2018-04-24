/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordimaripare
 */
public class XatJavaClient {
    //Al executar XatJavaClient s'ha d'especificar el arguments "adreça_ip" "port" "nick"
    public static void main(String[] args){
        try {
            InetAddress ip = InetAddress.getByName(args[0]);
            int port  = Integer.parseInt(args[1]);
            Client c = new Client(ip, port, args[2]);
            c.getOutput().run();
            c.getInput().run();
        } catch (UnknownHostException ex) {
            System.out.println("L'adreça IP donada no existeix!");
        }
    }
}
