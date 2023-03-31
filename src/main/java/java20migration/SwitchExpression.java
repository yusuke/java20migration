package java20migration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SwitchExpression {
    public static void main(String[] args) {
        List<String> lists = Arrays.asList("apple", "banana", "melon", "grape");
        Random random = new Random();
        String fruit = lists.get(random.nextInt(lists.size()));
        String fruitEmoji;
        switch (fruit) {
            case "apple":
                fruitEmoji = "🍎";
                break;
            case "banana":
                fruitEmoji = "🍌";
                break;
            case "melon":
                fruitEmoji = "🍈";
                break;
            case "grapefruit":
                fruitEmoji = "🍇";
                break;
            default:
                fruitEmoji = "🍍";
        }
        System.out.println(fruitEmoji);
    }
}
