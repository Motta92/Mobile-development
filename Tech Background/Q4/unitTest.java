package Q4;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class unitTest {
	@Test
	public void testAgeStrategyOrdering(){
		List<Student> test = new ArrayList<Student>();
		sorter sort = new sorter(new ageSortStrategy());
		
		test.add(new Student("jesus","lozano",14));
		test.add(new Student("luis","alberto",11));
		test.add(new Student("maria","dias",22));
		test.add(new Student("carlos","motta",17));
		
		sort.applyStrategy(sort.getStrategy(), test);
		// Tests
		assertEquals(11,test.get(0).getAge());
		assertEquals(14,test.get(1).getAge());
		assertEquals(17,test.get(2).getAge());
		assertEquals(22,test.get(3).getAge());
	}
	
	@Test
	public void testNameStrategyOrdering(){
		List<Student> test = new ArrayList<Student>();
		sorter sort = new sorter(new nameSortStrategy());
		
		test.add(new Student("jesus","lozano",14));
		test.add(new Student("luis","alberto",11));
		test.add(new Student("maria","dias",22));
		test.add(new Student("carlos","motta",17));
		
		sort.applyStrategy(sort.getStrategy(), test);
		// Tests
		assertEquals("carlos",test.get(0).getName());
		assertEquals("jesus",test.get(1).getName());
		assertEquals("luis",test.get(2).getName());
		assertEquals("maria",test.get(3).getName());
	}
	
	@Test
	public void testLastNameStrategyOrdering(){
		List<Student> test = new ArrayList<Student>();
		sorter sort = new sorter(new lastNameSortStrategy());
		
		test.add(new Student("jesus","lozano",14));
		test.add(new Student("luis","alberto",11));
		test.add(new Student("maria","dias",22));
		test.add(new Student("carlos","motta",17));
		
		sort.applyStrategy(sort.getStrategy(), test);
		// Tests
		assertEquals("alberto",test.get(0).getLastName());
		assertEquals("dias",test.get(1).getLastName());
		assertEquals("lozano",test.get(2).getLastName());
		assertEquals("motta",test.get(3).getLastName());
	}
	

}
