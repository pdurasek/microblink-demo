package com.pdurasek.demo.model.enums;

public enum ContactType {
    phone("Phone"),
    mobile("Mobile"),
    email("Email");

    public final String type;

    ContactType(String type) {
        this.type = type;
    }
}
