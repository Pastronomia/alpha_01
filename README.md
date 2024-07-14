# ¿Dónde, en Uruguay, está Carmen Sandiego? (Licencia CC)

Carmen Sandiego Uruguay es un juego educativo inspirado en la famosa serie "Carmen Sandiego", localizado en Uruguay y desarrollado en Java. El juego se enfoca en los puntos de interés geográficos, históricos, leyendas, turismo y gastronomía de Uruguay.

## Descripción del juego

En este juego, el jugador asume el papel de un detective de la organización ACME que debe atrapar a una banda de criminales liderada por la infame Carmen Sandiego. El detective deberá seguir pistas y recorrer diferentes localidades de Uruguay para capturar a los secuaces de Carmen Sandiego y, finalmente, a la propia Carmen.

## Características principales

- **Educativo**: Aprende sobre la geografía, historia y cultura de Uruguay mientras juegas.
- **Accesible**: Diseñado para ser accesible a personas con discapacidad visual severa.
- **Sistema de rangos**: Progresa en tu carrera de detective a medida que resuelves casos.
- **Base de datos local**: Almacena información de usuarios, localidades, pistas y progreso del juego.
- **Generación automática de pistas**: Sistema para generar pistas relevantes para cada localidad.
- **Interfaz gráfica**: Ventanas de juego implementadas para una experiencia visual mejorada.

## Tecnologías utilizadas

- Java 17
- SQLite JDBC (3.36.0.3)
- Maven (gestión de dependencias y construcción)
- JUnit 5 y Mockito (para pruebas unitarias)

## Requisitos del sistema

- Java Development Kit (JDK) 17 o superior
- Maven 3.6.0 o superior

## Instrucciones de instalación y ejecución

1. Clona este repositorio:
   ```sh
   git clone <URL_DEL_REPOSITORIO>
Navega hasta la carpeta raíz del proyecto:
sh


cd CarmenSandiegoUruguay
Compila el proyecto:
sh


mvn clean compile
Ejecuta el juego:
sh


mvn exec:java -Dexec.mainClass="com.ejemplo.carmenuy.Main"


## Pseudocódigo para la Creación y Actualización de la Base de Datos SQLite con Manejo de Errores

```plaintext
// Inicializar la base de datos SQLite
inicializarBaseDeDatos()

// Crear tablas necesarias si no existen
crearTablasSiNoExisten()

// Inicializar entidades del juego
detective = inicializarDetective()
secuaces = inicializarSecuaces()
carmenSandiego = inicializarCarmenSandiego()

// Guardar entidades iniciales en la base de datos
guardarDetectiveEnBaseDeDatos(detective)
guardarSecuacesEnBaseDeDatos(secuaces)
guardarCarmenSandiegoEnBaseDeDatos(carmenSandiego)

