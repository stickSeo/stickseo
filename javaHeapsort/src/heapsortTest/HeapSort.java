package heapsortTest;

import java.util.Scanner;

//  문제풀이용
public class HeapSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		int n = scanner.nextInt();
		int[] a_array = new int[n];
		int[] b_array = new int[n];
		for(int index=0; index<n; index++){
			a_array[index] = scanner.nextInt();
		}
		for(int index=0; index<n; index++){
			b_array[index] = scanner.nextInt();
		}
		min_heapsort(a_array,n);
		min_heapsort(b_array,n);
		for(int i=0; i<n; i++){
			 sum += (a_array[i] * b_array[n-1-i]);
		}
		System.out.println(sum);
  }
	public static void min_heapsort(int[] array, int n)
	{
		min_build(array, n);
		for(int i = n; i > 1; i--)
	    {
	        swap(array, 0, i-1);
	        n--;
	        min_build(array,n);
	    }
	}
	public static void min_build(int[] array, int n)
	{
		for(int index=n/2; index>=0; index--)  
		{
			min_heap(array,n,index);
		}
		
	}
	public static void min_heap(int[] array, int n, int index)
	{
		int lc = left_node(index);
		int rc = right_node(index);
		int min = 0;

		if( lc<n ){
			if(array[lc] <= array[index])
			{
				min = lc;
			}else if(array[lc]>array[index])
			{
				min = index;
			}
		}
		if(rc<n){
			if(array[rc] <= array[min])
			{
				min = rc;
			}
		}
		swap(array, index, min);
	}
	public static void swap(int[] array, int x, int y)
	{
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	public static int parent(int index){
		return ((index-1)/2);
	}
	public static int left_node(int index)
	{
		return (index*2)+1;
	}
	public static int right_node(int index)
	{
		return (index*2)+2;
	}
}
