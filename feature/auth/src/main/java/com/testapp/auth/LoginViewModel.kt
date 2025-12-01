package com.testapp.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        val allowedEmailCharsRegex = "[a-zA-Z0-9@._-]".toRegex()
        val emailFiltered = allowedEmailCharsRegex.findAll(newEmail).joinToString("") { it.value }

        _uiState.update { it.copy(email = emailFiltered) }
        validateInput()
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
        validateInput()
    }

    private fun validateInput() {
        val emailMaskRegex = "^[^@.]+@[^@.]+\\.[^@.]+\$".toRegex()

        val currentEmail = _uiState.value.email
        val currentPassword = _uiState.value.password

        val isEmailValid = currentEmail.matches(emailMaskRegex)
        val isPasswordValid = currentPassword.isNotEmpty()

        _uiState.update {
            it.copy(isLoginButtonEnabled = isEmailValid && isPasswordValid)
        }
    }
}
