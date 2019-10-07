import java.io.*;
import java.lang.*;
import java.net.*;
import java.time.temporal.TemporalAdjuster;
import java.util.*;


public class Stub implements Serializable
{
    private String a;
    private String x;
    private String y;

    public Stub()
    {
        a="";
        x="";
        y="";
        
    }

    public Stub(String x , String y)
    {
        this.x=x;
        this.y=y;
        
    }
    public Stub (String a)
    {
        this.a=a;
        
    }
    public Stub(double[] arr)
    {
        
    }
    public Stub(int[][] brr)
    {
        
    }
    String message(int[][] arr,int[][] brr, int M, int N, int P, int Q)
    {
        String str1="";
        for(int i = 0;i<M;i++)
        {
            for(int j = 0;j<N;j++)
            {
                if((i==M-1) && (j==N-1))
                {
                    str1 = str1 + arr[i][j];
                }
                else
                {
                    str1 = str1 + arr[i][j]+",";
                }
            }
        }
        String str2="";
        for(int i = 0;i<P;i++)
        {
            for(int j = 0;j<Q;j++)
            {
                if((i==P-1) && (j==Q-1))
                {
                    str2 = str2 + brr[i][j];
                }
                else
                {
                    str2 = str2 + brr[i][j]+",";
                }
            }
        }
        String str=str1+"/"+str2;
        return str;
    }
    String arrmessage(double[] arr)
    {
        String str ="";
        for(int i = 0; i <arr.length;i++)
        {
            if(i!=arr.length-1)
            {
            str=str+arr[i]+",";
            }
            else
            {
            str=str+arr[i];
            }
            //System.out.println(arr[i]);
        }
        return str;


    }
     String message()
    {
        String sep ="/";
        String message = this.x+sep+this.y;
        return message;
    }
}

