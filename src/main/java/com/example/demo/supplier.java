package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class supplier {
    private final StringProperty id_supplier;
    private final StringProperty nama_supplier;
    private final StringProperty lokasi;
    public supplier()
    {
        id_supplier = new SimpleStringProperty(this, "idCust");
        nama_supplier = new SimpleStringProperty(this, "idDelivery");
        lokasi = new SimpleStringProperty(this, "total");

    }
    public StringProperty id_supplierProperty() { return id_supplier; }
    public String getid_supplier() { return id_supplier.get(); }
    public void setid_supplier(String newId) { id_supplier.set(newId); }
    public StringProperty nama_supplierProperty() { return nama_supplier; }
    public String getnama_supplier() { return nama_supplier.get(); }
    public void setnama_supplier(String newidDelivery) { nama_supplier.set(newidDelivery); }
    public StringProperty lokasiProperty() { return lokasi; }
    public String getlokasi() { return lokasi.get(); }
    public void setlokasi(String newdetail) { lokasi.set(newdetail); }

}
