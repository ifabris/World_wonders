package hr.algebra.world_wonders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import hr.algebra.world_wonders.ui.WonderAppTheme
import hr.algebra.world_wonders.view.nav.RootNavGraph

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            WonderAppTheme {
                Surface {
                    RootNavGraph(navController = rememberNavController())
                }
            }
        }
        }

    }


