# Finance App - Proyecto Final

Esta es una aplicación de finanzas personales para Android, desarrollada con Jetpack Compose, arquitectura MVVM y Firebase como backend.

---

Esta sección contiene todo lo necesario para probar la funcionalidad principal de la aplicación conectada a la nube de Firebase.

### 1. Instalación de la Aplicación (Modo Producción)

El repositorio ya incluye el archivo de instalación final (`.apk`) que se conecta directamente a la nube de Firebase.

**Pasos para instalar:**

1.  **Localiza el APK**: El archivo se encuentra en la siguiente ruta dentro del proyecto:
    ```
    /app/build/outputs/apk/release/app-release.apk
    ```
2.  **Inicia un Emulador de Android**: Abre Android Studio y lanza un emulador desde el "Device Manager".
3.  **Instala el APK**:
    *   **Método fácil**: Arrastra y suelta el archivo `app-release.apk` directamente sobre la ventana del emulador.

### 2. Prueba de Funcionalidad

1.  **Abre la aplicación** desde el menú del emulador (no desde Android Studio).
2.  **Usuario de Prueba Automático**: Al iniciar la app por primera vez, un usuario de prueba se creará automáticamente en la nube.
    - **Email:** `test@email.com`
    - **Password:** `123456`
    Puedes iniciar sesión con estas credenciales para probar la app.
3.  **Registra Nuevos Usuarios**: También puedes usar la opción "Sign Up" para crear tus propios usuarios.

### 3. Verificación de Datos en la Nube de Firebase

Como has sido añadido como **propietario del proyecto de Firebase**, puedes verificar todos los datos en tiempo real.

1.  **Accede a la Consola de Firebase**:
    - https://console.firebase.google.com/u/0/project/financeapp-a17da/authentication/users
2.  **Ver Usuarios Registrados**:
    - En el menú de la izquierda, ve a **Build > Authentication**.
    - En la pestaña **Users**, verás una lista con todos los usuarios registrados, incluido el de prueba.
3.  **Ver Datos en la Base de Datos**:
    - En el menú, ve a **Build > Firestore Database**.
    - Aquí podrás ver la colección `users` con los documentos correspondientes a cada usuario.

---

## Guía para Desarrollo (Configuración Local)

Esta sección es para desarrolladores que deseen ejecutar el proyecto en un entorno de desarrollo local utilizando los emuladores de Firebase.

1.  **Inicia los Emuladores**:
    ```bash
    firebase emulators:start --only auth,firestore
    ```
2.  **Ejecuta desde Android Studio**: Presiona el botón "Run" (▶️) para compilar en modo `debug`. La app se conectará automáticamente a los emuladores locales.
3.  **Verifica los Datos Locales**: Los datos creados en este modo estarán disponibles en la **UI de los Emuladores** (`http://localhost:4000`), no en la consola de la nube.

💰 Aclaracion
Para ir a las transactions de la seccion 9.3.2 , ir a Transaction y hacer click en "Total Balance"

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
🔐 Seguridad
Nota Importante: En un entorno de producción, las contraseñas deberían ser hasheadas antes de almacenarse. Esta implementación actual almacena contraseñas en texto plano únicamente con fines educativos. Otro detalle, es que se indica si el usuario no existe al hacer login o si la password es invalida. Entendemos que es un error grave en cuanto a la seguridad, esto lo hicimos asi solo a fines de demostrar que se persiste un usuario y que se va a un local storage a buscar a dicho usuario y evidenciar que se valida la password si existe.
