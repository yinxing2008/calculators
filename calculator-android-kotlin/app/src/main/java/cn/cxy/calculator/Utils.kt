package cn.cxy.calculator

/**
 * 将列表中连续的三个元素替换为一个元素
 * 用于实现计算结果替换，如原先的元素是：1 + 2，替换为3
 */
fun replaceThreeElementsByOne(
    numOrOpList: MutableList<String>,
    middleElementPosition: Int,
    replacement: String
): MutableList<String> {
    val newList = mutableListOf<String>()
    newList.addAll(numOrOpList)
    newList.removeAt(middleElementPosition - 1)
    newList.removeAt(middleElementPosition - 1)
    newList.removeAt(middleElementPosition - 1)
    newList.add(middleElementPosition - 1, replacement)
    return newList
}

/**
 * 获取字符串中最后一个操作符后面的元素
 * 如字符串为：123+45*67，那返回67
 */
fun getLastElementExceptOp(input: String): String {
    val regex = Regex("[$KEY_ADD|\\$KEY_SUB|$KEY_MULTIPLY|$KEY_DIV]")
    val result = input.split(regex)
    return result.last()
}