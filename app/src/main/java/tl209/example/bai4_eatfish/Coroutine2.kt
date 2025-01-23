package tl209.example.bai4_eatfish

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun fetchData(): String {
    delay(1000L) // Giả lập việc lấy dữ liệu mất 1 giây
    return "Data fetched"
}

fun main() = runBlocking {
    println("Start fetching data")
    val data = fetchData() // Gọi hàm suspend
    println(data)
}