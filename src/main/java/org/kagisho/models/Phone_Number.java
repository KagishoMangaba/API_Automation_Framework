package org.kagisho.models;

public class Phone_Number {

    private String phone_number;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    @Override
    public String toString() {
        return "Phone_Number{" +
                "phone_number='" + phone_number + '\'' +
                '}';
    }
}
