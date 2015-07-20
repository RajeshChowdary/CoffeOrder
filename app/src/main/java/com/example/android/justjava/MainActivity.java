package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Get user name
        EditText editText = (EditText) findViewById(R.id.Name_view);
        Editable nameEditable = editText.getText();
        String name = nameEditable.toString();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}