package com.devfalah.ui.screen.search

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.ClubItem
import com.devfalah.ui.composable.FriendItem
import com.devfalah.ui.composable.SearchTextField
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.search.SearchUIState
import com.devfalah.viewmodels.search.SearchViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current

    SearchContent(
        state = state,
        onSearchValueChanged = viewModel::onSearchTextChange,
        onClubSelected = {Toast.makeText(context, "Should Navigate to Club Id = $it", Toast.LENGTH_LONG).show()},
        OnUserClick = { navController.navigateToProfile(it) }
    )

    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController, color = LightBackgroundColor, darkIcons = true
        )
    }
}

@Composable
fun SearchContent(
    state: SearchUIState,
    onSearchValueChanged: (String) -> Unit,
    onClubSelected: (Int) -> Unit,
    OnUserClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .background(LightBackgroundColor)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(key = "keyword") {
            SearchTextField(
                text = state.keyword,
                onValueChanged = onSearchValueChanged
            )
        }

        items(
            items = state.clubs,
            key = { "${it.id} ${it.title}" }
        ) { club ->
            ClubItem(state = club, onClubSelected = onClubSelected)
        }

        items(
            items = state.users,
            key = { it.id }
        ) { user ->
            FriendItem(state = user, onOpenProfileClick = OnUserClick)
        }

    }
}


