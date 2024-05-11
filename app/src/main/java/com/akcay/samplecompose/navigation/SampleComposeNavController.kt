package com.akcay.samplecompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


object MainDestinations {
    const val HOME_ROUTE = "home"
    const val MOVIE_DETAIL_ROUTE = "movieDetail"
    const val MOVIE_ID_KEY = "movieId"
}


@Composable
fun rememberSampleComposeNavController(
    navController: NavHostController = rememberNavController()
): SampleComposeNavController = remember(navController) {
    SampleComposeNavController(navController)
}

@Stable
class SampleComposeNavController(
    val navController: NavHostController,
) {

    val currentRoute: String?
        get() = navController.currentDestination?.route


    fun upPress() {
        navController.navigateUp()
    }


    fun navigateToMovieDetail(movieId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.MOVIE_DETAIL_ROUTE}/$movieId")
        }
    }



    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED

    private val NavGraph.startDestination: NavDestination?
        get() = findNode(startDestinationId)

}