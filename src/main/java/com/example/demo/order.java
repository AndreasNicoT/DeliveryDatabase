package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class order {
    private final StringProperty idOrder;
    private final StringProperty tanggal;
    private final StringProperty idDelivery;
    private final StringProperty idWarehouse;
    public order()
    {
        idOrder = new SimpleStringProperty(this, "idOrder");
        tanggal = new SimpleStringProperty(this, "tanggal");
        idDelivery = new SimpleStringProperty(this, "idDelivery");
        idWarehouse = new SimpleStringProperty(this, "idWarehouse");
    }
    public StringProperty idOrderProperty() { return idOrder; }
    public String getidOrder() { return idOrder.get(); }
    public void setidOrder(String newId) { idOrder.set(newId); }
    public StringProperty tanggalProperty() { return tanggal; }
    public String gettanggal() { return tanggal.get(); }
    public void settanggal(String newNama) { tanggal.set(newNama); }
    public StringProperty idDeliveryProperty() { return idDelivery; }
    public String getidDelivery() { return idDelivery.get(); }
    public void setidDelivery(String newLokasi) { idDelivery.set(newLokasi); }
    public StringProperty idWarehouseProperty() { return idWarehouse; }
    public String getidWarehouse() { return idWarehouse.get(); }
    public void setidWarehouse(String newLokasi) { idWarehouse.set(newLokasi); }

}
