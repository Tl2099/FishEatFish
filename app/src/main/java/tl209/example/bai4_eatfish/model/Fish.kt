package tl209.example.bai4_eatfish.model

import kotlinx.coroutines.*

data class Position(var x: Int, var y: Int)

data class Fish(
    val name: String,
    val color: String,
    var size: Int,
    var position: Position
) {
    private var moveJob: Job? = null

    // Khởi tạo Coroutine riêng cho việc di chuyển cá
    fun startMoving(scope: CoroutineScope, fishTank: FishTank) {
        moveJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                fishTank.moveFish(this@Fish) // Gọi phương thức moveFish từ FishTank
                delay(10)  // Delay giữa các lần di chuyển
            }
        }
    }

    // Dừng việc di chuyển khi không cần nữa
    fun stopMoving() {
        moveJob?.cancel()
    }

    companion object {
        // Tạo phương thức để tạo cá với vị trí ngẫu nhiên
        fun createRandomFish(fishTank: FishTank): Fish {
            // Vị trí ngẫu nhiên trong phạm vi của bể cá
            val randomX = (0 until fishTank.width).random()
            val randomY = (0 until fishTank.height).random()

            // Tạo cá với vị trí ngẫu nhiên
            return Fish("Fish ${System.currentTimeMillis()}", "Red", 10, Position(randomX, randomY))
        }
    }
}
