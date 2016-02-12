package Q5;

public class Rectangular extends GeometricFigure{
	private float height;
	private float width;
	
	public float getHeight(){
		return this.height;
	}
	
	public float getWidth(){
		return this.width;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	@Override
	public float calculateArea(){
		return height*width;
	}
	
	@Override
	public float calculatePerimeter(){
		return 2*height + 2*width;
	}
}
