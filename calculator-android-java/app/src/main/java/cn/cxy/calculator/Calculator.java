package cn.cxy.calculator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public final class Calculator {
    public static final String KEY_CLEAR = "C";
    public static final String KEY_DIV = "÷";
    public static final String KEY_MULTIPLY = "×";
    public static final String KEY_DEL = "D";
    public static final String KEY_SEVEN = "7";
    public static final String KEY_EIGHT = "8";
    public static final String KEY_NINE = "9";
    public static final String KEY_MINUS = "-";
    public static final String KEY_FOUR = "4";
    public static final String KEY_FIVE = "5";
    public static final String KEY_SIX = "6";
    public static final String KEY_ADD = "+";
    public static final String KEY_ONE = "1";
    public static final String KEY_TWO = "2";
    public static final String KEY_THREE = "3";
    public static final String KEY_GET_RESULT = "=";
    public static final String KEY_ZERO = "0";
    public static final String KEY_DOT = ".";

    private final StringBuffer inputStringBuffer;
    private final ResultCallback resultCallback;

    private static final int CALC_SCALE = 10;

    public Calculator(@NotNull ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
        this.inputStringBuffer = new StringBuffer();
    }

    public final void accept(@NotNull String input) {
        String tempResult = "";
        String result = "";
        if (KEY_CLEAR.equals(input)) {
            clear();
        } else if (KEY_DEL.equals(input)) {
            deleteLastInput();
        } else if (KEY_ADD.equals(input) || KEY_MINUS.equals(input) || KEY_MULTIPLY.equals(input) || KEY_DIV.equals(input)) {
            checkInputOp(input);
        } else if (KEY_DOT.equals(input)) {
            checkInputDot(input);
        } else if (KEY_GET_RESULT.equals(input)) {
            result = getResult();
        } else {
            checkInputNum(input);
        }
        if (!result.isEmpty()) {
            inputStringBuffer.replace(0, this.inputStringBuffer.length(), result);
        }
        this.resultCallback.updateResult(this.inputStringBuffer.toString());
    }

    private String getResult() {
        String inputString = inputStringBuffer.toString();
        if (inputString.isEmpty())
            return "";
        List<String> numOrOpList = spitIntoList(inputString);
        if (isOp(numOrOpList.get(numOrOpList.size() - 1)))
            numOrOpList.remove(numOrOpList.size() - 1);
        String result = String.valueOf(calculate(numOrOpList));
        result = formatResult(result);
        return result;
    }

    private String formatResult(String result) {
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - ".0".length());
        return result;
    }

    private double calculate(List<String> numOrOpList) {
        if (numOrOpList.size() == 1) {
            return Double.parseDouble(numOrOpList.get(0));
        }
        int opIndex = numOrOpList.indexOf(KEY_MULTIPLY);
        if (opIndex != -1) {
            String lastNum = numOrOpList.get(opIndex - 1);
            String nextNum = numOrOpList.get(opIndex + 1);
            BigDecimal result = new BigDecimal(lastNum).multiply(new BigDecimal(nextNum));
            List<String> newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString());
            return calculate(newList);
        }
        opIndex = numOrOpList.indexOf(KEY_DIV);
        if (opIndex != -1) {
            String lastNum = numOrOpList.get(opIndex - 1);
            String nextNum = numOrOpList.get(opIndex + 1);
            BigDecimal result = new BigDecimal(lastNum).divide(new BigDecimal(nextNum), CALC_SCALE, BigDecimal.ROUND_HALF_EVEN);
            List<String> newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString());
            return calculate(newList);
        }

        opIndex = numOrOpList.indexOf(KEY_ADD);
        if (opIndex != -1) {
            String lastNum = numOrOpList.get(opIndex - 1);
            String nextNum = numOrOpList.get(opIndex + 1);
            BigDecimal result = new BigDecimal(lastNum).add(new BigDecimal(nextNum));
            List<String> newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString());
            return calculate(newList);
        }
        opIndex = numOrOpList.indexOf(KEY_MINUS);
        if (opIndex != -1) {
            String lastNum = numOrOpList.get(opIndex - 1);
            String nextNum = numOrOpList.get(opIndex + 1);
            BigDecimal result = new BigDecimal(lastNum).subtract(new BigDecimal(nextNum));
            List<String> newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString());
            return calculate(newList);
        }
        return 0.0D;
    }

    private List<String> spitIntoList(String inputString) {
        List<String> list = new ArrayList<>();
        int startIndex = 0;
        for (int index = 0; index < inputString.length(); index++) {
            if (!isDigitOrDot(inputString.charAt(index) + "")) {
                list.add(inputString.substring(startIndex, index));
                list.add(inputString.charAt(index) + "");
                startIndex = index + 1;
            } else if (index == inputString.length() - 1) {
                list.add(inputString.substring(startIndex, index + 1));
            }
        }
        return list;
    }

    private void checkInputDot(String input) {
        String lastInput = getLastInput();
        Intrinsics.checkExpressionValueIsNotNull(this.inputStringBuffer.toString(), "inputStringBuffer.toString()");
        if (isDigit(lastInput) && !getLastElementExceptOp(inputStringBuffer.toString()).contains(KEY_DOT))
            append(input);
    }

    private void checkInputOp(String input) {
        String lastInput = getLastInput();
        if (isDigit(lastInput))
            append(input);
    }

    private void checkInputNum(String input) {
        String lastElementExceptOp = getLastElementExceptOp(this.inputStringBuffer.toString());
        if (lastElementExceptOp.isEmpty()) {
            append(input);
        } else if (Double.parseDouble(lastElementExceptOp) != 0.0 || lastElementExceptOp.contains(KEY_DOT) || input != KEY_ZERO) {
            append(input);
        }
    }

    private String getLastInput() {
        String result = "";
        if (inputStringBuffer.length() > 0) {
            result = inputStringBuffer.charAt(inputStringBuffer.length() - 1) + "";
        }
        return result;
    }

    private void deleteLastInput() {
        if (inputStringBuffer.length() > 0) {
            inputStringBuffer.deleteCharAt(inputStringBuffer.length() - 1);
        }
    }

    private void append(String input) {
        inputStringBuffer.append(input);
    }

    private void clear() {
        inputStringBuffer.delete(0, inputStringBuffer.length());
    }

    private boolean isDigit(String input) {
        return input.compareTo(KEY_ZERO) >= 0 && input.compareTo(KEY_NINE) <= 0;
    }

    private boolean isDigitOrDot(String input) {
        return isDigit(input) || KEY_DOT.equals(input);
    }

    private boolean isOp(String input) {
        return (Intrinsics.areEqual(input, KEY_ADD) || Intrinsics.areEqual(input, KEY_MINUS) || Intrinsics.areEqual(input, KEY_MULTIPLY) || Intrinsics.areEqual(input, KEY_DIV));
    }

    private final List<String> replaceThreeElementsByOne(@NotNull List<String> numOrOpList, int middleElementPosition, @NotNull String replacement) {
        List<String> newList = new ArrayList<>(numOrOpList);
        newList.remove(middleElementPosition - 1);
        newList.remove(middleElementPosition - 1);
        newList.remove(middleElementPosition - 1);
        newList.add(middleElementPosition - 1, replacement);
        return newList;
    }

    public final String getLastElementExceptOp(@NotNull String input) {
        String regex = "[" + KEY_ADD + "|\\" + KEY_MINUS + "|" + KEY_MULTIPLY + "|" + KEY_DIV + "]";
        String[] arr = input.split(regex);
        if (arr.length > 0) {
            return arr[arr.length - 1];
        } else {
            return "";
        }
    }
}
