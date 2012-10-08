package com.example.fem.cad;

import android.database.Cursor;

public class Material {
	
	private String NAME = "";

	private Double YOUNG;
	
	private Double POISSON;
	
	private Double DENSITY;
	

	public Material(Cursor c){
		if (c.moveToFirst()){
		if (!c.isNull(1))
			this.setName(c.getString(1));
		if (!c.isNull(2))
			this.setYoung(c.getString(2));
		if (!c.isNull(3))
			this.setPoisson(c.getString(3));
		if (!c.isNull(4))
			this.setDensity(c.getString(4));
		}
	}
	
	public String getName() {
		return (NAME);
	}

	public void setName(String name) {
		this.NAME = name;
	}
	
	public void setYoung(Double young){
		this.YOUNG=young;
	}
	public void setYoung(String young){
		this.YOUNG=Double.valueOf(young);
	}
	
	public String getStringYoung(){
		return String.valueOf(this.YOUNG);
	}
	public Double getDoubleYoung(){
		return this.YOUNG;
	}
	
	
	public void setPoisson(Double poisson){
		this.POISSON=poisson;
	}
	public void setPoisson(String poisson){
		this.POISSON=Double.valueOf(poisson);
	}
	
	public String getStringPoisson(){
		return String.valueOf(this.POISSON);
	}
	public Double getDoublePoisson(){
		return this.POISSON;
	}
	
	
	public void setDensity(Double density){
		this.DENSITY=density;
	}
	public void setDensity(String density){
		this.DENSITY=Double.valueOf(density);
	}
	
	public String getStringDensity(){
		return String.valueOf(this.DENSITY);
	}
	public Double getDoubleDensity(){
		return this.DENSITY;
	}
	
	
}
