package Q5;

public abstract class GeometricFigure {
	private String name;
	private String description;
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public abstract float calculateArea();
	public abstract float calculatePerimeter();
}
