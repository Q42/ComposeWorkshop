package nl.q42.instagram.composeapp.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.q42.instagram.theme.AppTheme

@Preview
@Composable
private fun HomeContentPreviewEmpty() {
    AppTheme {
        HomeContent(viewState = previewHomeEmpty)
    }
}

@Preview
@Composable
private fun HomeContentPreviewWithContent() {
    AppTheme {
        HomeContent(viewState = previewHomeWithContent)
    }
}

@Preview
@Composable
private fun HomeContentPreviewSingleItem() {
    AppTheme {
        HomeContent(viewState = previewHomeSingleItem)
    }
}

@Preview
@Composable
private fun HomeContentPreviewMixedPermissions() {
    AppTheme {
        HomeContent(viewState = previewHomeWithMixedPermissions)
    }
}
