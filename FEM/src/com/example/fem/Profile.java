package com.example.fem;

public class Profile {
	private String Name="";
	private String Shape="";

	public String getName() {
	    return(Name);
	  }	  
	  public void setName(String Name) {
	    this.Name=Name;
	  }

	public String getShape() {
	    return(Shape);
	  }	  
	  public void setShape(String Shape) {
	    this.Shape=Shape;
	  }
	  
	  public class Circle extends Profile{
		  float radius;	
	  }
	  public class Rectangle extends Profile{
		  float height;
		  float base;
	  }
}

