package com.example.businesscard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity(), ColorPickerFragment.ColorPickerDialogListener {

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
            openDialog()
        }
    }

    private fun openDialog() {
        val colorDialog = ColorPickerFragment()
        colorDialog.show(supportFragmentManager, colorDialog.tag)
    }

    override fun applyColor(colorHex: String) {
        binding.tilCor.editText?.setText(colorHex)
    }
}