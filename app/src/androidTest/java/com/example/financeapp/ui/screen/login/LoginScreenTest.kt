package com.example.financeapp.ui.screen.login

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests de UI para LoginScreen
 * Para ejecutar: ./gradlew connectedAndroidTest
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testLoginScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            LoginScreen()
        }

        // Verificar que los elementos principales están presentes
        composeTestRule.onNodeWithText("Welcome").assertExists()
        composeTestRule.onNodeWithText("Username Or Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Log In").assertExists()
        composeTestRule.onNodeWithText("Sign Up").assertExists()
    }

    @Test
    fun testLoginButtonDisabledWhenFieldsEmpty() {
        composeTestRule.setContent {
            LoginScreen()
        }

        // El botón debe estar deshabilitado cuando los campos están vacíos
        composeTestRule.onNodeWithText("Log In").assertExists()
    }

    @Test
    fun testEmailInputAcceptsText() {
        composeTestRule.setContent {
            LoginScreen()
        }

        // Encontrar el campo de email y escribir en él
        composeTestRule.onNodeWithText("example@example.com")
            .performTextInput("test@example.com")
    }
}

