package hr.algebra.world_wonders.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import hr.algebra.world_wonders.view.BottomNavScreen
import hr.algebra.world_wonders.view.main.*
import hr.algebra.world_wonders.viewmodel.MapViewModel
import hr.algebra.world_wonders.viewmodel.QuizViewModel
import hr.algebra.world_wonders.viewmodel.WonderViewModel

@ExperimentalPagingApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.WorldWonders.route
    ) {
        composable(route = BottomNavScreen.WorldWonders.route) {
            val wonderViewModel = hiltViewModel<WonderViewModel>()
            val wondersState = WonderState(wonderViewModel)
            WondersScreen(
                wondersState = wondersState
            )
        }
        composable(route = BottomNavScreen.Map.route) {
            val mapViewModel = hiltViewModel<MapViewModel>()
            MapScreen(mapState = mapViewModel.mapState.value)
        }
        composable(route = BottomNavScreen.Quiz.route) {
            val quizViewModel = hiltViewModel<QuizViewModel>()
            QuizScreen(quizState = quizViewModel.quizState.value)
        }
    }
}