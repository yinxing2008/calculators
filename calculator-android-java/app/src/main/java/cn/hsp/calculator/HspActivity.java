package cn.hsp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hspcalculator.R;

public class HspActivity extends AppCompatActivity {
    private TextView resultTv;
    private String operator = ""; // 操作符
    private String firstNum = ""; // 前一个操作数
    private String nextNum = ""; // 后一个操作数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsp);

        resultTv = findViewById(R.id.tv_result);

        //加号
        findViewById(R.id.btn_plus).setOnClickListener(View -> {
            operator = "+";
            showResult();
        });

        //减号
        findViewById(R.id.btn_minus).setOnClickListener(View -> {
            if (nextNum.equals("") && !operator.equals("")) {
                nextNum = "-";
            } else {
                operator = "-";
            }
            showResult();

        });

        //乘号
        findViewById(R.id.btn_multiply).setOnClickListener(View -> {
            operator = "X";
            showResult();

        });

        //除号
        findViewById(R.id.btn_divide).setOnClickListener(View -> {
            operator = "÷";
            showResult();
        });

        //等号
        findViewById(R.id.btn_equal).setOnClickListener(View -> {
            String temp;
            temp = firstNum.equals("") ? "0" : firstNum;
            Double leftNo = Double.valueOf(temp);
            temp = nextNum.equals("") ? "0" : nextNum;
            Double rightNo = Double.valueOf(temp);
            double result = 0.00;
            switch (operator) {
                case "+":
                    result = leftNo + rightNo;
                    break;
                case "-":
                    result = leftNo - rightNo;
                    break;
                case "X":
                    result = leftNo * rightNo;
                    break;
                case "÷":
                    result = leftNo / rightNo;
                    break;
            }
            resultTv.setText(String.valueOf(result));
            firstNum = "";
            nextNum = "";
            operator = "";
        });

        //清空
        findViewById(R.id.btn_clear).setOnClickListener(View -> cleanUp());

        findViewById(R.id.btn_zero).setOnClickListener(View -> setMyValue("0"));
        findViewById(R.id.btn_one).setOnClickListener(View -> setMyValue("1"));
        findViewById(R.id.btn_two).setOnClickListener(View -> setMyValue("2"));
        findViewById(R.id.btn_three).setOnClickListener(View -> setMyValue("3"));
        findViewById(R.id.btn_four).setOnClickListener(View -> setMyValue("4"));
        findViewById(R.id.btn_five).setOnClickListener(View -> setMyValue("5"));
        findViewById(R.id.btn_six).setOnClickListener(View -> setMyValue("6"));
        findViewById(R.id.btn_seven).setOnClickListener(View -> setMyValue("7"));
        findViewById(R.id.btn_eight).setOnClickListener(View -> setMyValue("8"));
        findViewById(R.id.btn_nine).setOnClickListener(View -> setMyValue("9"));
        findViewById(R.id.btn_dot).setOnClickListener(View -> {
            if (!nextNum.equals("")) {
                nextNum = nextNum + ".";
            } else {
                if (operator.equals("")) {
                    if (!firstNum.equals("")) {
                        firstNum = firstNum + ".";
                    } else {
                        firstNum = "0.";
                    }
                }
            }
            showResult();
        });
    }

    //设置firstNum、nextNum、operator的值，并设置显示
    private void setMyValue(String myValue) {
        if (firstNum.trim().equals("")) {
            firstNum = firstNum + myValue;
        } else {
            if (operator.equals("")) {
                firstNum = firstNum + myValue;
            } else {
                nextNum = nextNum + myValue;
            }
        }
        showResult();
    }

    //变量初始化清空
    private void cleanUp() {
        // 清空并初始化
        resultTv.setText("");
        operator = "";
        firstNum = "";
        nextNum = "";
    }

    //显示结果
    private void showResult() {
        resultTv.setText(String.format(getResources().getString(R.string.display_Bar), firstNum, operator, nextNum));
    }

}