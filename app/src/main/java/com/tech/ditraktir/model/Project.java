package com.tech.ditraktir.model;

import com.google.gson.annotations.SerializedName;

public class Project {

    @SerializedName("version")
    private Integer version;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private RetroPhoto data;




    public Project(Integer version,String status, RetroPhoto data) {
        this.version = version;
        this.status= status;
        this.data=data;
    }

}