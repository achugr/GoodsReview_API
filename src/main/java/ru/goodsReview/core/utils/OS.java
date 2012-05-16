package ru.goodsReview.core.utils;

/**
* @author daddy-bear
*         Date: 16.05.12 - 22:53
*/

public enum OS {
   
   NIX,
   WIN;
   
   public static OS getOS() {
       String os = System.getProperty("os.name").toLowerCase();
       if (os.contains("win")) {
           return WIN;
       } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
           return NIX;
       }
       throw new RuntimeException("Invalid Operating System");
   }
}