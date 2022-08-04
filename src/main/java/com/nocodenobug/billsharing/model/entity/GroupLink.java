package com.nocodenobug.billsharing.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class GroupLink{
    private String link;

    public GroupLink(){
    }

    @Override
    public String toString() {
        return  link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupLink groupLink = (GroupLink) o;
        return Objects.equals(link, groupLink.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}
