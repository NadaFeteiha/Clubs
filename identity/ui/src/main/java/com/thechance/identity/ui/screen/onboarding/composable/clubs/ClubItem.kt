package com.thechance.identity.ui.screen.onboardclubs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thechance.identity.ui.R
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.*

@Composable
fun ClubItem(
    @DrawableRes iconId: Int,
    tintColor: Color,
    isSelected: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit,
    selectedColor: Color = LightPrimaryBrandColor
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 2.dp,
                color = if (isSelected) selectedColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = Color(0xFFFAFAFA),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {onChecked(!isSelected)}
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if(isSelected) selectedColor else tintColor,
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = text,
            style = Typography.CardTitle,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(bottom = 30.dp, start = 30.dp, end = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubItem(){
    val state = remember {
        mutableStateOf(false)
    }

    ClubItem(
        iconId = R.drawable.ic_coding,
        tintColor = Color.Black,
        isSelected = state.value,
        text = "Coding",
        onChecked = {
                    state.value = it
        },
    )
}