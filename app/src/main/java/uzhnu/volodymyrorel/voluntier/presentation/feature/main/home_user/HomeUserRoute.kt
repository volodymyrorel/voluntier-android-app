@file:OptIn(ExperimentalMaterial3Api::class)

package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.components.AnswerItem

const val homeUserRoute = "homeUserRoute"

@Composable
fun HomeUserRoute(
    viewModel: HomeUserViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeUserScreen(
        state = state
    )
}

@Composable
fun HomeUserScreen(
    state: HomeUserStateUi
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
                    Text("HomeUser")
                },
//                actions = {
//                    IconButton(
//                        onClick = onCreateRequestClicked
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = null
//                        )
//                    }
//                }
//                ,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.answers) { answer ->
//                Text(answer.demandTitle)
//                HorizontalDivider()
                AnswerItem(answer)
            }
        }
    }
}