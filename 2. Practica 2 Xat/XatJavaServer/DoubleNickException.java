/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;

/**
 *
 * @author jordimaripare
 */
public class DoubleNickException extends Exception {

    /**
     * Creates a new instance of <code>DoubleNickException</code> without detail
     * message.
     */
    public DoubleNickException() {
    }

    /**
     * Constructs an instance of <code>DoubleNickException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DoubleNickException(String msg) {
        super(msg);
    }
}
