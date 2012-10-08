package com.example.fem.cad;
import android.database.Cursor;


public class Element {
	private String NAME="";
	private String PROFILE="";
	private String MATERIAL="";
	private Double X1;
	private Double Y1;
	private Double Z1;
	private Double X2;
	private Double Y2;
	private Double Z2;
	
	public Element(Cursor c){
<<<<<<< HEAD
=======
		if (c.moveToFirst()){
>>>>>>> implemented new constructor from cursor to populate the classes... (and
		if (!c.isNull(1))
			this.setName(c.getString(1));
		if (!c.isNull(2))
			this.setProfile(c.getString(2));
		if (!c.isNull(3))
			this.setMaterial(c.getString(3));
		if (!c.isNull(4))
			this.setX1(c.getString(4));
		if (!c.isNull(5))
			this.setY1(c.getString(5));
		if (!c.isNull(6))
			this.setZ1(c.getString(6));
		if (!c.isNull(7))
			this.setX2(c.getString(7));
		if (!c.isNull(8))
			this.setY2(c.getString(8));
		if (!c.isNull(9))
			this.setZ2(c.getString(9));
<<<<<<< HEAD
		
=======
		}
>>>>>>> implemented new constructor from cursor to populate the classes... (and
	}
	
	public void setName(String name){
		this.NAME=name;
	}
	public String getName(){
		return this.NAME;
	}
	public void setProfile(String profile){
		this.PROFILE=profile;
	}
	public String getProfile(){
		return this.PROFILE;
	}
	public void setMaterial(String material){
		this.MATERIAL=material;
	}
	public String getMaterial(){
		return this.MATERIAL;
	}
	
	
	
	public void setX1(Double x1){
		this.X1=x1;
	}
	public void setX1(String x1){
		this.X1=Double.valueOf(x1);
	}
	
	public String getStringX1(){
		return String.valueOf(this.X1);
	}
	public Double getDoubleX1(){
		return this.X1;
	}
	
	public void setX2(Double x2){
		this.X2=x2;
	}
	public void setX2(String x2){
		this.X2=Double.valueOf(x2);
	}
	
	public String getStringX2(){
		return String.valueOf(this.X2);
	}
	public Double getDoubleX2(){
		return this.X2;
	}
	public void setY1(Double y1){
		this.Y1=y1;
	}
	public void setY1(String y1){
		this.Y1=Double.valueOf(y1);
	}
	
	public String getStringY1(){
		return String.valueOf(this.Y1);
	}
	public Double getDoubleY1(){
		return this.Y1;
	}
	
	public void setY2(Double y2){
		this.Y2=y2;
	}
	public void setY2(String y2){
		this.Y2=Double.valueOf(y2);
	}
	
	public String getStringY2(){
		return String.valueOf(this.Y2);
	}
	public Double getDoubleY2(){
		return this.Y2;
	}
	
	
	
	public void setZ1(Double z1){
		this.Z1=z1;
	}
	public void setZ1(String z1){
		this.Z1=Double.valueOf(z1);
	}
	
	public String getStringZ1(){
		return String.valueOf(this.Z1);
	}
	public Double getDoubleZ1(){
		return this.Z1;
	}
	
	public void setZ2(Double z2){
		this.Z2=z2;
	}
	public void setZ2(String z2){
		this.Z2=Double.valueOf(z2);
	}
	
	public String getStringZ2(){
		return String.valueOf(this.Z2);
	}
	public Double getDoubleZ2(){
		return this.Y2;
	}
	
	
}
