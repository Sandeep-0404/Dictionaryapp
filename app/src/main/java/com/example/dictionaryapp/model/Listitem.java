package com.example.dictionaryapp.model;

public class Listitem {

    private  String head;
    private  String desc;

    public Listitem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
