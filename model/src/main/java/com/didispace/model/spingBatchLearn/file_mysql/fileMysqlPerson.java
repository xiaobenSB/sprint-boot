package com.didispace.model.spingBatchLearn.file_mysql;

public class fileMysqlPerson {
	    private String lastName;
	    private String firstName;
        
	    public fileMysqlPerson() {
	    	//System.out.println("这个无参构造函数必须要");
	    	System.out.println("任务1之正在写入数据到fileMysqlPerson类里");
	    }

	    public fileMysqlPerson(String firstName, String lastName) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLaNa(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public void setName(String lastName) {
	        this.lastName = lastName;
	    }

	    @Override
	    public String toString() {
	        return "firstName: " + firstName + ", lastName: " + lastName;
	    }
}
