package com.example.laba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Calculation1Activity : AppCompatActivity() {
    private lateinit var etMonthlyIncome: EditText
    private lateinit var etPercentage: EditText
    private lateinit var btnNext: Button
    
    companion object {
        const val KEY_MONTHLY_INCOME = "monthly_income"
        const val KEY_PERCENTAGE = "percentage"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation1)
        
        etMonthlyIncome = findViewById(R.id.etMonthlyIncome)
        etPercentage = findViewById(R.id.etPercentage)
        btnNext = findViewById(R.id.btnNext)
        
        // Відновлення стану при повороті екрану
        savedInstanceState?.let {
            etMonthlyIncome.setText(it.getString(KEY_MONTHLY_INCOME, ""))
            etPercentage.setText(it.getString(KEY_PERCENTAGE, ""))
        }
        
        btnNext.setOnClickListener {
            val incomeText = etMonthlyIncome.text.toString().trim()
            val percentageText = etPercentage.text.toString().trim()
            
            if (incomeText.isEmpty()) {
                Toast.makeText(this, R.string.error_invalid_income, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (percentageText.isEmpty()) {
                Toast.makeText(this, R.string.error_invalid_percentage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val monthlyIncome = incomeText.toDoubleOrNull()
            val percentage = percentageText.toDoubleOrNull()
            
            if (monthlyIncome == null || monthlyIncome <= 0) {
                Toast.makeText(this, R.string.error_invalid_income, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (percentage == null || percentage <= 0 || percentage >= 1) {
                Toast.makeText(this, R.string.error_invalid_percentage, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val intent = Intent(this, Calculation2Activity::class.java)
            intent.putExtra(KEY_MONTHLY_INCOME, monthlyIncome)
            intent.putExtra(KEY_PERCENTAGE, percentage)
            startActivityForResult(intent, Calculation2Activity.REQUEST_CODE)
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_MONTHLY_INCOME, etMonthlyIncome.text.toString())
        outState.putString(KEY_PERCENTAGE, etPercentage.text.toString())
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Calculation2Activity.REQUEST_CODE && resultCode == RESULT_OK) {
            setResult(RESULT_OK)
            finish()
        }
    }
}

