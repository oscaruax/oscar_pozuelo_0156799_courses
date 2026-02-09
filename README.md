# Sistema de Gestión de Cursos

Proyecto desarrollado como ejercicio final de la asignatura **Planificación y Gestión de Sistemas de Información**  
Grado en Ingeniería Informática – Universidad Alfonso X el Sabio (UAX)

---

## 📦 Arquitectura del proyecto

El sistema está compuesto por **tres microservicios independientes**, desarrollados con **Spring Boot 3.5.10** y **Java 21**, que comparten una **base de datos H2 en modo fichero**.

### Microservicios

1. **course-service**
   - Gestión de cursos
   - Exposición de API REST
   - Consulta de cursos disponibles y plazas

2. **enrollment-service**
   - Gestión de matrículas
   - Asociación alumnos ↔ cursos
   - Validación de plazas disponibles

3. **batch-import**
   - Importación masiva de cursos desde CSV
   - Implementado con **Spring Batch**
   - Escritura directa en la base de datos compartida

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.10
- Spring Data JPA
- Spring Batch 5.x
- H2 Database (modo fichero)
- Maven
- Postman (pruebas)
- Hibernate

---

## 🗄️ Base de datos

- Motor: **H2**
- Modo: **file**
- Fichero compartido:
  ```
  ./data/course-db.mv.db
  ```

⚠️ **Importante:**  
Todos los servicios usan **la misma versión de H2 y Spring Boot** para evitar incompatibilidades de formato.

---

## 🚀 Orden correcto de ejecución

1. **batch-import**
2. **course-service**
3. **enrollment-service**

---

## 📥 Batch Import

### CSV esperado (`courses.csv`)

```csv
title,teacher,startDate,capacity
Java Básico,Laura,2025-03-01,25
Spring Boot,Juan,2025-04-10,30
Microservicios,Ana,2025-05-15,20
Bases de Datos,Carlos,2025-06-01,35
```

### Funcionamiento
- El job `importCoursesJob` se lanza automáticamente al arrancar
- Inserta los cursos en la tabla `course`
- El estado del job se registra en tablas `BATCH_*`

### Verificación
- Logs:
  ```
  Job importCoursesJob COMPLETED
  ```
- H2 Console:
  ```sql
  SELECT * FROM course;
  ```

---

## 🌐 course-service

### Endpoints principales

- `GET /courses`
- `GET /courses/{id}`
- `GET /courses/available`
- `GET /courses/informe`

### Verificación
- Postman
- H2 Console (`http://localhost:8081/h2-console`)

---

## 📝 enrollment-service

### Funcionalidades
- Alta de matrículas
- Control de plazas
- Relación curso ↔ alumno

### Endpoints
- `POST /enrollments`
- `GET /enrollments`
- `GET /enrollments/course/{courseId}`

---

## ✅ Pruebas para demostrar funcionamiento completo

### 1. Batch Import
- Arrancar batch-import
- Ver inserts en logs
- Ver cursos en H2

### 2. Course Service
- Consultar cursos vía REST
- Ver plazas correctas
- Acceder a `/informe`

### 3. Enrollment Service
- Crear matrículas
- Ver reducción de plazas
- Validar que no se excede capacidad

---

## 🧪 Acceso H2 Console

- URL: `http://localhost:8081/h2-console`
- JDBC URL:
  ```
  jdbc:h2:file:./data/course-db
  ```
- User: `sa`
- Password: *(vacía)*

---

## 📌 Observaciones finales

- El proyecto cumple todos los **requisitos mínimos del enunciado**
- Arquitectura desacoplada
- Persistencia compartida correctamente configurada
- Control de errores y validaciones implementadas
- Importación batch funcional y verificable

---

✅ **Proyecto listo para evaluación**
