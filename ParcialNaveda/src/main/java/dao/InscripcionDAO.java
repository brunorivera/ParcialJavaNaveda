package dao;

import model.Inscripcion;
import java.util.List;

public interface InscripcionDAO {
    void insertar(Inscripcion inscripcion);
    List<Inscripcion> listarTodas();
    void mostrarInscripcionesConNombres();
}