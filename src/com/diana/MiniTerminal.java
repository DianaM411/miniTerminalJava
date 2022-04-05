package com.diana;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class MiniTerminal {

    public static void main(String[] args) throws Exception, NoExisteFichero, NoEsLetraExcepcion {
        Scanner leer = new Scanner(System.in);
        MiniFileManager fileManager = null;

        //Objetos tipo File para hacer pruebas con el menu
        //!OJO! Hay que cambiar las rutas por rutas que existen en tu ordena ;) No borres nada importante XD
        //---------------------------------------------------------------------
        File directorioPWD = new File("C:\\Users\\DAW\\Desktop\\Documentos");
        File ficheroCD = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shorts");
        File DIR = new File("C:\\Users\\DAW\\Desktop\\DIR");
        File directorioActual = new File("C:\\Users\\DAW\\Desktop\\programacion");
        File ficheroBorrar = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\borra2");
        File ficheroRenombrar = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\nombre1");
        File ficheroRenombrar2 = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\nombre2");


        boolean bucleMenu = true;

        while (bucleMenu) {

            try { // Instrucciones que podrían lanzar una excepción.
                String opcion;
                opcion = leer.nextLine();


                switch (opcion) {
                    case "pwd":
                        System.out.println("----------------------------------------");
                        fileManager.getPWD(directorioPWD);
                        break;
                    case "cd" :
                        System.out.println("----------------------------------------");
                        fileManager.cd(directorioPWD, DIR);
                        break;
                    case "ls" :
                        System.out.println("----------------------------------------");
                        fileManager.ls(directorioActual);
                        break;
                    case "ll":
                        System.out.println("----------------------------------------");
                        fileManager.ll(directorioActual);
                        break;
                    case "mkdir" :
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mkdir(directorioActual) ? "Se ha creado correctamente" : "No se puede crear una carpeta aqui. Elige otro directorioActual.");
                        break;
                    case "rm":
                        System.out.println("----------------------------------------");
                        fileManager.rm(ficheroBorrar);
                        break;
                    case "mv":
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mv(ficheroRenombrar, ficheroRenombrar2) ? "Se ha renombrado correctamente" : "No se puede.");
                        break;
                    case "help":
                        System.out.println("--------------------Manual--------------------");
                        fileManager.help();
                        break;
                    case "exit":
                        bucleMenu = false;
                        break;

                    default:
                        System.out.println("Command not found");
                }


            } catch (NoExisteFichero e) { // Instrucciones que se ejecutan cuando ‘try’ lanza una excepción.
                System.err.println("Error: " + e.getMessage()); //Devuelve un String con un texto simple sobre el error.
            } catch (NoEsLetraExcepcion e2) {
                System.err.println("Error: " + e2.getMessage());
            } catch (Exception e3) {
                System.err.println("Error: " + e3.getMessage());//con esto estoy intentando atrapar cualquier otro error
            }
        } System.out.println("That´s all folks!");//fin programa
    }
}
