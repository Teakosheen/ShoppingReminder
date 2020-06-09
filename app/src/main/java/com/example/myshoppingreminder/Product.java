package com.example.myshoppingreminder;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "product_table")
public class Product {
    @PrimaryKey (autoGenerate= true)
    private Integer _id;
    private String Name;
    private String detail;
    private int priority;
    private String email;

    public Product(String Name, String detail, int priority, String email) {
        this.Name = Name;
        this.detail = detail;
        this.priority = priority;
        this.email = email;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public Integer getId() {
        return _id;
    }

    public String getName() {
        return Name;
    }

    public String getDetail() {
        return detail;
    }

    public int getPriority() {
        return priority;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
