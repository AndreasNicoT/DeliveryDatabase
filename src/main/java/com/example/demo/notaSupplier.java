package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class notaSupplier {
    private final StringProperty idSupplier;
    private final StringProperty idDelivery;
    private final StringProperty detail;
    public notaSupplier()
    {
        idSupplier = new SimpleStringProperty(this, "idDelivery");
        idDelivery = new SimpleStringProperty(this, "jenisDelivery");
        detail = new SimpleStringProperty(this, "detail");

    }
    public StringProperty idSupplierProperty() { return idSupplier; }
    public String getidSupplier() { return idSupplier.get(); }
    public void setidSupplier(String newId) { idSupplier.set(newId); }
    public StringProperty idDeliveryProperty() { return idDelivery; }
    public String getidDelivery() { return idDelivery.get(); }
    public void setidDelivery(String newidDelivery) { idDelivery.set(newidDelivery); }
    public StringProperty detailProperty() { return detail; }
    public String getdetail() { return detail.get(); }
    public void setdetail(String newdetail) { detail.set(newdetail); }

}
