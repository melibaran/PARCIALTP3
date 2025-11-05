package com.example.financeapp.ui.screen.onlinesupport

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green

data class ChatItem(
    val id: String,
    val title: String,
    val message: String,
    val time: String,
    val isActive: Boolean = false
)

data class ChatSection(
    val sectionTitle: String,
    val chats: List<ChatItem>
)

@Composable
fun OnlineSupportScreen(navController: NavController) {
    val chatSections = listOf(
        ChatSection(
            sectionTitle = "Active Chats",
            chats = listOf(
                ChatItem(
                    id = "1",
                    title = "Support Assistant",
                    message = "Hello! I'm here to assist you",
                    time = "2 Min Ago",
                    isActive = true
                )
            )
        ),
        ChatSection(
            sectionTitle = "Ended Chats",
            chats = listOf(
                ChatItem(
                    id = "2",
                    title = "Help Center",
                    message = "Your account is ready to use...",
                    time = "Feb 08 - 2024"
                ),
                ChatItem(
                    id = "3",
                    title = "Support Assistant",
                    message = "Hello! I'm here to assist you",
                    time = "Dic 24 - 2023"
                ),
                ChatItem(
                    id = "4",
                    title = "Support Assistant",
                    message = "Hello! I'm here to assist you",
                    time = "Sep 10 - 2023"
                ),
                ChatItem(
                    id = "5",
                    title = "Help Center",
                    message = "Hi, how are you today?",
                    time = "June 12 - 2023"
                )
            )
        )
    )

    Scaffold(
        topBar = {
            TopBar(
                title = "Online Support",
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.popBackStack() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green)
        },
        containerColor = Caribbean_green
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                chatSections.forEach { section ->
                    // Section header
                    item {
                        Text(
                            text = section.sectionTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                    // Chat items
                    items(section.chats) { chat ->
                        ChatItemCard(
                            chat = chat,
                            onClick = {
                                navController.navigate("chat_detail/${chat.id}")
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Start Another Chat button
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { /* TODO: Start new chat */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Caribbean_green
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "Start Another Chat",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
private fun ChatItemCard(
    chat: ChatItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = Light_green
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Caribbean_green),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.online_support),
                    contentDescription = chat.title,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = chat.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = chat.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Time
            Text(
                text = chat.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }
    }
}


