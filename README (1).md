# 📚 Proyecto Java: Gestión de Alumnos, Cursos e Inscripciones

Este proyecto es una aplicación de consola en Java que gestiona **alumnos**, **cursos** y **sus inscripciones** utilizando JDBC, base de datos H2 y una arquitectura organizada por capas. Implementa las prácticas fundamentales de **Programación Orientada a Objetos (POO)**, **validaciones**, **DAO genéricos** y uso de **SLF4J** para logging.

---

## 🧱 Estructura del Proyecto

```
src/
├── main/
│   ├── dao/            # Interfaces y clases DAO
│   ├── model/          # Clases modelo: Alumno, Curso, Inscripcion
│   ├── util/           # Clase de conexión a la base de datos (H2)
│   └── main/           # Clase Main con menú principal y lógica
```

---

## 🗃️ Base de Datos H2
- Se utiliza una **base de datos H2 en modo archivo** (`./parcialnavedaDB.mv.db`).
- Al ejecutar el programa, se crean automáticamente las siguientes tablas:
  - `alumno(id, nombre, edad)`
  - `curso(id, nombre, creditos)`
  - `inscripcion(id, idAlumno, idCurso)` con claves foráneas

---

## ✅ Funcionalidades

### 👤 Gestión de Alumnos
- Insertar alumno con validación de nombre y edad
- Listar todos los alumnos
- Buscar alumno por ID
- Actualizar datos de un alumno
- Eliminar alumno por ID

### 📘 Gestión de Cursos
- Insertar curso con validación de nombre y créditos
- Listar todos los cursos
- Buscar curso por ID
- Actualizar datos de un curso
- Eliminar curso por ID

### 📝 Gestión de Inscripciones
- Inscribir alumno en un curso
- Listar inscripciones (IDs)
- Mostrar inscripciones con nombres de alumno y curso

---

## 🔐 Validaciones Agregadas
- **Nombre** debe tener solo letras y espacios.
- **Edad y créditos** deben ser números enteros positivos.
- **ID** debe ser numérico.
- Las entradas inválidas muestran un mensaje y redirigen al menú correspondiente.

---

## 🧠 Uso de SLF4J (Logging)
- Se utiliza `Logger` de SLF4J para imprimir mensajes de estado, advertencias y errores.
- Permite monitorear el flujo del sistema y detectar fallas.

---

## 🚀 Cómo ejecutar
1. Clonar o descargar este repositorio.
2. Abrir en un IDE como IntelliJ o VS Code.
3. Ejecutar `main.Main`.
4. Seguir las instrucciones por consola.

---

## 📂 Requisitos Técnicos
- Java 17+
- Gradle o entorno con dependencias JDBC y SLF4J
- H2 Database Engine

---

## ✍️ Autor
Proyecto realizado por **Bruno Rivera Garetti** como parte de un ejercicio integrador de Java + JDBC + POO + Validaciones.

---

¿Listo para compilar, ejecutar y probar? ¡El sistema está validado y listo para entregar! 🚀
