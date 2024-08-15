package com.example.calculater;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onButtonClicked(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "=":
                if (operator.isEmpty() || currentInput.length() == 0) {
                    return;
                }
                secondValue = Double.parseDouble(currentInput.toString());
                double result = performOperation(firstValue, secondValue, operator);
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
                currentInput.append(result);
                operator = "";
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (currentInput.length() == 0) {
                    return;
                }
                firstValue = Double.parseDouble(currentInput.toString());
                operator = buttonText;
                currentInput.setLength(0);
                break;
            case "%":
                if (currentInput.length() == 0) {
                    return;
                }
                firstValue = Double.parseDouble(currentInput.toString());
                operator = buttonText;
                currentInput.setLength(0);
                break;
            default: 
                currentInput.append(buttonText);
                display.setText(currentInput.toString());
                break;
        }
    }

    private void clear() {
        currentInput.setLength(0);
        firstValue = 0;
        secondValue = 0;
        operator = "";
        display.setText("0");
    }

    private double performOperation(double firstValue, double secondValue, String operator) {
        switch (operator) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            case "/":
                if (secondValue != 0) {
                    return firstValue / secondValue;
                } else {
                    return 0; // Handle division by zero
                }
            case "%":
                return firstValue * (secondValue / 100);
            default:
                return 0;
        }
    }
}
