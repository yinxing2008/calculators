import tkinter as tk


def printout(result1, result2, frm):
    label1 = tk.Label(frm, font=('微软雅黑', 20), bg='#EEE9E9', bd='9', fg='#828282', anchor='se', textvariable=result1)
    label1.place(relx=1, rely=0.5, relwidth=1, relheight=0.5, anchor='se')
    label2 = tk.Label(frm, font=('微软雅黑', 20), bg='#EEE9E9', bd='9', fg='#828282', anchor='se', textvariable=result2)
    label2.place(relx=1, rely=1, relwidth=1, relheight=0.5, anchor='se')


def layout(root, result1, result2):
    frm1 = tk.Frame(root)
    frm1.config(width=320, height=100, relief='solid', highlightthickness=2, highlightbackground='red')

    printout(result1, result2, frm1)
    frm1.grid(row=0, column=0)
    frm1.grid_propagate(0)
    frm1.update()

    frm2 = tk.Frame(root)
    frm2.config(width=320, height=400, bg='#dfe9f4', relief='solid', highlightthickness=2, highlightbackground='blue')
    layout_button(frm2)

    frm2.grid(row=1, column=0)
    frm2.grid_propagate(0)


def layout_button(frm):
    btn7 = tk.Button(frm, text='7', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('7'))
    btn7.place(relx=0.125, rely=0.125, relwidth=0.23, relheight=0.23, anchor='center')
    btn8 = tk.Button(frm, text='8', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('8'))
    btn8.place(relx=0.375, rely=0.125, relwidth=0.23, relheight=0.23, anchor='center')
    btn9 = tk.Button(frm, text='9', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('9'))
    btn9.place(relx=0.625, rely=0.125, relwidth=0.23, relheight=0.23, anchor='center')
    btn4 = tk.Button(frm, text='4', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('4'))
    btn4.place(relx=0.125, rely=0.375, relwidth=0.23, relheight=0.23, anchor='center')
    btn5 = tk.Button(frm, text='5', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('5'))
    btn5.place(relx=0.375, rely=0.375, relwidth=0.23, relheight=0.23, anchor='center')
    btn6 = tk.Button(frm, text='6', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('6'))
    btn6.place(relx=0.625, rely=0.375, relwidth=0.23, relheight=0.23, anchor='center')
    btn1 = tk.Button(frm, text='1', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('1'))
    btn1.place(relx=0.125, rely=0.625, relwidth=0.23, relheight=0.23, anchor='center')
    btn2 = tk.Button(frm, text='2', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('2'))
    btn2.place(relx=0.375, rely=0.625, relwidth=0.23, relheight=0.23, anchor='center')
    btn3 = tk.Button(frm, text='3', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('3'))
    btn3.place(relx=0.625, rely=0.625, relwidth=0.23, relheight=0.23, anchor='center')
    btn10 = tk.Button(frm, text='0', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('0'))
    btn10.place(relx=0.125, rely=0.875, relwidth=0.23, relheight=0.23, anchor='center')
    btn11 = tk.Button(frm, text='.', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_num('.'))
    btn11.place(relx=0.375, rely=0.875, relwidth=0.23, relheight=0.23, anchor='center')

    btn12 = tk.Button(frm, text='=', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_equal())
    btn12.place(relx=0.625, rely=0.875, relwidth=0.23, relheight=0.23, anchor='center')

    btn13 = tk.Button(frm, text='+', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_compute('+'))
    btn13.place(relx=0.875, rely=0.125, relwidth=0.23, relheight=0.23, anchor='center')
    btn13 = tk.Button(frm, text='-', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_compute('-'))
    btn13.place(relx=0.875, rely=0.375, relwidth=0.23, relheight=0.23, anchor='center')
    btn13 = tk.Button(frm, text='X', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_compute('*'))
    btn13.place(relx=0.875, rely=0.625, relwidth=0.23, relheight=0.23, anchor='center')
    btn13 = tk.Button(frm, text='÷', font=('微软雅黑', 20), bg='#f1f5fa', command=lambda: press_compute('/'))
    btn13.place(relx=0.875, rely=0.875, relwidth=0.23, relheight=0.23, anchor='center')


def press_num(num):
    global isintbtn  # 标记按钮按的数字，默认未按
    global isstrbtn  # 标记按钮按的符号，默认未按
    global is_equal_last  # 标记上一个按键
    global last_num  # 记录上一个数字
    global list
    if isstrbtn == False:
        pass
    else:
        result2.set(0)
        isstrbtn = False  # 若上次按键为符号，数字重新清零开始计数
    if is_equal_last == True:
        result2.set(0)  # 等号同理

    oldnum = result2.get()
    if oldnum == '0':
        result2.set(num)
        last_num = num
    else:
        newnum = oldnum + num
        result2.set(newnum)
        last_num = newnum  # 连续按数字，将数字连起来作为新的数字
    is_equal_last = False  # 标记下上个按键已不是等号


def press_compute(sign):
    global isintbtn
    global isstrbtn
    global is_equal_last
    global last_cpt
    global list
    num = result2.get()
    list.append(num)  # 把数字记录到列表中

    list.append(sign)
    isstrbtn = True  # 把符号记录到列表中

    is_equal_last = False
    last_cpt = sign
    cur_out = ''.join(list)
    result1.set(cur_out)  # 上部分显示板显示之前输入的数字


def press_equal():
    global isintbtn
    global isstrbtn
    global is_equal_last
    global last_num
    global list
    if is_equal_last == False:
        curnum = result2.get()
        list.append(curnum)
        computeStr = ''.join(list)
        endnum = eval(computeStr)  # 使用eval函数将list内容计算出结果

        result2.set(endnum)
        result1.set(computeStr)  # 将计算结果和内容显示在显示板上
        is_equal_last = True  # 标记此时最后一个按键是等号
        list.clear()  # 清空list
    else:
        curnum = result2.get()  # 如果上次按键是等号，记录上次的计算结果
        list.append(curnum)
        list.append(last_cpt)
        list.append(str(last_num))  # 将计算结果、最后一次的符号、最后一个数字添加到list中
        # print(list)

        computeStr = ''.join(list)
        endnum = eval(computeStr)
        # print(computeStr)

        result2.set(endnum)
        result1.set(computeStr)
        is_equal_last = True
        list.clear()  # 按照新的list计算结果并显示


root = tk.Tk()
root.title("计算器1.0")
root['bg'] = '#dfe9f4'
root.geometry("320x500")

isintbtn = False  # 确认是否按下了计算符，默认为未按下
isstrbtn = False
is_equal_last = False  # 确认上一个按键是不是等号，便于处理连续按了等号的情况
last_num = []  # 上一个数字保存
last_cpt = ''  # 上一个计算符号保存，便与处理如果连续按了等号的情况
list = []  # 保存按下的数据和字符，一是用于最终结果计算，而是现实在上面现实板上
result1 = tk.StringVar()
result1.set('')  # 上面显示板，默认显示空
result2 = tk.StringVar()
result2.set(0)  # 下面显示板，默认显示0

layout(root, result1, result2)

root.mainloop()