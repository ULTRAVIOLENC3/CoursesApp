package com.testapp.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.testapp.auth.components.SocialsButtonComponent
import com.testapp.designsystem.theme.CoursesAppTheme
import com.testapp.designsystem.theme.Typography


@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        onLoginClick = { onLoginClick() },
        onSignUpClick = {},
        onForgetPasswordClick = {},
        onVkClicked = { },
        onOkClicked = { },
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgetPasswordClick: () -> Unit,
    onVkClicked: () -> Unit,
    onOkClicked: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Scaffold() { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 140.dp)
        )
        {
            Text(
                text = "Вход",
                style = Typography.headlineMedium
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Email",
                style = Typography.titleMedium
            )

            TextField(
                value = uiState.email,
                onValueChange = { onEmailChange(it) },
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        modifier = Modifier.alpha(0.5f)
                    ) },
                singleLine = true,
                textStyle = Typography.bodyMedium,
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Пароль",
                style = Typography.titleMedium
            )
            TextField(
                value = uiState.password,
                onValueChange = { onPasswordChange(it) },
                placeholder = {
                    Text(
                        text = "Введите пароль",
                        modifier = Modifier.alpha(0.5f),
                    ) },
                singleLine = true,
                textStyle = Typography.bodyMedium,
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { onLoginClick() },
                modifier = Modifier.fillMaxWidth(),
                content = { Text("Вход") },
                enabled = uiState.isLoginButtonEnabled
            )
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "Нет аккаунта? ",
                    style = Typography.labelMedium,
                    modifier = Modifier
                )
                Text(
                    text = "Регистрация",
                    style = Typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSignUpClick() }
                )
            }
            Text(
                text = "Забыл пароль",
                style = Typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onForgetPasswordClick() }
            )
            Spacer(Modifier.size(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.outline)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            )
            {
                SocialsButtonComponent(
                    onClick = onVkClicked,
                    colors = Brush.verticalGradient(listOf(Color(0xFF2683ED), Color(0xFF2683ED))),
                    iconRes = R.drawable.ic_vk,
                    iconWidthDp = 26.dp,
                    iconHeightDp = 16.dp,
                    modifier = Modifier.size(156.dp, 40.dp)
                )
                SocialsButtonComponent(
                    onClick = onOkClicked,
                    colors = Brush.verticalGradient(listOf(Color(0xFFF98509), Color(0xFFF95D00))),
                    iconRes = R.drawable.ic_ok,
                    modifier = Modifier.size(156.dp, 40.dp),
                    iconWidthDp = 15.3.dp,
                    iconHeightDp = 26.dp,
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun AuthScreenPreview() {
    CoursesAppTheme {
        LoginScreen(
            onLoginClick = {},
            onSignUpClick = {},
            onForgetPasswordClick = {},
            onVkClicked = {},
            onOkClicked = {},
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}