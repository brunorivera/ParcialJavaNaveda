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

  ## 🧠 DAO Genérico

Se creó una interfaz `GenericDAO<T>` que generaliza operaciones CRUD.  
`AlumnoDAO` extiende `GenericDAO<Alumno>`.

```java
public interface GenericDAO<T> {
    void insertar(T entidad);
    T buscarPorId(int id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(int id);
}
```

---


# 📊 Logging profesional

Implementado con **Log4j** a través de **SLF4J**.  
Reemplaza todos los `System.out.println` por `logger.info`, `logger.warn`, `logger.error`.

Ejemplo en `Main.java`:

```java
private static final Logger logger = LoggerFactory.getLogger(Main.class);
logger.info("Tablas creadas correctamente.");
```

Configuración en `src/main/resources/log4j.properties`.

---
## 💾 Base de datos

- Se crea automáticamente al ejecutar el programa
- Archivo generado: `parcialnavedaDB.mv.db`
- Motor: **H2 modo archivo**
- Tablas:
  - `alumno(id, nombre, edad)`
  - `curso(id, nombre, creditos)`
  - `inscripcion(id, idAlumno, idCurso)`

---
# 🛠️ Acceder a la base de datos desde H2 Console

Si querés visualizar los datos (alumnos, cursos, inscripciones) usando la H2 Console, seguí estos pasos:

### 1. Ruta del archivo

Buscá el archivo `.mv.db` generado, por ejemplo:

```
C:/ruta/completa/a/tu/proyecto/parcialnavedaDB.mv.db
```

Usá la ruta **sin la extensión**.

---

### 2. Ingresá en H2 Console

Abrí el navegador y entrá a:

```
http://localhost:8082
```

En el formulario de conexión:

- **JDBC URL**:  
  ```
  jdbc:h2:file:C:/ruta/completa/a/tu/proyecto/parcialnavedaDB
  ```
- **User**: `sa`  
- **Password**: (dejá vacío)  
- Clic en **Connect**

---

### 3. Consultas útiles

Ver inscripciones completas con nombres de alumno y curso:

```sql
SELECT i.id, a.nombre AS alumno, c.nombre AS curso
FROM inscripcion i
JOIN alumno a ON i.idAlumno = a.id
JOIN curso c ON i.idCurso = c.id;
```

Ver todos los alumnos:

```sql
SELECT * FROM alumno;
```

Ver todos los cursos:

```sql
SELECT * FROM curso;
```

Ver tabla de inscripciones cruda:

```sql
SELECT * FROM inscripcion;
```
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

