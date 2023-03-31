package java20migration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public record Fortune(String お名前) {

    private static List<String> 占いバリエーション = Arrays.asList(
            "今日の運勢は最悪です。\n" +
                    "家に帰りましょう。\n",
            "今日あなたは世界一ラッキーです。\n" +
                    "宝くじか馬券を買って帰りましょう。\n",
            "今日は微妙な一日になります。\n" +
                    "帽子をかぶってお出かけすると運気を引き寄せるでしょう。\n"

    );

    public String 占い結果() {
        String 名前の先頭1文字 = お名前.length() > 0 ? お名前.substring(0, 1) : "";

        String luckyColor;
        switch (名前の先頭1文字) {
            case "山":
                luckyColor = "黒";
                break;
            case "東":
                luckyColor = "赤";
                break;
            case "西":
                luckyColor = "白";
                break;
            case "南":
                luckyColor = "青";
                break;
            case "北":
                luckyColor = "ボンダイブルー";
                break;
            default:
                luckyColor = "藍";
        }

        String result = 占いバリエーション.get((LocalDate.now().hashCode() + お名前.hashCode()) % 3);
        result += String.format("ラッキーカラーは%sです", luckyColor);
        DB.execute("insert into 占いログ(お名前, 占い日, 占い結果)\n" +
                " values ( ?,?,? )\n", お名前, LocalDate.now(), result);
        return result;
    }
}
