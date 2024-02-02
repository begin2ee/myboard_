package com.codingrecipe.myboard.controller;

public class test {
    public static void main(String args[]){
        System.out.println("hello world");
        int arr[]= new int[5];
        int sum=0;
        for(int i=0; i< arr.length; i++){
            arr[i]=i+1;
            sum += arr[i];
        }

        System.out.println("합은???" + sum);
    }

}
