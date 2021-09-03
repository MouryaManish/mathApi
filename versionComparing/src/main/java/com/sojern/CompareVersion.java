package com.sojern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompareVersion {

    private static final Logger logger = LogManager.getLogger();

    public int compare(String version_1,String version_2){
        // split the string based on "." separator.
        String[] a = version_1.split("\\.");
        String[] b = version_2.split("\\.");

//        We need to get max length. The semantics can vary to any length.
//        As values can be 0.1, 0.1.0.0 and yet be equal.
        int maxLength = a.length> b.length? a.length:b.length;

//      stop the iteration as soon as we can decide or else keep iterating
        for(int i=0; i<maxLength;i++){
            int value_1 = i < a.length? Integer.parseInt(a[i]):0;
            int value_2 = i < b.length? Integer.parseInt(b[i]):0;
            int result = value_1 - value_2;
            if(result > 0)
                return 1;
            else if(result < 0)
                return -1;
        }
        return 0;
    }

    public static void main(String[] args){
        CompareVersion compareVersion = new CompareVersion();
        int result = compareVersion.compare(args[0],args[1]);
        System.out.println(String.format("******* result *******\n%d",result));
    }
}
