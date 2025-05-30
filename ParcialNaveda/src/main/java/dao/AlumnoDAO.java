package dao;

import model.Alumno;
import java.util.List;

public interface AlumnoDAO extends GenericDAO<Alumno> {
    void insertar(Alumno alumno);
    Alumno buscarPorId(int id);
    List<Alumno> listarTodos();
    void actualizar(Alumno alumno);
    void eliminar(int id);
}