/**
 *Clase Main.java
 *
 *@author Guillermo Martin Fueyo
 *@version 1.0
 */

package principal;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utils.ComparadorNombreEspectaculo;

import entidades.Credenciales;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Sesion;

public class Main {
	private static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {

		Sesion sesionActual = new Sesion("invitado", Perfiles.INVITADO);
		int opcion;
		do {
			opcion = seleccionPerfil(sesionActual);
			sesionActual = opcionesMenu(sesionActual, opcion);
		} while (opcion != 0);
		System.out.println("Fin del programa");
	}

	private static int seleccionPerfil(Sesion sesion) {
		while (true) {
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

			default:
				System.out.println("Error");
			}

			System.out.println("Selecciona una opcion: ");
			try {
				int opcion = leer.nextInt();
				leer.nextLine();
				return opcion;
			} catch (InputMismatchException e) {
				System.out.println("Formato invalido, introduce un numero correcto");
				leer.nextLine();
			}

		}
	}

	private static Sesion opcionesMenu(Sesion sesion, int opcion) {
		try {
			switch (sesion.getPerfil()) {

			case INVITADO:
				switch (opcion) {
				case 1:
					System.out.println("Mostrando espectaculos:");
					mostrarEspectaculos();
					break;
				case 2:
					System.out.println("Iniciando sesion");
					sesion = login(sesion);
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
					mostrarEspectaculos();
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesion = cerrarSesion();

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
					mostrarEspectaculos();
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesion = cerrarSesion();

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
					mostrarEspectaculos();
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesion = cerrarSesion();

					break;
				case 3:
					System.out.println("Mostrando ficha");
					break;
				default:
					System.out.println("Opcion no valida");
				}
				break;
			}

		} catch (Exception e) {
			System.out.println("Introduce una opcion valida");
		}

		return sesion;
	}

	private static Credenciales comprobarLogin(String nombre, String password) {
		if (nombre.equals("admin") && password.equals("admin")) {
			Credenciales admin = new Credenciales();
			admin.setId(0L);
			admin.setNombre("admin");
			admin.setPassword("admin");
			admin.setPerfil(Perfiles.ADMIN);
			System.out.println("Te has autenticado como admin");
			return admin;
		}

		try (BufferedReader br = new BufferedReader(
				new FileReader("ficheros/credenciales.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("\\|");
				if (partes.length < 7)
					continue;
				String nombreUsuario = partes[1].trim();
				String pass = partes[2].trim();
				String perfil = partes[6].trim();

				if (nombre.equals(nombreUsuario) && password.equals(pass)) {
					Credenciales credencial = new Credenciales();
					credencial.setId(Long.parseLong(partes[0]));
					credencial.setNombre(nombreUsuario);
					credencial.setPassword(pass);
					try {
						credencial.setPerfil(
								Perfiles.valueOf(perfil.toUpperCase()));
					} catch (IllegalArgumentException e) {
						credencial.setPerfil(Perfiles.INVITADO);
					}
					System.out.println(
							"Te has autenticado como: " + nombreUsuario);
					return credencial;
				}
			}
		} catch (IOException e) {
			System.out.println("Error leyendo el fichero credenciales.txt");
		}

		return null;
	}

	private static Sesion login(Sesion sesionActual) {
		if (sesionActual.getPerfil() != Perfiles.INVITADO) {
			System.out.println("Hay una sesion ya iniciada");
			return sesionActual;
		}

		System.out.println("Nombre: ");
		String nombre = leer.next();
		System.out.println("Contraseña: ");
		String password = leer.next();
		if (nombre.isEmpty() || password.isEmpty()) {
			System.out.println(
					"El nombre o la contraseña no pueden estar vacios");
			return sesionActual;
		}
		Credenciales credencial = comprobarLogin(nombre, password);
		if (credencial != null) {
			System.out.println("Bienvenido: " + credencial.getNombre() + " - "
					+ credencial.getPerfil());

			return new Sesion(credencial.getNombre(), credencial.getPerfil());
		} else {
			System.out.println("El usuario o la contraseña son incorrectos");

			return sesionActual;
		}
	}

	private static Sesion cerrarSesion() {
		System.out.println("Has cerrado Sesion");
		return new Sesion("invitado", Perfiles.INVITADO);

	}

	private static void mostrarEspectaculos() {
		List<Espectaculo> listaEspectaculos = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("espectaculos.dat"))) {
			while (true) {
				Espectaculo espec = (Espectaculo) ois.readObject();
				listaEspectaculos.add(espec);
			}
		} catch (EOFException eof) {

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero espectaculos.dat");
			return;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer el fichero");
			return;
		}

		if (listaEspectaculos.isEmpty()) {
			System.out.println("Todavía no hay espectáculos para visualizar");
			return;
		}

		Collections.sort(listaEspectaculos, new ComparadorNombreEspectaculo());

		for (Espectaculo espe : listaEspectaculos) {
			System.out.println(espe);
		}
	}
	private static void ficheroCredenciales()  {
		 File fichero = new File("ficheros/credenciales.txt");
		 try (PrintWriter writer = new PrintWriter(fichero)) {
			 writer.println("1|luisdbb|miP@ss|luisdbb@educastur.org|Luis de Blas|España|coordinacion ");
			 writer.println("2|camila|cam1las|camilas@circo.es|Camila Sánchez|Bolivia|artista ");
			 writer.println("3|patrigc|patri|patriciagc@circo.es|Patricia González|España|artista");
			 writer.println("4|alberto|alberto|alberto@circo.es|Alberto García|España|artista ");
		 } catch (IOException e) {
             System.out.println("Error creando fichero de credenciales");
         }
			 
			 
		 
		 }
	private static void registrarPersona() {
		System.out.println("--Registro de nueva Persona--");
		try {
			Map<String,String>paises =leerPaises();
			if(paises.isEmpty()) {
				System.out.println("No hay paises disponibles");
				
			}
			 System.out.print("Introduce el ID del país:");
	            leer.nextLine();
	            String idPais = leer.nextLine().trim();
	            String nacionalidad = paises.get(idPais);
	            if (nacionalidad == null) {
	                System.out.println("El ID introducido no existe.");
	                return;
			
		}
	}catch(Exception e) {
		System.out.println("Error");
		
	}

	
	}
	private static Map<String,String> leerPaises(){
		 Map<String, String> paises = new LinkedHashMap<>();
		return paises;
		
	}
}

