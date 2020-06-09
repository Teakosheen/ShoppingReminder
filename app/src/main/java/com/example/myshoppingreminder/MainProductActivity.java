package com.example.myshoppingreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainProductActivity extends AppCompatActivity {

    private static final int ADD_PRODUCT_REQUEST = 1;
    private  ProductViewModel productViewModel;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Product");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //Product dbProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FloatingActionButton buttonAddProduct = findViewById(R.id.button_add_product);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProductActivity.this, AddProductActivity.class);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST);
            }

        });

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ProductAdapter productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);

        try {
            productViewModel = new ProductViewModel(getApplication(), user.getEmail());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        productViewModel.getShoppingListByEmail(user.getEmail()).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productAdapter.setProducts(products);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Toast.makeText(MainProductActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
                //return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Product product = productAdapter.getProductAt(viewHolder.getAdapterPosition());
                productViewModel.delete(product);
                Query applesQuery = dbref.orderByChild("name").equalTo(product.getName());
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("onCancelled", String.valueOf(databaseError.toException()));
                    }
                });
                Toast.makeText(MainProductActivity.this, "Product deleted", Toast.LENGTH_SHORT).show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String email = data.getStringExtra(AddProductActivity.EXTRA_EMAIL);
            String name = data.getStringExtra(AddProductActivity.EXTRA_NAME);
            String detail = data.getStringExtra(AddProductActivity.EXTRA_DETAIL);
            int priority = data.getIntExtra(AddProductActivity.EXTRA_PRIORITY, 1);
            Product product = new Product(name, detail, priority, email);
            productViewModel.insert(product);
            try {
                //dbProduct = new Product();
                //dbProduct.setId(product.getId());
                //dbProduct.setName(name);
                //dbProduct.setDetail(detail);
                //dbProduct.setPriority(priority);
                //dbProduct.setEmail(email);
                dbref = FirebaseDatabase.getInstance().getReference().child("Product");
                dbref.push().setValue(product);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_products:
                productViewModel.deleteShoppingListByEmail(user.getEmail());
                Query applesQuery = dbref.orderByChild("email").equalTo(user.getEmail());
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("onCancelled", String.valueOf(databaseError.toException()));
                    }
                });
                Toast.makeText(this, "The Shopping List is deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
