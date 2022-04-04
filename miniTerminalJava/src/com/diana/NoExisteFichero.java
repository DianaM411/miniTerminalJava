package com.diana;

import java.io.File;
//se que existe FileNotFoundException pero queria probar con mis excentions
public class NoExisteFichero extends Exception {
    File archivo;

    public NoExisteFichero(String message) {
        super(message);
    }

    public String toString() {
        return "Fichero no existe: " + this.archivo;
    }
}
