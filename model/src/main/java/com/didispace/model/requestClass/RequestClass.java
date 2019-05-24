package com.didispace.model.requestClass;

public class RequestClass {
   
	private String type;
	private Value value;
	
	public RequestClass() {
		
	}
	
	public String getType() {
	    return type;	
	}
	
	public void setType(String type) { //这个好像是spring boot会解析返回数据 并执行传入类里的set + key 方法，并传递key对应的值
		System.out.println(type);
		this.type = type;             //这样我们就可以保存他传递过来的值了
	}
	
	public Value getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "RequestClass{" +
                "type='" + type + '\'' +
                "typesss='" + type + '\'' +
                ", value=" + value +
                '}';
	}	
}
