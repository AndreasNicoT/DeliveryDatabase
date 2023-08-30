package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class warehouse {
    private final StringProperty idWh;
    private final StringProperty namaWh;
    private final StringProperty lokasi;
    public warehouse()
    {
        idWh = new SimpleStringProperty(this, "idBranch");
        namaWh = new SimpleStringProperty(this, "namaBranch");
        lokasi = new SimpleStringProperty(this, "lokasi");

    }
    public StringProperty idProperty() { return idWh; }
    public String getId() { return idWh.get(); }
    public void setId(String newId) { idWh.set(newId); }
    public StringProperty namaProperty() { return namaWh; }
    public String getNama() { return namaWh.get(); }
    public void setNama(String newNama) { namaWh.set(newNama); }
    public StringProperty lokasiProperty() { return lokasi; }
    public String getLokasi() { return lokasi.get(); }
    public void setLokasi(String newLokasi) { lokasi.set(newLokasi); }

}
