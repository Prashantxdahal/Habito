package com.example.habito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                delay(1000) // Simulate network request
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error("Email and password cannot be empty.")
                }
            } catch (e: CancellationException) {
                // This is expected when the user navigates away.
                // We reset the state and allow the coroutine to be cancelled.
                _authState.value = AuthState.Unauthenticated
            }
        }
    }

    fun signupUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading
                delay(1000) // Simulate network request
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // In a real app, you'd also check if the user already exists.
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error("Email and password cannot be empty.")
                }
            } catch (e: CancellationException) {
                // This is expected when the user navigates away.
                _authState.value = AuthState.Unauthenticated
            }
        }
    }
}
