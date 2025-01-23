package tl209.example.bai4_eatfish.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import tl209.example.bai4_eatfish.model.Fish
import tl209.example.bai4_eatfish.model.FishTank
import tl209.example.bai4_eatfish.viewmodel.FishViewModel

class FishTankView(context: Context, private val fishViewModel: FishViewModel) : View(context) {
    private val paint: Paint = Paint()

    init {
        paint.color = Color.BLACK
        paint.textSize = 50f
    }

    // Thêm cá vào bể thông qua ViewModel
    fun addFish(fish: Fish, tank: FishTank) {
        fishViewModel.addFish(fish, tank)  // Gọi ViewModel để thêm cá vào danh sách
        invalidate()  // Cập nhật giao diện sau khi thêm cá
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Vẽ bể cá với màu nền trắng
        canvas.drawColor(Color.WHITE)

        // Vẽ các con cá (dưới dạng chấm)
        fishViewModel.fishList.forEach { fish ->
            paint.color = Color.parseColor(fish.color)  // Màu cá
            canvas.drawCircle(fish.position.x.toFloat(), fish.position.y.toFloat(), fish.size.toFloat(), paint)
        }

        // Lặp lại vẽ (sử dụng postInvalidateDelayed để làm cho cá di chuyển liên tục)
        postInvalidateDelayed(100)  // Cập nhật mỗi 100ms
    }
}
