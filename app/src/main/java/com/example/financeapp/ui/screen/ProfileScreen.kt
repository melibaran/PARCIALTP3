package com.example.financeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.profile_title),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                color = Void,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(80.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                colors = CardDefaults.cardColors(containerColor = com.example.financeapp.ui.theme.Honeydew)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(Modifier.height(60.dp))

                    Text(
                        text = "John Smith",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Void,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "ID: 25030024",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Void.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ProfileMenuItem(
                            icon = R.drawable.edit_profile,
                            text = stringResource(R.string.edit_profile),
                            onClick = onEditProfileClick
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = R.drawable.security,
                            text = stringResource(R.string.security),
                            onClick = onSecurityClick
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = R.drawable.settings,
                            text = stringResource(R.string.setting),
                            onClick = onSettingClick
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = R.drawable.help,
                            text = stringResource(R.string.help),
                            onClick = onHelpClick
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        ProfileMenuItem(
                            icon = R.drawable.logout,
                            text = stringResource(R.string.logout),
                            onClick = onLogoutClick
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
        
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 130.dp)
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 4.dp),
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

