package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class transportasi {
    private final StringProperty id_transportasi ;
    private final StringProperty nomor_lisensi;
    private final StringProperty jenis_transportasi;
    private final StringProperty status_keberangkatan;
    private final StringProperty id_branch;
    public transportasi()
    {
        id_transportasi = new SimpleStringProperty(this, "id_transportasi");
        nomor_lisensi = new SimpleStringProperty(this, "nomor_lisensi");
        jenis_transportasi = new SimpleStringProperty(this, "jenis_transportasi");
        status_keberangkatan = new SimpleStringProperty(this, "status_keberangkatan");
        id_branch = new SimpleStringProperty(this, "id_branch");
    }
    public StringProperty id_transportasiProperty() { return id_transportasi; }
    public String getid_transportasi() { return id_transportasi.get(); }
    public void setid_transportasi(String newId) { id_transportasi.set(newId); }
    public StringProperty nomor_lisensiProperty() { return nomor_lisensi; }
    public String getnomor_lisensi() { return nomor_lisensi.get(); }
    public void setnomor_lisensi(String newNama) { nomor_lisensi.set(newNama); }
    public StringProperty jenis_transportasiProperty() { return jenis_transportasi; }
    public String getjenis_transportasit() { return jenis_transportasi.get(); }
    public void setjenis_transportasi(String newLokasi) { jenis_transportasi.set(newLokasi); }
    public StringProperty status_keberangkatanProperty() { return status_keberangkatan; }
    public String getstatus_keberangkatan() { return status_keberangkatan.get(); }
    public void setstatus_keberangkatan(String newLokasi) { status_keberangkatan.set(newLokasi); }
    public StringProperty id_branchProperty() { return id_branch; }
    public String getid_branch() { return id_branch.get(); }
    public void setId_branch(String newLokasi) { id_branch.set(newLokasi); }

}
