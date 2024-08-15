package com.example.calculater; // Package declaration

// Import necessary classes
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display; // TextView to display input and result
    private StringBuilder currentInput = new StringBuilder(); // StringBuilder to store current input
    private double firstValue = 0; // Variable to store the first value in the operation
    private double secondValue = 0; // Variable to store the second value in the operation
    private String operator = ""; // String to store the selected operator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call superclass onCreate method
        setContentView(R.layout.activity_main); // Set the layout resource for this activity

        display = findViewById(R.id.display); // Find and assign the TextView by its ID
    }

    public void onButtonClicked(View view) {
        Button button = (Button) view; // Inherit a Button
        String buttonText = button.getText().toString(); // Get the text of the clicked button

        switch (buttonText) { // Switch statement to handle different operation of button while clicking
            case "C":
                clear(); // Call the clear method to clear everything
                break;
            case "=":
                if (operator.isEmpty() || currentInput.length() == 0) { // If user press = without enter any operator or number, do nothing
                    return;
                }
                secondValue = Double.parseDouble(currentInput.toString()); // Parse the second value from string
                double result = performOperation(firstValue, secondValue, operator); // Perform the operation
                display.setText(String.valueOf(result)); // Display the result
                currentInput.setLength(0); // after display result clear the current input
                currentInput.append(result);// append the currentInput as result because user can continue another operation continuously for further calculation
                operator = ""; // Reset the operator other wise old operator active for new calculation
                break;
            case "+": // Cases for operators
            case "-":
            case "*":
            case "/":
                if (currentInput.length() == 0) {// length = 0 mean no input, If there's no input then return
                    return;
                }
                firstValue = Double.parseDouble(currentInput.toString());// Parse the first value
                operator = buttonText;  // Store the selected operator
                currentInput.setLength(0);    // Clear the current input for the next number
                break;
            case "%":// Case for percentage calculation
                if (currentInput.length() == 0) {// If there's no input then return
                    return;
                }
                firstValue = Double.parseDouble(currentInput.toString());// Parse the first value
                operator = buttonText;// Store the percentage operator
                currentInput.setLength(0); // Clear the current input
                break;
            default: // Default case for number buttons and decimal point
                currentInput.append(buttonText); // Append the button text to the input
                display.setText(currentInput.toString());// Update the display
                break;
        }
    }

    private void clear() {// Clear all
        currentInput.setLength(0);// Clear the current input
        firstValue = 0;// Reset the first value
        secondValue = 0;// Reset the second value
        operator = "";// Reset the operator
        display.setText("0");// Reset the display to "0"
    }

    private double performOperation(double firstValue, double secondValue, String operator) {
        switch (operator) {// Switch statement to handle operations
            case "+":
                return firstValue + secondValue;// addition
            case "-":
                return firstValue - secondValue;//Subscription
            case "*":
                return firstValue * secondValue;//multiplication
            case "/"://error handling devised by 0 became infinity so if clause used to avoid it
                if (secondValue != 0) {
                    return firstValue / secondValue;
                } else {
                    return 0; // Handle division by zero
                }
            case "%":// Percentage calculation
                return firstValue * (secondValue / 100);
            default:
                return 0;// Return 0 for any other case
        }
    }
}
