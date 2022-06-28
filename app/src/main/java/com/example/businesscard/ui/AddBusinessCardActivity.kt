package com.example.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity(), ColorPickerFragment.ColorPickerDialogListener {

    lateinit var binding: ActivityAddBusinessCardBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertListeners()
    }

    //TODO: set an onLongClickListener to update an card
    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilName.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                background = if ((binding.tilCor.editText?.text.toString()).isEmpty())
                    "#FFFFFFFF"
                else
                    binding.tilCor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_succses, Toast.LENGTH_SHORT).show()
            finish()
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