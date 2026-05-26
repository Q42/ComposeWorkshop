package nl.q42.instagram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import nl.q42.instagram.ui.data.dummyViewState
import nl.q42.instagram.ui.homeContent.HomeContent
import nl.q42.instagram.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScaffold()
        }
    }
}
@Composable
fun HomeScaffold(){
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    InstAnimalAppBar()
                },
                content = { contentPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) {
                        HomeContent(dummyViewState)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstAnimalAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "InstaAnimal",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
            )
        },
    )
}

