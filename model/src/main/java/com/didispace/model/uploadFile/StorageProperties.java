package com.didispace.model.uploadFile;


import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "E:\\upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
    	System.out.println("ok3333333333333333333");
        this.location = location;
    }

}
