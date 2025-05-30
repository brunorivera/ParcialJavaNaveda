package main;

import dao.*;
import model.Alumno;
import model.Curso;
import model.Inscripcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (Connection conn = util.Conexion.getConexion();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS alumno (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nombre VARCHAR(100) NOT NULL," +
                    "edad INT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS curso (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nombre VARCHAR(100) NOT NULL," +
                    "creditos INT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS inscripcion (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "idAlumno INT," +
                    "idCurso INT," +
                    "FOREIGN KEY (idAlumno) REFERENCES alumno(id)," +
                    "FOREIGN KEY (idCurso) REFERENCES curso(id))");

            logger.info("Tablas creadas correctamente.");

        } catch (Exception e) {
            logger.error("Error al crear las tablas", e);
            return;
        }

        AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
        CursoDAO cursoDAO = new CursoDAOImpl();
        InscripcionDAO inscripcionDAO = new InscripcionDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("\n--- MENU PRINCIPAL ---\n1. Gestionar alumnos\n2. Gestionar cursos\n3. Gestionar inscripciones\n0. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> menuAlumnos(scanner, alumnoDAO);
                case "2" -> menuCursos(scanner, cursoDAO);
                case "3" -> menuInscripciones(scanner, alumnoDAO, cursoDAO, inscripcionDAO);
                case "0" -> {
                    logger.info("Saliendo...");
                    return;
                }
                default -> logger.warn(" Opción inválida.");
            }
        }
    }

    private static void menuAlumnos(Scanner scanner, AlumnoDAO alumnoDAO) {
        while (true) {
            logger.info("\n--- MENU ALUMNOS ---\n1. Insertar alumno\n2. Listar alumnos\n3. Buscar alumno por ID\n4. Actualizar alumno\n5. Eliminar alumno\n0. Volver");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    if (!Alumno.nombreValido(nombre)) {
                        logger.warn(" Nombre inválido. Solo letras y espacios. Volviendo al menú.");
                        return;
                    }
                    System.out.print("Edad: ");
                    String edadStr = scanner.nextLine();
                    if (!Alumno.edadValida(edadStr)) {
                        logger.warn(" Edad inválida. Ingresá un número válido. Volviendo al menú.");
                        return;
                    }
                    int edad = Integer.parseInt(edadStr);
                    alumnoDAO.insertar(new Alumno(0, nombre, edad));
                }
                case "2" -> alumnoDAO.listarTodos().forEach(System.out::println);
                case "3" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    System.out.println(alumnoDAO.buscarPorId(id));
                }
                case "4" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    if (!Alumno.nombreValido(nombre)) {
                        logger.warn(" Nombre inválido. Volviendo al menú.");
                        return;
                    }
                    System.out.print("Nueva edad: ");
                    String edadStr = scanner.nextLine();
                    if (!Alumno.edadValida(edadStr)) {
                        logger.warn(" Edad inválida. Volviendo al menú.");
                        return;
                    }
                    int edad = Integer.parseInt(edadStr);
                    alumnoDAO.actualizar(new Alumno(id, nombre, edad));
                }
                case "5" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    alumnoDAO.eliminar(id);
                }
                case "0" -> { return; }
                default -> logger.warn(" Opción inválida.");
            }
        }
    }

    private static void menuCursos(Scanner scanner, CursoDAO cursoDAO) {
        while (true) {
            logger.info("\n--- MENU CURSOS ---\n1. Insertar curso\n2. Listar cursos\n3. Buscar curso por ID\n4. Actualizar curso\n5. Eliminar curso\n0. Volver");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    if (!Curso.nombreValido(nombre)) {
                        logger.warn(" Nombre inválido. Solo letras/números/espacios. Volviendo al menú.");
                        return;
                    }
                    System.out.print("Créditos: ");
                    String creditosStr = scanner.nextLine();
                    if (!Curso.creditosValidos(creditosStr)) {
                        logger.warn(" Créditos inválidos. Ingresá un número. Volviendo al menú.");
                        return;
                    }
                    int creditos = Integer.parseInt(creditosStr);
                    cursoDAO.insertar(new Curso(0, nombre, creditos));
                }
                case "2" -> cursoDAO.listarTodos().forEach(System.out::println);
                case "3" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    System.out.println(cursoDAO.buscarPorId(id));
                }
                case "4" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    if (!Curso.nombreValido(nombre)) {
                        logger.warn(" Nombre inválido. Volviendo al menú.");
                        return;
                    }
                    System.out.print("Nuevos créditos: ");
                    String creditosStr = scanner.nextLine();
                    if (!Curso.creditosValidos(creditosStr)) {
                        logger.warn(" Créditos inválidos. Volviendo al menú.");
                        return;
                    }
                    int creditos = Integer.parseInt(creditosStr);
                    cursoDAO.actualizar(new Curso(id, nombre, creditos));
                }
                case "5" -> {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    if (!idStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);
                    cursoDAO.eliminar(id);
                }
                case "0" -> { return; }
                default -> logger.warn(" Opción inválida.");
            }
        }
    }

    private static void menuInscripciones(Scanner scanner, AlumnoDAO alumnoDAO, CursoDAO cursoDAO, InscripcionDAO inscripcionDAO) {
        while (true) {
            logger.info("\n--- MENU INSCRIPCIONES ---\n1. Inscribir alumno en curso\n2. Ver todas las inscripciones\n3. Ver inscripciones con nombres\n0. Volver");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    alumnoDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID del alumno: ");
                    String idAlumnoStr = scanner.nextLine();
                    if (!idAlumnoStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int idAlumno = Integer.parseInt(idAlumnoStr);

                    cursoDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID del curso: ");
                    String idCursoStr = scanner.nextLine();
                    if (!idCursoStr.matches("\\d+")) {
                        logger.warn(" ID inválido. Volviendo al menú.");
                        return;
                    }
                    int idCurso = Integer.parseInt(idCursoStr);

                    inscripcionDAO.insertar(new Inscripcion(0, idAlumno, idCurso));
                }
                case "2" -> inscripcionDAO.listarTodas().forEach(System.out::println);
                case "3" -> inscripcionDAO.mostrarInscripcionesConNombres();
                case "0" -> { return; }
                default -> logger.warn(" Opción inválida.");
            }
        }
    }
}
