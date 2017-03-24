package me.grzesik.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

enum CalculatorOperation {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    NONE,
}

public class CalculatorActivity extends AppCompatActivity {

    private StringBuilder secondNumber;
    private StringBuilder firstNumber;
    private CalculatorOperation operation;
    private String previousResult;

    public CalculatorActivity() {
        this.firstNumber = new StringBuilder();
        this.secondNumber = new StringBuilder();
        this.previousResult = "";
        this.operation = CalculatorOperation.NONE;
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


    private void computeResult() {
        if(this.operation != CalculatorOperation.NONE) {
            String firstStringRepresentation = this.previousResult.length() == 0
                    ? this.firstNumber.toString()
                    : this.previousResult;

            Double firstNumber = Double.valueOf(firstStringRepresentation);
            Double secondNumber = Double.valueOf(this.secondNumber.toString());
            Double result = 0.0;

            switch (this.operation) {
                case MINUS:
                    result = firstNumber - secondNumber;
                    break;
                case PLUS:
                    result = firstNumber + secondNumber;
                    break;
                case MULTIPLY:
                    result = firstNumber * secondNumber;
                    break;
                case DIVIDE:
                    result = firstNumber / secondNumber;
                    break;
            }

            this.clear();
            this.setResultText(result.toString());
            this.previousResult = result.toString();
        }
    }


    private void addOperation(String operation) {
        if (this.operation != CalculatorOperation.NONE) {
            this.computeResult();
        }

        switch (operation) {
            case "+":
                this.operation = CalculatorOperation.PLUS;
                break;
            case "-":
                this.operation = CalculatorOperation.MINUS;
                break;
            case "/":
                this.operation = CalculatorOperation.DIVIDE;
                break;
            case "*":
                this.operation = CalculatorOperation.MULTIPLY;
                break;
        }

        this.appendToResult(" ");
        this.appendToResult(operation);
        this.appendToResult(" ");
    }


    private void addNumberPart(String numberPart) {
        if (this.operation == CalculatorOperation.NONE) {
            if (previousResult.length() != 0) {
                previousResult = "";
                this.setResultText("");
            }
            this.firstNumber.append(numberPart);
        } else {
            this.secondNumber.append(numberPart);
        }

        this.appendToResult(numberPart);
    }

    private void clear() {
        setResultText("");
        this.previousResult = "";
        this.firstNumber.setLength(0);
        this.secondNumber.setLength(0);
        this.operation = CalculatorOperation.NONE;
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

