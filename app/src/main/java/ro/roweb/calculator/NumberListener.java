package ro.roweb.calculator;


import android.view.View;

/**
 * Created by Dragos on 10/11/2017.
 */

public class NumberListener implements View.OnClickListener {

    MainActivity activity;

    public NumberListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                activity.sendNumber(0);
                break;
            case R.id.btn1:
                activity.sendNumber(1);
                break;
            case R.id.btn2:
                activity.sendNumber(2);
                break;
            case R.id.btn3:
                activity.sendNumber(3);
                break;
            case R.id.btn4:
                activity.sendNumber(4);
                break;
            case R.id.btn5:
                activity.sendNumber(5);
                break;
            case R.id.btn6:
                activity.sendNumber(6);
                break;
            case R.id.btn7:
                activity.sendNumber(7);
                break;
            case R.id.btn8:
                activity.sendNumber(8);
                break;
            case R.id.btn9:
                activity.sendNumber(9);
                break;
            case R.id.btnminus:
                activity.sendCommand('-');
                break;
            case R.id.btnplus:
                activity.sendCommand('+');
                break;
            case R.id.btndivide:
                activity.sendCommand('/');
                break;
            case R.id.btnmultiply:
                activity.sendCommand('*');
                break;
            case R.id.btnequal:
                activity.sendCommand('=');
                break;
        }
    }
}
