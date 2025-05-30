package model;

public class Alumno {
    public int id;
    public String nombre;
    public int edad;

    public Alumno() {}

    public Alumno(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+");
    }

    public static boolean edadValida(String edadStr) {
        return edadStr.matches("\\d+");
    }

    @Override
    public String toString() {
        return "Alumno{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
    }
}
