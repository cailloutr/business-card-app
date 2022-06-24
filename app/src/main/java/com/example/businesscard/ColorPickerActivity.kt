package com.example.businesscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding
import com.example.businesscard.databinding.ActivityColorPickerBinding

class ColorPickerActivity : AppCompatActivity() {

    lateinit var binding: ActivityColorPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColorPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}