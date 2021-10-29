package br.com.usp.stoodi.utils;

public class RandomData {

    public static String randomText(Integer size){
        var builder = new StringBuilder();

        for(var i = 0; i < size; i++)
            builder.append("a");

        return builder.toString();
    }
}
