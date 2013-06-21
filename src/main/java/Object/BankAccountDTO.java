package Object;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/18/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    String accountNumber;
    double balance;
    long timeStamp;
    static Calendar calendar = Calendar.getInstance();
    public BankAccountDTO(String accountNumber) {
        this.accountNumber=accountNumber;
        this.balance=0.0;
        this.timeStamp= calendar.getTimeInMillis();
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return this.accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public double getBalance() {
        return this.balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBalance(double amount) {
        this.balance=this.balance+amount;
    }

    public long getTimeStamp() {
        return this.timeStamp;  //To change body of created methods use File | Settings | File Templates.
    }
    public static void setCalendar(Calendar calendars)
    {
        calendar=calendars;
    }
}
