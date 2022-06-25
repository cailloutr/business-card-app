package com.example.businesscard.ui

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.businesscard.R
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText

class ColorPickerFragment : DialogFragment() {

    private lateinit var sliderRed: Slider
    private lateinit var sliderGreen: Slider
    private lateinit var sliderBlue: Slider
    private lateinit var edtRed: TextInputEditText
    private lateinit var edtGreen: TextInputEditText
    private lateinit var edtBlue: TextInputEditText
    private lateinit var tvColorView: TextView

    private lateinit var listener: ColorPickerDialogListener
    private var colorHex: String = "0"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.color_picker_fragment, null)


            builder.setView(view)
                // Add action buttons
                .setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { _, _ ->
                        applyColor()
                        listener.applyColor(colorHex)
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })

            sliderRed = view.findViewById(R.id.sld_red)
            sliderGreen = view.findViewById(R.id.sld_green)
            sliderBlue = view.findViewById(R.id.sld_blue)

            edtRed = view.findViewById(R.id.input_red)
            edtGreen = view.findViewById(R.id.input_green)
            edtBlue = view.findViewById(R.id.input_blue)
            tvColorView = view.findViewById(R.id.tv_color_view)

            insertListeners(sliderRed, edtRed)
            insertListeners(sliderGreen, edtGreen)
            insertListeners(sliderBlue, edtBlue)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun insertListeners(sld: Slider, editText: TextInputEditText) {
        sld.addOnChangeListener { _, value, _ ->
            editText.setText(value.toInt().toString())
            applyColor()
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var value = if (s.isNullOrEmpty()) 0 else Integer.parseInt(s.toString())
                when {
                    value in 0..255 -> {
                        sld.value = value.toFloat()
                    }
                    value <= 0 -> {
                        value = 0
                        sld.value = value.toFloat()
                        editText.setText(value.toString())
                    }
                    value > 255 -> {
                        value = 255
                        sld.value = value.toFloat()
                        editText.setText(value.toString())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }


    private fun applyColor() {
        val red = edtRed.text.toString().toFloatOrNull()?.toInt()
        val green = edtGreen.text.toString().toFloatOrNull()?.toInt()
        val blue = edtBlue.text.toString().toFloatOrNull()?.toInt()

        val color = "#${"%02x".format(red)}${"%02x".format(green)}${"%02x".format(blue)}"
        Log.v("Color", "Value of color: $color")

        tvColorView.setBackgroundColor(Color.parseColor(color))
        this.colorHex = color
    }

    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

        try {
            listener = context as ColorPickerDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} must implement ColorPickerDialogListener")
        }
    }

    interface ColorPickerDialogListener{
        fun applyColor(colorHex: String)
    }
}
