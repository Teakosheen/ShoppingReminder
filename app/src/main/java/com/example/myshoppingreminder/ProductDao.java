package com.example.myshoppingreminder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

    @Query("DELETE FROM product_table")
    void deleteShoppingList();

    @Query("DELETE FROM product_table WHERE email=:email ")
    void deleteShoppingListByEmail(String email);

    @Query("SELECT * FROM product_table ORDER BY priority DESC ")
    LiveData<List<Product>> getShoppingList();

    @Query("SELECT * FROM product_table WHERE email=:email ORDER BY priority DESC ")
    LiveData<List<Product>> getShoppingListByEmail(String email);

}
