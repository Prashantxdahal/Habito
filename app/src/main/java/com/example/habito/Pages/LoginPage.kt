package com.example.habito.Pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habito.ui.theme.AppColors
import com.example.habito.viewmodel.AuthState
import com.example.habito.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authState by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.PeaceBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // App Icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(AppColors.Blue)
        ) {
            Text("H", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Habito",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.Blue
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Welcome back! Let's stay consistent.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password Link
        Text(
            text = "Forgot Password?",
            color = AppColors.Blue,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    onNavigateToForgotPassword()
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button
        Button(
            onClick = {
                viewModel.loginUser(email.trim(), password.trim())
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.Blue),
            shape = RoundedCornerShape(16.dp)
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text("Log In", color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigate to Signup
        Row {
            Text("Don’t have an account? ")
            Text(
                "Sign Up",
                color = AppColors.Blue,
                modifier = Modifier.clickable {
                    onNavigateToSignup()
                }
            )
        }
    }

    // Handle State Changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                onLoginSuccess()
            }

            is AuthState.Error -> {
                val message = (authState as AuthState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}
