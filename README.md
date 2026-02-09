# Sistema de Gestión de Cursos

Proyecto desarrollado como ejercicio final de la asignatura **Programación Concurrente**  
Grado en Ingeniería Informática – Universidad Alfonso X el Sabio (UAX)

---

## 📦 Arquitectura del proyecto

El sistema está compuesto por **tres microservicios independientes**, desarrollados con **Spring Boot 3.5.10** y **Java 17**, que comparten una **base de datos H2 en modo fichero**.

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

- Java 17
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

1.**course-service**
2. **batch-import**
3. **course-service**
4. **enrollment-service**

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
Importación OK
<img width="983" height="568" alt="ImporteOK" src="https://github.com/user-attachments/assets/aa0bf485-196d-4c59-bed9-dad976a73fd6" />

GET courses OK
<img width="1806" height="1234" alt="GETcursos" src="https://github.com/user-attachments/assets/27a5e8cb-af83-40ee-ab94-0e4cf793cb6e" />

POST Enrollment OK
<img width="1803" height="828" alt="POSTenrollmentOK" src="https://github.com/user-attachments/assets/a9896fcf-cefc-4a6d-ae8b-8bdb7fef4697" />

GET courses con alumnos OK
<img width="2128" height="1003" alt="GETlistarcursosOK" src="https://github.com/user-attachments/assets/b19225fd-6bef-48b1-8674-2ed8e482508e" />

DELETE course OK
<img width="1812" height="697" alt="DELETEcurso OK" src="https://github.com/user-attachments/assets/8456c209-7f16-4e5a-89bb-966f23606563" />



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

---

✅ **Proyecto listo para evaluación**
