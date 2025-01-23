package tl209.example.bai4_eatfish.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import tl209.example.bai4_eatfish.R
import tl209.example.bai4_eatfish.databinding.ActivityMainBinding
import tl209.example.bai4_eatfish.model.Fish
import tl209.example.bai4_eatfish.model.FishTank
import tl209.example.bai4_eatfish.viewmodel.FishViewModel
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

//    @SuppressLint("MissingSuperCall")
//    override fun onResume() {
////        lifecycleScope.launch {
////            kotlin1()
////        }
//        lifecycleScope.launch {
//            Log.i("MainThread", "Current Thread: ${Thread.currentThread().name}")
//            kotlinCore()
//        }
//        super.onResume()
//        //Log.i("Thread", "Nhay4")
//    }
//
//    suspend fun kotlin1(){
//        withContext(Dispatchers.IO) {
//            Log.i("Thread", "Nhay1")
//            withContext(Dispatchers.Main) {
//                Log.i("Thread", "Nhay2")
//            }
//        }
//    }
//
//    // Giả lập hàm tải dữ liệu từ API (có độ trễ khác nhau)
//    suspend fun fetchDataFromApi(apiName: String): String {
//        delay(Random.nextLong(500, 1500)) // Giả lập độ trễ mạng
//        return "$apiName data"
//    }
//
//    // Giả lập tính toán phức tạp
//    suspend fun performComplexCalculation(id: Int): Int {
//        delay(800) // Giả lập tính toán
//        return id * id
//    }
//
//    // Giả lập việc lọc dữ liệu
//    suspend fun filterData(data: String): String {
//        delay(500) // Giả lập thời gian lọc
//        return data.split(" ")[0] // chỉ lấy phần đầu của chuỗi
//    }
//
//    // Giả lập việc cập nhật UI (thực hiện trên main thread)
//    suspend fun updateUi(message: String) {
//        println("UI Updated: $message")
//    }
//
//    // Hàm thực thi chính, sử dụng nhiều launch
//    suspend fun kotlinCore() = runBlocking {
//        // Đo thời gian thực thi
//        val time = measureTimeMillis {
//
//            val job1 = lifecycleScope.launch(Dispatchers.IO) {
//                try {
//                    Log.i("MainThread1", "Current Thread: ${Thread.currentThread().name}")
//
//                    val data1 = fetchDataFromApi("API 1")
//                    println("Fetched from API 1: $data1")
//
//                    // Tiến hành xử lý dữ liệu
//                    val filteredData = filterData(data1)
//                    println("Filtered Data from API 1: $filteredData")
//
//                    // Thực hiện tính toán phức tạp
//                    val result = performComplexCalculation(2)
//                    println("Calculation result for API 1: $result")
//                    Log.i("MainThread", "Is Main Thread: ${Looper.myLooper() == Looper.getMainLooper()}")
//                    Log.i("MainThread", "Current Thread: ${Thread.currentThread().name}")
//                    // Cập nhật UI sau khi hoàn thành
//                    withContext(Dispatchers.Main) {
//                        Log.i("MainThread", "Current Thread: ${Thread.currentThread().name}")
//                        updateUi("API 1 Completed: $filteredData with result $result")
//                    }
//
//                } catch (e: Exception) {
//                    println("Error in job1: ${e.message}")
//                }
//            }
//
//
//            val job2 = lifecycleScope.launch(Dispatchers.IO) {
//                try {
//                    val data2 = fetchDataFromApi("API 2")
//                    println("Fetched from API 2: $data2")
//
//                    // Tiến hành xử lý dữ liệu
//                    val filteredData = filterData(data2)
//                    println("Filtered Data from API 2: $filteredData")
//
//                    // Thực hiện tính toán phức tạp
//                    val result = performComplexCalculation(3)
//                    println("Calculation result for API 2: $result")
//
//                    // Cập nhật UI sau khi hoàn thành
//                    withContext(Dispatchers.Main) {
//                        updateUi("API 2 Completed: $filteredData with result $result")
//                    }
//                } catch (e: Exception) {
//                    println("Error in job2: ${e.message}")
//                }
//            }
//
//            // Chờ tất cả các tác vụ hoàn thành
//            job1.join()
//            println("Work 1 is: ${job1.isActive}")
//            job2.join()
//            println("All tasks completed.")
//        }
//
//        println("Total execution time: $time ms")
//    }

    private lateinit var binding: ActivityMainBinding
    private val fishViewModel: FishViewModel by viewModels()  // Khởi tạo ViewModel
    private lateinit var fishTankView: FishTankView
    private lateinit var fishTank: FishTank

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fishTank = FishTank(this)
        // Khởi tạo FishTankView và thêm vào layout
        fishTankView = FishTankView(this, fishViewModel)
        binding.container.addView(fishTankView)

        // Nút thêm cá
        val addButton: Button = findViewById(R.id.addFishButton)
        addButton.setOnClickListener {
            val newFish = Fish.createRandomFish(fishTank)
            fishTankView.addFish(newFish, fishTank)
        }
    }
}
