public class Account{

    int bankAcc;
    String owner;
    double balance;

   //constructor to initialize program
    public Account(int bankAcc, String owner) {
        this.bankAcc = bankAcc;
        this.owner = owner;
        this.balance = 0;  // initialize to zero
    }

    void deposit(double amount){
        balance += amount;
    }
    boolean withdraw(double amount){
        if(balance -amount >=0){
            balance -=amount;
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "bankAcc=" + bankAcc +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}