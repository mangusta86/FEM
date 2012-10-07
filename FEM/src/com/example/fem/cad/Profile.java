package com.example.fem.cad;

public class Profile {

	private String Name = "";

	private String Shape = "";
	
	private String Jx = "";
	
	private String Jy = "";
	
	private String A = "";

	public String getName() {
		return (Name);
	}

	public void setName(String Name) {
		this.Name = Name;
	}


	public String getShape() {
		return (Shape);
	}

	public void setShape(String Shape) {
		this.Shape = Shape;
	}

	public String getJx() {
		return (Jx);
	}

	public void setJx(String Jx) {
		this.Jx = Jx;
	}

	public String getJy() {
		return (Jy);
	}

	public void setJy(String Jy) {
		this.Jy = Jy;
	}

	public String getA() {
		return (A);
	}

	public void setA(String A) {
		this.A = A;
	}
	public boolean isProRectangle(){
		if (this.Shape.equalsIgnoreCase("rectangle"))
			return true;
		else return false;		
	}
	public boolean isProCircle(){
		if (this.Shape.equalsIgnoreCase("circle"))
			return true;
		else return false;		
	}
	public boolean isProCShape(){
		if (this.Shape.equalsIgnoreCase("cshape"))
			return true;
		else return false;		
	}
}