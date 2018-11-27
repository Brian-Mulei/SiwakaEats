package com.example.brioz.foodapp.Model;

import java.util.List;

public class Request {

    private String phone;
    private String name;
    private String total;
    private List<order> foods;
    private String status;

    public Request() {
    }

    public Request(String phone, String name, String total, List<order> foods) {
        this.phone = phone;
        this.name = name;
        this.total = total;
        this.foods = foods;
        this.status="0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<order> getFoods() {
        return foods;
    }

    public void setFoods(List<order> foods) {
        this.foods = foods;
    }
}
