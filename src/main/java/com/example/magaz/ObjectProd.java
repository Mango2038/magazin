package com.example.magaz;

import javafx.scene.image.Image;

import java.sql.Blob;

public class ObjectProd {
    int ID;
    String name;
    int kolVo;
    double prise;
    Blob image;
    String KratkoeOpisanie;
    String vid;
    String Sklad;
    String Suppliers;


    public ObjectProd(int ID, String name, int kolVo, double prise, Blob image , String KratkoeOpisanie, String vid,String Sklad, String Suppliers){
        this.ID = ID;
        this.name = name;
        this.kolVo = kolVo;
        this.prise = prise;
        this.image = image;
        this.KratkoeOpisanie = KratkoeOpisanie;
        this.vid = vid;
        this.Sklad = Sklad;
        this.Suppliers = Suppliers;
    }
}
