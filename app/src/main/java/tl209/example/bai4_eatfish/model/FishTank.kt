package tl209.example.bai4_eatfish.model

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import kotlinx.coroutines.CoroutineScope

class FishTank(context: Context) {
    val width: Int
    val height: Int
    private val fishList = mutableListOf<Fish>()

    init {
        // Lấy kích thước màn hình để làm kích thước của bể cá
        val display: Display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        width = size.x
        height = size.y
    }

    // Thêm cá vào bể
    fun addFish(fish: Fish, viewModelScope: CoroutineScope) {
        fishList.add(fish)
        fish.startMoving(viewModelScope,this)  // Mỗi con cá bắt đầu di chuyển trong một Coroutine
    }

    // Di chuyển cá trong bể
    fun moveFish(fish: Fish) {
        val directions = listOf("UP", "DOWN", "LEFT", "RIGHT")
        val direction = directions.random()

        when (direction) {
            "UP" -> fish.position.y = (fish.position.y - 1).coerceAtLeast(0)
            "DOWN" -> fish.position.y = (fish.position.y + 1).coerceAtMost(height - 1)
            "LEFT" -> fish.position.x = (fish.position.x - 1).coerceAtLeast(0)
            "RIGHT" -> fish.position.x = (fish.position.x + 1).coerceAtMost(width - 1)
        }

        checkForCollision(fish)
    }

    // Kiểm tra va chạm và xử lý khi cá gặp nhau hoặc gặp chướng ngại vật
    private fun checkForCollision(fish: Fish) {
        for (otherFish in fishList) {
            if (fish != otherFish && fish.position == otherFish.position) {
                if (fish.size > otherFish.size) {
                    fish.size += otherFish.size  // Cá lớn ăn cá nhỏ
                    fishList.remove(otherFish)
                    otherFish.stopMoving()
                } else if (fish.size == otherFish.size) {
                    moveFish(fish)
                    moveFish(otherFish)
                }
            }
        }
    }
}
