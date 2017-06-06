package com.cardGame;


public class CardMethod {
 
 static int[] a_array = {1,2,3,4,5,6,7,8,9,10};
 
 
 public static int[] shuffle(){
  int temp = 0;
   
  for(int i=0; i<10; i++){
   
   int index = (int)(Math.random()*9);
   temp = a_array[i];
   a_array[i] = a_array[index];
   a_array[index] = temp;

  }
  return a_array;
 }
 
 
}