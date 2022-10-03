package com.if5a.peta;

import com.google.android.gms.maps.model.LatLng;

public class lokasi {
    private String nama;
    private LatLng latLng;


    public lokasi() {
    }

    public lokasi(String nama, LatLng latlng) {
        this.nama = nama;
        this.latLng = latlng;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
