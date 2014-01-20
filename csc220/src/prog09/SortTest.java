package prog09;
import java.lang.reflect.Array;
import java.util.Random;

/*
------------------------------------------
Running Time for n=1000:

prog09.InsertionSort@6af62373
Sorting Time = 23.0 milliseconds!

prog09.HeapSort@4318f375
Sorting Time = 1.0 milliseconds!

prog09.QuickSort@3f0ef90c
Sorting Time = 2.0 milliseconds!

prog09.MergeSort@39d8957f
Sorting Time = 2.0 milliseconds!
------------------------------------------
Running Time for n=10,000:

prog09.InsertionSort@65690726
Sorting Time = 134.0 milliseconds!

prog09.HeapSort@39d8957f
Sorting Time = 14.0 milliseconds!

prog09.QuickSort@768965fb
Sorting Time = 30.0 milliseconds!

prog09.MergeSort@37dd7056
Sorting Time = 28.0 milliseconds!
-------------------------------------------
Running Time for n=100,000

prog09.HeapSort@64c3c749
Sorting Time = 79.0 milliseconds!

prog09.QuickSort@544a5ab2
Sorting Time = 128.0 milliseconds!

prog09.MergeSort@2e6e1408
Sorting Time = 602.0 milliseconds!
-------------------------------------------
Running Time for n=1,000,000

prog09.HeapSort@544a5ab2
Sorting Time = 204.0 milliseconds!

prog09.QuickSort@6af62373
Sorting Time = 863.0 milliseconds!

prog09.MergeSort@65690726
Sorting Time = 1179.0 milliseconds!
-------------------------------------------
Running Time for n=10,000,000

prog09.HeapSort@2e6e1408
Sorting Time = 917.0 milliseconds!

prog09.QuickSort@525483cd
Sorting Time = 12299.0 milliseconds!

prog09.MergeSort@2f9ee1ac
Sorting Time = 12143.0 milliseconds!

**/


public class SortTest<E extends Comparable<E>> {
  public void test (Sorter<E> sorter, E[] array) {
    E[] copy = array.clone();
    //measure time
    
    long startTime = System.currentTimeMillis();   
    sorter.sort(copy);
    long endTime = System.currentTimeMillis(); 
    
    double sortTime = endTime - startTime;
    
    System.out.println(sorter);
    if (array.length < 100) {
        for (int i = 0; i < copy.length; i++)
            System.out.print(copy[i] + " ");
    }
    System.out.println("Sorting Time = " + sortTime + " milliseconds!");
    System.out.println();
  }
  
  public static void main (String[] args) {
    int arraySize = 10;
    Random random = new Random(0);
	    if (args.length > 0) {
	        arraySize = Integer.parseInt(args[0]);
	    }
	 
	//create array with size arraySize
	Integer[] array = new Integer [arraySize];
	for (int i = 0; i < array.length; i++) {
		array[i] = random.nextInt();
	}
		
    //Integer[] array = { 3, 1, 4, 1, 5, 9, 2, 6 };
    SortTest<Integer> tester = new SortTest<Integer>();
    
    //tester.test(new InsertionSort<Integer>(), array);
    tester.test(new HeapSort<Integer>(), array);
    tester.test(new QuickSort<Integer>(), array);
    tester.test(new MergeSort<Integer>(), array);
  }
}

class InsertionSort<E extends Comparable<E>>
  implements Sorter<E> {
  public void sort (E[] array) {
    for (int n = 0; n < array.length; n++) {
      E data = array[n];
      int i = n;
      // move array[i-1] to array[i] as long as array[i-1] > data
      // and decrement i
      for (i = n; i > 0 && array[i-1].compareTo(data) > 0 ; i--) {
    	  array[i] = array[i-1];
      }
      array[i] = data;
    }
  }
}

class HeapSort<E extends Comparable<E>>
  implements Sorter<E> {
  
  private E[] array;
  
  private void swap (int i, int j) {
    E data = array[i];
    array[i] = array[j];
    array[j] = data;
  }
  
  public void sort (E[] array) {
    this.array = array;
    
    for (int i = parent(array.length - 1); i >= 0; i--)
      swapDown(i, array.length - 1);
    
    for (int n = array.length - 1; n >= 0; n--) {
      swap(0, n);
      swapDown(0, n - 1);
    }
  }
  
  public void swapDown (int root, int end) {
   //   Calculate the left child of root.
    int biggerChild = left(root);
   //   while the child is still in the array
    while (biggerChild <= end) {
   //   if the right child is in the array and 
   //      it is bigger than than the left
   //     switch to the right child
    	if (right(root) <= end && array[right(root)].compareTo(array[left(root)]) > 0) {
    		biggerChild = right(root);
    	}
   //   if the root is not less than the bigger child
   //     return
    	if (array[root].compareTo(array[biggerChild]) >= 0) {
    		return;
    	}
   //   swap the root with the bigger child
   //   update root and child
    	swap(root, biggerChild);
    	root = biggerChild;
    }      
  }
  
  private int left (int i) { return 2 * i + 1; }
  private int right (int i) { return 2 * i + 2; }
  private int parent (int i) { return (i - 1) / 2; }
}

class QuickSort<E extends Comparable<E>>
  implements Sorter<E> {
  
  private E[] array;
  private void swap (int i, int j) {
    E data = array[i];
    array[i] = array[j];
    array[j] = data;
  }
  
  public void sort (E[] array) {
    this.array = array;
    sort(0, array.length-1);
  }
  
  private void sort(int left, int right) {
    if (left >= right)
      return;
    
    swap(left, (left + right) / 2);
    
    E pivot = array[left];
    int a = left + 1;
    int b = right;
    while (a <= b) {
      // Move a forward if array[a] <= pivot
    	if (array[a].compareTo(pivot) <= 0){
    		a++;
      // Move b backward if array[b] > pivot
    	} else if (array[b].compareTo(pivot) > 0) {
    		b--;
      // Otherwise swap array[a] and array[b]
    	} else {
          swap (a, b);
    	}
    }
    
    swap(left, b);
    
    sort(left, b-1);
    sort(b+1, right);
  }
}

class MergeSort<E extends Comparable<E>>
  implements Sorter<E> {
  
  private E[] array, array2;
  
  public void sort (E[] array) {
    this.array = array;
    array2 = array.clone();
    sort(0, array.length-1);
  }
  
  private void sort(int left, int right) {
    if (left >= right)
      return;
    
    int middle = (left + right) / 2;
    sort(left, middle);
    sort(middle+1, right);
    
    int i = left;
    int a = left;
    int b = middle+1;
    while (a <= middle || b <= right) {
      // If both a <= middle and b <= right
      // copy the smaller of array[a] or array[b] to array2[i]
    	if(a <= middle && b <= right) {
    		if (array[a].compareTo(array[b]) < 0) {
    			array2[i] = array[a];
    			a++;
    			i++;
    		} else {
    			array2[i] = array[b];
    			i++;
    			b++;
    		}
    	} else {
      // Otherwise just copy the remaining elements to array2	
    		if (a > middle) {
    			System.arraycopy(array, b, array2, i, right - b + 1);
    			b = right +1;
    		} else {
    			System.arraycopy(array, a, array2, i, middle - a + 1);
    			a = right +1;
    		}
    	}
      
    }
    
    System.arraycopy(array2, left, array, left, right - left + 1);
  }
}
