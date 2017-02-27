/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static com.example.android.justjava.R.string.price;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    String strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText inputName = (EditText) findViewById(R.id.name_input);
        strName = inputName.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(strName, price, hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order.
     *
     * @return price total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        //base price for a cup of coffee
        int basePrice = 5;

        //if use wants whipped cream add $1
        if (hasWhippedCream) {
            basePrice = basePrice + 1;

            //if use wants chocolate add $2
        }
        if (hasChocolate) {
            basePrice = basePrice + 2;
        }

        //return total price for all cups of coffee
        return quantity * basePrice;
        }


            /**
             * This method creates the order summary.
             *
             * @param strName is the name the user input in the name field
             * @param price of the order
             * @param addWhippedCream to determine if user wants whipped cream
             * @param addChocolate to determine if user wants whipped cream
             * @return order summary
             */
    private String createOrderSummary(String strName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + strName;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {

        if (quantity < 100) {
            quantity++;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Sorry! No more than 100 cups of coffee.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
    }
    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You must order at least 1 cup of coffee.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}