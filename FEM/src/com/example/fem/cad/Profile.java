package com.example.fem.cad;
			
import android.database.Cursor;

public class Profile {

	private String NAME = "";
	private String SHAPE = "";
	private Double JP;
	private Double JX;
	private Double JY;
	private Double A;
	private Double VALUE1;
	private Double VALUE2;
	private Double VALUE3;
	private Double VALUE4;
	private Double VALUE5;

	public Profile(){
		
	}
	
	public Profile(Cursor c){
		if (c.moveToFirst()){
		if (!c.isNull(1))
			this.setName(c.getString(1));
		if (!c.isNull(2))
			this.setShape(c.getString(2));
		if (!c.isNull(3))
			this.setJp(c.getString(3));
		if (!c.isNull(4))
			this.setJx(c.getString(4));
		if (!c.isNull(5))
			this.setJy(c.getString(5));
		if (!c.isNull(6))
			this.setA(c.getString(6));
		if (!c.isNull(7))
			this.setValue1(c.getString(7));
		if (!c.isNull(8))
			this.setValue2(c.getString(8));
		if (!c.isNull(9))
			this.setValue3(c.getString(9));
		if (!c.isNull(10))
			this.setValue4(c.getString(10));
		if (!c.isNull(11))
			this.setValue5(c.getString(11));
		}
	}
	
	
	public String getName() {
		return (NAME);
	}

	public void setName(String name) {
		this.NAME = name;
	}
	
	public String getShape() {
		return (SHAPE);
	}

	public void setShape(String shape) {
		this.SHAPE = shape;
	}

	public void setJp(Double jp){
		this.JP=jp;
	}
	public void setJp(String jp){
		this.JP=Double.valueOf(jp);
	}
	
	public String getStringJp(){
		return String.valueOf(this.JP);
	}
	public Double getDoubleJp(){
		return this.JP;
	}
	
	public void setJx(Double jx){
		this.JX=jx;
	}
	public void setJx(String jx){
		this.JX=Double.valueOf(jx);
	}
	
	public String getStringJx(){
		return String.valueOf(this.JX);
	}
	public Double getDoubleJx(){
		return this.JX;
	}
	
	
	public void setJy(Double jy){
		this.JY=jy;
	}
	public void setJy(String jy){
		this.JY=Double.valueOf(jy);
	}
	
	public String getStringJy(){
		return String.valueOf(this.JY);
	}
	public Double getDoubleJy(){
		return this.JY;
	}
	
	
	public void setA(Double a){
		this.A=a;
	}
	public void setA(String a){
		this.A=Double.valueOf(a);
	}
	
	public String getStringA(){
		return String.valueOf(this.A);
	}
	public Double getDoubleA(){
		return this.A;
	}
	
	
	
	public void setValue1(Double value1){
		this.VALUE1=value1;
	}
	public void setValue1(String value1){
		this.VALUE1=Double.valueOf(value1);
	}
	
	public String getStringValue1(){
		return String.valueOf(this.VALUE1);
	}
	public Double getDoubleValue1(){
		return this.VALUE1;
	}
	
	public void setValue2(Double value2){
		this.VALUE2=value2;
	}
	public void setValue2(String value2){
		this.VALUE2=Double.valueOf(value2);
	}
	
	public String getStringValue2(){
		return String.valueOf(this.VALUE2);
	}
	public Double getDoubleValue2(){
		return this.VALUE2;
	}
	
	
	
	public void setValue3(Double value3){
		this.VALUE3=value3;
	}
	public void setValue3(String value3){
		this.VALUE3=Double.valueOf(value3);
	}
	
	public String getStringValue3(){
		return String.valueOf(this.VALUE3);
	}
	public Double getDoubleValue3(){
		return this.VALUE3;
	}
	
	public void setValue4(Double value4){
		this.VALUE4=value4;
	}
	public void setValue4(String value4){
		this.VALUE4=Double.valueOf(value4);
	}
	
	public String getStringValue4(){
		return String.valueOf(this.VALUE4);
	}
	public Double getDoubleValue4(){
		return this.VALUE4;
	}
	
	
	public void setValue5(Double value5){
		this.VALUE5=value5;
	}
	public void setValue5(String value5){
		this.VALUE5=Double.valueOf(value5);
	}
	
	public String getStringValue5(){
		return String.valueOf(this.VALUE5);
	}
	public Double getDoubleValue5(){
		return this.VALUE5;
	}
	
	
	public boolean isProRectangle(){
		if (this.SHAPE.equalsIgnoreCase("rectangle"))

			return true;
		else return false;		
	}
	public boolean isProCircle(){

		if (this.SHAPE.equalsIgnoreCase("circle"))

			return true;
		else return false;		
	}
	public boolean isProCShape(){

		if (this.SHAPE.equalsIgnoreCase("cshape"))
			return true;
		else return false;		
	}
}