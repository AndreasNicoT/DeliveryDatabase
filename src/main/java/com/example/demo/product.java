package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class product {
    private final StringProperty idProduct;
    private final StringProperty namaProduct;
    private final StringProperty jenisProduct;
    private final StringProperty hargaProduct;
    public product()
    {
        idProduct = new SimpleStringProperty(this, "idProduct");
        namaProduct = new SimpleStringProperty(this, "namaProduct");
        jenisProduct = new SimpleStringProperty(this, "jenisProduct");
        hargaProduct = new SimpleStringProperty(this, "hargaProduct");
    }
    public StringProperty idProductProperty() { return idProduct; }
    public String getidProduct() { return idProduct.get(); }
    public void setidProduct(String newId) { idProduct.set(newId); }
    public StringProperty namaProductProperty() { return namaProduct; }
    public String getnamaProduct() { return namaProduct.get(); }
    public void setnamaProduct(String newNama) { namaProduct.set(newNama); }
    public StringProperty jenisProductProperty() { return jenisProduct; }
    public String getjenisProduct() { return jenisProduct.get(); }
    public void setjenisProduct(String newLokasi) { jenisProduct.set(newLokasi); }
    public StringProperty hargaProductProperty() { return hargaProduct; }
    public String gethargaProduct() { return hargaProduct.get(); }
    public void sethargaProduct(String newLokasi) { hargaProduct.set(newLokasi); }

}
