package ro.roweb.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtNumber;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn0;
    Button btnMinus;
    Button btnPlus;
    Button btnDivide;
    Button btnMultiply;
    Button btnEqual;
    ImageButton backspace;

    DecimalFormat format;

    double number = 0;
    int negative = 1;
    double decimal = 0.0d;
    double numberold = 0;
    char command = '|';
    char previousCommand;// = '|';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnMinus = (Button) findViewById(R.id.btnminus);
        btnPlus = (Button) findViewById(R.id.btnplus);
        btnDivide = (Button) findViewById(R.id.btndivide);
        btnMultiply = (Button) findViewById(R.id.btnmultiply);
        btnEqual = (Button) findViewById(R.id.btnequal);
        txtNumber = (TextView) findViewById(R.id.txt_number);
        backspace = (ImageButton) findViewById(R.id.backspace);

        format = new DecimalFormat("0.#####");

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if(number == "-0")
                    number = "0";
                if (number.length() == 1)
                    number = "0";
                else if(number.length()==2 && number.charAt(0)=='-')
                    number = "-0";
                else
                    number = number.substring(0, number.length() - 1);
                */
                if (previousCommand == '=' && command!='|') {
                    number = 0;
                    numberold = 0;
                    decimal = 0;
                    command = '|';
                }
                if (decimal != 0) {
                    decimal *= 0.1d;
                    String numar = String.valueOf(number);
                    numar = numar.substring(0, numar.length() - 1);
                    number = Double.parseDouble(numar);
                    if (number == (long) number)
                        decimal = 0;
                } else {
                    number = (long) number / 10;
                }
                if (number == 0)
                    negative = 1;
                setText();
            }
        });

        NumberListener listener = new NumberListener(this);
        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btnMinus.setOnClickListener(listener);
        btnPlus.setOnClickListener(listener);
        btnDivide.setOnClickListener(listener);
        btnMultiply.setOnClickListener(listener);
        btnEqual.setOnClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_point) {
            if (previousCommand == '=' && command!='|') {
                number = 0;
                numberold = 0;
                decimal = 0;
                command = '|';
            }
            if (number == (long) number) {
                txtNumber.setText((long) number + ".");
                decimal = 1;
            }
        }
        return true;
    }

    void sendNumber(int numberr) {
        if (previousCommand == '=' && command!='|') {
            number = 0;
            numberold = 0;
            decimal = 0;
            command = '|';
        }
        /*
        if (this.number == "0")
            this.number = String.valueOf(number);
        else
            this.number += number;*/
        if (numberr == 0 && decimal == 0)
            numberr *= negative;
        if (decimal != 0 && decimal > 0.000001) {
            decimal *= 0.1d;
            number += negative * numberr * decimal;
            Log.d("deci", String.valueOf(decimal));
        } else {
            number = number * 10 + negative * numberr;
        }
        setText();
    }


    void sendCommand(char commandd) {
        if (commandd != '=' && commandd!= '|') {
            //
            if (number == 0 && command == '|') {
                if (commandd == '-') {
                    //negative
                    negative = -1;
                } else if (commandd == '+') {
                    //positive
                    negative = 1;
                }
            } else {
                numberold = number;
                number = 0;
                this.command = commandd;
                negative = 1;
                decimal = 0;
            }
        } else {
            //TODO apasa egal*/
            if (previousCommand == '=') {
                double aux = number;
                number = numberold;
                numberold = aux;
            }
            double aux = number;
            switch (command) {
                case '+':
                    numberold += number;
                    break;
                case '-':
                    numberold -= number;
                    break;
                case '*':
                    numberold *= number;
                    break;
                case '/':
                    numberold /= number;
            }
            number = numberold;
            numberold = aux;
            Log.d("numere", numberold + " old   new " + number);
            setText();
        }
        previousCommand = commandd;
    }

    void setText() {
        if (number == (long) number)
            txtNumber.setText(String.valueOf((long) number));
        else
            txtNumber.setText(format.format(number));
        if (decimal == 1)
            txtNumber.setText((long) number + ".");
    }
}
