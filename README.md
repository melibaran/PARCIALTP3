# Finance App

Una aplicación Android de finanzas personales construida con Jetpack Compose, arquitectura MVVM y Firebase.

##  Configuración y Pruebas con Firebase

Esta aplicación utiliza Firebase para la autenticación de usuarios y el almacenamiento de datos. Funciona en dos modos:

---

### 1. Modo Desarrollo (Pruebas Locales con Emuladores)

Este es el modo que usarás para correr el proyecto desde Android Studio. Los datos se guardan localmente en tu computadora y no en la nube.

**Pasos para ejecutar:**

1.  **Inicia los emuladores de Firebase**: Abre una terminal en la raíz del proyecto y ejecuta:
    ```bash
    firebase emulators:start --only auth,firestore
    ```
    Esto levantará los servicios de Autenticación y Base de Datos localmente.

2.  **Ejecuta la aplicación**: Abre el proyecto en Android Studio y presiona el botón "Run" (▶️) para instalar la app en tu emulador o dispositivo Android.

3.  **Usuario de Prueba Automático**: La primera vez que inicies la app, se creará automáticamente un usuario de prueba:
    - **Email:** `test@email.com`
    - **Password:** `123456`
    Puedes iniciar sesión directamente con estas credenciales.

4.  **Registra nuevos usuarios**: También puedes ir a "Sign Up" para crear más usuarios.

5.  **Verifica los datos**: Para ver todos los usuarios que has creado (incluido el de prueba), abre la siguiente dirección en tu navegador:
    - **URL:** `http://localhost:4000`
    Navega a las pestañas **Authentication** y **Firestore** para ver los datos en tiempo real.

---

### 2. Modo Producción (Conexión a la Nube de Firebase)

Este modo es para generar una versión final de la app (`.apk`) que se conecta a la nube real de Firebase.

**Pasos para ejecutar:**

1.  **Genera el APK de Release**:
    - En Android Studio, ve a **Build > Generate Signed Bundle / APK...**.
    - Selecciona **APK** y sigue los pasos para firmar la aplicación. El proyecto ya incluye un `release.keystore` de prueba.
    - El archivo se generará en `app/build/outputs/apk/release/app-release.apk`.

2.  **Instala el APK**: Instala el `app-release.apk` en un dispositivo físico o emulador.

3.  **Prueba en la Nube**: Al abrir esta versión, los usuarios que registres (incluido el de prueba automático) se guardarán en la consola de Firebase en la nube, la cual puedes consultar en [console.firebase.google.com](https://console.firebase.google.com).

📱 Instrucciones de Uso
Primer Uso
Para utilizar la aplicación por primera vez, debes seguir estos pasos:

Registro de Usuario

Al abrir la aplicación, verás la pantalla de Login
Presiona el botón "Sign Up" para crear una cuenta
Completa el formulario con:
Nombre completo
Email (será tu identificador único)
Contraseña (mínimo 6 caracteres)
Confirmación de contraseña
Presiona "Sign Up" para crear tu cuenta
Inicio de Sesión

Después del registro, serás redirigido a la pantalla de inicio de sesión
Ingresa tu email y contraseña
Presiona "Log In" para acceder a la aplicación
Uso de la Aplicación

Una vez autenticado, podrás acceder a todas las funcionalidades de la app
🗄️ Persistencia de Datos con Room
La aplicación utiliza Room Database para la persistencia local de datos de usuario.

Modelo de Datos
La entidad User almacena la información del usuario:

@Entity(tableName = "user")
data class User(
@PrimaryKey(autoGenerate = true)
val id: Int = 0,
@ColumnInfo(name = "email")
val email: String,
@ColumnInfo(name = "first_name")
val firstName: String,
@ColumnInfo(name = "last_name")
val lastName: String,
@ColumnInfo(name = "password")
val password: String
)
Capa de Acceso a Datos (DAO)
UserDao define las operaciones de base de datos:

@Dao
interface UserDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
    
    @Query("UPDATE user SET password = :newPassword WHERE email = :email")
    suspend fun updatePassword(email: String, newPassword: String): Int
    
    @Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?
    
    @Query("DELETE FROM user WHERE email = :email")
    suspend fun deleteUserByEmail(email: String): Int
}
Repository Pattern
UserRepository abstrae el acceso a datos:

interface UserRepository {
suspend fun insertUser(user: User): Long
suspend fun getUserByEmail(email: String): User?
suspend fun updatePassword(email: String, newPassword: String): Int
suspend fun getUserById(userId: Int): User?
suspend fun deleteUser(email: String): Int
}
Base de Datos
AppDatabase configura la base de datos Room:

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
abstract fun userDao(): UserDao
}
Flujo de Registro y Login
Registro (SignUp)
El usuario completa el formulario de registro
SignUpViewModel valida los datos:
Campos obligatorios completos
Contraseñas coinciden
Contraseña tiene al menos 6 caracteres
Email no está duplicado en la base de datos
Si las validaciones pasan, se crea un objeto User
UserRepository.insertUser() guarda el usuario en Room
El usuario es redirigido a la siguiente pantalla
Login
El usuario ingresa email y contraseña
LoginViewModel busca el usuario por email usando UserRepository.getUserByEmail()
Si el usuario existe, verifica que la contraseña coincida
Si las credenciales son correctas, el usuario accede a la aplicación
Si no, se muestra un mensaje de error específico
## 🔐 Seguridad

**Nota Importante**: En un entorno de producción real, las contraseñas deberían ser hasheadas antes de almacenarse. Esta implementación actual almacena contraseñas en texto plano únicamente con fines educativos.

Además, por motivos de demostración, el sistema de login indica explícitamente si un usuario no existe o si la contraseña es incorrecta, una práctica que se debe evitar en producción para no dar pistas a posibles atacantes.

