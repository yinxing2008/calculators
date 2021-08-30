var rpn = require("../../utils/rpn.js");
Page({
  data: {
    idc: "clear",
    idadd: "+",
    id9: "9",
    id8: "8",
    id7: "7",
    idj: "-",
    id6: "6",
    id5: "5",
    id4: "4",
    idx: "*",
    id3: "3",
    id2: "2",
    id1: "1",
    iddiv: "/",
    id0: "0",
    ide: "=",
    screenData: "0",
    operaSymbo: { "+": "+", "-": "-", "*": "*", "÷": "/" },
    lastIsOperaSymbo: false,
    iconType: 'waiting_circle',
    iconColor: 'white',
  },
  clickBtn: function (event) {
    //获取触发点击事件的标签的id
    var id = event.target.id
    if (id == this.data.idc) {//清屏
      this.setData({
        'screenData': '0'
      });
    } else if (id == this.data.ide) {//等于=
      var data = this.data.screenData;
      if (data == "0") {
        return;
      }
      var lastWord = data.charAt(data.length - 1);
      if (isNaN(lastWord)) {
        return;
      }
      var expression = this.data.screenData;
      //获取rpn库运算结果
      var result = rpn.calCommonExp(expression);
      this.setData({
        'screenData': result + ""
      });

    } else {//运算符和数字的问题
      if (this.data.operaSymbo[id]) { //如果是符号+-*/
        if (this.data.lastIsOperaSymbo) {
          //如果是最后一位是符号，就不能在收入符号
          return;
        }
      }
      var sd = this.data.screenData;
      var data;
      if (sd == 0) {
        data = id;
      } else {
        data = sd + id;
      }
      this.setData({
        'screenData': data
      });

      ///置一下最后一位是否为运算符的标志位
      if (this.data.operaSymbo[id]) {
        this.setData({ "lastIsOperaSymbo": true });
      } else {
        this.setData({ "lastIsOperaSymbo": false });
      }
    }
  },
})