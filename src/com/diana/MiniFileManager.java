package com.diana;

import java.io.File;
import java.util.*;
import java.util.Arrays;

public class MiniFileManager {


    public MiniFileManager() {
    }


    //pwd: Muestra cual es la carpeta actual.
    //---------------------------------------
    public static void getPWD(File carpetaActual) throws NoExisteFichero {
        if (carpetaActual.exists())//si la ruta existe lo imprimo por pantalla
            System.out.println("pwd: " + carpetaActual.getAbsolutePath());
        else throw new NoExisteFichero("No existe el fichero " + carpetaActual);//si no, me sale esta exception
    }

    //cd: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.
    //-------------------------------------------------------------------------
    public static void cd(File carpetaActual, File DIR) throws NoExisteFichero {
        boolean cambiaDIR = carpetaActual.renameTo(DIR);
        System.out.println("Cambiamos a la carpeta DIR: " + cambiaDIR);
        if (cambiaDIR)
            System.out.println("cd .. \n" + DIR.getParent());
        else throw new NoExisteFichero("No existe el fichero " + carpetaActual);
    }


    //ls: Muestra la lista de directorios y archivos de la carpeta actual
    //-------------------------------------------------------------------
    public static void ls(File carpeta) throws NoExisteFichero {
        if (carpeta.isDirectory()) {
            File[] vectorArchivos = carpeta.listFiles();

            Arrays.sort(vectorArchivos);//ordenamos el vector alfabeticamente
            //lo imprimo por pantalla
            for (int i = 0; i < vectorArchivos.length; i++) {
                if (vectorArchivos[i].isDirectory()) {//primero los directorios
                    System.out.print("*: " + vectorArchivos[i].getName() + " \n");
                }
            }
            for (int t = 0; t < vectorArchivos.length; t++) {
                if (vectorArchivos[t].isFile()) {//y despues los archivos
                    System.out.print("A: " + vectorArchivos[t].getName() + " \n");
                }
            }
        }else throw new NoExisteFichero("Ruta incorrecta");
    }


    //ll: Como ls pero muestra también el tamaño y la fecha de última modificación
    //----------------------------------------------------------
    public static void ll(File fichero) throws NoExisteFichero{
        long ultimaMod = fichero.lastModified();
        Date fecha = new Date(ultimaMod);

        if (fichero.isDirectory()) {
            File[] vectorArchivos = fichero.listFiles();

            Arrays.sort(vectorArchivos);//ordenamos el vector alfabeticamente
            //lo imprimo por pantalla
            for (int i = 0; i < vectorArchivos.length; i++) {
                if (vectorArchivos[i].isDirectory()) {//si es una carpeta sumamos todos los ficheros de dentro
                    float tamanoTotal = 0;
                    for (int q = 0; q < vectorArchivos.length; q++) {
                        File f = vectorArchivos[q];
                        tamanoTotal += f.length();
                    }
                    System.out.print("*: " + vectorArchivos[i].getName() + "   Última modificación (fecha): " + fecha +";   Tamaño del directorio: " + tamanoTotal +" \n");
                }
            }
            for (int t = 0; t < vectorArchivos.length; t++) {
                if (vectorArchivos[t].isFile()) {//y despues los archivos
                    System.out.print("A: " + vectorArchivos[t].getName() + "   Última modificación (fecha): " + fecha +";   Tamaño del archivo: " + fichero.length() +" \n");
                }
            }
        }else throw new NoExisteFichero("Ruta incorrecta");
    }


    //funcion que nos ayuda validar el nombre de nueva carpeta en mkdir
    static boolean esSoloLetras(String cadena) {
        //Recorremos cada caracter de la cadena y comprobamos si son letras.
        //Para comprobarlo, lo pasamos a mayuscula y consultamos su numero ASCII.
        //Si está fuera del rango 65 - 90, es que NO son letras.
        //Para ser más exactos al tratarse del idioma español, tambien comprobamos
        //el valor 165 equivalente a la Ñ

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.toUpperCase().charAt(i);
            int valorASCII = (int) caracter;
            if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
                return false; //Se ha encontrado un caracter que no es letra
        }

        //Terminado el bucle sin que se hay retornado false, es que todos los caracteres son letras
        return true;
    }


    //mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.
    //----------------------------------------------------------
    public static boolean mkdir(File directorioActual) throws NoEsLetraExcepcion {
        System.out.println("Introduzca el nombre de la carpeta que quieres crear (solo letras por favor): ");
        Scanner reader = new Scanner(System.in);
        String nombreNuevaCarpeta = reader.nextLine();
        //validar nombre de carpeta
        if (esSoloLetras(nombreNuevaCarpeta)) {

            File nuevaCarpeta = new File(directorioActual.getAbsolutePath() + "//" + nombreNuevaCarpeta);
            boolean crearDirectorio = nuevaCarpeta.mkdir();//crear nueva carpeta
            return crearDirectorio;

        } else throw new NoEsLetraExcepcion("La cadena contiene caracteres que NO son letras");
    }


    //rm <FILE>: Borra ‘FILE’
    //------------------------
    public static void rm(File fichero) throws NoExisteFichero {
        if (fichero.exists()) {//compruebo que la ruta existe
            if (fichero.isFile()) {// si es un archivo lo borro
                boolean borrar = fichero.delete();
                System.out.println("Se ha podido borrar el archivo? " + borrar);
            } else if (fichero.isDirectory()) {// si es un directorio compruebo que no tenga subdirectorios
                File[] vectorArchivos = fichero.listFiles();
                boolean tieneSubcarpetas = false;

                for (File f : vectorArchivos) {

                    if (f.isDirectory()) {
                        tieneSubcarpetas = true;//tiene subdirectorios asi que no se puede borrar
                        System.out.println("No se puede borrar porque tiene subcarpetas. ");
                        break;
                    }

                }
                if (!tieneSubcarpetas) {//no tiene subdirectorios
                    for (int i = 0; i < vectorArchivos.length; i++) {//se borran los archivos de dentro uno por uno
                        File f = vectorArchivos[i];
                        boolean borrar = f.delete();
                        System.out.println("Se borraron todos los archivos. " + borrar);
                    }

                    boolean borrar2 = fichero.delete();
                    System.out.println("La carpeta se ha borrado. " + borrar2);
                }
            }
        } else throw new NoExisteFichero("No existe el fichero " + fichero);
    }


    //mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’.
    //------------------------------------------------------
    public static boolean mv(File rutaInicial, File rutaNueva) throws NoExisteFichero {
        if (rutaInicial.exists()) {
            boolean renombrar = rutaInicial.renameTo(rutaNueva);
            return renombrar;
        } else throw new NoExisteFichero("No existe el fichero " + rutaInicial);
    }

    //help: Muestra una breve ayuda con los comandos disponibles
    //----------------------------------------------------------
    public static void help() {
        System.out.println(" ");
        System.out.println("pwd: Muestra cual es la carpeta actual.");
        System.out.println(" ");
        System.out.println("cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.");
        System.out.println(" ");
        System.out.println("ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n" +
                "archivos, ambos ordenados alfabéticamente).");
        System.out.println(" ");
        System.out.println("ll: Como ls pero muestra también el tamaño y la fecha de última modificación.");
        System.out.println(" ");
        System.out.println("mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.");
        System.out.println(" ");
        System.out.println("rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si\n" +
                "tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.");
        System.out.println(" ");
        System.out.println("mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’ ");
    }
}
