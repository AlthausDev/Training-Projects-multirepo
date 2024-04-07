package com.example.p3_happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3_happybirthday.ui.theme.P3_HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P3_HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage("Happy Birthday Helen!", "From Sam\n& Family")
                }
            }
        }
    }
}

@Composable
fun GreetingText(msg: String, from: String, modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = msg,
            fontSize = 100.sp,
            lineHeight = 136.sp,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, blurRadius = 13f

                )
            )
        )

        Text(
            text = from,
            fontSize = 28.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .padding(12.dp)
                .align(alignment = Alignment.End),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, blurRadius = 7f

                )
            )
        )

    }

}


@Composable
fun GreetingImage(msg: String, from: String, modifier: Modifier = Modifier) {
    Box(modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_20231006_141218),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.4F,
            modifier = Modifier.fillMaxSize()
        )
        GreetingText(
            msg = msg, from = from, modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    P3_HappyBirthdayTheme {
        GreetingImage("Happy Birthday Helen!", "From Sam\n& Family")
    }
}