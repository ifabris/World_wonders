package hr.algebra.world_wonders.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import hr.algebra.world_wonders.R

sealed class BottomNavScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    object WorldWonders: BottomNavScreen(
        route = "wonders",
        title = R.string.wonders,
        icon = Icons.Default.Star
    )
    object Map: BottomNavScreen(
        route = "map",
        title = R.string.map,
        icon = Icons.Default.Map
    )
    object Quiz: BottomNavScreen(
        route = "quiz",
        title = R.string.quiz,
        icon = Icons.Default.Quiz
    )
}