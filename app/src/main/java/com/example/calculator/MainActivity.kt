package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var firstNum = 0
    private var secondNum = 0
    private var isNumeric = false
    private var isDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigitClick(view: View) {
        tv_input.append((view as Button).text)
        isNumeric = true
    }

    fun clear(view: View) {
        firstNum = 0
        secondNum = 0
        tv_input.text = ""
        isDecimal = false
        isNumeric = false
    }

    fun onDecimalClick(view: View) {
        if (isNumeric && !isDecimal) {
            isDecimal = true
            isNumeric = false
            tv_input.append(".")
        }
    }

    fun onOperator(view: View) {
        if ((view as Button).text == "-" && tv_input.text.isEmpty()) {
            tv_input.text = "-"
        }
        if (isNumeric && !isOperatorAdded(tv_input.text.toString())) {
            tv_input.append((view as Button).text)
            isNumeric = false
            isDecimal = false
        }
    }

    private fun isOperatorAdded(text: String): Boolean {
        return if (text.startsWith("-")) {
            false
        } else {
            text.contains("/") || text.contains("*") ||
                    text.contains("-") || text.contains("+")
        }
    }

    fun onEqual(view: View) {
        if (isNumeric) {
            var tvValue = tv_input.text.toString()
            var prefix = ""

            if (tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            try {

                if (tvValue.contains("-")) {
                    val split = tvValue.split("-")
                    var one = split[0]
                    val two = split[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val split = tvValue.split("+")
                    var one = split[0]
                    val two = split[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val split = tvValue.split("*")
                    var one = split[0]
                    val two = split[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val split = tvValue.split("/")
                    var one = split[0]
                    val two = split[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }
    }

    private fun removeZeroAfterDot(value: String): String {
        val splitValue = value.split(".")
        isNumeric = true
        return if (splitValue[1] == "0") {
            isDecimal = false
            splitValue[0]
        } else {
            isDecimal = true
            value
        }
    }

}


//fun onEqualPressed(view: View){
//    str = tv_input.text.toString()
//    secondNum = str.toInt()
//    when(action){
//        "/" -> firstNum = firstNum/secondNum
//        "*" -> firstNum = firstNum*secondNum
//        "-" -> firstNum = firstNum-secondNum
//        "+" -> firstNum = firstNum+secondNum
//    }
//    tv_input.text = firstNum.toString()
//}