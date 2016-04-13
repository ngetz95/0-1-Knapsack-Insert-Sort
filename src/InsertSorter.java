//Noah Getz
//Insertion Sort Algorithm
import java.util.ArrayList;
public class InsertSorter<T> implements Comparable<T>{

	
	//Sort the list by getting an item, then comparing it to its previous item
	
	public boolean isInOrder(ArrayList<Object> a){
		int b = 0;
		for(int i = 0; i < a.size() - 1; i++){
			if ( ( (Comparable<T>) a.get(i) ).compareTo( (T) a.get(i+1) ) <= 0){
				b++; //Checks every pair of elements in the arraylist if the next has a highest value than the previous
			}
		}
		if(b == a.size() - 1)
			return true; //If b increased for every time in the loop, the list should aready be in order
		else
			return false;
	}
	
	public <Object> void insertSortMyList(ArrayList<Object> a){
		
		assert this.isInOrder((ArrayList<java.lang.Object>) a) == false;
		for(int i = 1; i < a.size(); i++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... a.size() - 1
			int j = i;
			Object currentItem = a.get(j);
			//This while loop switches the item if they are out of order and moves the selected item down each time
			while(j > 0 && ((Comparable<T>) a.get(j-1)).compareTo((T) currentItem) >  0){ //INVARIANT: j > j - 1, j = i, i-1, ... 1
				a.set(j,a.get(j-1));
				j--;
			}
			//If the while loop runs, this places the current item where it belongs
			a.set(j,currentItem);
		}
		assert this.isInOrder((ArrayList<java.lang.Object>) a) == true;
	}
	

	@Override
	public int compareTo(T arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}