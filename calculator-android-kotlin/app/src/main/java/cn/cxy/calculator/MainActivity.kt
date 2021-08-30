package cn.cxy.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@ExperimentalStdlibApi
class MainActivity : AppCompatActivity(), View.OnClickListener, ResultCallback {
    lateinit var calculator: Calculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculator = Calculator(this)
        initListeners()
    }

    private fun initListeners() {
        clearBtn.setOnClickListener(this)
        divideBtn.setOnClickListener(this)
        multiplyBtn.setOnClickListener(this)
        delBtn.setOnClickListener(this)
        sevenBtn.setOnClickListener(this)
        eightBtn.setOnClickListener(this)
        nineBtn.setOnClickListener(this)
        subBtn.setOnClickListener(this)
        fourBtn.setOnClickListener(this)
        fiveBtn.setOnClickListener(this)
        sixBtn.setOnClickListener(this)
        addBtn.setOnClickListener(this)
        oneBtn.setOnClickListener(this)
        twoBtn.setOnClickListener(this)
        threeBtn.setOnClickListener(this)
        getResultTv.setOnClickListener(this)
        zeroBtn.setOnClickListener(this)
        dotBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.clearBtn -> calculator.accept(KEY_CLEAR)
            R.id.divideBtn -> calculator.accept(KEY_DIV)
            R.id.multiplyBtn -> calculator.accept(KEY_MULTIPLY)
            R.id.delBtn -> calculator.accept(KEY_DEL)
            R.id.sevenBtn -> calculator.accept(KEY_SEVEN)
            R.id.eightBtn -> calculator.accept(KEY_EIGHT)
            R.id.nineBtn -> calculator.accept(KEY_NINE)
            R.id.subBtn -> calculator.accept(KEY_SUB)
            R.id.fourBtn -> calculator.accept(KEY_FOUR)
            R.id.fiveBtn -> calculator.accept(KEY_FIVE)
            R.id.sixBtn -> calculator.accept(KEY_SIX)
            R.id.addBtn -> calculator.accept(KEY_ADD)
            R.id.oneBtn -> calculator.accept(KEY_ONE)
            R.id.twoBtn -> calculator.accept(KEY_TWO)
            R.id.threeBtn -> calculator.accept(KEY_THREE)
            R.id.getResultTv -> calculator.accept(KEY_GET_RESULT)
            R.id.zeroBtn -> calculator.accept(KEY_ZERO)
            R.id.dotBtn -> calculator.accept(KEY_DOT)
        }
    }

    override fun updateResult(text: String) {
        resultTv.text = text
    }

    override fun updateTempResult(text: String) {
        tempResultTv.text = text
    }
}