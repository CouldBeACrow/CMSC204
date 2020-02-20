
public class ArraySum {

	public int sumOfArray(Integer[] myArray, int maxIndex) {
		int currentIndex = 0;
		return recursiveSum(myArray, maxIndex, currentIndex);
	}
	
	public int recursiveSum(Integer[] myArray, int maxIndex, int currentIndex) {
		if (currentIndex == maxIndex) {
			return myArray[currentIndex];
		}
		else {
			return myArray[currentIndex] + recursiveSum(myArray, maxIndex, ++currentIndex);
		}
	}
	
}
