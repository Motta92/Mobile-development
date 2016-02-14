package Q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.junit.Test;

public class testPerformance {
	/*************** ArrayLists ***************/
	@Test
	public void testArrayList10(){
		structures s = new structures(new ArrayList<Integer>());
		
		s.insertList(s.getArrayList(),10);
		s.getList(s.getArrayList(), 10);
		s.removeList(s.getArrayList(), 10);
	}
	
	@Test
	public void testArrayList100(){
		structures s = new structures(new ArrayList<Integer>());
		
		s.insertList(s.getArrayList(),100);
		s.getList(s.getArrayList(), 100);
		s.removeList(s.getArrayList(), 100);
	}
	
	@Test
	public void testArrayList1000(){
		structures s = new structures(new ArrayList<Integer>());
		
		s.insertList(s.getArrayList(),1000);
		s.getList(s.getArrayList(), 1000);
		s.removeList(s.getArrayList(), 1000);
	}
	
	/*************** LinkedLists ***************/
	
	@Test
	public void testLinkedList10(){
		structures s = new structures(new LinkedList<Integer>());
		
		s.insertList(s.getLinkedList(),10);
		s.getList(s.getLinkedList(), 10);
		s.removeList(s.getLinkedList(), 10);
	}
	
	@Test
	public void testLinkedList100(){
		structures s = new structures(new LinkedList<Integer>());
		
		s.insertList(s.getLinkedList(),100);
		s.getList(s.getLinkedList(), 100);
		s.removeList(s.getLinkedList(), 100);
	}
	
	@Test
	public void testLinkedList1000(){
		structures s = new structures(new LinkedList<Integer>());
		
		s.insertList(s.getLinkedList(),1000);
		s.getList(s.getLinkedList(), 1000);
		s.removeList(s.getLinkedList(), 1000);
	}
	
	/*************** Vectors ***************/
	
	@Test
	public void testVector10(){
		structures s = new structures(new Vector<Integer>());
		
		s.insertList(s.getVector(),10);
		s.getList(s.getVector(), 10);
		s.removeList(s.getVector(), 10);
	}
	
	@Test
	public void testVector100(){
		structures s = new structures(new Vector<Integer>());
		
		s.insertList(s.getVector(),100);
		s.getList(s.getVector(), 100);
		s.removeList(s.getVector(), 100);
	}
	
	@Test
	public void testVector1000(){
		structures s = new structures(new Vector<Integer>());
		
		s.insertList(s.getVector(),1000);
		s.getList(s.getVector(), 1000);
		s.removeList(s.getVector(), 1000);
	}
	
	/*************** HashMap ***************/
	
	@Test
	public void testHashMap10(){
		structures s = new structures(new HashMap<Integer,Integer>());
		
		s.putMap(s.getHashMap(), 10);
		s.getMap(s.getHashMap(), 10);
		s.removeMap(s.getHashMap(), 10);
	}
	
	@Test
	public void testHashMap100(){
		structures s = new structures(new HashMap<Integer,Integer>());
		
		s.putMap(s.getHashMap(), 100);
		s.getMap(s.getHashMap(), 100);
		s.removeMap(s.getHashMap(), 100);
	}
	
	@Test
	public void testHashMap1000(){
		structures s = new structures(new HashMap<Integer,Integer>());
		
		s.putMap(s.getHashMap(), 1000);
		s.getMap(s.getHashMap(), 1000);
		s.removeMap(s.getHashMap(), 1000);
	}
	
	/*************** LinkedHashMap ***************/
	
	@Test
	public void testLinkedHashMap10(){
		structures s = new structures(new LinkedHashMap<Integer,Integer>());
		
		s.putMap(s.getLinkedHashMap(), 10);
		s.getMap(s.getLinkedHashMap(), 10);
		s.removeMap(s.getLinkedHashMap(), 10);
	}
	
	@Test
	public void testLinkedHashMap100(){
		structures s = new structures(new LinkedHashMap<Integer,Integer>());
		
		s.putMap(s.getLinkedHashMap(), 100);
		s.getMap(s.getLinkedHashMap(), 100);
		s.removeMap(s.getLinkedHashMap(), 100);
	}
	
	@Test
	public void testLinkedHashMap1000(){
		structures s = new structures(new LinkedHashMap<Integer,Integer>());
		
		s.putMap(s.getLinkedHashMap(), 1000);
		s.getMap(s.getLinkedHashMap(), 1000);
		s.removeMap(s.getLinkedHashMap(), 1000);
	}
	
	
}
