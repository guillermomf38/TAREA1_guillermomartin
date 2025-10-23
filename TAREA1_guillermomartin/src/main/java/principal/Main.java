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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import utils.ComparadorNombreEspectaculo;

import entidades.Credenciales;
import entidades.Espectaculo;
import entidades.Perfiles;
import entidades.Sesion;

public class Main {
	private static Scanner leer = new Scanner(System.in);

	public static void main(String[] args) {
        //Sesion por defecto de invitado
		Sesion sesionActual = new Sesion("invitado", Perfiles.INVITADO);
		crearFicheroCredenciales();
		int opcion;
		do {
			opcion = seleccionPerfil(sesionActual);
			sesionActual = opcionesMenu(sesionActual, opcion);
		} while (opcion != 0);
		System.out.println("Fin del programa");
	}

	private static int seleccionPerfil(Sesion sesionActual) {
		while (true) {
			System.out.println("== Menu Principal ==");
			//Se muestra las opciones segun el perfil del usuario actual
			switch (sesionActual.getPerfil()) {
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
				System.out.println(
						"Formato invalido, introduce un numero correcto");
				System.out.println("");
				leer.nextLine();

			}

		}
	}

	private static Sesion opcionesMenu(Sesion sesionActual, int opcion) {
		try {
			switch (sesionActual.getPerfil()) {

			case INVITADO:
				switch (opcion) {
				case 1:
					mostrarEspectaculos(sesionActual);
					break;
				case 2:
					System.out.println("Iniciando sesion");
					sesionActual = login(sesionActual);
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
					mostrarEspectaculos(sesionActual);
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesionActual = cerrarSesion();
					break;
				case 3:
					registrarPersona(sesionActual);
					break;
				case 4:
					gestionarEspectaculos(sesionActual);
					break;
				case 0:
					System.out.println("Saliendo del programa");
					break;
				default:
					System.out.println("Opción no valida");
				}
				break;

			case COORDINACION:
				switch (opcion) {
				case 1:
					System.out.println("Mostrando espectaculos");
					mostrarEspectaculos(sesionActual);
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesionActual = cerrarSesion();

					break;
				case 3:
					gestionarEspectaculos(sesionActual);
					break;
				case 0:
					System.out.println("Saliendo del programa");
					break;
				default:
					System.out.println("Opcion no valida");
				}
				break;

			case ARTISTA:
				switch (opcion) {
				case 1:
					System.out.println("Mostrando espectaculos");
					mostrarEspectaculos(sesionActual);
					break;
				case 2:
					System.out.println("Cerrando sesion");
					sesionActual = cerrarSesion();
					break;
				case 0:
					System.out.println("Saliendo del programa");
					break;

				default:
					System.out.println("Opcion no valida");
				}
				break;
			}

		} catch (Exception e) {
			System.out.println("Introduce una opcion valida");
		}

		return sesionActual;
	}

	private static void crearFicheroCredenciales() {
		File carpeta = new File("ficheros");
		if (!carpeta.exists()) {
			if (carpeta.mkdirs()) {
				System.out.println("Carpeta ficheros creada");
			} else {
				System.out.println("No se pudo crear la carpeta ficheros ");
				return;
			}
		}

		File fichero = new File(carpeta, "credenciales.txt");
		if (!fichero.exists()) {
			try (PrintWriter pw = new PrintWriter(fichero)) {
				pw.println(
						"1|luisdbb|miP@ss|luisdbb@educastur.org|Luis de Blas|España|coordinacion");
				pw.println(
						"2|camila|cam1las|camilas@circo.es|Camila Sánchez|Bolivia|artista");
				pw.println(
						"3|patrigc|patri|patriciagc@circo.es|Patricia González|España|artista");
				pw.println(
						"4|alberto|alberto|alberto@circo.es|Alberto García|España|artista");
			} catch (IOException e) {
				System.out.println(" Error creando el fichero");
			}
		}
	}

