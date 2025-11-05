# Finance App

Una aplicación Android de finanzas personales construida con Jetpack Compose y arquitectura MVVM.

## 🔑 Usuario por Defecto

La aplicación incluye un usuario de prueba creado automáticamente:

- **Email:** `test@email.com`
- **Password:** `123456`

Puedes usar estas credenciales para iniciar sesión sin necesidad de registrarte.

## 📱 Instrucciones de Uso

### Primer Uso
Para utilizar la aplicación por primera vez, debes seguir estos pasos:

## Aclaracion
Para ir a las transactions de la seccion 9.3.2 , ir a Transaction y hacer click en "Total Balance"
<img width="227" height="158" alt="Captura de pantalla 2025-11-05 a la(s) 1 10 26 a  m" src="https://github.com/user-attachments/assets/5e896819-5b44-423a-8a29-3b28fdec5ca4" />

1. **Registro de Usuario**
   - Al abrir la aplicación, verás la pantalla de Login
   - Presiona el botón "Sign Up" para crear una cuenta
   - Completa el formulario con:
     - Nombre completo
     - Email (será tu identificador único)
     - Contraseña (mínimo 6 caracteres)
     - Confirmación de contraseña
   - Presiona "Sign Up" para crear tu cuenta

2. **Inicio de Sesión**
   - Después del registro, serás redirigido a la pantalla de inicio de sesión
   - Ingresa tu email y contraseña
   - Presiona "Log In" para acceder a la aplicación

3. **Uso de la Aplicación**
   - Una vez autenticado, podrás acceder a todas las funcionalidades de la app

## 🗄️ Persistencia de Datos con Room

La aplicación utiliza **Room Database** para la persistencia local de datos de usuario.

#### Modelo de Datos
La entidad `User` almacena la información del usuario:
```kotlin
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
```

#### Capa de Acceso a Datos (DAO)
`UserDao` define las operaciones de base de datos:
```kotlin
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
```

#### Repository Pattern
`UserRepository` abstrae el acceso a datos:
```kotlin
interface UserRepository {
    suspend fun insertUser(user: User): Long
    suspend fun getUserByEmail(email: String): User?
    suspend fun updatePassword(email: String, newPassword: String): Int
    suspend fun getUserById(userId: Int): User?
    suspend fun deleteUser(email: String): Int
}
```

#### Base de Datos
`AppDatabase` configura la base de datos Room:
```kotlin
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

### Flujo de Registro y Login

#### Registro (SignUp)
1. El usuario completa el formulario de registro
2. `SignUpViewModel` valida los datos:
   - Campos obligatorios completos
   - Contraseñas coinciden
   - Contraseña tiene al menos 6 caracteres
   - Email no está duplicado en la base de datos
3. Si las validaciones pasan, se crea un objeto `User`
4. `UserRepository.insertUser()` guarda el usuario en Room
5. El usuario es redirigido a la siguiente pantalla

#### Login
1. El usuario ingresa email y contraseña
2. `LoginViewModel` busca el usuario por email usando `UserRepository.getUserByEmail()`
3. Si el usuario existe, verifica que la contraseña coincida
4. Si las credenciales son correctas, el usuario accede a la aplicación
5. Si no, se muestra un mensaje de error específico

## 🔐 Seguridad

**Nota Importante**: En un entorno de producción, las contraseñas deberían ser hasheadas antes de almacenarse. Esta implementación actual almacena contraseñas en texto plano únicamente con fines educativos.
Otro detalle, es que se indica si el usuario no existe al hacer login o si la password es invalida. Entendemos que es un error grave en cuanto a la seguridad, esto lo hicimos asi solo a fines de demostrar
que se persiste un usuario y que se va a un local storage a buscar a dicho usuario y evidenciar que se valida la password si existe.


