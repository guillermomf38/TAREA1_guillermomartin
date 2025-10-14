/**
 *Clase Main.java
 * 
 *@author Guillermo Martin Fueyo
 *@version 1.0
 */



package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.Perfiles;
import entidades.Sesion;

public class Main {
    private static Scanner leer=new Scanner(System.in);
    
    public static void main(String[] args) {
        
        Sesion sesionActual=new Sesion("invitado",Perfiles.INVITADO);
        int opcion;
        do {
            opcion = seleccionPerfil(sesionActual);
            sesionActual = opcionesMenu(sesionActual,opcion);
        } while(opcion!=0);
        System.out.println("Fin del programa");
    }

    private static int seleccionPerfil(Sesion sesion) {
    	
    	System.out.println("== Menu Principal ==");
        switch (sesion.getPerfil()) {
        case INVITADO:
            System.out.println("1-Ver espectaculos");
            System.out.println("2-Iniciar sesion");
            System.out.println("0-Salir");
            break;
        case ADMIN:
            System.out.println("1-Ver espectaculos");
            System.out.println("2-Cerrar sesion");
            System.out.println("3-Gestionar personas y credenciales");
            System.out.println("4-Gestionar espectaculos");
            System.out.println("0-Salir");
            break;
        case COORDINACION:
            System.out.println("1-Ver espectaculos");
            System.out.println("2-Cerrar sesion");
            System.out.println("3-Gestionar espectaculos");
            System.out.println("0-Salir");
            break;
        case ARTISTA:
            System.out.println("1-Ver espectaculos");
            System.out.println("2-Cerrar sesion");
            System.out.println("3-Ver ficha personal");
            System.out.println("0-Salir");
            break;
            
            default:System.out.println("Error");
    }
        System.out.println("Selecciona una opcion: ");
         int opcion=leer.nextInt();
		 return opcion;
         
    	}
     
    
    private static Sesion opcionesMenu(Sesion sesion, int opcion) {
        try {
            switch (sesion.getPerfil()) {

                case INVITADO:
                    switch (opcion) {
                        case 1:
                            System.out.println("Mostrando espectaculos:");
                            break;
                        case 2:
                            System.out.println("Iniciando sesion");         
                            break;
                        case 0:
                            System.out.println("Saliendo del programa");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    break;

                case ADMIN:
                    switch (opcion) {
                        case 1:
                            System.out.println("Mostrando todos los espectaculos");
                            break;
                        case 2:
                            System.out.println("Cerrando sesion");
                            
                            break;
                        case 3:
                            System.out.println("Introduce el nombre de usuario");
                            break;
                        case 4:
                        	System.out.println("Gestionando espectaculo");
                        	break;
                        default:
                            System.out.println("Opción no valida");
                    }
                    break;

                case COORDINACION:
                    switch (opcion) {
                        case 1:
                            System.out.println("Mostrando espectaculos");
                            break;
                        case 2:
                            System.out.println("Cerrando sesion");
                            
                            break;
                        case 3:
                            System.out.println("Gestionando espectaculo");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    break;

                case ARTISTA:
                    switch (opcion) {
                        case 1:
                            System.out.println("Mostrando espectaculos");
                            break;
                        case 2:
                            System.out.println("Cerrando sesion");
                         
                            break;
                        case 3:
                            System.out.println("Mostrando ficha");
                            break;
                        default:
                            System.out.println("Opcion no valida");
                    }
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Introduce una opcion valida" );
        }

        return sesion;
    }
}
