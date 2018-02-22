package com.leverton.shortURL.utilities;

import java.util.Random;

public class RandomNumberGenerator {
	public static  int getRandonNumber(int lowLimit, int highLimit){
        int random = 0;
        Random randomObj = new Random();
        do{
            random = randomObj.nextInt(highLimit);

        }while(!(random >= lowLimit && random <= highLimit) ||
                (random > 57 && random < 65) || 
                (random > 90 && random <97));

        return random;
    }

}
