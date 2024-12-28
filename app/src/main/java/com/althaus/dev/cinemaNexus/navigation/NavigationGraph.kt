package com.althaus.dev.cinemaNexus.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.althaus.dev.cinemaNexus.ui.detail.DetailView
import com.althaus.dev.cinemaNexus.ui.detail.DetailViewModel
import com.althaus.dev.cinemaNexus.ui.home.HomeView
import com.althaus.dev.cinemaNexus.ui.home.HomeViewModel
import com.althaus.dev.cinemaNexus.ui.login.LoginView
import com.althaus.dev.cinemaNexus.ui.login.LoginViewModel
import com.althaus.dev.cinemaNexus.ui.signup.SignUpView
import com.althaus.dev.cinemaNexus.ui.signup.SignUpViewModel
import com.althaus.dev.cinemaNexus.ui.splash.SplashView
import com.althaus.dev.cinemaNexus.ui.splash.SplashViewModel


sealed class Routes(val route: String) {
    data object SplashView : Routes("splash")
    data object LoginView : Routes("login")
    data object HomeView : Routes("home")
    data object DetailView : Routes("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }

    data object SignUpView : Routes("signup")
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashView.route,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        composable(Routes.SplashView.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashView(viewModel = splashViewModel,
                navigateToLogin = {
                    navController.navigate(Routes.LoginView.route)
                }, navigateToGoogleLogin = {
                    navController.navigate(Routes.HomeView.route)
                }, navigateToSignUp = {
                    navController.navigate(Routes.SignUpView.route)
                }
            )
        }

        composable(Routes.LoginView.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginView(viewModel = loginViewModel,
                navigateToHome = {
                    navController.navigate(Routes.HomeView.route)
                }, navigateToSignUp = {
                    navController.navigate(Routes.SignUpView.route)
                }, navigateToError = {}
            )
        }

        composable(Routes.SignUpView.route) {
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            SignUpView(viewModel = signUpViewModel,
                navigateToHome = {
                    navController.navigate(Routes.HomeView.route)
                }, navigateToLogin = {
                    navController.navigate(Routes.LoginView.route)
                }, navigateToError = {}
            )
        }

        composable(Routes.HomeView.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeView(viewModel = homeViewModel)
        }

        composable(Routes.DetailView.route) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailView(viewModel = detailViewModel)
        }
    }
}
