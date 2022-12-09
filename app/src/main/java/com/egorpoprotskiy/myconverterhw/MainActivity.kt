package com.egorpoprotskiy.myconverterhw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.egorpoprotskiy.myconverterhw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            calculate()
        }
        binding.enterMetr.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun calculate() {
        val metersText = binding.enterMetrEditText.text.toString()
        val meters = metersText.toDoubleOrNull()
        if (meters == null || meters == 0.0) {
            binding.result.setText(null)
            return
        }
        val variable = when (binding.change.checkedRadioButtonId) {
            R.id.milya -> 0.000621
            R.id.yard -> 1.093613
            else -> 39.37008
        }
        val result = meters!! * variable
        binding.result.setText("$result")
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}

