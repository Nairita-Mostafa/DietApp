package com.example.dietapp;

public class RequestModel {
    private String request;

    public RequestModel(){

    }

    public RequestModel(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
