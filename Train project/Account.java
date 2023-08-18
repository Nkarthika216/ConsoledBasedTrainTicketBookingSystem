package TrainTicketBookingProject.Booking;
public class Account{
    public String number;
    private double balance;
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String bankname;
    public String branch_name;
    public String holder_name;
    public Payment payment;
    // String customer_id;
    public Account(String number, double balance, String bankname, String branch_name, String holder_name,Payment payment) {
        this.number = number;
        this.balance = balance;
        this.bankname = bankname;
        this.branch_name = branch_name;
        this.holder_name = holder_name;
        this.payment=payment;
    }

}