package hr.algebra.world_wonders.view.main

import androidx.paging.ExperimentalPagingApi
import hr.algebra.world_wonders.viewmodel.WonderViewModel

@ExperimentalPagingApi
class WonderState(wonderViewModel: WonderViewModel) {
    val wonders = wonderViewModel.wonders
}