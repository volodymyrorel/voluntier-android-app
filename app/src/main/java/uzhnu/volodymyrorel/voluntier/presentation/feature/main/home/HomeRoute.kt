@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

const val homeRoute = "homeRoute"

@Composable
fun HomeRoute(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    HomeScreen(
        onCreateRequestClicked = viewModel::onCreateNewRequestClicked
    )
}

@Composable
fun HomeScreen(
    onCreateRequestClicked: () -> Unit
) {
//    Column (
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text("HomeScreen")
//        Button(
//            onClick = onCreateRequestClicked
//        ) {
//            Text(text = "Create request")
//        }
//    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Home")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.LightGray)
                .fillMaxSize()
        ) {  }
    }
}