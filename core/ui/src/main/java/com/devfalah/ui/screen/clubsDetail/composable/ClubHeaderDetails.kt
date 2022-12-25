package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.ui.util.htmlText
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@Composable
fun ClubHeaderDetails(
    state: ClubDetailsUiState,
    onBack: () -> Unit,
    maxLineContentExpand: Int = 2,
) {

    var expanded by remember { mutableStateOf(false) }
    var popupController by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPrimaryBrandColor)
    ) {

        val (backButton, textDescription, textName, readMore, cover) = createRefs()

        BackButton(
            modifier = Modifier
                .wrapContentSize()
                .nonRippleEffect { onBack() }
                .padding(16.dp)
                .constrainAs(backButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            tint = WhiteColor
        )

        if (state.ownerId == state.userId) {

            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.nonRippleEffect { expanded = true },
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(WhiteColor),
                offset = DpOffset(50.dp, 0.dp)
            ) {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        expanded = false
//                        onClickPostSetting(post)
                    }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.users_requests),
                        textAlign = TextAlign.Center
                    )
                    HeightSpacer8()

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.edit_club),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Text(
            text = state.name,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textName) {
                    top.linkTo(backButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 1
        )

        ReadMorePopup(
            text = state.description.htmlText(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .constrainAs(textDescription) {
                    top.linkTo(textName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            minimizedMaxLines = maxLineContentExpand,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                color = WhiteColor,
            ),
            onShowDescription = { popupController = true }
        )

        if (popupController) {
            DescriptionClubDialog(
                descriptionClub = state.description.htmlText(),
            ) {
                popupController = false
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(cover) {
                    top.linkTo(textDescription.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(124.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                    .background(LightBackgroundColor),
                contentAlignment = Alignment.Center
            ) {

                Row {
                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_menu_language,
                        text = state.privacy
                    )

                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_people,
                        text = state.membersCount.toString()
                    )

                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_comment,
                        text = state.postCount.toString()
                    )

                }

            }
        }

    }
}