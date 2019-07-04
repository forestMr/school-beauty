package edu.forest;

public class Main {

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
         new MM();
         Long endTime =  System.currentTimeMillis();

        System.out.println("执行耗时："+(endTime-startTime)/1000+"s");
    }
}
