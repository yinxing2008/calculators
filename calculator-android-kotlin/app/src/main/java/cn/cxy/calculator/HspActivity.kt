package cn.cxy.calculator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.cxy.calculator.Calculator.Companion.KEY_ADD
import cn.cxy.calculator.Calculator.Companion.KEY_CLEAR
import cn.cxy.calculator.Calculator.Companion.KEY_DIV
import cn.cxy.calculator.Calculator.Companion.KEY_DOT
import cn.cxy.calculator.Calculator.Companion.KEY_EIGHT
import cn.cxy.calculator.Calculator.Companion.KEY_FIVE
import cn.cxy.calculator.Calculator.Companion.KEY_FOUR
import cn.cxy.calculator.Calculator.Companion.KEY_GET_RESULT
import cn.cxy.calculator.Calculator.Companion.KEY_MINUS
import cn.cxy.calculator.Calculator.Companion.KEY_MULTIPLY
import cn.cxy.calculator.Calculator.Companion.KEY_NINE
import cn.cxy.calculator.Calculator.Companion.KEY_ONE
import cn.cxy.calculator.Calculator.Companion.KEY_SEVEN
import cn.cxy.calculator.Calculator.Companion.KEY_SIX
import cn.cxy.calculator.Calculator.Companion.KEY_THREE
import cn.cxy.calculator.Calculator.Companion.KEY_TWO
import cn.cxy.calculator.Calculator.Companion.KEY_ZERO
import kotlinx.android.synthetic.main.activity_hsp.*

@ExperimentalStdlibApi
class HspActivity : AppCompatActivity(), ResultCallback {
    lateinit var calculator: Calculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hsp)
        calculator = Calculator(this)
        initListeners()
    }

    private fun initListeners() {
        clearBtn.setOnClickListener { calculator.accept(KEY_CLEAR) }
        addBtn.setOnClickListener { calculator.accept(KEY_ADD) }
        minusBtn.setOnClickListener { calculator.accept(KEY_MINUS) }
        multiplyBtn.setOnClickListener { calculator.accept(KEY_MULTIPLY) }
        divideBtn.setOnClickListener { calculator.accept(KEY_DIV) }
        getResultBtn.setOnClickListener { calculator.accept(KEY_GET_RESULT) }
        zeroBtn.setOnClickListener { calculator.accept(KEY_ZERO) }
        oneBtn.setOnClickListener { calculator.accept(KEY_ONE) }
        twoBtn.setOnClickListener { calculator.accept(KEY_TWO) }
        threeBtn.setOnClickListener { calculator.accept(KEY_THREE) }
        fourBtn.setOnClickListener { calculator.accept(KEY_FOUR) }
        fiveBtn.setOnClickListener { calculator.accept(KEY_FIVE) }
        sixBtn.setOnClickListener { calculator.accept(KEY_SIX) }
        sevenBtn.setOnClickListener { calculator.accept(KEY_SEVEN) }
        eightBtn.setOnClickListener { calculator.accept(KEY_EIGHT) }
        nineBtn.setOnClickListener { calculator.accept(KEY_NINE) }
        dotBtn.setOnClickListener { calculator.accept(KEY_DOT) }
        aboutMe.setOnClickListener { gotoAboutMe() }
    }

    override fun updateResult(text: String) {
        resultTv.text = text
    }

    private fun gotoAboutMe() {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse("https://juejin.cn/post/7002792005688360968/")
        intent.data = uri
        startActivity(intent)
    }
}