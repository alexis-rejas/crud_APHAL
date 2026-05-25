# CRUD APHAL — Sistema de Gestión de Usuarios
Proyecto de escritorio en Java (Swing) con conexión a MySQL vía Docker.

---

## ✅ Requisitos — Instalar en orden

### 1. Java JDK 17
- Descargar: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Instalar y verificar en terminal:
  ```
  java -version
  ```
  Debe mostrar: `java version "17.x.x"`

### 2. IntelliJ IDEA Community (gratis)
- Descargar: https://www.jetbrains.com/idea/download/
- Instalar con opciones por defecto

### 3. Docker Desktop
- Descargar: https://www.docker.com/products/docker-desktop/
- Instalar y **abrirlo** antes de correr el proyecto
- Verificar en terminal:
  ```
  docker -v
  ```

### 4. MySQL Workbench (opcional, para ver la BD)
- Descargar: https://dev.mysql.com/downloads/workbench/
- Instalar con opciones por defecto

---

## 🚀 Pasos para correr el proyecto

### Paso 1 — Clonar el repositorio
Abrir terminal (PowerShell o CMD) y ejecutar:
```
git clone https://github.com/alexis-rejas/crud_APHAL.git
cd crud_APHAL
```

### Paso 2 — Levantar la base de datos con Docker
Asegúrate de que Docker Desktop esté abierto y corriendo, luego ejecutar:
```
docker-compose -f docker/docker-compose.yml up -d
```
Esto levanta MySQL en el puerto **3307** con:
- Usuario: `root`
- Contraseña: `root1234`
- Base de datos: `vallegrande_db`

### Paso 3 — Crear la tabla en la base de datos
Ejecutar este comando en la terminal:
```
docker exec vallegrande_db mysql -uroot -proot1234 vallegrande_db -e "CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, correo VARCHAR(100) NOT NULL, telefono VARCHAR(20) NOT NULL, asunto VARCHAR(100) NOT NULL, mensaje TEXT NOT NULL);"
```

### Paso 4 — Abrir el proyecto en IntelliJ
1. Abrir IntelliJ IDEA
2. Clic en **Open**
3. Seleccionar la carpeta `crud_APHAL`
4. Esperar a que Maven descargue las dependencias automáticamente

### Paso 5 — Correr el proyecto
1. En IntelliJ: **Build → Rebuild Project**
2. Luego: **Run → Run 'Main'**
3. Se abre la ventana del sistema

---

## 🔌 Conexión a MySQL Workbench (opcional)
Para ver los datos directamente en la base de datos:

| Campo | Valor |
|---|---|
| Hostname | `127.0.0.1` |
| Port | `3307` |
| Username | `root` |
| Password | `root1234` |

Consulta para ver usuarios:
```sql
SELECT * FROM vallegrande_db.usuarios;
```

---

## 📁 Estructura del proyecto
```
crud_APHAL/
├── docker/
│   └── docker-compose.yml       # Configuración de MySQL
├── src/main/java/vallegrande/edu/pe/
│   ├── Main.java                # Punto de entrada
│   ├── controller/
│   │   └── UsuarioController.java
│   ├── dao/
│   │   └── UsuarioDAO.java      # Operaciones SQL
│   ├── model/
│   │   └── Usuario.java
│   ├── util/
│   │   ├── Conexion.java        # Configuración BD
│   │   └── Validador.java
│   └── view/
│       ├── PantallaBienvenida.java
│       ├── UsuarioListaView.java
│       └── UsuarioFormView.java
└── pom.xml                      # Dependencias Maven
```

---

## ⚙️ Dependencias (se descargan automáticamente con Maven)
- `mysql-connector-j 8.3.0` — Driver JDBC para MySQL
