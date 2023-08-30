package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class delivery {
    private final StringProperty idDelivery;
    private final StringProperty jenisDelivery;
    private final StringProperty idTransportasi;
    public delivery()
    {
        idDelivery = new SimpleStringProperty(this, "idDelivery");
        jenisDelivery = new SimpleStringProperty(this, "jenisDelivery");
        idTransportasi = new SimpleStringProperty(this, "idTransportasi");

    }
    public StringProperty idProperty() { return idDelivery; }
    public String getId() { return idDelivery.get(); }
    public void setId(String newId) { idDelivery.set(newId); }
    public StringProperty jenisDeliveryProperty() { return jenisDelivery; }
    public String getjenisDelivery() { return jenisDelivery.get(); }
    public void setjenisDelivery(String newjenisDelivery) { jenisDelivery.set(newjenisDelivery); }
    public StringProperty idTransportasiProperty() { return idTransportasi; }
    public String getidTransportasi() { return idTransportasi.get(); }
    public void setidTransportasi(String newidTransportasi) { idTransportasi.set(newidTransportasi); }

}
