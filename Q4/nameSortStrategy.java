package Q4;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class nameSortStrategy implements IComparisonStrategy, Comparator<Student>{
	public int compare(Student s1, Student s2){
		return s1.getName().compareTo(s2.getName());
	}
	
	@Override
	public void sortStudents(List<Student> studentList){
		Collections.sort(studentList, new nameSortStrategy());
	};
}