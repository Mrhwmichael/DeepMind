package com.example.deepmind.data;

public class DataItem {
    private String name;
    private int imageId;

    public DataItem(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getImageId(){
        return imageId;
    }

    public void setImageId(){
        this.imageId = imageId;
    }

}
