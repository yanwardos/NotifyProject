package com.example.notify.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ItemNotifikasi {
    private String judul;
    private LocalDateTime waktuRilisNotifikasi;
    private String organizationId;

    public ItemNotifikasi(String judul){
        this.waktuRilisNotifikasi = LocalDateTime.now();
        this.judul = judul;
    }

    public String getJudul(){
        return this.judul;
    }

    public String getWaktu(){
        DateTimeFormatter formatWaktu = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
        return formatWaktu.toString();
    }

    public String getOrganizationId(){
        return  this.organizationId;
    }
}
