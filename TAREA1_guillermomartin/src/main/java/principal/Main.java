/**
 *Clase Main.java
 * 
 *@author Guillermo Martin Fueyo
 *@version 1.0
 */



package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.Credenciales;
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
   private static Credenciales comprobarLogin(String nombre, String password) {
  	if(nombre.equals("admin") && password.equals("admin")) {
    		Credenciales admin= new Credenciales();
    		admin.setId(0L);
    		admin.setNombre("admin");
    		admin.setPassword("admin");
    		admin.setPerfil(Perfiles.ADMIN);
    		System.out.println("Te has autenticado como admin");
    		return admin;
    	}
		return null;
    	
    }
    private static Sesion login(Sesion sesionActual) {
    	if(sesionActual.getPerfil()!= Perfiles.INVITADO) {
    		System.out.println("Hay una sesion ya iniciada");
    		return sesionActual;
    	}
    	System.out.println("Inicio Sesion: ");
    	System.out.println("Nombre: ");
    	String nombre=leer.next();
        System.out.println("Contraseña: ");
        String password=leer.next();
        Credenciales credencial=comprobarLogin(nombre,password);
        if(credencial!=null) {
        	System.out.println("Bienvenido: "+ credencial.getNombre()+" - "+credencial.getPerfil());
        	
        	return new Sesion(credencial.getNombre(),credencial.getPerfil());
        }
        else {
        	System.out.println("El usuario o la contraseña son incorrectos");
        	
        	return sesionActual;
        }
    	
	
    	
    }
    
}
