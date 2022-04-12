public class Customer implements Comparable<Customer> {
    private String last, first;
    private String accNo;
    private double balance;

    public Customer(String first, String last, String accNo, double balance) {
        this.last = last;
        this.first = first;
        this.accNo = accNo;
        this.balance = balance;

    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance += amount;
    }

    public boolean equals(Customer other) {
        if (this.accNo.equals(other.accNo)) {
            return true;
        }
        return false;
    }

    // set and get
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // compareTo
    @Override
    public int compareTo(Customer other) {
        int value = this.last.compareTo(other.last);
        if (value != 0) {
            return value;
        }
        return this.first.compareTo(other.first);

    }

    @Override
    public String toString() {
        return "Customer [accNo=" + accNo + ", first=" + first + ", last=" + last + ", balance=" + balance + "]";
    }
}
