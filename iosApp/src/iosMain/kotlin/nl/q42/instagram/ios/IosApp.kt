package nl.q42.instagram.ios

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nl.q42.instagram.composeapp.home.HomeScreen
import nl.q42.instagram.composeapp.home.applyActionResult
import nl.q42.instagram.composeapp.home.reduceInteraction
import nl.q42.instagram.composeapp.home.HomeViewState as SharedHomeViewState

typealias HomeViewState = SharedHomeViewState

@Composable
fun IosApp(viewState: HomeViewState) {
    var currentViewState by remember { mutableStateOf(viewState) }

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
