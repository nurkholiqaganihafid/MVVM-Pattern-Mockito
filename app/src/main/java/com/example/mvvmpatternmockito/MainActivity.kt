/*
author: Nurkholiq Agani Hafid
 */

package com.example.mvvmpatternmockito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLenght: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button
    private lateinit var btnSave: Button

    // Metode Save Orientation
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
    //End

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "MVVM Pattern - Mockito"

        mainViewModel = MainViewModel(ModelKubus())

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLenght = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)

        // Metode Save Orientation
        if (savedInstanceState !=null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
        // end save

    }

    override fun onClick(p0: View?) {
        val length = edtLenght.text.toString().trim()
        val width = edtWidth.text.toString().trim()
        val height = edtHeight.text.toString().trim()

        when {
            length.isEmpty() -> edtLenght.error = "Field ini tidak boleh kosong"
            width.isEmpty() -> edtWidth.error = "Field ini tidak boleh kosong"
            height.isEmpty() -> edtHeight.error = "Field ini tidak boleh kosong"

            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()


                when {
                    p0?.id == R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }
                    p0?.id == R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    p0?.id == R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    p0?.id == R.id.btn_calculate_volume -> {
                        tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {

        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
        tvResult.visibility = View.GONE

    }
    private fun gone() {
        btnCalculateVolume.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
        tvResult.visibility = View.VISIBLE

    }
}