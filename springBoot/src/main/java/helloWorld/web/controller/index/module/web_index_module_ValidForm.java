package helloWorld.web.controller.index.module;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class web_index_module_ValidForm {
	    @NotNull
	    @Size(min=2, max=30)
	    private String name = "xiaoben";

	    @NotNull
	    @Min(18)
	    private Integer age = 10;

	    public String getName() {  
	    	
	        return this.name;
	    }

	    public void setName(String name) {     

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
