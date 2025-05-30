package dao;

import model.Curso;
import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    Curso buscarPorId(int id);
    List<Curso> listarTodos();
    void actualizar(Curso curso);
    void eliminar(int id);
}

