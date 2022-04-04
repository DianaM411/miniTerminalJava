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
        File directorioActual = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts");
        File ficheroBorrar = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\borra2");
        File ficheroRenombrar = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\nombre1");
        File ficheroRenombrar2 = new File("C:\\Users\\diana\\OneDrive\\Desktop\\shortcuts\\nombre2");


        boolean bucleMenu = true;

        while (bucleMenu) {

            System.out.println("      ");
            System.out.println("Que accion quieres realizar?");
            System.out.println("   ");
            System.out.println("1. pwd ");
            System.out.println("2. cd <DIR> ");
            System.out.println("3. ls ");
            System.out.println("4. ll ");
            System.out.println("5. mkdir <DIR> ");
            System.out.println("6. rm <FILE> ");
            System.out.println("7. mv <FILE1> <FILE2> ");
            System.out.println("8. help ");
            System.out.println("9. salir.");
            System.out.println("   ");

            int opcion;

            try { // Instrucciones que podrían lanzar una excepción.

                while (!leer.hasNextInt()) {

                    System.out.println("Error, introduce un número del '1' al '9' ");
                    leer.next();
                }
                opcion = leer.nextInt();


                switch (opcion) {
                    case 1:
                        System.out.println("----------------------------------------");
                        fileManager.getPWD(directorioPWD);
                        break;
                    case 2:
                        System.out.println("----------------------------------------");
                        fileManager.cd(directorioPWD, DIR);
                        break;
                    case 3:
                        System.out.println("----------------------------------------");
                        fileManager.ls(directorioActual);
                        break;
                    case 4:
                        System.out.println("----------------------------------------");
                        fileManager.ll(directorioActual);
                        break;
                    case 5:
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mkdir(directorioActual) ? "Se ha creado correctamente" : "No se puede crear una carpeta aqui. Elige otro directorioActual.");
                        break;
                    case 6:
                        System.out.println("----------------------------------------");
                        fileManager.rm(ficheroBorrar);
                        break;
                    case 7:
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mv(ficheroRenombrar, ficheroRenombrar2) ? "Se ha renombrado correctamente" : "No se puede.");
                        break;
                    case 8:
                        System.out.println("--------------------Manual--------------------");
                        fileManager.help();
                        break;
                    case 9:
                        bucleMenu = false;
                        break;

                    default:
                        System.out.println("ERROR!Esta operacion no existe.");
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
