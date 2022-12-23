package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryGrayColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentBody(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickLike: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.text,
            textAlign = if (state.isOwnerComment) {
                TextAlign.End
            } else {
                TextAlign.Start
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = state.timeCreate,
                fontSize = 12.sp,
                color = LightSecondaryGrayColor,
                fontWeight = FontWeight.Normal
            )

            LikeCommentIcon(
                painter = painterResource(
                    id = if (state.isLikedByUser) {
                        R.drawable.heart_full
                    } else {
                        R.drawable.like_icon
                    }
                ),
                tint = if (state.isLikedByUser) {
                    LightPrimaryBrandColor
                } else {
                    LightSecondaryGrayColor
                },
                onClick = onClickLike,
                totalLikes = state.totalLikes
            )
        }
    }

}


@Composable
fun LikeCommentIcon(
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = Color.Unspecified,
    totalLikes: Int,
) {
    Row {

        if (totalLikes > 0) {
            Text(
                text = "$totalLikes",
                fontSize = 12.sp,
                color = LightSecondaryGrayColor,
                fontWeight = FontWeight.Normal
            )
            WidthSpacer4()
        }

        Icon(
            modifier = Modifier.nonRippleEffect { onClick() },
            painter = painter,
            tint = tint,
            contentDescription = null
        )
    }
}