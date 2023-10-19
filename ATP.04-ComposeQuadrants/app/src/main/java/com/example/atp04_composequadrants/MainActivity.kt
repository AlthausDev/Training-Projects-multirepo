package com.example.atp04_composequadrants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.atp04_composequadrants.ui.theme.ATP04ComposeQuadrantsTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ATP04ComposeQuadrantsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}


@Preview
@Composable
fun Content() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(Modifier.weight(1f)) {

            Cuadrante(
                "Text composable",
                "Displays text and follows the recommended Material Design guidelines.",
                Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)

            )
            Cuadrante(
                "Image composable",
                "Creates a composable that lays out and draws a given Painter class object.",
                Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }


        Row(Modifier.weight(1f)) {
            Cuadrante(
                "Row composable",
                "A layout composable that places its children in a horizontal sequence.",
                Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)

            )

            Cuadrante(
                "TColumn composable",
                "A layout composable that places its children in a vertical sequence.",
                Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun Cuadrante(titulo: String, cuerpo: String, backColor: Color, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = titulo,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = cuerpo,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

