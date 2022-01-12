package com.example.tipcalculator

import android.animation.ArgbEvaluator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
private const val  Initial_Tip_Percent=15
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar.progress= Initial_Tip_Percent
        tvTipPercentage.text= "$Initial_Tip_Percent%"
        UpdateTipInfo(Initial_Tip_Percent)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG,"the percent is ${seekBar.progress}")
                tvTipPercentage.text="${seekBar.progress}%"
                changeInformation()
                UpdateTipInfo(seekBar.progress)
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

    private fun UpdateTipInfo(tipProgress: Int) {
        val tipinfo:String = when (tipProgress) {
            in 0..6 -> "poor"
            in 7..12 -> "Acceptable"
            in 13..19 -> "Good"
            else -> "Amazing"
        }
            tvTipInfo.text=tipinfo
        val color=ArgbEvaluator().evaluate(tipProgress.toFloat()/seekBar.max,
            ContextCompat.getColor(this,R.color.red),
            ContextCompat.getColor(this,R.color.green)) as Int
        tvTipInfo.setTextColor(color)
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