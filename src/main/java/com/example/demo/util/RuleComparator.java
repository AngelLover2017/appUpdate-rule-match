package com.example.demo.util;

import com.example.demo.entity.Rule;

import java.util.Comparator;

public class RuleComparator implements Comparator<Rule> {
    public int compareId(String id1 , String id2){
        String[] s1=id1.split("\\.");
        String[] s2=id2.split("\\.");
        int len=Math.min(s1.length,s2.length);
        for(int i=0 ; i<len ; i++){
            int a1=Integer.parseInt(s1[i]);
            int a2=Integer.parseInt(s2[i]);
            if(a1>a2)   return 1;
            if(a1<a2)   return -1;
        }
        return Integer.compare(s1.length, s2.length);
    }
    @Override
    public int compare(Rule o1, Rule o2) {
        return -compareId(o1.getUpdate_version_code(),o2.getUpdate_version_code());
    }
}
