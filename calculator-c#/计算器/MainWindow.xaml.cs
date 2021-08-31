using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using MahApps.Metro.Controls;
using System.Windows.Controls.Primitives;

namespace 计算器
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : MetroWindow
    {
        public MainWindow()
        {
            InitializeComponent();
            B_MC.IsEnabled = false;
            B_M__1.IsEnabled = false;
            B_M__.IsEnabled = false;
            B_MR.IsEnabled = false;
        }

        //temp用于临时存储操作数，oper存储最近一次的运算符
        private double temp, op1, op2, memory, result;
        private string oper = null;
        //next用来表示是否要输入新的操作数
        private bool next;

        //连续计算时先计算前面的结果
        private void Aut()
        {
            op2 = Convert.ToDouble(Out_1.Text);
            switch (oper)
            {
                case "+":
                    result = op1 + op2;
                    break;
                case "-":
                    result = op1 - op2;
                    break;
                case "×":
                    result = op1 * op2;
                    break;
                case "÷":
                    result = op1 / op2;
                    break;
                default:
                    break;

            }
            Out_1.Text = Convert.ToString(result);
            //Out_2.Text = Convert.ToString(result);
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            //目标
            this.contextMenu.PlacementTarget = this.btnMenu;
            //位置
            this.contextMenu.Placement = PlacementMode.Top;
            //显示菜单
            this.contextMenu.IsOpen = true;
        }

        private void B_0_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text != "0")
            {
                Out_1.Text += "0";
            }
            if (next)
            {
                Out_1.Text = "0";
                next = false;
            }
            
        }

        private void B_1_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "1";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "1";

        }

        private void B_2_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "2";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "2";
        }

        private void B_3_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "3";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "3";
        }

        private void B_4_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "4";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "4";
        }

        private void B_5_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "5";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "5";
        }

        private void B_6_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "6";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "6";
        }

        private void B_7_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "7";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "7";
        }

        private void B_8_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "8";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "8";
        }

        private void B_9_Click(object sender, RoutedEventArgs e)
        {
            if (Out_1.Text == "0" || next)
            {
                Out_1.Text = "9";
                if (next)
                {
                    next = false;
                }
            }
            else
                Out_1.Text += "9";
        }

        private void B_C_Click(object sender, RoutedEventArgs e)
        {
            Out_1.Text = "0";
            Out_2.Text = "";
            oper = null;
        }

        private void B_CE_Click(object sender, RoutedEventArgs e)
        {
            Out_1.Text = "0";
            Out_2.Text = "";
            oper = null;
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            Out_2.Text += Out_1.Text + "+";
            if (oper != null)
            {
                Aut();
            }
            op1 = Convert.ToDouble(Out_1.Text);
            oper = "+";
            next = true;
        }

        private void B_sub_Click(object sender, RoutedEventArgs e)
        {
            Out_2.Text += Out_1.Text + "-";
            if (oper != null)
            {
                Aut();
            }
            op1 = Convert.ToDouble(Out_1.Text);
            oper = "-";
            next = true;
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            Out_2.Text += Out_1.Text + "×";
            if (oper != null)
            {
                Aut();
            }
            op1 = Convert.ToDouble(Out_1.Text);
            oper = "×";
            next = true;
        }

        private void Button_Click_4(object sender, RoutedEventArgs e)
        {
            Out_2.Text += Out_1.Text + "÷";
            if (oper != null)
            {
                Aut();
            }
            op1 = Convert.ToDouble(Out_1.Text);
            oper = "÷";
            next = true;
        }

        private void B_delete_Click(object sender, RoutedEventArgs e)
        {
            if (!next)
            {
                if (Out_1.Text.Length == 1)
                {
                    Out_1.Text = "0";
                }
                else
                {
                    Out_1.Text = Out_1.Text.Substring(0, Out_1.Text.Length - 1);
                }                
            }
        }

        private void B_reciprocal_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                temp = Convert.ToDouble(Out_1.Text);
                temp = 1 / temp;
                Out_1.Text = Convert.ToString(temp);
            }
            catch(Exception ee)
            {
                Out_1.Text = "除数不能为0";
            }
        }

        private void B_sq_Click(object sender, RoutedEventArgs e)
        {
            temp = Convert.ToDouble(Out_1.Text);
            temp = Math.Pow(temp, 2);
            Out_1.Text = Convert.ToString(temp);
        }

        private void B_cu_Click(object sender, RoutedEventArgs e)
        {
            temp = Convert.ToDouble(Out_1.Text);
            temp = Math.Pow(temp, 3);
            Out_1.Text = Convert.ToString(temp);
        }

        private void B_radical_Click(object sender, RoutedEventArgs e)
        {
            temp = Convert.ToDouble(Out_1.Text);
            temp = Math.Pow(temp, 0.5);
            Out_1.Text = Convert.ToString(temp);
        }

        private void B_per_Click(object sender, RoutedEventArgs e)
        {
            temp = Convert.ToDouble(Out_1.Text);
            temp = temp / 100;
            Out_1.Text = Convert.ToString(temp);
        }

        private void B_MR_Click(object sender, RoutedEventArgs e)
        {
            Out_1.Text = Convert.ToString(memory);
        }

        private void B_M__1_Click(object sender, RoutedEventArgs e)
        {
            memory += Convert.ToDouble(Out_1.Text);
        }

        private void B_M___Click(object sender, RoutedEventArgs e)
        {
            memory -= Convert.ToDouble(Out_1.Text);
        }

        private void B_MC_Click(object sender, RoutedEventArgs e)
        {
            memory = 0;
            B_MC.IsEnabled = false;
            B_M__1.IsEnabled = false;
            B_M__.IsEnabled = false;
            B_MR.IsEnabled = false;
        }

        private void B_MS_Click(object sender, RoutedEventArgs e)
        {
            memory = Convert.ToDouble(Out_1.Text);
            B_MC.IsEnabled = true;
            B_M__1.IsEnabled = true;
            B_M__.IsEnabled = true;
            B_MR.IsEnabled = true;
        }

        private void B_eq_Click(object sender, RoutedEventArgs e)
        {
            op2 = Convert.ToDouble(Out_1.Text);
            switch (oper)
            {
                case "+":
                    result = op1 + op2;
                    break;
                case "-":
                    result = op1 - op2;
                    break;
                case "×":
                    result = op1 * op2;
                    break;
                case "÷":
                    result = op1 / op2;
                    break;
                default:
                    break;

            }
            oper = null;
            Out_1.Text = Convert.ToString(result);
            Out_2.Text = "";
        }

        private void B_opp_Click(object sender, RoutedEventArgs e)
        {
            temp = Convert.ToDouble(Out_1.Text);
            if (temp != 0)
            {
                temp = 0 - temp;
            }
            Out_1.Text = Convert.ToString(temp);
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            Out_1.Text += ".";
        }
    }
}
