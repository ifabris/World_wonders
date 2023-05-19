package hr.algebra.world_wonders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.algebra.world_wonders.model.Wonder
import hr.algebra.world_wonders.repository.WondersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class WonderViewModel @Inject constructor(
    private val repository: WondersRepository,
) : ViewModel() {
    val wonders = repository.getWonders()

    fun update(wonder: Wonder) {
        viewModelScope.launch {
            repository.update(wonder)
        }
    }

    fun delete(wonder: Wonder) {
        viewModelScope.launch {
            repository.delete(wonder)
        }
    }
}
