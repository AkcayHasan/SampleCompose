package com.akcay.samplecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.akcay.samplecompose.navigation.MainDestinations
import com.akcay.samplecompose.navigation.rememberSampleComposeNavController
import com.akcay.samplecompose.screens.detail.DetailScreen
import com.akcay.samplecompose.screens.home.HomeScreen
import com.akcay.samplecompose.screens.home.HomeViewModel
import com.akcay.samplecompose.ui.theme.SampleComposeTheme


@Composable
fun SampleComposeApp() {
    SampleComposeTheme {
        val sampleComposeNavController = rememberSampleComposeNavController()
        NavHost(
            navController = sampleComposeNavController.navController,
            startDestination = MainDestinations.HOME_ROUTE
        ) {
            sampleComposeNavGraph(
                onMovieSelected = sampleComposeNavController::navigateToMovieDetail,
                upPress = sampleComposeNavController::upPress
            )
        }
    }
}



private fun NavGraphBuilder.sampleComposeNavGraph(
    onMovieSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    composable(
        route = MainDestinations.HOME_ROUTE
    ) {
        HomeScreen(onMovieClick = { id ->
            onMovieSelected.invoke(id, it)
        })
    }
    composable(
        "${MainDestinations.MOVIE_DETAIL_ROUTE}/{${MainDestinations.MOVIE_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.MOVIE_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val movieId = arguments.getLong(MainDestinations.MOVIE_ID_KEY)
        DetailScreen(movieId = movieId, upPress = upPress)
    }
}