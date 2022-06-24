package com.example.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.ColorPickerActivity
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBusinessCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            //TODO: implement save button
        }

        binding.btnCor.setOnClickListener {
//            val colorDialog = ColorPickerFragment()
//            colorDialog.show(supportFragmentManager, "Color")
////            colorDialog.show(supportFragmentManager, colorDialog.tag)
            val intent = Intent(this, ColorPickerActivity::class.java)
            startActivity(intent)
        }
    }
}