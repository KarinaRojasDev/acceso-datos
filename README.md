# 📂 Acceso a Datos - Ejercicios

## 📖 Descripción
Este repositorio contiene ejercicios de manejo de ficheros, bases de datos y tecnologías de persistencia en Java.

## 📂 Contenido del Repositorio

```python
import os

def listar_ejercicios():
    directorios = [d for d in os.listdir('.') if os.path.isdir(d)]
    directorios.sort()
    for carpeta in directorios:
        print(f'- 📁 **{carpeta}**')

listar_ejercicios()
```

🔹 El código anterior genera dinámicamente la lista de carpetas contenidas en el repositorio. Puedes ejecutarlo en un entorno local para obtener un listado actualizado.

### 📁 Ejercicios incluidos
- **Manejo de Ficheros** (`FicheroDAM02`, `ejerciciosficheros`)
- **Bases de Datos con JDBC** (`reto3ConsultasJDBC`)
- **Lectura y Escritura de XML y JSON** (`reto1LecturaXml`, `reto2EscrituraJSON`)
- **ORM con Hibernate** (`hibernate/pruebasHibernateNetbeans`)

## 🛠 Requisitos
- **Java 8+**
- **Apache Maven** (opcional para Hibernate)
- **Base de datos MySQL (para pruebas con JDBC)
- **Un IDE compatible** (NetBeans, IntelliJ)

## 📌 Autor
**KarinaRojasDev**  
📌 GitHub: [https://github.com/KarinaRojasDev](https://github.com/KarinaRojasDev)

