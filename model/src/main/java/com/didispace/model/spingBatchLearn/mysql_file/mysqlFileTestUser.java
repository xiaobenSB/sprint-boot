package com.didispace.model.spingBatchLearn.mysql_file;



public class mysqlFileTestUser {
	private Integer id;
	private String lastName;
	private String firstName;
	
	public mysqlFileTestUser(){
		System.out.println("任务2之正在写入数据到mysqlFileTestUser类里");
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
