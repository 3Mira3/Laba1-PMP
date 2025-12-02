package com.example.laba1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val btnStartCalculation = findViewById<Button>(R.id.btnStartCalculation)
        val btnExit = findViewById<Button>(R.id.btnExit)
        
        btnStartCalculation.setOnClickListener {
            val intent = Intent(this, Calculation1Activity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CALCULATION)
        }
        
        btnExit.setOnClickListener {
            finish()
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CALCULATION && resultCode == RESULT_OK) {
            // Результати вже оброблені, просто повертаємось на початковий екран
        }
    }
    
    companion object {
        const val REQUEST_CODE_CALCULATION = 1
    }
}