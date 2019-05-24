package com.didispace.model.Form;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ValidForm {
	    @NotNull
	    @Size(min=2, max=30)
	    private String name = "xiaoben";

	    @NotNull
	    @Min(18)
	    private Integer age = 10;

	    public String getName() {     //模板是通过 get+ '设置的模板信息 （th:field="*{name}，也就是这里的name）' 渲染数据  
	    	
	        return this.name;
	    }

	    public void setName(String name) {     //@Valid 是通过 set + '设置的模板信息' 保存上传的信息

	        this.name = name;
	    }

	    public Integer getAge() {

	        return age;
	    }

	    public void setAge(Integer age) {
	        this.age = age;
	    }

	    public String toString() {
	        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
	    }
}
