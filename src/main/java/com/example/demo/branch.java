package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class branch {
    private final StringProperty idBranch;
    private final StringProperty namaBranch;
    private final StringProperty lokasi;
    public branch()
    {
        idBranch = new SimpleStringProperty(this, "idBranch");
        namaBranch = new SimpleStringProperty(this, "namaBranch");
        lokasi = new SimpleStringProperty(this, "lokasi");

    }
    public StringProperty idProperty() { return idBranch; }
    public String getId() { return idBranch.get(); }
    public void setId(String newId) { idBranch.set(newId); }
    public StringProperty namaProperty() { return namaBranch; }
    public String getNama() { return namaBranch.get(); }
    public void setNama(String newNama) { namaBranch.set(newNama); }
    public StringProperty lokasiProperty() { return lokasi; }
    public String getLokasi() { return lokasi.get(); }
    public void setLokasi(String newLokasi) { lokasi.set(newLokasi); }

}
