package com.example.myshoppingreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.myshoppingreminder.EXTRA_NAME";
    public static final String EXTRA_DETAIL = "com.example.myshoppingreminder.EXTRA_DETAIL";
    public static final String EXTRA_PRIORITY = "com.example.myshoppingreminder.EXTRA_PRIORITY";
    public static final String EXTRA_EMAIL = "com.example.myshoppingreminder.EXTRA_EMAIL";

    private EditText editTextName;
    private EditText editTextDetail;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDetail = findViewById(R.id.edit_text_detail);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Product");
    }

    private void saveProduct() {
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String name = editTextName.getText().toString();
        String detail = editTextDetail.getText().toString();
        int priority = numberPickerPriority.getValue();
        if (name.trim().isEmpty() || detail.trim().isEmpty()) {
            Toast.makeText(this, "Please insert name and detail", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DETAIL, detail);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_EMAIL, email);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_product:
                saveProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
