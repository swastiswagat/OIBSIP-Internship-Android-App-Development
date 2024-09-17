package com.example.unitconvertor

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var inputEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    private val units = arrayOf("Meter", "Kilometer", "Centimeter", "Millimeter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        inputEditText = findViewById(R.id.inputEditText)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter
        convertButton.setOnClickListener {
            performConversion()
        }
    }

    private fun performConversion() {
        val inputValue = inputEditText.text.toString()

        if (inputValue.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = fromSpinner.selectedItem.toString()
        val toUnit = toSpinner.selectedItem.toString()
        val value = inputValue.toDouble()

        val result = when (fromUnit) {
            "Meter" -> convertFromMeter(value, toUnit)
            "Kilometer" -> convertFromKilometer(value, toUnit)
            "Centimeter" -> convertFromCentimeter(value, toUnit)
            "Millimeter" -> convertFromMillimeter(value, toUnit)
            else -> value
        }
        resultTextView.text = "$value $fromUnit = $result $toUnit"
    }
    private fun convertFromMeter(value: Double, toUnit: String): Double {
        return when (toUnit) {
            "Kilometer" -> value / 1000
            "Centimeter" -> value * 100
            "Millimeter" -> value * 1000
            else -> value
        }
    }

    private fun convertFromKilometer(value: Double, toUnit: String): Double {
        return when (toUnit) {
            "Meter" -> value * 1000
            "Centimeter" -> value * 100000
            "Millimeter" -> value * 1000000
            else -> value
        }
    }

    private fun convertFromCentimeter(value: Double, toUnit: String): Double {
        return when (toUnit) {
            "Meter" -> value / 100
            "Kilometer" -> value / 100000
            "Millimeter" -> value * 10
            else -> value
        }
    }

    private fun convertFromMillimeter(value: Double, toUnit: String): Double {
        return when (toUnit) {
            "Meter" -> value / 1000
            "Kilometer" -> value / 1000000
            "Centimeter" -> value / 10
            else -> value
        }
    }
}
