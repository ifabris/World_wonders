package hr.algebra.world_wonders.view

sealed class AuthScreen(val route: String) {
    object Login: AuthScreen(route = "login")
    object Register: AuthScreen(route = "register")
}
