package cn.explo.gufeng.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Common {
    public static String getFromAssets(String fileName, Context ctx){
        try {
            InputStreamReader inputReader = new InputStreamReader( ctx.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
