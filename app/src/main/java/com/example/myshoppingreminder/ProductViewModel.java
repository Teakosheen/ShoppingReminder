package com.example.myshoppingreminder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
//to use context inside ViewModel we use AndroidViewModel because it contains the application context (to retrieve the context call)
public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    private LiveData<List<Product>> shoppingList;
    private LiveData<List<Product>> shoppingListByEmail;


    public ProductViewModel(@NonNull Application application, String email) {
        super(application);
        productRepository = new ProductRepository(application, email);
        shoppingList = productRepository.getShoppingList();
        shoppingListByEmail = productRepository.getShoppingListByEmail(email);
    }
    public void insert(Product product) {
        productRepository.insert(product);
    }
    public void update(Product product) {
        productRepository.update(product);
    }
    public void delete(Product product) {
        productRepository.delete(product);
    }
        public void deleteShoppingList() {
        productRepository.deleteShoppingList();
    }
    public void deleteShoppingListByEmail(String email) {
        productRepository.deleteShoppingListByEmail(email);
    }
    public LiveData<List<Product>> getShoppingList() {
        return shoppingList;
    }
    public LiveData<List<Product>> getShoppingListByEmail(String email) {
        return shoppingListByEmail;
    }
}
