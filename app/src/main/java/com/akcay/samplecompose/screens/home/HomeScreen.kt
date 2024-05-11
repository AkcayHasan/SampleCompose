package com.akcay.samplecompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.akcay.samplecompose.components.JWTopAppBar
import com.akcay.samplecompose.components.MovieListItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMovieClick: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val list by viewModel.popularMovieList.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            JWTopAppBar(
                upPress = { },
                toolbarTitle = "List",
                barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                isNavigationIconVisible = false
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding())
        ) {
            if (loadingState) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    list?.movieResults?.let { itemsList ->
                        items(count = itemsList.size) { index ->
                            MovieListItem(
                                imageUrl = itemsList[index].posterPath,
                                itemId = itemsList[index].id ?: 0L,
                                movieName = itemsList[index].originalTitle ?: "",
                                onCardClicked = {
                                    onMovieClick(itemsList[index].id ?: 0L)
                                },
                                onAddIconClicked = {}
                            )
                        }
                    }
                }
            }
        }
    }


}