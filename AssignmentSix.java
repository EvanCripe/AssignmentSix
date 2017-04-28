package assignmentsix;

import java.io.*;
import java.util.*;

/**
 * AssignmentSix.java Purpose is to implement a non-recursive algorithm using 
 * stacks to help efficiently solve the traveling salesperson problem. 
 * 
 * @author Evan Cripe
 * @version 1.0 4/28/17
 */
public class AssignmentSix  {

    //Attributes
    int CITI;
    int[][] adjacency;
    Stack<Integer> pathStack = new Stack<>();
    ArrayList<Integer> visitedCities = new ArrayList<>();
    int closestCity;
    boolean minFlag;
    int currentCity;
    int totalCost;    
    
    /**
     * Constructor containing the int CITI, and 2D array adjacency
     * @param N number of cities in the tour (integer)
     */
    public AssignmentSix(int N) {
        CITI = N;
        adjacency = new int[N][N];
        pathStack.push(0);
        visitedCities.add(0);
        closestCity = 0;
        minFlag = false;
        totalCost = 0;
    }//Lab4C202
    
    /**
     * Method that will load the adjacency matrix by reading from a file
     */
    public void populateMatrix() { //CITI is the number of cities and it is a constant  
        int value, i, j;
        try {
            File infile = new File("tsp" + CITI + ".txt");
            Scanner input = new Scanner(infile);
            for (i = 0; i < CITI && input.hasNext(); i++) {
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;
                    }//if
                    else {
                        value = input.nextInt();
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }//else
                }//forj
            }//fori
            input.close();
        }//try
        catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//populateMatrix
   
    /**
     * Method that finds the minimum spanning tree of the given adjacency matrix
     */
    public void minimumSpanningTree(){
        System.out.print(0);
        while(!pathStack.isEmpty()){
            currentCity = pathStack.peek();
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < adjacency.length; i++) {
                if(adjacency[currentCity][i] != 0 && !visitedCities.contains(i)
                        && adjacency[i][currentCity] < min){
                    min = adjacency[currentCity][i];
                    closestCity = i;
                    minFlag = true;
                }//if   
            }//for
            if(minFlag){
                visitedCities.add(closestCity);
                pathStack.push(closestCity);
                totalCost += adjacency[currentCity][closestCity];
                System.out.print(", " + closestCity);
                minFlag = false;
            }//if
            pathStack.pop();
        }//while
    }//tsp

    public static void main(String[] args) {
        AssignmentSix run;
        int[] n = {12, 13, 14, 15, 16, 19, 29};
        for (int i = 0; i < n.length; i++) {
            System.out.println("======= MSP for n = " + n[i] + " =======");
            run = new AssignmentSix(n[i]);
            run.populateMatrix();
            System.out.print("Path: ");
            run.minimumSpanningTree(); 
            System.out.println("\nCost: " + run.totalCost);
        }//for
        
    }//main 
}//class
