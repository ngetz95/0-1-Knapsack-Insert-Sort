//priceList.size()oah Getz
// 0/1 Knapsack Solutions Method
// Help recieved from: http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
import java.util.ArrayList;
import java.util.Collections;
public class Solutions {
	
	InsertSorter mySorter = new InsertSorter();
	public void makeValues(ArrayList<Integer> priceList, int toAdd, int capacity){ //Adds values of items to an empty ArrayList
		for(int i = 0; i < toAdd; i++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... toAdd, where toAdd is how many items you want the list to have
			int ranNum = (int) (Math.random() * capacity); //Makes a random value for each item
			priceList.add(ranNum);
		}
		mySorter.insertSortMyList(priceList); //Uses the insertion sort to put the items in order by price
	}
	
	public void makeWeights(ArrayList<Integer> weightList, int toAdd, int capacity){ //Adds weights of items to an empty ArrayList
		for(int i = 0; i < toAdd; i++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... toAdd, where toAdd is how many items you want the list to have
			int ranNum = (int) (Math.random() * capacity); //Makes a random weight for each item
			weightList.add(ranNum);
		}
		mySorter.insertSortMyList(weightList); //Uses the insertion sort to put the items in order by weight
	}
	
	public void makeItems(ArrayList<Integer> priceList, ArrayList<Integer> weightList, int toAdd, int capacity){
		//This method does what both the previous methods do, but garutees that both list will have the same length
		//Since typically heavier items means more value, having both lists sorted in numerical order should create a sensible list of items
		//This also makes the rest of the code work properly
		this.makeValues(priceList, toAdd, capacity);
		this.makeWeights(weightList, toAdd, capacity);
	}
	
	public int getPriciest(ArrayList<Integer> priceList){ //Returns the index of the most expesive and heaviest item
		int largest = 0;
		int largestIndex = 0;
		for(int i = 0; i < priceList.size(); i++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... a.size() - 1
			if(largest < priceList.get(i)){
				largest = priceList.get(i);
				largestIndex = i;
			}
		}
		return largestIndex;
	}
	
	public int max(int a, int b){ //Simply returns the max of 2 integers
		if(a >= b)
			return a;
		else
			return b;
	}
	
	ArrayList<Integer> weightTaken = new ArrayList<Integer>();
	ArrayList<Integer> valueTaken = new ArrayList<Integer>();
	
	public int greedySolution(ArrayList<Integer> priceList, ArrayList<Integer> weightList, int capacity){ //Gets the greedy solution to the knapsack problem
		int totalWeight = 0;
		int totalValue = 0;
		int heaviest = weightList.get(this.getPriciest(weightList));
		int priciest = priceList.get(this.getPriciest(priceList));
		weightTaken.clear();
		valueTaken.clear();;
		
		while(totalWeight + heaviest < capacity){ //Takes the most valuable remaining item the knapsack can carry until there is no more room
			valueTaken.add(priceList.remove(this.getPriciest(priceList)));
			weightTaken.add(weightList.remove(this.getPriciest(weightList)));
			totalValue = totalValue + priciest;
			totalWeight = totalWeight + heaviest;
		}
		
		System.out.println("For this problem we are using the greedy solution:");
		System.out.println("The total items taken include:");
		System.out.println(valueTaken.toString() + "in value");
		System.out.println(weightTaken.toString() + "in weight");
		System.out.println("For a total value of: $" + totalValue); //Prints out the results
		
		return totalValue;
	}
	
	
	public int dynamicSolution(ArrayList<Integer> priceList, ArrayList<Integer> weightList, int capacity){ //Finds the dynamic solution to the 0/1 knapsack problem
		int[][] table = new int[priceList.size() + 1][capacity + 1]; //Creates a two-dimmensional array to have a more organized way of finding the solution
		
		for (int i = 0; i <= priceList.size(); i++) //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... a.size()
		   {
		       for (int j = 0; j <= capacity; j++) //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... capacity
		       {
		           if (i == 0 || j == 0) //Makes the first value of the rows/collumns zero
		               table[i][j] = 0;
		           else if (weightList.get(i - 1) <= j)
		                 table[i][j] = max(priceList.get(i - 1) + table[i-1][j - weightList.get(i - 1)],  table[i-1][j]);
		           else
		                 table[i][j] = table[i-1][j];
		       }
		   }
		return table[priceList.size()][capacity]; //Returns the final value of the table which should be the dynamic solution
	}
}
