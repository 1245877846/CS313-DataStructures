import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Customer weijie = new Customer("weijie", "a", "01", 1000);
        Customer shuqing = new Customer("shuqing", "c", "02", 10000);
        Customer xingyu = new Customer("xingyu", "b", "03", 20000);
        myBST t = new myBST(weijie);
        t.insertBST(shuqing);
        t.insertBST(xingyu);
        t.Inorder();
        System.out.println();
        System.out.println(weijie.getBalance());
        weijie.setBalance(5000);
        t.Inorder();

        // File inputFile = new File("input.txt");
        // Scanner in = new Scanner(inputFile);
        // String line = "";
        // while (in.hasNextLine()) {
        // line = in.nextLine();
        // }
        // System.out.print(line);
        // in.close();
    }
}
