package tl209.example.bai4_eatfish.model

import kotlinx.coroutines.*
import kotlin.random.Random

data class Position(var x: Int, var y: Int)

open class Fish(
    val name: String,
    val color: String,
    var size: Int,
    var position: Position,
    //val check: (fish: Fish) -> (Unit)
) {
    private var moveJob: Job? = null

//    fun nextStep(): Pair<Int, Int>{
//        val newX = position.x + Random.nextInt()
//        val newY = position.y + Random.nextInt()
//        return Pair<>
//    }
    open fun draw(){
        //Nen tao o day, con thang khac se thuc hien cai logic o day de ke thua
        // va tiep tuc phat trien no
    }

    // Khởi tạo Coroutine riêng cho việc di chuyển cá
    fun startMoving(scope: CoroutineScope, fishTank: FishTank) {
        moveJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                fishTank.moveFish(this@Fish) // Gọi phương thức moveFish từ FishTank
                //check.invoke(this@Fish)
                delay(1)  // Delay giữa các lần di chuyển
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
