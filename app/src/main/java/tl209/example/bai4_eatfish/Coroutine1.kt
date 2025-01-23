package tl209.example.bai4_eatfish

import kotlinx.coroutines.*

fun main() = runBlocking {
    // Đoạn mã trong runBlocking sẽ chờ tất cả các coroutine con hoàn thành
    launch {
        delay(1000L) // Tạm dừng coroutine trong 1 giây
        println("Hello from Coroutine!")
    }
    println("Hello from Main Thread!")
}

