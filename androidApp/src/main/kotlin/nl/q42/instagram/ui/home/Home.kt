package nl.q42.instagram.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import nl.q42.instagram.composeapp.home.applyActionResult
import nl.q42.instagram.composeapp.home.HomeScreen
import nl.q42.instagram.composeapp.home.reduceInteraction
import nl.q42.instagram.composeapp.home.HomeViewState as SharedHomeViewState
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.theme.AppTheme

typealias HomeViewState = SharedHomeViewState

@Composable
fun Home(viewState: HomeViewState) {
    var currentViewState by remember(viewState) { mutableStateOf(viewState) }

    HomeScreen(
        viewState = currentViewState,
        onInteraction = { event ->
            currentViewState = currentViewState.reduceInteraction(event)
        },
        onActionResult = { result ->
            currentViewState = currentViewState.applyActionResult(result)
        },
    )
}

@Composable
@Preview(showBackground = true)
private fun HomePreview() {
    AppTheme {
        Home(viewState = dummyViewState)
    }
}
