package com.diana;

/**
 * Sencilla clase que hereda de Exception para crear una excepcion
 * que avise en el caso de que la cadena contenga caracteres que no son letras
 */

public class NoEsLetraExcepcion extends Exception {
    public NoEsLetraExcepcion(String mensajeExcepcion) {
        super(mensajeExcepcion);
    }
}
