package com.testapp.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.testapp.designsystem.theme.CoursesAppTheme


@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
) {
    ProfileScreen(modifier = modifier)
}

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold() { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 56.dp),
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {}
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun ProfileScreenPreview() {
    CoursesAppTheme {
        ProfileScreen()
    }
}