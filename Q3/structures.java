package Q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class structures {
	private ArrayList<Integer> arrayList;
	private LinkedList<Integer> linkedList;
	private Vector<Integer> vector;
	private HashMap<Integer, Integer> hashMap;
	private LinkedHashMap<Integer, Integer> linkedHashMap;
	
	structures(ArrayList<Integer> aList){
		this.arrayList = aList;
	}
	
	structures(LinkedList<Integer> lList){
		this.linkedList = lList;
	}
	
	structures(Vector<Integer> vector){
		this.vector = vector;
	}

	structures(HashMap<Integer, Integer> hMap){
		this.hashMap = hMap;
	}
	
	structures(LinkedHashMap<Integer, Integer> lHMap){
		this.linkedHashMap = lHMap;
	}
	
	/*       SETS AND GETS       */
	/* ------------------------- */ 
	public void setArrayList(ArrayList<Integer> arrayList){
		this.arrayList = arrayList;
	}
	public void setLinkedList(LinkedList<Integer> linkedList){
		this.linkedList = linkedList;
	}
	public void setVector(Vector<Integer> vector){
		this.vector = vector;
	}
	public ArrayList<Integer> getArrayList(){
		return this.arrayList;
	}
	public LinkedList<Integer> getLinkedList(){
		return this.linkedList;
	}
	public Vector<Integer> getVector(){
		return this.vector;
	}
	public void setHashMap(HashMap<Integer, Integer> hashMap){
		this.hashMap = hashMap;
	}
	public void setLinkedHashMap(LinkedHashMap<Integer, Integer> linkedHashMap){
		this.linkedHashMap = linkedHashMap;
	}
	public HashMap<Integer, Integer> getHashMap(){
		return this.hashMap;
	}
	public LinkedHashMap<Integer, Integer> getLinkedHashMap(){
		return this.linkedHashMap;
	}
	/* ------------------------- */ 
	
	public void insertList(List<Integer> list, int iterations){
		for (int i = 0; i < iterations; i++) {
			list.add(i);
		}
	}
	
	public void removeList(List<Integer> list, int iterations){
		for (int i = 0; i < iterations; i++) {
			list.remove(0);
			System.out.println("Removed: " + i);
		}
	}
	
	public void getList(List<Integer> list, int iterations){
		for (int i = 0; i < iterations; i++) {
			list.get(i);
			System.out.println("Gotten: " + i);
		}
	}
	
	public void putMap(Map<Integer, Integer> map, int iterations){
		for (int i = 0; i < iterations; i++) {
			map.put(i,i);
		}
	}
	public void getMap(Map<Integer, Integer> map, int iterations){
		for (int i = 0; i < iterations; i++) {
			map.get(i);
		}
	}
	public void removeMap(Map<Integer, Integer> map, int iterations){
		for (int i = 0; i < iterations; i++) {
			map.remove(0);
		}
	}
	
}
