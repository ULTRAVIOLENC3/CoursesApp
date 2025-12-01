package com.testapp.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.testapp.auth.R

@Composable
fun SocialsButtonComponent(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit,
    colors: Brush,
    iconRes: Int,
    iconWidthDp: Dp = 50.dp,
    iconHeightDp: Dp = 50.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(brush = colors, shape = RoundedCornerShape(30.dp))
    )
    {
        Button(
            onClick = onClick,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            )
        ) {}
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(width = iconWidthDp, height = iconHeightDp),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }

}

@Preview
@Composable
private fun SocialsButtonComponentPreview() {
    SocialsButtonComponent(
        onClick = {},
        colors = Brush.verticalGradient(listOf(Color(0xFF2683ED), Color(0xFF2683ED))),
        iconRes = R.drawable.ic_vk,
        modifier = Modifier.size(150.dp, 40.dp)
    )
}