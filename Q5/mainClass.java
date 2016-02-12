package Q5;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c = new Circle();
		Square s = new Square();
		Rectangular r = new Rectangular();
		
		c.setName("Circulito");
		c.setRatio(4);
		c.setPi();
		
		s.setEdge(3);
		
		r.setHeight(5);
		r.setWidth(3);
		
		System.out.println(c.calculateArea());
		System.out.println(c.calculatePerimeter());
		
		System.out.println(s.calculateArea());
		System.out.println(s.calculatePerimeter());
		
		System.out.println(r.calculateArea());
		System.out.println(r.calculatePerimeter());
		
		System.out.println("Overload Circle method:");
		System.out.println(c.calculateArea(3.14));
	}
}
