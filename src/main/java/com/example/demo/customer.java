package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class customer {
    private final StringProperty idCust;
    private final StringProperty namaCust;
    private final StringProperty alamat;
    private final StringProperty notelp;

    public customer()
    {
        idCust = new SimpleStringProperty(this, "idCust");
        namaCust = new SimpleStringProperty(this, "namaCust");
        alamat = new SimpleStringProperty(this, "alamat");
        notelp = new SimpleStringProperty(this, "notelp");

    }
    public StringProperty idProperty() { return idCust; }
    public String getId() { return idCust.get(); }
    public void setId(String newId) { idCust.set(newId); }
    public StringProperty namaProperty() { return namaCust; }
    public String getNama() { return namaCust.get(); }
    public void setNama(String newNama) { namaCust.set(newNama); }
    public StringProperty alamatProperty() { return alamat; }
    public String getAlamat() { return alamat.get(); }
    public void setAlamat(String newLokasi) { alamat.set(newLokasi); }
    public StringProperty notelpProperty() { return notelp; }
    public String getNotelp() { return notelp.get(); }
    public void setNotelp(String newLokasi) { notelp.set(newLokasi); }

}
