package Q5;

public class Circle extends GeometricFigure{
	private double ratio;
	private double pi;
	
	public double getRatio(){
		return this.ratio;
	}
	
	public double getPi(){
		return this.pi;
	}
	
	public void setRatio(double ratio){
		this.ratio = ratio;
	}
	
	public void setPi(){
		this.pi = Math.PI;
	}
	
	@Override
	public float calculateArea(){
		return (float) (pi*(Math.pow(ratio, 2)));
	}
	
	public float calculateArea(double PI){
		return (float) (PI*(Math.pow(ratio, 2)));
	}
	
	@Override
	public float calculatePerimeter(){
		return (float) (2*pi*ratio);
	}
}
