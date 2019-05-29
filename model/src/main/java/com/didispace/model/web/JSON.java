package com.didispace.model.web;

public class JSON {
    
	private String a;
	private String b;
    public String c;
	
	public JSON(String a,String b,String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	public String getA() {
		return this.a;
	}
	
	public void setB(String b) {
		this.b = b;
	}
	
	public String getB() {
		return b;
	}
	

	
	 @Override
	    public String toString() {
	        return "Book{" + "isbna='" + a + '\'' + ", titlew='" + b + '\'' +  '}';
	    }
	
}
