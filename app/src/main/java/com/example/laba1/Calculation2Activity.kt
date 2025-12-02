package com.example.laba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class Calculation2Activity : AppCompatActivity() {
    private lateinit var rgCurrency: RadioGroup
    private lateinit var rbEuro: RadioButton
    private lateinit var rbDollar: RadioButton
    private lateinit var btnCalculate: Button
    
    private var monthlyIncome: Double = 0.0
    private var percentage: Double = 0.0
    
    companion object {
        const val REQUEST_CODE = 2
        const val KEY_MONTHLY_INCOME = "monthly_income"
        const val KEY_PERCENTAGE = "percentage"
        const val KEY_CURRENCY = "currency"
        const val KEY_SELECTED_CURRENCY = "selected_currency"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation2)
        
        rgCurrency = findViewById(R.id.rgCurrency)
        rbEuro = findViewById(R.id.rbEuro)
        rbDollar = findViewById(R.id.rbDollar)
        btnCalculate = findViewById(R.id.btnCalculate)
        
        // Отримуємо дані з попереднього екрану
        monthlyIncome = intent.getDoubleExtra(KEY_MONTHLY_INCOME, 0.0)
        percentage = intent.getDoubleExtra(KEY_PERCENTAGE, 0.0)
        
        // Відновлення стану при повороті екрану
        savedInstanceState?.let {
            val selectedId = it.getInt(KEY_SELECTED_CURRENCY, R.id.rbEuro)
            rgCurrency.check(selectedId)
        }
        
        btnCalculate.setOnClickListener {
            val selectedCurrency = when (rgCurrency.checkedRadioButtonId) {
                R.id.rbEuro -> CurrencyCalculator.Currency.EURO
                R.id.rbDollar -> CurrencyCalculator.Currency.DOLLAR
                else -> CurrencyCalculator.Currency.EURO
            }
            
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra(KEY_MONTHLY_INCOME, monthlyIncome)
            intent.putExtra(KEY_PERCENTAGE, percentage)
            intent.putExtra(KEY_CURRENCY, selectedCurrency.ordinal)
            startActivityForResult(intent, ResultsActivity.REQUEST_CODE)
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_CURRENCY, rgCurrency.checkedRadioButtonId)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ResultsActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            setResult(RESULT_OK)
            finish()
        }
    }
}

