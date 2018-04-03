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
public class NoExportatException extends Exception {

    /**
     * Creates a new instance of <code>NoExportat</code> without detail message.
     */
    public NoExportatException() {
    }

    /**
     * Constructs an instance of <code>NoExportat</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoExportatException(String msg) {
        super(msg);
    }
    @Override
    public String getMessage(){
        return "Si no et trobes en un terminal de Linux aquest programa no és compatible!\n"
                + "En cas de ser-hi has d'executar les següents comanes perquè el codi funcioni:\n"
                + "export $COLUMNS\n"
                + "export $LINES\n"
                + "D'aquesta manera podem veure les columnes i línies del teu terminal.";
    }
}
