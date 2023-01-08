package com.varadasainikhil.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
//TODO: Try to implement OnEqual calculation using other functions.
// For + write a function, For - write a function,
// For * write a function,For / write a function

class MainActivity : AppCompatActivity() {

    private var tvInput :TextView?=null
    private var isDot : Boolean = false
    private var islastNumeric : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        islastNumeric = true

    }

    fun onClear(view : View){
        tvInput?.text = null
        isDot = false
        islastNumeric = false

    }

    fun onDot(view : View){
        if (!isDot){
            tvInput?.append(".")
        }
        isDot = true
        islastNumeric = false
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View){
        if (islastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix=""
            if (tvValue.contains("-")){
                //Subtraction
                try {
                    if (tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue= tvValue.substring(1)
                    }
                    if (tvValue.contains(prefix)){
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        val two = splitValue[1]
                        if (prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        val output = (one.toDouble() * two.toDouble()).toString()
                        tvInput?.text= removeZeroAfterDot(output)
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
            else if(tvValue.contains("+")){
                //Addition
                try {
                    if (tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue= tvValue.substring(1)
                    }
                    if (tvValue.contains(prefix)){
                        val splitValue = tvValue.split("+")
                        val one = splitValue[0]
                        val two = splitValue[1]
                        val output = (one.toDouble() * two.toDouble()).toString()
                        tvInput?.text= removeZeroAfterDot(output)
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
            else if(tvValue.contains("/")){
                try {
                    if (tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue= tvValue.substring(1)
                    }
                    if (tvValue.contains(prefix)){
                        val splitValue = tvValue.split("/")
                        val one = splitValue[0]
                        val two = splitValue[1]
                        val output = (one.toDouble() * two.toDouble()).toString()
                        tvInput?.text= removeZeroAfterDot(output)
                    }
                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
            else if(tvValue.contains("*")){
                try {
                    if (tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue= tvValue.substring(1)
                    }
                    if (tvValue.contains(prefix)){
                        val splitValue = tvValue.split("*")
                        val one = splitValue[0]
                        val two = splitValue[1]
                        val output = (one.toDouble() * two.toDouble()).toString()
                        tvInput?.text= removeZeroAfterDot(output)
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }

        }
    }


    fun onOperator (view:View){
            tvInput?.text?.let {
            if (islastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                isDot = false
                islastNumeric = false
            }
        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")||value.contains("+")||value.contains("*")||value.contains("-")
        }

    }

    fun removeZeroAfterDot(result :String):String{
        if(result.contains(".0")){
            return result.substring(0,result.length-2)
        }
        return result
    }
}