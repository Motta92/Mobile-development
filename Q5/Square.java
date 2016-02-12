package Q5;

public class Square extends GeometricFigure{
	private float edge;
	
	public float getEdge(){
		return this.edge;
	}
	
	public void setEdge(float edge){
		this.edge = edge;
	}
	
	@Override
	public float calculateArea(){
		return (float) Math.pow(edge, 2);
	}
	
	@Override
	public float calculatePerimeter(){
		return 4*edge;
	}
}
