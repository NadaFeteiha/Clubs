package com.devfalah.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devfalah.ui.screen.accountSettings.accountSettingsRoute
import com.devfalah.ui.screen.clubs.clubsRoute
import com.devfalah.ui.screen.createPost.createPostRoute
import com.devfalah.ui.screen.friendrequest.friendRequestRoute
import com.devfalah.ui.screen.friends.friendsRoute
import com.devfalah.ui.screen.home.homeRoute
import com.devfalah.ui.screen.menu.menuRoute
import com.devfalah.ui.screen.notification.notificationRoute
import com.devfalah.ui.screen.profile.profileRoute
import com.devfalah.ui.screen.savedPosts.savedPostsRoute
import com.devfalah.ui.screen.search.searchRoute


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClubsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.screen_route
    ) {

        homeRoute(navController = navController)
        searchRoute(navController = navController)
        clubsRoute(navController = navController)
        notificationRoute(navController = navController)
        menuRoute(navController = navController)
        friendRequestRoute(navController = navController)
        profileRoute(navController = navController)
        createPostRoute(navController = navController)
        friendsRoute(navController = navController)
        savedPostsRoute(navController = navController)
        accountSettingsRoute(navController = navController)
    }
}

fun NavController.showingBack():Boolean{
    return when(this.currentBackStackEntry?.destination?.route){
        Screen.Home.screen_route ,
        Screen.Clubs.screen_route ,
        Screen.Search.screen_route,
        Screen.Notification.screen_route ,
        Screen.Menu.screen_route -> false
        else -> true
    }
}