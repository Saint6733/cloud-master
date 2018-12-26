package com.cloud.gateway.api;

import java.util.Scanner;

/**
 * @author 黄志强
 * @data 2018/12/14 9:38
 */
public class Te {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str;
        while (true){
            str=sc.next();
            str=str.replace("吗","");
            str=str.replace("?","!");
            str=str.replace("?","!");
            System.out.println(str);
        }
    }
}
