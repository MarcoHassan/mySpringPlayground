package com.example.jackson;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MyBean {
    public int id;
    public String name;
    public String helloworld;
    
//    @JsonGetter("name")
//    public String getTheName() {
//        return name;
//    }
}