/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.wmp.prja.laboratorium5;

/**
 *
 * @author pkacz_000
 */
public class MiastoIstniejeException extends Exception {
        public MiastoIstniejeException() {
    }

    /**
     * Constructs an instance of <code>sda</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public MiastoIstniejeException(String msg) {
        super(msg);
    }
}
