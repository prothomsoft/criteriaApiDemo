package com.lumesse.tutorial.dto;

public class SimpleEntityDto {

    private final Long id;
    private final String name;

    public SimpleEntityDto(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
