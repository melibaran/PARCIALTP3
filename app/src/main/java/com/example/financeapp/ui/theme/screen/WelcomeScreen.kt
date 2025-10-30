package com.example.financeapp.ui.theme.screen
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.DashboardActivity
import com.example.financeapp.R
import com.example.financeapp.ui.theme.robotoFamily

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController, intent: Intent) {
   //Creo un contexto, para pasarselo al Intent (el contexto que esta activo, todos los componentes/activities de ese contexto)
    val context = LocalContext.current

    Column (modifier = Modifier.fillMaxSize()) {
/*        Image(
            modifier = Modifier,
            painter = painterResource(id = com.example.financeapp.R.drawable.welcome),
            contentDescription = "welcome"
        )*/

        Text(text=stringResource(R.string.Login), style = MaterialTheme.typography.bodyLarge)

        Button(onClick = {
            //el ACTION_SEND es para compartir info con otras acitivtys EXTERNAS (gmail, wpp, etc.), es un share
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Subject o Título")
            }
            context.startActivity(intent)

            //ACTION_DIAL: para hacer la accion de hacer una llamada telefonica:
            val intentCALL = Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"))

            //ACTION_PICK: para abrir el selector de archivos del dispositivo,
            // en este caso para los contactos
            val intentCONTACT = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }

            //INTERNO: o para llamar a algo INTERNO, nuestro, como el DashboardActivity:
            val intentINTERNO = Intent(context, DashboardActivity::class.java)

        }) {
            Text(text = stringResource(id = R.string.go_to_login))
        }

        //ELEVATION STATE:
        //se usa el remember para q el valor se guarde. Este componente se va a rendeziar!!
        var name = remember { mutableStateOf("Melissa") } //tiene un estado
        Text(
            text = "Hola, $name"
        )


    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController= rememberNavController()
    WelcomeScreen (Modifier.fillMaxSize(), navController, Intent(""))
}

@Preview(showBackground = true)
@Composable
fun Prueba() {
    //le puedo poner el fontFFamily
    Text(text = "Discover your dream Job", fontFamily = robotoFamily)
}