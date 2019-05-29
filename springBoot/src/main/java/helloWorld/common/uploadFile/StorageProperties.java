package helloWorld.common.uploadFile;


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

        this.location = location;
    }

}
