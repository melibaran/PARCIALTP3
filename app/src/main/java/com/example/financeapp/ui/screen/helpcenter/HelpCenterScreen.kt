package com.example.financeapp.ui.screen.helpcenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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

data class FAQItem(
    val question: String,
    val answer: String
)

enum class HelpTab {
    FAQ, CONTACT_US
}

enum class CategoryTab {
    GENERAL, ACCOUNT, SERVICES
}

data class ContactOption(
    val title: String,
    val iconResId: Int
)

@Composable
fun HelpCenterScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(HelpTab.FAQ) }
    var selectedCategory by remember { mutableStateOf(CategoryTab.GENERAL) }
    var searchQuery by remember { mutableStateOf("") }

    val faqItems = listOf(
        FAQItem("How to use FinWise?", "FinWise is easy to use. Simply download the app, create an account, and start tracking your expenses."),
        FAQItem("How much does it cost to use FinWise?", "FinWise offers a free basic plan and premium features with a subscription."),
        FAQItem("How to contact support?", "You can contact us through the Contact Us tab or email support@finwise.com"),
        FAQItem("How can I reset my password if I forget it?", "Click on 'Forgot Password' on the login screen and follow the instructions sent to your email."),
        FAQItem("Are there any privacy or data security measures in place?", "Yes, we use industry-standard encryption and follow strict privacy policies."),
        FAQItem("Can I customize settings within the application?", "Yes, you can customize various settings in the app preferences."),
        FAQItem("How can I delete my account?", "Go to Settings > Account > Delete Account and follow the confirmation steps."),
        FAQItem("How do I access my expense history?", "Navigate to the Transactions screen to view your complete expense history."),
        FAQItem("Can I use the app offline?", "Yes, basic features are available offline, but syncing requires internet connection.")
    )

    val contactOptions = listOf(
        ContactOption("Customer Service", R.drawable.customer_service),
        ContactOption("Website", R.drawable.website),
        ContactOption("Facebook", R.drawable.facebook),
        ContactOption("Whatsapp", R.drawable.whatsapp),
        ContactOption("Instagram", R.drawable.instagram)
    )

    Scaffold(
        topBar = {
            TopBar(
                title = "Help & FAQS",
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
                // "How Can We Help You?" header
                item {
                    Text(
                        text = "How Can We Help You?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                // FAQ / Contact Us toggle
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(50),
                        color = Light_green
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TabButton(
                                text = "FAQ",
                                isSelected = selectedTab == HelpTab.FAQ,
                                onClick = { selectedTab = HelpTab.FAQ },
                                modifier = Modifier.weight(1f)
                            )
                            TabButton(
                                text = "Contact Us",
                                isSelected = selectedTab == HelpTab.CONTACT_US,
                                onClick = { selectedTab = HelpTab.CONTACT_US },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                if (selectedTab == HelpTab.FAQ) {
                    // Category tabs
                    item {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            shape = RoundedCornerShape(50),
                            color = Light_green
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                CategoryTabButton(
                                    text = "General",
                                    isSelected = selectedCategory == CategoryTab.GENERAL,
                                    onClick = { selectedCategory = CategoryTab.GENERAL },
                                    modifier = Modifier.weight(1f)
                                )
                                CategoryTabButton(
                                    text = "Account",
                                    isSelected = selectedCategory == CategoryTab.ACCOUNT,
                                    onClick = { selectedCategory = CategoryTab.ACCOUNT },
                                    modifier = Modifier.weight(1f)
                                )
                                CategoryTabButton(
                                    text = "Services",
                                    isSelected = selectedCategory == CategoryTab.SERVICES,
                                    onClick = { selectedCategory = CategoryTab.SERVICES },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    // Search bar
                    item {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 12.dp),
                            placeholder = { Text("Search") },
                            shape = RoundedCornerShape(50),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Caribbean_green,
                                unfocusedBorderColor = Light_green,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            singleLine = true
                        )
                    }

                    // FAQ items
                    items(faqItems) { faq ->
                        FAQItemCard(faq = faq)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                } else {
                    // Contact Us options
                    items(contactOptions) { option ->
                        ContactOptionItem(
                            option = option,
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(12.dp))
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
private fun TabButton(
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
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
private fun CategoryTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Light_green else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun FAQItemCard(faq: FAQItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = faq.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.down_arrow),
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp)
            )
        }

        if (isExpanded) {
            Text(
                text = faq.answer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Caribbean_green.copy(alpha = 0.2f),
            thickness = 1.dp
        )
    }
}

@Composable
private fun ContactOptionItem(
    option: ContactOption,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (option.title) {
                    "Customer Service" -> navController.navigate("online_support")
                    "Website" -> { /* TODO: Open website */ }
                    "Facebook" -> { /* TODO: Open Facebook */ }
                    "Whatsapp" -> { /* TODO: Open Whatsapp */ }
                    "Instagram" -> { /* TODO: Open Instagram */ }
                }
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Caribbean_green),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = option.iconResId),
                    contentDescription = option.title,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = option.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Image(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Navigate",
            modifier = Modifier.size(20.dp)
        )
    }
}


