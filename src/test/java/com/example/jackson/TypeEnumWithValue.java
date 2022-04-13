package com.example.jackson;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");

	
    public Integer id;
    private String name;

    // standard constructors
    TypeEnumWithValue(int p, String str) { id = p; name = str; }

    // With this you specify that you only should serialize based on the fields 
    // marked with this annotation.
    @JsonValue
    public String getName() {
        return name;
    }
    
    // Note the documentation. You can use a single annotation of such kind.
    // Based on the result of this, you will serialize your object. 
    //    @JsonValue
	//    public Integer getId() {
	//        return id;
	//    }
    
}