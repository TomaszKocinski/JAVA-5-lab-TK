/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

/**
 *
 * @author pkacz_000
 */
public class MiastoNieIstniejeException extends Exception {
     /**
     * Creates a new instance of <code>sda</code> without detail message.
     */
    public MiastoNieIstniejeException() {
    }

    /**
     * Constructs an instance of <code>sda</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public MiastoNieIstniejeException(String msg) {
        super(msg);
    }
}
