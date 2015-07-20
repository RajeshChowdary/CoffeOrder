package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText editText = (EditText) findViewById(R.id.Name_view);
        String value = editText.getText().toString();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, value));
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the totalprice
     */
    private int calculatePrice(boolean whippedcream, boolean chocolate)
    {
        // price for 1 cup of coffe
        int baseprice = 5;

        // add $1 if user wants whipped cream
        if(whippedcream)
        {
            baseprice += 1;
        }
        // add $2 if user wants whipped cream
        if (chocolate)
        {
            baseprice += 2;
        }
        // calculate the total price
        return quantity * baseprice;
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name)
    {
        String priceMessage = "Name: "+ name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity +"\nTotal: $"+price + "\nThank you!";
        return priceMessage;
    }
    // this methos is used for when + button pressed
    public void increment(View view) {
        if (quantity == 100)
        {
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }
    // this methos is used for when - button pressed
    public void  decrement (View view) {
        if (quantity == 1)
        {
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
        */
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}