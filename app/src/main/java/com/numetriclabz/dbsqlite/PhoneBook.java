package com.numetriclabz.dbsqlite;

public class PhoneBook {

    int _id;
    String name, phoneNumber, type;


    public PhoneBook(){}

    public PhoneBook(int id, String name, String _phone_number, String type){
        this._id = id;
        this.name = name;
        this.phoneNumber = _phone_number;
        this.type = type;
    }

    public PhoneBook(String name, String _phone_number, String type){
        this.name = name;
        this.phoneNumber = _phone_number;
        this.type = type;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone_number){
        this.phoneNumber = phone_number;
    }
}
