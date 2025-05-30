package model;

public class Inscripcion {
    public int id;
    public int idAlumno;
    public int idCurso;

    public Inscripcion() {}

    public Inscripcion(int id, int idAlumno, int idCurso) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "Inscripcion{id=" + id + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso + "}";
    }
}
