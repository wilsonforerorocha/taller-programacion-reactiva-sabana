package org.example;

import javax.xml.namespace.QName;

public enum Channel {

    YOUTUBE("youtube", 1),FACEBOOK("facebook", 2),INSTAGRAM("instagram", 3),TIKTOK("tiktok", 4);

    Channel(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    private final String name;

    private final int order;



}
