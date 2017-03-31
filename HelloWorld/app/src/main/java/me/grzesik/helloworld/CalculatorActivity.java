package me.grzesik.helloworld;

import android.renderscript.RSInvalidStateException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum CalculatorState {
    WAITING_FOR_FIRST_NUMBER,
    WAITING_FOR_NEXT_OPERATOR,
    WAITING_FOR_OPERATOR,
    WAITING_FOR_SECOND_NUMBER,
    READY_TO_COMPUTE
}

public class CalculatorActivity extends AppCompatActivity {

    private StringBuilder secondNumber;
    private StringBuilder firstNumber;
    private CalculatorState state;
    private String operation;

    public CalculatorActivity() {
        this.firstNumber = new StringBuilder();
        this.secondNumber = new StringBuilder();
        this.state = CalculatorState.WAITING_FOR_FIRST_NUMBER;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleOne(View view) {
        this.addNumberPart("1");
    }

    public void handleTwo(View view) {
        this.addNumberPart("2");
    }

    public void handleThree(View view) {
        this.addNumberPart("3");
    }

    public void handleFour(View view) {
        this.addNumberPart("4");
    }

    public void handleFive(View view) {
        this.addNumberPart("5");
    }

    public void handleSix(View view) {
        this.addNumberPart("6");
    }

    public void handleSeven(View view) {
        this.addNumberPart("7");
    }

    public void handleEight(View view) {
        this.addNumberPart("8");
    }

    public void handleNine(View view) {
        this.addNumberPart("9");
    }

    public void handleZero(View view) {
        this.addNumberPart("0");
    }

    public void handleComma(View view) {
        this.addNumberPart(".");
    }

    public void handlePlus(View view) {
        this.addOperation("+");
    }

    public void handleMinus(View view) {
        this.addOperation("-");
    }

    public void handleDivide(View view) {
        this.addOperation("/");
    }

    public void handleTimes(View view) {
        this.addOperation("*");
    }


    public void handleClearPressed(View view) {
        clear();
    }

    public void handleGoPressed(View view) {
        this.computeResult();
    }

    public void handleParsePressed(View view) {
        EditText e = (EditText) findViewById(R.id.editText);

        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*)\\s*(\\*|\\+|-|\\/)\\s*(\\d+\\.?\\d*)");

        String s = e.getText().toString();
        Matcher matcher = pattern.matcher(s);

        if (matcher.matches()) {
            this.firstNumber = new StringBuilder(matcher.group(1));
            this.operation = matcher.group(2);
            this.secondNumber = new StringBuilder(matcher.group(3));

            this.state = CalculatorState.READY_TO_COMPUTE;
            this.computeResult();
        }

        e.setText("");
    }


    private void computeResult() {
        if (this.state == CalculatorState.READY_TO_COMPUTE) {
            Double firstNumber = Double.valueOf(this.firstNumber.toString());
            Double secondNumber = Double.valueOf(this.secondNumber.toString());
            Double result = 0.0;

            switch (this.operation) {
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
            }

            this.clear();
            this.setResultText(result.toString());
            this.firstNumber.append(result.toString());

            this.state = CalculatorState.WAITING_FOR_NEXT_OPERATOR;
        }
    }


    private void addOperation(String operation) {
        if (this.state == CalculatorState.WAITING_FOR_OPERATOR || this.state == CalculatorState.WAITING_FOR_NEXT_OPERATOR) {
            this.operation = operation;

            this.appendToResult(" ");
            this.appendToResult(operation);
            this.appendToResult(" ");

            this.state = CalculatorState.WAITING_FOR_SECOND_NUMBER;
        }
    }

    private void addNumberPart(String digitOrComma) {
        if (this.state == CalculatorState.WAITING_FOR_NEXT_OPERATOR) {
            this.clear();
        }

        if (this.state == CalculatorState.WAITING_FOR_FIRST_NUMBER) {
            this.firstNumber.append(digitOrComma);
            this.state = CalculatorState.WAITING_FOR_OPERATOR;

        } else if (this.state == CalculatorState.WAITING_FOR_OPERATOR) {
            this.firstNumber.append(digitOrComma);

        } else if (this.state == CalculatorState.WAITING_FOR_SECOND_NUMBER) {
            this.secondNumber.append(digitOrComma);
            this.state = CalculatorState.READY_TO_COMPUTE;

        } else if (this.state == CalculatorState.READY_TO_COMPUTE) {
            this.secondNumber.append(digitOrComma);
        } else {
            throw new RSInvalidStateException("Calculator should not be in this state");
        }

        this.appendToResult(digitOrComma);
    }

    private void clear() {
        setResultText("");
        EditText e = (EditText) findViewById(R.id.editText);
        e.setText("");
        this.operation = "";
        this.firstNumber.setLength(0);
        this.secondNumber.setLength(0);
        this.state = CalculatorState.WAITING_FOR_FIRST_NUMBER;
    }

    private void setResultText(String text) {
        TextView resultTextView = (TextView) findViewById(R.id.resultTextLabel);
        resultTextView.setText(text);
    }

    private void appendToResult(String text) {
        TextView resultTextView = (TextView) findViewById(R.id.resultTextLabel);
        resultTextView.append(text);
    }
}

