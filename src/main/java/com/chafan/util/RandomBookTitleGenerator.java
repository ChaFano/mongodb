package com.chafan.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Auther: 茶凡
 * @ClassName RandomBookTitleGenerator
 * @date 2023/10/29 22:43
 * @Description TODO
 */

@Component
public class RandomBookTitleGenerator {

    private static final String[] FIRST_PART = {
            "The", "A", "An", "One", "My", "Your", "His", "Her", "Our", "Their"
    };

    private static final String[] SECOND_PART = {
            "Adventure", "Mystery", "Journey", "Quest", "Legacy", "Secret", "Discovery", "Legacy", "Chronicles", "Tale"
    };

    private static final String[] THIRD_PART = {
            "of", "in", "from", "on", "to", "with", "under", "beyond", "within", "through"
    };

    private static final String[] FOURTH_PART = {
            "Time", "Space", "Dreams", "Reality", "Stars", "Shadows", "Destiny", "Kingdoms", "Dimensions", "Illusions"
    };

    public static String generateRandomTitle() {
        Random rand = new Random();
        StringBuilder title = new StringBuilder();
        title.append(getRandomPart(FIRST_PART, rand)).append(" ");
        title.append(getRandomPart(SECOND_PART, rand)).append(" ");
        title.append(getRandomPart(THIRD_PART, rand)).append(" ");
        title.append(getRandomPart(FOURTH_PART, rand));
        return title.toString();
    }

    private static String getRandomPart(String[] parts, Random rand) {
        int index = rand.nextInt(parts.length);
        return parts[index];
    }

//    public static void main(String[] args) {
//        String randomTitle = generateRandomTitle();
//        System.out.println("Random Book Title: " + randomTitle);
//    }

}
