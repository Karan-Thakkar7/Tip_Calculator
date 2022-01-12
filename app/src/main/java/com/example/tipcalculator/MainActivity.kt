package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
private const val  Initial_Tip_Percent=15
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar.progress= Initial_Tip_Percent
        tvTipPercentage.text= "$Initial_Tip_Percent%"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG,"the percent is ${seekBar.progress}")
                tvTipPercentage.text="${seekBar.progress}%"
                changeInformation()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG,"amount change is ${etBillAmount.text}")
                changeInformation()
            }

        })

    }

    private fun changeInformation() {
        if (etBillAmount.text.isEmpty()){
            TotalAmount.text=""
            Tiplable.text=""
            return
        }
        val billamount = etBillAmount.text.toString().toDouble()
        val tipper = seekBar.progress
        val tip = billamount * tipper / 100
        val totalamount = billamount + tip
        Tiplable.text="%.2f".format(tip)
        TotalAmount.text="%.2f".format(totalamount)

    }
}