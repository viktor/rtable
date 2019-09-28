package com.viktor.rtable.dto;

import com.viktor.rtable.annotations.GridColumn;

public class PersonDetail {
    @GridColumn(width = 50, position = 0)
    private String id;
    @GridColumn(width = 200, position = 1)
    private String name;
    @GridColumn(width = 300, position = 2)
    private String address;

    public PersonDetail(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public PersonDetail() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
