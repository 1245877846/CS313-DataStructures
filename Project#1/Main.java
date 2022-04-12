import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void loadCustomers(myBST customersList, Customer[] AccDB, String fileName)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        String last, first, accNo;
        double balance;
        // read the input and create a new customer
        while (in.hasNextLine()) {
            accNo = in.next();
            first = in.next().toLowerCase();
            last = in.next().toLowerCase();
            balance = in.nextDouble();
            Customer temp = new Customer(first, last, accNo, balance);
            customersList.insertBST(temp);
            AccDB[Integer.parseInt(accNo)] = temp;
        }
        in.close();
    }

    public static Customer searchCustomer(myBST customersList, Customer[] AccDB, Scanner input) {
        String first, last, choose;
        Customer temp = new Customer();
        System.out.println("Do you have the customer’s account number(yes or no):");
        choose = input.next().toLowerCase();
        // search by account number
        if (choose.equals("yes")) {
            System.out.println("Please enter the customer account number(4-digit):");
            int accNo = input.nextInt();
            temp = AccDB[accNo];
            // search by account name
        } else {
            System.out.print("Please enter the customer name(first name, last name):");
            first = input.next().toLowerCase();
            last = input.next().toLowerCase();
            temp = new Customer(first, last);
            temp = customersList.search(temp);
        }
        return temp;
    }

    public static void storeCustomers(myBST customersList, String fileName) throws FileNotFoundException {
        PrintWriter output = new PrintWriter(fileName);
        // use Recursion Inorder to store the data
        InorderStore(customersList.root, output);
        output.close();
    }

    // use Recursion to store the data
    private static void InorderStore(BSTnode temp, PrintWriter output) {
        if (temp != null) {
            InorderStore(temp.left, output);
            output.println(temp.data);
            InorderStore(temp.right, output);
        }
    }

    public static void main(String[] args) throws Exception {
        myBST customersList = new myBST();
        Customer[] AccDB = new Customer[1000];
        loadCustomers(customersList, AccDB, "input.txt");
        // dispaly the menu
        System.out.println("Bank System");
        System.out.println("Menu:");
        System.out.println("* Press (1) to Make a deposit, Press (2) to Make a withdrawal");
        System.out.println("* Press (3) to Check balance");
        System.out.println("* Press (4) to Open a new customer account, Press (5) to Close a customer account");
        System.out.println("* Press (6) to Exit the program(6) \n ----------");
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        double amount;
        while (flag) {
            System.out.println("Enter numbers 1~6 to do what you want:");
            try {
                int choice = input.nextInt();
                Customer temp = new Customer();
                switch (choice) {
                    case 1: // make a deposit
                        System.out.println("making a deposit now");
                        temp = searchCustomer(customersList, AccDB, input);
                        if (temp != null) {
                            System.out.print("Please enter the amount of the deposit:");
                            amount = input.nextDouble();
                            temp.deposit(amount);
                            System.out.println(temp);
                        } else {
                            System.out.println("Unable to find");
                        }
                        break;
                    case 2:// make a withdrawal
                        System.out.println("making a withdrawal now");
                        temp = searchCustomer(customersList, AccDB, input);
                        if (temp != null) {
                            System.out.print("Please enter the amount of the withdrawal:");
                            amount = input.nextDouble();
                            temp.withdraw(amount);
                            System.out.println(temp);
                        } else {
                            System.out.println("Unable to find");
                        }
                        break;
                    case 3:// check balance
                        System.out.println("checking the balance now");
                        temp = searchCustomer(customersList, AccDB, input);
                        if (temp != null) {
                            System.out.println(temp);
                        } else {
                            System.out.println("Unable to find");
                        }
                        break;
                    case 4:// open a new account
                        System.out.println("opening a new account now");
                        System.out.print("Please enter the customer name(first name, last name):");
                        String first = input.next();
                        String last = input.next();
                        System.out.print("Please enter 4-digit account number:");
                        String accNo = input.next();
                        System.out.print("Please enter a deposit to open the accountr:");
                        Double balance = input.nextDouble();
                        temp = new Customer(first, last, accNo, balance);
                        customersList.insertBST(temp);
                        AccDB[Integer.parseInt(temp.getAccNo())] = temp;
                        System.out.println("The customer was successfully opened.");
                        break;
                    case 5:// close a customer account
                        System.out.println("closing a customer account now");
                        temp = searchCustomer(customersList, AccDB, input);
                        if (temp != null) {
                            AccDB[Integer.parseInt(temp.getAccNo())] = null;
                            customersList.deleteBST(temp);
                            System.out.println("The customer was successfully closed.");
                        } else {
                            System.out.println("Unable to find");
                        }
                        break;
                    case 6:// exit the program
                        flag = false;
                        break;
                    default:
                        System.out.println("Please enter the correct number!");
                }
            } catch (Exception e) {
                System.out.println(e);
                flag = false;
            }
        }
        input.close();
        System.out.println("Exit the Program.");
        storeCustomers(customersList, "output.txt");
    }
}
