package com.althaus.dev.cinemaNexus

//import com.althaus.dev.cinemaNexus.ui.detail.DetailViewModel
//import com.althaus.dev.cinemaNexus.ui.login.LoginViewModel
//import com.althaus.dev.cinemaNexus.ui.signup.SignUpViewModel
//import com.althaus.dev.cinemaNexus.ui.home.HomeViewModel
//import com.althaus.dev.cinemaNexus.ui.splash.SplashViewModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.althaus.dev.cinemaNexus.ui.theme.Project04CarteleraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Project04CarteleraTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            CinemaNexusNavGraph()
                        }
                    }
                )
            }
        }
    }
}