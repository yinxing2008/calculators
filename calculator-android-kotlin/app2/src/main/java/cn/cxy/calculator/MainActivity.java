package cn.cxy.calculator;

import static cn.cxy.calculator.Calculator.KEY_ADD;
import static cn.cxy.calculator.Calculator.KEY_CLEAR;
import static cn.cxy.calculator.Calculator.KEY_DEL;
import static cn.cxy.calculator.Calculator.KEY_DIV;
import static cn.cxy.calculator.Calculator.KEY_DOT;
import static cn.cxy.calculator.Calculator.KEY_EIGHT;
import static cn.cxy.calculator.Calculator.KEY_FIVE;
import static cn.cxy.calculator.Calculator.KEY_FOUR;
import static cn.cxy.calculator.Calculator.KEY_GET_RESULT;
import static cn.cxy.calculator.Calculator.KEY_MULTIPLY;
import static cn.cxy.calculator.Calculator.KEY_NINE;
import static cn.cxy.calculator.Calculator.KEY_ONE;
import static cn.cxy.calculator.Calculator.KEY_SEVEN;
import static cn.cxy.calculator.Calculator.KEY_SIX;
import static cn.cxy.calculator.Calculator.KEY_SUB;
import static cn.cxy.calculator.Calculator.KEY_THREE;
import static cn.cxy.calculator.Calculator.KEY_TWO;
import static cn.cxy.calculator.Calculator.KEY_ZERO;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity implements View.OnClickListener, ResultCallback {
    public Calculator calculator;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calculator = new Calculator(this);
        initListeners();
    }

    private void initListeners() {
        findViewById(R.id.clearBtn).setOnClickListener(this);
        findViewById(R.id.divideBtn).setOnClickListener(this);
        findViewById(R.id.multiplyBtn).setOnClickListener(this);
        findViewById(R.id.delBtn).setOnClickListener(this);
        findViewById(R.id.sevenBtn).setOnClickListener(this);
        findViewById(R.id.eightBtn).setOnClickListener(this);
        findViewById(R.id.nineBtn).setOnClickListener(this);
        findViewById(R.id.subBtn).setOnClickListener(this);
        findViewById(R.id.fourBtn).setOnClickListener(this);
        findViewById(R.id.fiveBtn).setOnClickListener(this);
        findViewById(R.id.sixBtn).setOnClickListener(this);
        findViewById(R.id.addBtn).setOnClickListener(this);
        findViewById(R.id.oneBtn).setOnClickListener(this);
        findViewById(R.id.twoBtn).setOnClickListener(this);
        findViewById(R.id.threeBtn).setOnClickListener(this);
        findViewById(R.id.getResultTv).setOnClickListener(this);
        findViewById(R.id.zeroBtn).setOnClickListener(this);
        findViewById(R.id.dotBtn).setOnClickListener(this);
    }

    public void onClick(@Nullable View view) {
        switch (view.getId()) {
            case R.id.clearBtn:
                calculator.accept(KEY_CLEAR);
                break;
            case R.id.divideBtn:
                calculator.accept(KEY_DIV);
                break;
            case R.id.multiplyBtn:
                calculator.accept(KEY_MULTIPLY);
                break;
            case R.id.delBtn:
                calculator.accept(KEY_DEL);
                break;
            case R.id.sevenBtn:
                calculator.accept(KEY_SEVEN);
                break;
            case R.id.eightBtn:
                calculator.accept(KEY_EIGHT);
                break;
            case R.id.nineBtn:
                calculator.accept(KEY_NINE);
                break;
            case R.id.subBtn:
                calculator.accept(KEY_SUB);
                break;
            case R.id.fourBtn:
                calculator.accept(KEY_FOUR);
                break;
            case R.id.fiveBtn:
                calculator.accept(KEY_FIVE);
                break;
            case R.id.sixBtn:
                calculator.accept(KEY_SIX);
                break;
            case R.id.addBtn:
                calculator.accept(KEY_ADD);
                break;
            case R.id.oneBtn:
                calculator.accept(KEY_ONE);
                break;
            case R.id.twoBtn:
                calculator.accept(KEY_TWO);
                break;
            case R.id.threeBtn:
                calculator.accept(KEY_THREE);
                break;
            case R.id.getResultTv:
                calculator.accept(KEY_GET_RESULT);
                break;
            case R.id.zeroBtn:
                calculator.accept(KEY_ZERO);
                break;
            case R.id.dotBtn:
                calculator.accept(KEY_DOT);
                break;
        }
    }

    public void updateResult(@NotNull String text) {
        ((TextView) findViewById(R.id.resultTv)).setText(text);
    }

    public void updateTempResult(@NotNull String text) {
        ((TextView) findViewById(R.id.tempResultTv)).setText(text);
    }

}
