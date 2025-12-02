package com.example.laba1

object CurrencyCalculator {
    // Курси валют на початок року (CSTART)
    const val EURO_START_RATE = 40.0
    const val DOLLAR_START_RATE = 37.0
    
    // Курси валют на кінець року (CEND)
    const val EURO_END_RATE = 42.0
    const val DOLLAR_END_RATE = 38.5
    
    enum class Currency {
        EURO,
        DOLLAR
    }
    
    /**
     * Обчислює інтерпольований курс за i-тий місяць
     * Ci = CSTART + i * (CEND - CSTART) / 12
     */
    private fun getInterpolatedRate(month: Int, currency: Currency): Double {
        val startRate = when (currency) {
            Currency.EURO -> EURO_START_RATE
            Currency.DOLLAR -> DOLLAR_START_RATE
        }
        val endRate = when (currency) {
            Currency.EURO -> EURO_END_RATE
            Currency.DOLLAR -> DOLLAR_END_RATE
        }
        return startRate + month * (endRate - startRate) / 12.0
    }
    
    /**
     * Обчислює кількість валюти, придбаної за рік
     * W = Σ(from i=1 to 12) (p * M / Ci)
     */
    private fun calculateCurrencyPurchased(monthlyIncome: Double, percentage: Double, currency: Currency): Double {
        var total = 0.0
        for (i in 1..12) {
            val rate = getInterpolatedRate(i, currency)
            total += (percentage * monthlyIncome) / rate
        }
        return total
    }
    
    /**
     * Виконує всі розрахунки
     */
    fun calculate(
        monthlyIncome: Double,
        percentage: Double,
        currency: Currency
    ): CalculationResult {
        // 1. Гіпотетичний повний річний дохід у гривні (Sy)
        val sy = 12.0 * monthlyIncome
        
        // 2. Кількість гривень, витрачених на обмін валюти (Sc)
        val sc = percentage * sy
        
        // 3. Кількість валюти, придбаної за рік (W)
        val w = calculateCurrencyPurchased(monthlyIncome, percentage, currency)
        
        // 4. Гривнева вартість придбаної валюти на кінець року (SH)
        val endRate = when (currency) {
            Currency.EURO -> EURO_END_RATE
            Currency.DOLLAR -> DOLLAR_END_RATE
        }
        val sh = w * endRate
        
        // 5. Гривневий залишок (SL)
        val sl = sy - sc
        
        // 6. Сума гривневого залишку та гривневої вартості валюти (H)
        val h = sh + sl
        
        // 7. Сума заощадження (R)
        val r = h - sy
        
        return CalculationResult(
            sy = sy,
            sc = sc,
            w = w,
            sh = sh,
            sl = sl,
            h = h,
            r = r,
            currency = currency
        )
    }
    
    data class CalculationResult(
        val sy: Double,  // Гіпотетичний повний річний дохід
        val sc: Double,  // Кількість гривень, витрачених на обмін
        val w: Double,   // Кількість валюти, придбаної за рік
        val sh: Double,  // Гривнева вартість валюти на кінець року
        val sl: Double,  // Гривневий залишок
        val h: Double,   // Сума гривневого залишку та вартості валюти
        val r: Double,   // Сума заощадження
        val currency: Currency
    )
}

