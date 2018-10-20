package tests.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] args) {
        var test = new Test1();
        test.runTests();
    }

    private void runTests() {
        testRegExReplace();
    }

    private  void testRegExReplace() {
        var str = "asdf 123 asf ??? 124 asf asdfff";
        var pattern = Pattern.compile("(?i)[1-3]+");

    }

    private void testRegExChecker() {
        String longStr = " Derek Banas CA 123123 PA (44546)45645-456465 asdfasdf";
        String strangeStr = " 1Z aaa ** **** {{{{{ {{";

        regExChecker("(?i)[a-z]+", longStr);
    }

    private void regExChecker(String regExp, String str) {
        Pattern patter = Pattern.compile(regExp);
        Matcher matcher = patter.matcher(str);

        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println("Start index: " + matcher.start());
            System.out.println("End index: " + matcher.end());
        }

    }

}
