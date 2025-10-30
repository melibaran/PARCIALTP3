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

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController, intent: Intent) {



}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController= rememberNavController()
    WelcomeScreen (Modifier.fillMaxSize(), navController, Intent(""))
}

