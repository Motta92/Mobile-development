package Q4;
import java.util.List;

public class sorter {
	private IComparisonStrategy strategy;
	
	sorter(IComparisonStrategy strategy){
		this.strategy = strategy;
	}
	public void setStrategy(IComparisonStrategy strategy){
		this.strategy = strategy;
	}
	
	public IComparisonStrategy getStrategy(){
		return this.strategy;
	}
	
	public void applyStrategy(IComparisonStrategy strategy, List<Student> list){
		strategy.sortStudents(list);
	}
}
