package com.didispace.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(min=2, max=30)
    private String name = "xiaobenq";

    @NotNull
    @Min(18)
    private Integer age = 15;

    public String getName() {
    	System.out.println(1);
        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAge() {
    	System.out.println(3);
        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}