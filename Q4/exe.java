package Q4;
import java.util.ArrayList;
import java.util.List;

public class exe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		
		list.add(new Student("luisa","Porras",27));
		list.add(new Student("carlos","Motta",23));
		list.add(new Student("xonia","Gonzalez",15));
		
		
		ageSortStrategy s1 = new ageSortStrategy();
		lastNameSortStrategy s2 = new lastNameSortStrategy();
		nameSortStrategy s3 = new nameSortStrategy();
		
		s1.sortStudents(list);
		s2.sortStudents(list);
		s3.sortStudents(list);
		
		for(Student s : list){
			System.out.println(s.getName());
		}
		
	}

}
