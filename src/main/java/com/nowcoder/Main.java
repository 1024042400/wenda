package com.nowcoder;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void  main(){
        int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String s =(String)scanner.next();
            if(s!= null && !s.equals("\n")) {
                System.out.println(process(s));
            }
        }
        HashMap map = new HashMap();


        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap m = new ConcurrentHashMap();
    }

    public static int process(String s) {
        String ms = getManacherStr(s);
        char[] cs = ms.toCharArray();
        int[] rs = new int[ms.length()];
        int c = -1;
        int r = -1;
        int i = 0;
        int step;
        int max = 1;
        while(i<ms.length()) {
            if(i>=r) {
                step = 1;
                while(i-step>=0 && i+step<ms.length() && cs[i-step] == cs[i+step]) {
                    rs[i] = step;
                    c = i;
                    r = i+step;
                    step++;
                }
            }else {
                if(rs[2*c-i] + i < r) {
                    rs[i] = rs[2*c-i];
                } else if(rs[2*c-i] + i > r) {
                    rs[i] = r-i;
                } else {
                    step = 1;
                    while(i-step>=0 && i+step<ms.length() && cs[i-step] == cs[i+step]) {
                        rs[i] = step;
                        c = i;
                        r = i+step;
                        step++;
                    }
                }
            }
            max = Math.max(max,rs[i]);
            i++;

        }
//        System.out.println(ms);
//        for(i=0;i<rs.length;i++){
//            System.out.print(rs[i]+",");
//        }
//        System.out.println();
        return max;
    }
    public static String getManacherStr(String s) {
        char[] chars = s.toCharArray();
        char[] ret = new char[chars.length*2+1];
        for(int i=0;i<ret.length;i++) {
            ret[i] = i%2==1 ? chars[i/2] : '#';
        }
        return new String(ret);
    }
}