	private static Credenciales comprobarLogin(String nombre, String password) {
		//Se valida el admin asignandole el nombre y la contraseña admin
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
                //Se verifica usuario y contrasena en credenciales.txt
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
        //Si el perfil de usuario es distinto al de invitado ya hay una sesion iniciada
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
			System.out.println("");
			return new Sesion(credencial.getNombre(), credencial.getPerfil());

		} else {
			System.out.println("El usuario o la contraseña son incorrectos");

			return sesionActual;
		}
	}

	private static Sesion cerrarSesion() {
		//Se cierra sesion y se pone por defecto usuario invitado
		System.out.println("Has cerrado Sesion");
		System.out.println("");
		return new Sesion("invitado", Perfiles.INVITADO);

	}

	private static void mostrarEspectaculos(Sesion sesionActual) {
		//Lee y muestra los espectaculos desde espectaculos.dat
		List<Espectaculo> listaEspectaculos = leerEspectaculos();

		if (listaEspectaculos.isEmpty()) {
			System.out.println("Todavia no hay espectaculos para visualizar");
			return;
		}

		Collections.sort(listaEspectaculos, new ComparadorNombreEspectaculo());

		for (Espectaculo espe : listaEspectaculos) {
			System.out.println(espe);
		}
		System.out.println("");

	}

	private static Map<String, String> leerPaises() {
		//Lee la lista de paises desde un xml usando ruta.properties
		Map<String, String> paises = new LinkedHashMap<>();
		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(
				"src/main/resources/ruta.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			System.out.println("Error leyendo archivo properties ");
			return paises;
		}

		String ruta = prop.getProperty("rutaPaisesxml");
		if (ruta == null) {
			System.out.println("No esta configurada la ruta");
			return paises;
		}

		File xmlFile = new File(ruta);
		if (!xmlFile.exists()) {
			System.out.println("No se encontro el archivo xml");
			return paises;
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList lista = doc.getElementsByTagName("pais");
			for (int i = 0; i < lista.getLength(); i++) {
				org.w3c.dom.Element pais = (org.w3c.dom.Element) lista.item(i);
				String id = ((org.w3c.dom.Element) pais)
						.getElementsByTagName("id").item(0).getTextContent();
				String nombre = ((org.w3c.dom.Element) pais)
						.getElementsByTagName("nombre").item(0)
						.getTextContent();
				paises.put(id.trim(), nombre.trim());
			}

		} catch (Exception e) {
			System.out.println("Error leyendo paises.xml");

		}
		return paises;

	}

	private static void registrarPersona(Sesion sesionActual) {
		//Registro de una nueva persona
		System.out.println("--Registro de nueva Persona--");
		try {
			Map<String, String> paises = leerPaises();
			if (paises.isEmpty()) {
				System.out.println("No hay paises disponibles");
				return;

			}
			//Se muestra la lista de paises para la seleccion
			System.out.println("-Lista de paises disponibles-");

			for (Map.Entry<String, String> pais : paises.entrySet()) {
				System.out.println(pais.getKey() + " - " + pais.getValue());
			}

			System.out.print("Introduce el ID del pais:");
			String idPais = leer.nextLine().trim();
			String nacionalidad = paises.get(idPais);
			if (nacionalidad == null) {
				System.out.println("El ID introducido no existe");
				return;
			}
			//Datos personales
			System.out.println("Nombre real:");
			String nombreReal = leer.nextLine().trim();
			System.out.println("Email:");
			String email = leer.nextLine().trim();
			//Datos de credenciales
			System.out.println("Nombre usuario: ");
			String nombreUsuario = leer.nextLine().trim();
			System.out.println("Contrasena: ");
			String password = leer.nextLine().trim();
			//Restricciones de usuario y contraseña
			if (nombreReal.isEmpty() || email.isEmpty()
					|| nombreUsuario.isEmpty() || password.isEmpty()) {
				System.out.println("Ningun campo puede estar vacio");
				return;
			}
			if (nombreUsuario.contains(" ") || password.contains(" ")) {
				System.out.println(
						"Usuario o la contrasena no pueden contener espacios");
				return;
			}
			if (nombreUsuario.length() < 3 || password.length() < 3) {
				System.out.println(
						"Usuario y contrasena deben tener al menos 3 caracteres");
				return;
			}
			if (!Pattern.matches("^[a-z]+$", nombreUsuario)) {
				System.out.println(
						"El nombre de usuario solo puede contener letras minusculas sin tildes");
				return;
			}
            // Se comprueba que el nombre de usuario y el email no son ya existentes
			File fichero = new File("ficheros/credenciales.txt");
			long idUltimo = 0;
			try (BufferedReader br = new BufferedReader(
					new FileReader(fichero))) {
				String linea;
				while ((linea = br.readLine()) != null) {
					String[] partes = linea.split("\\|");
					if (partes.length < 7)
						continue;
					if (partes[1].equalsIgnoreCase(nombreUsuario)) {
						System.out.println("El nombre de usuario ya existe");
						return;
					}
					if (partes[3].equalsIgnoreCase(email)) {
						System.out.println("El email ya esta registrado");
						return;
					}
					idUltimo = Math.max(idUltimo, Long.parseLong(partes[0]));
				}
			}
			//Se obtiene el ultimo ID usado en el fichero y se suma 1 para asignar un ID unico a la nueva persona
			long nuevoId = idUltimo + 1;
			String perfil = "";
			String datosExtra = "";

			System.out.print("Perfil coordinacion o artista : ");
			perfil = leer.nextLine().trim().toLowerCase();

			if (perfil.equals("coordinacion")) {
				//Se pregunta si es senior y desde que fecha
				System.out.print("¿Es senior? (s/n): ");
				String respuesta = leer.nextLine().trim().toLowerCase();
				if (respuesta.equals("s")) {
					System.out.print("Fecha desde (dd/MM/yyyy): ");
					String fechaTexto = leer.nextLine().trim();
					try {
						DateTimeFormatter formatter = DateTimeFormatter
								.ofPattern("dd/MM/yyyy");
						LocalDate fechaSenior = LocalDate.parse(fechaTexto,
								formatter);
						if (fechaSenior.isAfter(LocalDate.now())) {
							System.out.println("La fecha no puede ser futura");
							return;
						}
						datosExtra = "Senior desde: "
								+ fechaSenior.format(formatter);
					} catch (DateTimeParseException e) {
						System.out.println(
								"Formato de fecha incorrecto. Usa dd/MM/yyyy.");
						return;
					}
				}
			} else if (perfil.equals("artista")) {
				//Se pregunta si el artista tiene apodo y cual es su apodo
				System.out.print("¿Tiene apodo? (s/n): ");
				String apodo = "";
				if (leer.nextLine().trim().equalsIgnoreCase("s")) {
					System.out.print("Introduce el apodo: ");
					apodo = leer.nextLine().trim();
				}
				System.out.println("Elige especialidades");
				System.out.println(
						"ACROBACIA, HUMOR, MAGIA, EQUILIBRISMO, MALABARISMO");
				String especialidades = leer.nextLine().trim();
				datosExtra = (apodo.isEmpty() ? "" : "Apodo: " + apodo + " - ")
						+ "Especialidades: " + especialidades;

			} else {
				System.out.println("Perfil no valido");
				return;
			}
			boolean guardado = guardarPersona(nuevoId, nombreUsuario, password,
					email, nombreReal, nacionalidad, perfil);

			if (guardado) {
				System.out.println("Registro completado correctamente.");
				if (!datosExtra.isEmpty())
					System.out.println("Datos extra: " + datosExtra);
			} else {
				System.out.println(" Ocurrio un error al guardar los datos");
			}

		} catch (IOException e) {
			System.out.println("Error al registrar persona");

		}

	}

	private static boolean guardarPersona(Long id, String nombreUsuario,
			String password, String email, String nombre, String nacionalidad,
			String perfil) {
		File fichero = new File("ficheros/credenciales.txt");
		try (FileWriter fw = new FileWriter(fichero, true);
				PrintWriter pw = new PrintWriter(fw)) {

			pw.printf("%d|%s|%s|%s|%s|%s|%s%n", id, nombreUsuario, password,
					email, nombre, nacionalidad, perfil);
			return true;
		} catch (IOException e) {
			System.out.println("Error guardando en credenciales.txt");
			return false;
		}
	}

	private static void gestionarEspectaculos(Sesion sesionActual) {

		try {
			System.out.print("Nombre del espectaculo: ");
			String nombre = leer.nextLine().trim();
			if (nombre.isEmpty() || nombre.length() > 25) {
				System.out.println(
						"El nombre no puede estar vacio ni superar 25 caracteres");
				return;
			}

			List<Espectaculo> lista = leerEspectaculos();
			for (Espectaculo e : lista) {
				if (e.getNombre().equalsIgnoreCase(nombre)) {
					System.out
							.println("Ya existe un espectaculo con ese nombre");
					return;
				}
			}

			System.out.print("Fecha de inicio (dd/MM/yyyy): ");
			String fechain = leer.nextLine().trim();
			System.out.print("Fecha de fin (dd/MM/yyyy): ");
			String fechafi = leer.nextLine().trim();

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaini, fechafin;
			try {
				fechaini = LocalDate.parse(fechain, fmt);
				fechafin = LocalDate.parse(fechafi, fmt);
			} catch (DateTimeParseException e) {
				System.out.println("Formato de fecha invalido");
				return;
			}
            //Se hacen restricciones para las fechas del espectaculo
			if (fechaini.isAfter(fechafin)) {
				System.out.println(
						"La fecha de inicio no puede ser posterior a la de fin");
				return;
			}

			if (fechaini.isAfter(LocalDate.now().plusYears(1))) {
				System.out
						.println("La fecha de inicio no puede superar un año");
				return;
			}

			long idCoord = 0;
			if (sesionActual.getPerfil() == Perfiles.COORDINACION) {

				idCoord = obtenerIdCoordinadorPorNombre(
						sesionActual.getUsuarioActual());
				if (idCoord == 0) {
					System.out.println(
							"no se encontro el ID del coordinador actual");
					return;
				}
			} else if (sesionActual.getPerfil() == Perfiles.ADMIN) {
				//Muestra los coordinadores disponibles con el id del coordinador y su nombre
				Map<Long, String> coordinadores = obtenerCoordinadores();
				System.out.println("Coordinadores disponibles: ");
				for (Map.Entry<Long, String> entry : coordinadores.entrySet()) {
					System.out
							.println(entry.getKey() + " - " + entry.getValue());
				}
				System.out.print("ID del coordinador responsable: ");
				idCoord = leer.nextLong();
				leer.nextLine();
				if (!coordinadores.containsKey(idCoord)) {
					System.out.println("ID de coordinador no valido");
					return;
				}
			}
			//Si la lista esta vacia, comienza en 1 si no, se toma el ID del ultimo y suma 1
			long idNuevo = lista.isEmpty() ? 1
					: lista.get(lista.size() - 1).getId() + 1;
			//Se crea un nuevo objeto Espectaculo con los datos capturados y el ID asignado
			Espectaculo nuevo = new Espectaculo(idNuevo, nombre, fechaini,
					fechafin, idCoord);
			guardarEspectaculo(nuevo);
			System.out.println("Espectaculo registrado");
		} catch (InputMismatchException e) {
			System.out.println("Entrada invalida");
			leer.nextLine();
		}
	}
	//Devuelve el ID de un coordinador especifico dado su nombre de usuario
	// Busca en el archivo de credenciales y retorna el ID si el perfil es coordinacion
	private static long obtenerIdCoordinadorPorNombre(String nombre) {
		File fichero = new File("ficheros/credenciales.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("\\|");
				if (partes.length < 7)
					continue;
				String nombreUsuario = partes[1].trim();
				String perfil = partes[6].trim();
				if (nombreUsuario.equalsIgnoreCase(nombre)
						&& perfil.equalsIgnoreCase("coordinacion")) {
					return Long.parseLong(partes[0]);
				}
			}
		} catch (IOException e) {
			System.out.println("Error leyendo credenciales");
		}
		return 0;
	}
	// Devuelve un mapa con todos los coordinadores (ID- Nombre)
	private static Map<Long, String> obtenerCoordinadores() {
		Map<Long, String> coordinadores = new LinkedHashMap<>();
		File fichero = new File("ficheros/credenciales.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("\\|");
				if (partes.length < 7)
					continue;
				String perfil = partes[6].trim();
				if (perfil.equalsIgnoreCase("coordinacion")) {
					Long id = Long.parseLong(partes[0]);
					String nombreUsuario = partes[1].trim();
					coordinadores.put(id, nombreUsuario);
				}
			}
		} catch (IOException e) {
			System.out.println("Error leyendo coordinadores");
		}
		return coordinadores;
	}
	//Lee todos los objetos Espectaculo almacenados en el fichero y los devuelve en una lista
	private static List<Espectaculo> leerEspectaculos() {
		List<Espectaculo> lista = new ArrayList<>();
		File fichero = new File("ficheros/espectaculos.dat");
		if (!fichero.exists()) {
			return lista;
		}

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(fichero))) {
			while (true) {
				Espectaculo espectaculo = (Espectaculo) ois.readObject();
				lista.add(espectaculo);
			}
		} catch (EOFException ignored) {
		} catch (Exception e) {
			System.out.println("Error leyendo espectaculos");
		}
		return lista;
	}
	//Añade un nuevo Espectaculo a la lista de espectaculos existentes y guarda la lista en el fichero
	private static void guardarEspectaculo(Espectaculo nuevo) {
		List<Espectaculo> listaEspectaculos = leerEspectaculos();
		listaEspectaculos.add(nuevo);
		File carpeta = new File("ficheros");
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}

		File fichero = new File("ficheros/espectaculos.dat");
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(fichero))) {
			for (Espectaculo espectaculo : listaEspectaculos) {
				oos.writeObject(espectaculo);
			}
		} catch (IOException e) {
			System.out.println("Error al guardar el espectaculo");
		}
	}

}
