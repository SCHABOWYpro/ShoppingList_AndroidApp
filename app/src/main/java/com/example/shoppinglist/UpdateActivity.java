package com.example.shoppinglist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText item_input, amount_input;
    Button update_button, delete_button;
    String id, item, amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_input = findViewById(R.id.item_input2);
        amount_input = findViewById(R.id.amount_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // First call this
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab!= null){
            ab.setTitle(item);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                item = item_input.getText().toString().trim();
                amount = amount_input.getText().toString().trim();
                myDB.updateData(id, item, amount);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        // Second call this
    }

    void getAndSetIntentData (){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("item") && getIntent().hasExtra("amount")){
            // Getting data from Intent
            id = getIntent().getStringExtra("id");
            item = getIntent().getStringExtra("item");
            amount = getIntent().getStringExtra("amount");

            //Setting Intent Data
            item_input.setText(item);
            amount_input.setText(amount);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + item + " ?");
        builder.setMessage("Are you sure you want to delete " + item + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}