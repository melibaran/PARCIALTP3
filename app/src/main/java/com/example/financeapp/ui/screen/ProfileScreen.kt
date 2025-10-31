package com.example.financeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onSettingClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    AuthScreenLayout(title = "Profile") {
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.newhome),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Martín Macció",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Void,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "ID: 28030024",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Void.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        ProfileMenuItem(
            icon = R.drawable.edit_profile,
            text = "Edit Profile",
            onClick = onEditProfileClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            icon = R.drawable.security,
            text = "Security",
            onClick = onSecurityClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            icon = R.drawable.settings,
            text = "Setting",
            onClick = onSettingClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            icon = R.drawable.help,
            text = "Help",
            onClick = onHelpClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuItem(
            icon = R.drawable.logout,
            text = "Logout",
            onClick = onLogoutClick
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProfileMenuItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Vivid_blue),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Void,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Profile Screen",
    showSystemUi = true
)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Profile Menu Item"
)
@Composable
fun ProfileMenuItemPreview() {
    MaterialTheme {
        ProfileMenuItem(
            icon = R.drawable.edit_profile,
            text = "Edit Profile",
            onClick = {}
        )
    }
}

