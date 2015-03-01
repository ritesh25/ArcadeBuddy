package com.dquid.baytektestapp;

/**
 * Created by shobhitg on 2/28/2015.
 */
public class UserModel {

    private String name;
    private String avatarUrl;

    public UserModel(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
