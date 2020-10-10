package com.example.fake_online_shop.entity;


import javax.validation.constraints.NotBlank;

public class Search {

    @NotBlank
    private String searchByName;
    private String type;
    private String sort;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
