package learn.other.ArrayList;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class readArrayList {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        

        /*ArrayList<Test> arr = new ArrayList<>();
        arr.add(new Test(1, 2));
        arr.add(new Test(2, 3));
        arr.add(new Test(3, 3));
        arr.add(new Test(4, 5));
        arr.add(new Test(5, 8));

        try(FileOutputStream fis = new FileOutputStream("test", true);
            ObjectOutputStream ois = new ObjectOutputStream(fis)) {

            ois.writeObject(arr);

        }

        ArrayList<Test> arr2 = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream("test/a");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            ArrayList<Test> loaded = (ArrayList<Test>)ois.readObject();
            if (loaded != null) arr2 = loaded;

        }

        System.out.println(arr.stream().map(Test::toString).collect(Collectors.joining(" ")));
        System.out.println(arr.stream().map(Test::toString).collect(Collectors.joining(" ")));
*/
    }

    static private class Test implements Serializable {
        public int a;
        public int b;

        public Test() {

        }

        public Test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return Integer.toString(this.a) + "|" + Integer.toString(this.b);
        }
    }

}
