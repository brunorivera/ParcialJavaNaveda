package model;

public class Curso {
    public int id;
    public String nombre;
    public int creditos;

    public Curso() {}

    public Curso(int id, String nombre, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+");
    }

    public static boolean creditosValidos(String creditosStr) {
        return creditosStr.matches("\\d+");
    }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', creditos=" + creditos + "}";
    }
}
