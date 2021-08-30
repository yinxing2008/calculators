package cn.cxy.calculator

import java.math.BigDecimal

@ExperimentalStdlibApi
class Calculator(private val resultCallback: ResultCallback) {
    var inputStringBuffer = StringBuffer()

    fun accept(input: String) {
        var tempResult = ""
        var result = ""
        when (input) {
            KEY_CLEAR -> clear()
            KEY_DEL -> deleteLastInput()
            KEY_ADD, KEY_SUB, KEY_MULTIPLY, KEY_DIV -> checkInputOp(input)
            KEY_DOT -> checkInputDot(input)
            KEY_GET_RESULT -> result = getResult()
            else -> checkInputNum(input)
        }
        if (result.isNotEmpty()) {
            inputStringBuffer.replace(0, inputStringBuffer.length, result)
        } else {
            tempResult = getResult()
        }
        resultCallback.updateResult(inputStringBuffer.toString())
        resultCallback.updateTempResult(tempResult)
    }

    private fun getResult(): String {
        val inputString = inputStringBuffer.toString()
        if (inputString.isEmpty()) {
            return ""
        }
        var numOrOpList = spitIntoList(inputString)
        //为便于后续计算，如果最后一个元素是操作符，则删除掉。
        if (isOp(numOrOpList.last())) {
            numOrOpList.removeLast()
        }
        var result = calculate(numOrOpList).toString()
        result = formatResult(result)
        return result
    }

    /**
     * 如果以".0"结尾，则直接去掉。
     */
    private fun formatResult(_result: String): String {
        var result = _result
        if (result.endsWith(".0")) {
            result = result.substringBefore(".0")
        }
        return result
    }

    private fun calculate(numOrOpList: MutableList<String>): Double {
        if (numOrOpList.size == 1) {
            return numOrOpList[0].toDouble()
        }
        var opIndex = -1
        opIndex = numOrOpList.indexOf(KEY_MULTIPLY)
        if (opIndex != -1) {
            val lastNum = numOrOpList[opIndex - 1]
            val nextNum = numOrOpList[opIndex + 1]
            val result = lastNum.toBigDecimal().multiply(nextNum.toBigDecimal())
            val newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString())
            return calculate(newList)
        }

        opIndex = numOrOpList.indexOf(KEY_DIV)
        if (opIndex != -1) {
            val lastNum = numOrOpList[opIndex - 1]
            val nextNum = numOrOpList[opIndex + 1]
            val result = lastNum.toBigDecimal()
                .divide(nextNum.toBigDecimal(), CALC_SCALE, BigDecimal.ROUND_HALF_EVEN)
            val newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString())
            return calculate(newList)
        }

        opIndex = numOrOpList.indexOf(KEY_ADD)
        if (opIndex != -1) {
            val lastNum = numOrOpList[opIndex - 1]
            val nextNum = numOrOpList[opIndex + 1]
            val result = lastNum.toBigDecimal().add(nextNum.toBigDecimal())
            val newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString())
            return calculate(newList)
        }

        opIndex = numOrOpList.indexOf(KEY_SUB)
        if (opIndex != -1) {
            val lastNum = numOrOpList[opIndex - 1]
            val nextNum = numOrOpList[opIndex + 1]
            val result = lastNum.toBigDecimal().subtract(nextNum.toBigDecimal())
            val newList = replaceThreeElementsByOne(numOrOpList, opIndex, result.toString())
            return calculate(newList)
        }

        return 0.0
    }


    /**
     * 拆分字符串为列表，如1+2*3+4拆分为：
     * 1
     * +
     * 2
     * *
     * 3
     * +
     * 4
     */
    private fun spitIntoList(inputString: String): MutableList<String> {
        var list = mutableListOf<String>()
        var startIndex = 0
        for (index in inputString.indices) {
            if (!isDigitOrDot(inputString.elementAt(index).toString())) {
                list.add(inputString.substring(startIndex, index))
                list.add(inputString.elementAt(index).toString())
                startIndex = index + 1
            } else if (index == inputString.length - 1) {
                list.add(inputString.substring(startIndex, index + 1))
            }
        }
        return list
    }

    /**
     * 检测输入"."是否合法
     * 前一个输入必须为数字，并且最后一个数中不能已经包含"."
     */
    private fun checkInputDot(input: String) {
        val lastInput = getLastInput()
        if (isDigit(lastInput)
            && !getLastElementExceptOp(inputStringBuffer.toString()).contains(KEY_DOT)
        ) {
            append(input)
        }
    }

    /**
     * 检测输入操作符"加减乘除"是否合法
     */
    private fun checkInputOp(input: String) {
        val lastInput = getLastInput()
        if (isDigit(lastInput)) {
            append(input)
        }
    }

    private fun checkInputNum(input: String) {
        val lastElementExceptOp = getLastElementExceptOp(inputStringBuffer.toString())
        if (lastElementExceptOp.isEmpty()) {
            append(input)
        } else if (lastElementExceptOp.toDouble() != 0.0 || lastElementExceptOp.contains(KEY_DOT) || input != KEY_ZERO) {
            append(input)
        }
    }

    private fun getLastInput(): String {
        var result = ""
        if (inputStringBuffer.count() > 0) {
            result = inputStringBuffer[inputStringBuffer.count() - 1].toString()
        }
        return result
    }

    private fun deleteLastInput() {
        if (inputStringBuffer.count() > 0) {
            inputStringBuffer.deleteCharAt(inputStringBuffer.count() - 1)
        }
    }

    private fun append(input: String) = inputStringBuffer.append(input)
    private fun clear() = inputStringBuffer.delete(0, inputStringBuffer.count())
    private fun isDigit(input: String) = input in KEY_ZERO..KEY_NINE
    private fun isDigitOrDot(input: String) = input in KEY_ZERO..KEY_NINE || input == KEY_DOT
    private fun isOp(input: String) =
        input == KEY_ADD || input == KEY_SUB || input == KEY_MULTIPLY || input == KEY_DIV
}

interface ResultCallback {
    fun updateResult(text: String)
    fun updateTempResult(text: String)
}