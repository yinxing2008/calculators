package cn.cxy.calculator;

import static cn.cxy.calculator.Calculator.KEY_ADD;
import static cn.cxy.calculator.Calculator.KEY_CLEAR;
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
import static cn.cxy.calculator.Calculator.KEY_MINUS;
import static cn.cxy.calculator.Calculator.KEY_THREE;
import static cn.cxy.calculator.Calculator.KEY_TWO;
import static cn.cxy.calculator.Calculator.KEY_ZERO;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HspActivity extends AppCompatActivity implements ResultCallback {
    private Calculator calculator;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsp);
        this.calculator = new Calculator(this);
        initListeners();
    }

    private void initListeners() {
        findViewById(R.id.clearBtn).setOnClickListener(view -> calculator.accept(KEY_CLEAR));
        findViewById(R.id.addBtn).setOnClickListener(view -> calculator.accept(KEY_ADD));
        findViewById(R.id.minusBtn).setOnClickListener(view -> calculator.accept(KEY_MINUS));
        findViewById(R.id.multiplyBtn).setOnClickListener(view -> calculator.accept(KEY_MULTIPLY));
        findViewById(R.id.divideBtn).setOnClickListener(view -> calculator.accept(KEY_DIV));
        findViewById(R.id.getResultBtn).setOnClickListener(view -> calculator.accept(KEY_GET_RESULT));
        findViewById(R.id.zeroBtn).setOnClickListener(view -> calculator.accept(KEY_ZERO));
        findViewById(R.id.oneBtn).setOnClickListener(view -> calculator.accept(KEY_ONE));
        findViewById(R.id.twoBtn).setOnClickListener(view -> calculator.accept(KEY_TWO));
        findViewById(R.id.threeBtn).setOnClickListener(view -> calculator.accept(KEY_THREE));
        findViewById(R.id.fourBtn).setOnClickListener(view -> calculator.accept(KEY_FOUR));
        findViewById(R.id.fiveBtn).setOnClickListener(view -> calculator.accept(KEY_FIVE));
        findViewById(R.id.sixBtn).setOnClickListener(view -> calculator.accept(KEY_SIX));
        findViewById(R.id.sevenBtn).setOnClickListener(view -> calculator.accept(KEY_SEVEN));
        findViewById(R.id.eightBtn).setOnClickListener(view -> calculator.accept(KEY_EIGHT));
        findViewById(R.id.nineBtn).setOnClickListener(view -> calculator.accept(KEY_NINE));
        findViewById(R.id.dotBtn).setOnClickListener(view -> calculator.accept(KEY_DOT));
        findViewById(R.id.aboutMe).setOnClickListener(view -> gotoAboutMe());
    }

    public void updateResult(@NotNull String text) {
        ((TextView) findViewById(R.id.resultTv)).setText(text);
    }

    private void gotoAboutMe() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("https://juejin.cn/post/7002792005688360968/");
        intent.setData(uri);
        startActivity(intent);
    }
}
