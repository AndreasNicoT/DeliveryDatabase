package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class resi {
    private final StringProperty idCust;
    private final StringProperty idDelivery;
    private final StringProperty total;
    public resi()
    {
        idCust = new SimpleStringProperty(this, "idCust");
        idDelivery = new SimpleStringProperty(this, "idDelivery");
        total = new SimpleStringProperty(this, "total");

    }
    public StringProperty idCustProperty() { return idCust; }
    public String getidCust() { return idCust.get(); }
    public void setidCust(String newId) { idCust.set(newId); }
    public StringProperty idDeliveryProperty() { return idDelivery; }
    public String getidDelivery() { return idDelivery.get(); }
    public void setidDelivery(String newidDelivery) { idDelivery.set(newidDelivery); }
    public StringProperty totalProperty() { return total; }
    public String gettotal() { return total.get(); }
    public void settotal(String newdetail) { total.set(newdetail); }

}
