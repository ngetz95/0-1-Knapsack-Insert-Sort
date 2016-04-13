//Noah Getz
// 0/1 Knapsack Main Method
import java.util.ArrayList;
import java.util.Collections;
public class main {

	public static void main(String[] args) {
		Solutions knapsack = new Solutions();
		ArrayList<Integer> itemValues = new ArrayList<Integer>();
		ArrayList<Integer> itemWeights = new ArrayList<Integer>();
		int capacity = 100;
		knapsack.makeItems(itemValues, itemWeights, 30, capacity);
		
		System.out.println("The items we are trying to hold have values of:");
		System.out.println(itemValues.toString());
		System.out.println("and weights of:");
		System.out.println(itemWeights.toString());
		System.out.println("to fit in a knapsack of capacity: " + capacity);
		
		knapsack.greedySolution(itemValues, itemWeights, capacity);
		System.out.println("The Dynamic Solution gets us: $" + knapsack.dynamicSolution(itemValues, itemWeights, capacity));
	}

}
