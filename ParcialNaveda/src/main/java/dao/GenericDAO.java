package dao;

import java.util.List;

public interface GenericDAO<T> {
    void insertar(T entidad);
    T buscarPorId(int id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(int id);
}

