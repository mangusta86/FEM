package com.example.fem;

public class Material {
	private String Name="";
	double Density;
	double ElasticModulus;
	double Nu;
	double SigmaMax;
	double EpsilonMax;
	double AlphaT;
	
	public String getName() {
	    return(Name);
	  }	  
	  public void setName(String Name) {
	    this.Name=Name;
	  }
}
