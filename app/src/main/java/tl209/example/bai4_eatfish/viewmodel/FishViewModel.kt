package tl209.example.bai4_eatfish.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import tl209.example.bai4_eatfish.model.Fish
import tl209.example.bai4_eatfish.model.FishTank

class FishViewModel : ViewModel() {
    private val _fishList = mutableListOf<Fish>()
    val fishList: List<Fish> get() = _fishList

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // Thêm cá vào bể
    fun addFish(fish: Fish, tank: FishTank) {
        _fishList.add(fish)
        tank.addFish(fish, viewModelScope)
        fish.startMoving(viewModelScope,tank)  // Bắt đầu di chuyển cá trong Coroutine
    }

    // Dừng tất cả coroutine khi ViewModel bị hủy
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()  // Hủy tất cả các coroutine khi ViewModel bị hủy
    }
}
