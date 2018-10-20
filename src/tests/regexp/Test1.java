package tests.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public Test1() {

        String longStr = " Derek Banas CA 123123 PA (44546)45645-456465 asdfasdf";
        String strangeStr = " 1Z aaa ** **** {{{{{ {{";

        regChecker("(?i)[a-z]+", longStr);

    }

    private void regChecker(String regExp, String str) {
        Pattern patter = Pattern.compile(regExp);
        Matcher matcher = patter.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println("Start index: " + matcher.start());
            System.out.println("End index: " + matcher.end());
        }

    }

}
