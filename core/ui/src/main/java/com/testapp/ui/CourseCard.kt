package com.testapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.testapp.designsystem.theme.CoursesAppTheme
import com.testapp.designsystem.theme.Typography
import com.testapp.model.CourseItem

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: CourseItem,
    onDetailsClick: () -> Unit,
    onBookmarkClick: () -> Unit,

    ) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = painterResource(com.testapp.designsystem.R.drawable.img_test),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            val bookmarkIcon: Int
            val bookmarkTint: Color

            if(course.hasLike) {
                bookmarkIcon = com.testapp.designsystem.R.drawable.ic_bookmark_fill
                bookmarkTint = MaterialTheme.colorScheme.primary
            } else
            {
                bookmarkIcon = com.testapp.designsystem.R.drawable.ic_bookmark
                bookmarkTint = MaterialTheme.colorScheme.onPrimary
            }


            Button(
                onClick = onBookmarkClick,
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                    contentColor = bookmarkTint
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .size(28.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(bookmarkIcon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 8.dp)
                    .padding(start = 8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(com.testapp.designsystem.R.drawable.ic_star),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = course.rate,
                            style = Typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                Spacer(Modifier.size(4.dp))
                Row(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = course.startDate,
                            style = Typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }

        Column(
            modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(
                text = course.title,
                style = Typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = course.text,
                style = Typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary.copy(0.7f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${course.price} ₽",
                    style = Typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row() {
                    Text(
                        text = "Подробнее",
                        style = Typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onDetailsClick() }
                    )
                    Icon(
                        painter = painterResource(com.testapp.designsystem.R.drawable.ic_arrow_right_short_fill),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 328, heightDp = 236, backgroundColor = 0xFF00FF00)
@Composable
private fun CourseItemComponentPreview() {
    CoursesAppTheme {
        CourseCard(
            onDetailsClick = {},
            onBookmarkClick = {},
            course = CourseItem(
                rate = "4.9",
                price = "999",
                startDate = "22 мая 2024",
                title = "Java разработчик с нуля",
                text = "Освойте backend-разработку и программирование на Java, " +
                        "фреймворки Spring и Maven, работу с базами данных и API. " +
                        "Создайте свой собственный проект, собрав портфолио и став" +
                        " востребованным специалистом для любой IT компании.",
                hasLike = true
            )
        )
    }
}