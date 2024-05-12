package com.akcay.samplecompose.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.akcay.samplecompose.components.JWTopAppBar
import com.akcay.samplecompose.util.SampleComposeConfig

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Long,
    upPress: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getMovieDetailById(movieId)
    }

    val movieDetail by viewModel.movieById.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            JWTopAppBar(
                upPress = upPress,
                barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                isNavigationIconVisible = true,
                isActionIconVisible = true
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = "${SampleComposeConfig.BASE_IMAGE_URL}${movieDetail?.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                movieDetail?.genres?.forEach {
                    Text(text = it.name)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = movieDetail?.originalTitle ?: "", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(modifier = Modifier.padding(horizontal = 5.dp), text = movieDetail?.overview ?: "")
        }
    }
}