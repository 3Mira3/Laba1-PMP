package com.example.laba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {
    private lateinit var tvSyValue: TextView
    private lateinit var tvScValue: TextView
    private lateinit var tvWValue: TextView
    private lateinit var tvShValue: TextView
    private lateinit var tvSlValue: TextView
    private lateinit var tvHValue: TextView
    private lateinit var tvRValue: TextView
    private lateinit var btnOk: Button
    
    private var result: CurrencyCalculator.CalculationResult? = null
    
    companion object {
        const val REQUEST_CODE = 3
        const val KEY_RESULT = "result"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        
        tvSyValue = findViewById(R.id.tvSyValue)
        tvScValue = findViewById(R.id.tvScValue)
        tvWValue = findViewById(R.id.tvWValue)
        tvShValue = findViewById(R.id.tvShValue)
        tvSlValue = findViewById(R.id.tvSlValue)
        tvHValue = findViewById(R.id.tvHValue)
        tvRValue = findViewById(R.id.tvRValue)
        btnOk = findViewById(R.id.btnOk)
        
        // Відновлення стану при повороті екрану
        if (savedInstanceState != null) {
            val sy = savedInstanceState.getDouble("sy", 0.0)
            val sc = savedInstanceState.getDouble("sc", 0.0)
            val w = savedInstanceState.getDouble("w", 0.0)
            val sh = savedInstanceState.getDouble("sh", 0.0)
            val sl = savedInstanceState.getDouble("sl", 0.0)
            val h = savedInstanceState.getDouble("h", 0.0)
            val r = savedInstanceState.getDouble("r", 0.0)
            val currencyOrdinal = savedInstanceState.getInt("currency", 0)
            val currency = CurrencyCalculator.Currency.values()[currencyOrdinal]
            
            result = CurrencyCalculator.CalculationResult(sy, sc, w, sh, sl, h, r, currency)
            displayResults()
        } else {
            // Отримуємо дані з попереднього екрану
            val monthlyIncome = intent.getDoubleExtra(Calculation2Activity.KEY_MONTHLY_INCOME, 0.0)
            val percentage = intent.getDoubleExtra(Calculation2Activity.KEY_PERCENTAGE, 0.0)
            val currencyOrdinal = intent.getIntExtra(Calculation2Activity.KEY_CURRENCY, 0)
            val currency = CurrencyCalculator.Currency.values()[currencyOrdinal]
            
            // Виконуємо розрахунки
            result = CurrencyCalculator.calculate(monthlyIncome, percentage, currency)
            displayResults()
        }
        
        btnOk.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        result?.let {
            outState.putDouble("sy", it.sy)
            outState.putDouble("sc", it.sc)
            outState.putDouble("w", it.w)
            outState.putDouble("sh", it.sh)
            outState.putDouble("sl", it.sl)
            outState.putDouble("h", it.h)
            outState.putDouble("r", it.r)
            outState.putInt("currency", it.currency.ordinal)
        }
    }
    
    private fun displayResults() {
        result?.let {
            val currencySymbol = when (it.currency) {
                CurrencyCalculator.Currency.EURO -> "EUR"
                CurrencyCalculator.Currency.DOLLAR -> "USD"
            }
            
            tvSyValue.text = String.format(getString(R.string.hryvnia_unit), it.sy)
            tvScValue.text = String.format(getString(R.string.hryvnia_unit), it.sc)
            tvWValue.text = String.format(getString(R.string.currency_unit), it.w, currencySymbol)
            tvShValue.text = String.format(getString(R.string.hryvnia_unit), it.sh)
            tvSlValue.text = String.format(getString(R.string.hryvnia_unit), it.sl)
            tvHValue.text = String.format(getString(R.string.hryvnia_unit), it.h)
            tvRValue.text = String.format(getString(R.string.hryvnia_unit), it.r)
        }
    }
}

