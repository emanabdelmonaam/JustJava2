/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity ==100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity + 1;
        displayquantity(quantity);
    }

    public void decrement(View view) {
        if (quantity ==1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity - 1;
        displayquantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        //to write the name
        EditText nameField =(EditText) findViewById(R.id. name_filed);
        String name =nameField.getText().toString();

        // to write the checkBox
        CheckBox whippedCreamCheckBox =(CheckBox)findViewById(R.id.whipped_cream_cheakbox) ;
        boolean haswhippdCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox) ;
        boolean haschocolateBox = chocolateCheckBox.isChecked();
        //  TO SHOW WHAT I WANT IN  LOGCAT  WRITE : Log.v("MainActivity","hhhhhhhhhhj : " +  haswhippdCream);

        int price = calculatePrice(haswhippdCream , haschocolateBox);
        //String PriceMessage ="Price $ "+ (quantity*5);
        String PriceMessage = createOrderSummery(name, price, haswhippdCream, haschocolateBox ) ;


        Intent intent = new Intent (Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));// this for only email app
        intent.putExtra(Intent. EXTRA_SUBJECT, "Just Java Order FOR" + name);
        intent.putExtra(Intent.EXTRA_TEXT, PriceMessage);
        if (intent.resolveActivity(getPackageManager())!=null);{
            startActivity(intent);
        }




    }


    /**
     * Calculates the price of the order.
     *
     */


    private int calculatePrice( boolean addWheppedCream, boolean addchocolate) {
        int basePrice = 5;

        if (addWheppedCream){
            basePrice = basePrice+ 1 ;    }

        if (addchocolate){
            basePrice =basePrice + 2; }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    // private void displayPrice(int number) {
    //   TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    // priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));    }

    private String createOrderSummery (String name, int price, boolean addwhippedCream, boolean addchocoleteBox){

        String PriceMessage= "Name : " + name;
        PriceMessage = PriceMessage + "\nAddWhippedCream? " + addwhippedCream;
        PriceMessage = PriceMessage + "\naddchocoleteBox? " + addchocoleteBox;
        PriceMessage = PriceMessage +"\nQuantity : " + quantity;
        PriceMessage =PriceMessage+ "\nTotal $" + price ;
        PriceMessage += "\n" + getString(R.string. thank_you);

        // PriceMessage = PriceMessage +"\nThank you !";
        return PriceMessage ;

    }

    /**
     * This method displays the given text on the screen.
     */
}