package nl.q42.instagram.composeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.ComposeUIViewController
import nl.q42.instagram.composeapp.home.dummyHomeViewState
import nl.q42.instagram.composeapp.home.HomeScreen
import nl.q42.instagram.composeapp.home.applyActionResult
import nl.q42.instagram.composeapp.home.reduceInteraction
import nl.q42.instagram.theme.AppTheme

fun MainViewController() = ComposeUIViewController {
    var currentViewState by remember { mutableStateOf(dummyHomeViewState) }

    AppTheme {
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
}
