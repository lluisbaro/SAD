/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jordimaripare
 */
public class Server {
    private ConcurrentHashMap<String, MySocket> mapa = new ConcurrentHashMap<String, MySocket>();
}