while juegoNoTerminado():
    localidadActual = obtenerLocalidadActual(detective)
    for secuaz in secuaces:
        if puedeCapturar(detective [[2]](http://elbauldeandroid.blogspot.com/2013/02/base-de-datos-sqlite.html), secuaz):
            capturarSecuaz(detective, secuaz)
            verificarAscenso(detective)
            if detective.getRango() == "DETECTIVE_JEFE":
                escaparSecuaz(secuaces)
            // Actualizar base de datos después de capturar un secuaz
            actualizarSecuazEnBaseDeDatos(secuaz)
            actualizarDetectiveEnBaseDeDatos(detective)
    if puedeCapturarCarmenSandiego(detective, carmenSandiego):
        capturarCarmenSandiego(detective, carmenSandiego)
        terminarJuego()
        // Actualizar base de datos después de capturar a Carmen Sandiego
        actualizarCarmenSandiegoEnBaseDeDatos(carmenSandiego)
    else:
        if detective.getRango() == "INSPECTOR" and detective.getMovimientos() > 3:
            perderJuego()
            // Actualizar base de datos al perder el juego
            actualizarEstadoJuegoEnBaseDeDatos("perdido")

def inicializarBaseDeDatos():
    // Conectar a la base de datos SQLite
    conexion = sqlite3.connect('carmen_sandiego.db')

def crearTablasSiNoExisten():
    // Crear tablas para detectives [[2]](http://elbauldeandroid.blogspot.com/2013/02/base-de-datos-sqlite.html), secuaces y Carmen Sandiego
    ejecutarSQL("CREATE TABLE IF NOT EXISTS detective (...)")
    ejecutarSQL("CREATE TABLE IF NOT EXISTS secuaz (...)")
    ejecutarSQL("CREATE TABLE IF NOT EXISTS carmen_sandiego (...)")
    ejecutarSQL("CREATE TABLE IF NOT EXISTS errores (id INTEGER PRIMARY KEY, mensaje TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)")

def guardarDetectiveEnBaseDeDatos(detective):
    // Insertar detective en la base de datos
    ejecutarSQL("INSERT INTO detective (...) VALUES (...)")

def guardarSecuacesEnBaseDeDatos(secuaces):
    // Insertar secuaces en la base de datos
    for secuaz in secuaces:
        ejecutarSQL("INSERT INTO secuaz (...) VALUES (...)")

def guardarCarmenSandiegoEnBaseDeDatos(carmenSandiego):
    // Insertar Carmen Sandiego en la base de datos
    ejecutarSQL("INSERT INTO carmen_sandiego (...) VALUES (...)")

def actualizarSecuazEnBaseDeDatos(secuaz):
    // Actualizar información del secuaz en la base de datos
    ejecutarSQL("UPDATE secuaz SET ... WHERE id = ...")

def actualizarDetectiveEnBaseDeDatos(detective):
    // Actualizar información del detective en la base de datos
    ejecutarSQL("UPDATE detective SET ... WHERE id = ...")

def actualizarCarmenSandiegoEnBaseDeDatos(carmenSandiego):
    // Actualizar información de Carmen Sandiego en la base de datos
    ejecutarSQL("UPDATE carmen_sandiego SET ... WHERE id = ...")

def actualizarEstadoJuegoEnBaseDeDatos(estado):
    // Actualizar el estado del juego en la base de datos
    ejecutarSQL("UPDATE juego SET estado = ... WHERE id = ...")

def guardarErrorEnBaseDeDatos(mensaje):
    // Insertar error en la base de datos
    ejecutarSQL("INSERT INTO errores (mensaje) VALUES (?)")


## Pseudocódigo para el Flujo de Login y Opciones de Teclas

```plaintext
// Inicializar el sistema
inicializarSistema()

// Mostrar pantalla de login/registro
mostrarPantallaLogin()

// Leer entrada del usuario para login/registro
usuario = leerEntradaUsuario()
contrasena = leerEntradaContrasena()

// Validar login/registro
if validarCredenciales(usuario, contrasena) then
    // Mostrar mensaje de bienvenida
    mostrarMensajeBienvenida(usuario)

    // Mostrar opciones disponibles
    mostrarOpciones()

    // Leer entrada del usuario para seleccionar opción
    opcion = leerEntradaUsuario()

    // Validar que la opción sea h, j, k
    while opcion != 'h' and opcion != 'j' and opcion != 'k' do
        // Mostrar mensaje de error
        mostrarMensajeError("Solo puedes elegir entre las teclas h, j, k.")

        // Leer entrada del usuario nuevamente
        opcion = leerEntradaUsuario()
    end while

    // Procesar la opción seleccionada
    procesarOpcion(opcion)
else
    // Mostrar mensaje de error
    mostrarMensajeError("Credenciales inválidas.")
end if

// Funciones auxiliares
def inicializarSistema():
    // Código para inicializar el sistema

def mostrarPantallaLogin():
    // Código para mostrar la pantalla de login/registro

def leerEntradaUsuario():
    // Código para leer la entrada del usuario
    return entradaUsuario

def leerEntradaContrasena():
    // Código para leer la contraseña del usuario
    return entradaContrasena

def validarCredenciales(usuario, contrasena):
    // Código para validar las credenciales del usuario
    return credencialesValidas

def mostrarMensajeBienvenida(usuario):
    // Código para mostrar el mensaje de bienvenida

def mostrarOpciones():
    // Código para mostrar las opciones disponibles (h, j, k)

def mostrarMensajeError(mensaje):
    // Código para mostrar un mensaje de error

def procesarOpcion(opcion):
    // Código para procesar la opción seleccionada por el usuario

## Características técnicas destacadas

- **DAOs implementados**: LocalidadDAO y PistaDAO para interactuar con la base de datos SQLite.
- **Generación automática de localidades y distancias**: El sistema genera automáticamente localidades y calcula distancias entre ellas.
- **Sistema de pistas**: Implementación de un sistema para generar y almacenar pistas específicas para cada localidad.
- **Interfaz gráfica**: Ventanas de juego implementadas utilizando Java Swing con características que requieren Java 17.

## Accesibilidad

El juego está diseñado para ser accesible a personas con discapacidad visual severa. Al iniciar, se pregunta al usuario si necesita ayuda visual. Si la respuesta es afirmativa, se activa un sistema de texto a voz (TTS) que lee todos los textos del juego.

## Contribución

[Mantén la sección de contribución que ya tenías en el README original]

## Licencia

Este proyecto está bajo la licencia [Commons Creative]. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Si tienes alguna pregunta o sugerencia sobre este proyecto, puedes contactarnos a través de [pastronomia@gmail.com] o visitar nuestro sitio web en [URL web].

## Agradecimientos

Queremos agradecer a todos los contribuidores y a la comunidad de desarrolladores que han hecho posible este proyecto.



```plaintext
├── CarmenSandiegoUruguay
│   ├── pom.xml
│   ├── README.md
│   ├── replit.nix
│   ├── scripts
│   │   └── list_structure.sh
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── ejemplo
│   │   │   │           └── carmenuy
│   │   │   │               ├── config
│   │   │   │               │   └── GitIgnoreConfig.java
│   │   │   │               ├── dao
│   │   │   │               │   ├── BaseDeDatosManager.java
│   │   │   │               │   ├── LocalidadDAO.java
│   │   │   │               │   ├── MisionDAO.java
│   │   │   │               │   ├── PistaDAO.java
│   │   │   │               │   └── UsuarioDAO.java
│   │   │   │               ├── exception
│   │   │   │               │   ├── LocalidadNotFoundException.java
│   │   │   │               │   └── PistaException.java
│   │   │   │               ├── Juego.java
│   │   │   │               ├── Main.java
│   │   │   │               ├── model
│   │   │   │               │   ├── Constants.java
│   │   │   │               │   ├── CSD.java
│   │   │   │               │   ├── Detective.java
│   │   │   │               │   ├── Grafo.java
│   │   │   │               │   ├── Jugador.java
│   │   │   │               │   ├── Localidad.java
│   │   │   │               │   ├── Mision.java
│   │   │   │               │   ├── Partida.java
│   │   │   │               │   ├── PistaGastronomia.java
│   │   │   │               │   ├── PistaGeografia.java
│   │   │   │               │   ├── PistaHistoria.java
│   │   │   │               │   ├── Pista.java
│   │   │   │               │   ├── PistaLeyenda.java
│   │   │   │               │   ├── PistaTurismo.java
│   │   │   │               │   ├── Rango.java
│   │   │   │               │   ├── Secuaz.java
│   │   │   │               │   └── Usuario.java
│   │   │   │               ├── service
│   │   │   │               │   ├── AccesibilidadManager.java
│   │   │   │               │   ├── AudioManager.java
│   │   │   │               │   ├── ConfigService.java
│   │   │   │               │   ├── DatabaseInitializationService.java
│   │   │   │               │   ├── GrafoService.java
│   │   │   │               │   ├── JuegoService.java
│   │   │   │               │   ├── LocalidadService.java
│   │   │   │               │   ├── MisionManager.java
│   │   │   │               │   ├── NarrativaManager.java
│   │   │   │               │   ├── PistaService.java
│   │   │   │               │   └── UsuarioService.java
│   │   │   │               ├── tts
│   │   │   │               │   └── TTSManager.java
│   │   │   │               └── ui
│   │   │   │                   ├── InputManager.java
│   │   │   │                   ├── VentanaAyudaVisual.java
│   │   │   │                   ├── VentanaCaptura.java
│   │   │   │                   ├── VentanaFinal.java
│   │   │   │                   ├── VentanaJuego.java
│   │   │   │                   ├── VentanaLogin.java
│   │   │   │                   └── VentanaRegistro.java
│   │   │   └── resources
│   │   │       ├── config.properties
│   │   │       ├── imagenes
│   │   │       │   └── secuaces
│   │   │       ├── init_database.sql
│   │   │       ├── localidades.csv
│   │   │       ├── messages.properties
│   │   │       ├── pistas.csv
│   │   │       ├── secuaces.csv
│   │   │       └── sonidos
│   │   │           ├── efectos
│   │   │           ├── musica
│   │   │           └── voz
│   │   │               ├── login_exitoso.mp3
│   │   │               └── login_fallido.mp3
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── ejemplo
│   │                   └── carmenuy
│   │                       ├── dao
│   │                       │   └── UsuarioDAOTest.java
│   │                       ├── model
│   │                       ├── service
│   │                       │   └── PistaServiceTest.java
│   │                       └── ui
│   │                           └── VentanaAyudaVisualTest.java
│   └── target
│       ├── classes
│       ├── dependency
│       └── test-classes
│           └── com
│               └── ejemplo
│                   └── carmenuy
│                       ├── dao
│                       ├── model
│                       ├── service
│                       └── ui



