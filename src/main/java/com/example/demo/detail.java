package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class detail {
    private final StringProperty idProduct;
    private final StringProperty idOrder;
    private final StringProperty namaProduct;
    private final StringProperty jumlah;
    public detail()
    {
        idProduct = new SimpleStringProperty(this, "idCust");
        idOrder = new SimpleStringProperty(this, "namaCust");
        namaProduct = new SimpleStringProperty(this, "alamat");
        jumlah = new SimpleStringProperty(this, "notelp");
    }
    public StringProperty getIdProductProperty() { return idProduct; }
    public String getidProduct() { return idProduct.get(); }
    public void setidProduct(String newidProduct) { idProduct.set(newidProduct); }
    public StringProperty getIdOrderProperty() { return idOrder; }
    public String getidOrder() { return idOrder.get(); }
    public void setidOrder(String newidOrder) { idOrder.set(newidOrder); }
    public StringProperty namaProductProperty() { return namaProduct; }
    public String getnamaProduct() { return namaProduct.get(); }
    public void setnamaProduct(String newnamaProduct) { namaProduct.set(newnamaProduct); }
    public StringProperty jumlahProperty() { return jumlah; }
    public String getjumlah() { return jumlah.get(); }
    public void setjumlah(String newjumlah) { jumlah.set(newjumlah); }

}
