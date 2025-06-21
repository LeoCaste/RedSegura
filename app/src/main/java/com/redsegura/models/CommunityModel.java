package com.redsegura.models;

import java.util.List;

public class CommunityModel {
    private String id;
    private String name;
    private List<String> members;

    public CommunityModel() {}

    public CommunityModel(String id, String name, List<String> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<String> getMembers() { return members; }

    public void setMembers(List<String> members) { this.members = members; }
}
