package com.example.financeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green

enum class MessageSender {
    USER, ASSISTANT
}

data class ChatMessage(
    val id: String,
    val text: String,
    val time: String,
    val sender: MessageSender
)

enum class ChatTabType {
    SUPPORT_ASSISTANT, HELP_CENTER
}

@Composable
fun ChatDetailScreen(
    navController: NavController,
    chatId: String = "1"
) {
    var selectedTab by remember { mutableStateOf(ChatTabType.SUPPORT_ASSISTANT) }
    var messageText by remember { mutableStateOf("") }

    val messages = listOf(
        ChatMessage(
            id = "1",
            text = "Welcome, I am your virtual assistant.",
            time = "14:00",
            sender = MessageSender.ASSISTANT
        ),
        ChatMessage(
            id = "2",
            text = "How can I help you today?",
            time = "14:00",
            sender = MessageSender.ASSISTANT
        ),
        ChatMessage(
            id = "3",
            text = "Hello! I have a question. How can I record my expenses by date?",
            time = "14:01",
            sender = MessageSender.USER
        ),
        ChatMessage(
            id = "4",
            text = "Response to your request:\nYou can register expenses in the top menu of the homepage.",
            time = "",
            sender = MessageSender.ASSISTANT
        ),
        ChatMessage(
            id = "5",
            text = "Enter the purchase information, including the date, etc.",
            time = "14:03",
            sender = MessageSender.ASSISTANT
        ),
        ChatMessage(
            id = "6",
            text = "Ok, thanks a lot.",
            time = "14:05",
            sender = MessageSender.USER
        ),
        ChatMessage(
            id = "7",
            text = "It was a pleasure to accommodate your request. See you soon!",
            time = "14:06 | Chat Ended",
            sender = MessageSender.ASSISTANT
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
                containerColor = Caribbean_green
            )
        },
        containerColor = Caribbean_green
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
            ) {
                // Tab selector
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(50),
                    color = Light_green
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChatTabButton(
                            text = "Support Assistant",
                            isSelected = selectedTab == ChatTabType.SUPPORT_ASSISTANT,
                            onClick = { selectedTab = ChatTabType.SUPPORT_ASSISTANT },
                            modifier = Modifier.weight(1f)
                        )
                        ChatTabButton(
                            text = "Help Center",
                            isSelected = selectedTab == ChatTabType.HELP_CENTER,
                            onClick = { selectedTab = ChatTabType.HELP_CENTER },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Messages
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    reverseLayout = false
                ) {
                    items(messages) { message ->
                        ChatMessageBubble(message = message)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Input area
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(50),
                    color = Caribbean_green
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Camera button
                        IconButton(
                            onClick = { /* TODO: Add image */ },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = "Add image",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Text input
                        OutlinedTextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            modifier = Modifier.weight(1f),
                            placeholder = {
                                Text(
                                    "Write Here...",
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedTextColor = MaterialTheme.colorScheme.onBackground
                            ),
                            shape = RoundedCornerShape(50),
                            singleLine = true
                        )

                        // Voice button
                        IconButton(
                            onClick = { /* TODO: Voice input */ },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.voice),
                                contentDescription = "Voice input",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Send button
                        IconButton(
                            onClick = {
                                if (messageText.isNotBlank()) {
                                    // TODO: Send message
                                    messageText = ""
                                }
                            },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Caribbean_green else Color.Transparent,
            contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ChatMessageBubble(message: ChatMessage) {
    val isUser = message.sender == MessageSender.USER

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier.widthIn(max = 280.dp),
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isUser) Caribbean_green else Light_green
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isUser) Color.White else MaterialTheme.colorScheme.onBackground
                )
            }

            if (message.time.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.time,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

