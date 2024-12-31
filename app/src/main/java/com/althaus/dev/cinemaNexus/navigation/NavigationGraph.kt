package com.althaus.dev.cinemaNexus.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            SplashView(
                viewModel = splashViewModel,
                onNavigateToLogin = {
                    navController.navigate(Routes.LoginView.route) {
                        popUpTo(Routes.SplashView.route) { inclusive = false }
                    }
                },
                onNavigateToGoogleLogin = {
                    navController.navigate(Routes.HomeView.route) {
                        popUpTo(Routes.SplashView.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Routes.SignUpView.route) {
                        popUpTo(Routes.SplashView.route) { inclusive = false }
                    }
                }
            )
        }

        composable(Routes.LoginView.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginView(
                viewModel = loginViewModel,
                onNavigateToHome = {
                    navController.navigate(Routes.HomeView.route) {
                        popUpTo(Routes.LoginView.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Routes.SignUpView.route) {
                        popUpTo(Routes.LoginView.route) { inclusive = false }
                    }
                },
                onError = {}
            )
        }

        composable(Routes.SignUpView.route) {
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            SignUpView(
                viewModel = signUpViewModel,
                onNavigateToHome = {
                    navController.navigate(Routes.HomeView.route) {
                        popUpTo(Routes.SignUpView.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Routes.LoginView.route) {
                        popUpTo(Routes.SignUpView.route) { inclusive = false }
                    }
                },
                onError = {}
            )
        }

        composable(Routes.HomeView.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeView(viewModel = homeViewModel)
        }

//        composable(
//            route = Routes.DetailView.route,
//            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
//            val detailViewModel: DetailViewModel = hiltViewModel()
//            DetailView(
//                viewModel = detailViewModel,
//                movieId = movieId
//            )
//        }
    }
}