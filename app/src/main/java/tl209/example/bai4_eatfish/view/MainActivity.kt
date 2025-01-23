package tl209.example.bai4_eatfish.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import tl209.example.bai4_eatfish.R
import tl209.example.bai4_eatfish.databinding.ActivityMainBinding
import tl209.example.bai4_eatfish.model.Fish
import tl209.example.bai4_eatfish.model.FishTank
import tl209.example.bai4_eatfish.viewmodel.FishViewModel

class MainActivity : AppCompatActivity() {

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
