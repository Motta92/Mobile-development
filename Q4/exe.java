package Q4;
import java.util.ArrayList;
import java.util.List;

public class exe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		
		list.add(new Student("luisa","motta",22));
		list.add(new Student("carlos","motta",23));
		list.add(new Student("laura","motta",15));
		
		
		ageSortStrategy s1 = new ageSortStrategy();
		
		s1.sortStudents(list);
		
		for(Student s : list){
			System.out.println(s.getAge());
		}
		
	}

}
