package TrainTicketBookingProject.Booking;

import java.util.ArrayList;
import java.util.Date;

import TrainTicketBookingProject.Train.Station;
import TrainTicketBookingProject.persons.*;
public class Ticket{
    public int train_no;
    public String status;
    public String bookedby;
    public Date Journey_date;
    public long transaction_id;
    public String fromString;
    public String toString;
    public String journeyDate;
    public int getTrain_no() {
        return train_no;
    }
    public String train_name;
    public String getTrain_name() {
        return train_name;
    }
    public Station from;
    public Station getFrom() {
        return from;
    }
    public Station to;
    public Station getTo() {
        return to;
    }
    public long pnr;
    public long getPnr() {
        return pnr;
    }
    public void setPnr(long pnr) {
        this.pnr = pnr;
    }
    public int passengers_count;
    public int getPassengers_count() {
        return passengers_count;
    }
    public void setPassengers_count(int passengers_count) {
        this.passengers_count = passengers_count;
    }
    public Payment payment;
    
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public ArrayList<Passenger> passengers;
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
    public Ticket(int no,String name,Station from,Station to,long pnr, int passengers_count, long transaction_id, ArrayList<Passenger> passengers,String status,String bookedby,String date) {
        train_no=no;
        train_name=name;
        this.from=from;
        this.to=to;
        this.pnr = pnr;
        this.passengers_count = passengers_count;
        // this.payment = payment;
        this.passengers = passengers;
        this.status=status;
        this.bookedby=bookedby;
        this.journeyDate=date;
    }
    // for collection 
    public Ticket(int no,String name,Station from,Station to,long pnr, int passengers_count,Payment payment, ArrayList<Passenger> passengers,String status,String bookedby,Date date) {
        train_no=no;
        train_name=name;
        this.from=from;
        this.to=to;
        this.pnr = pnr;
        this.passengers_count = passengers_count;
        this.payment = payment;
        this.passengers = passengers;
        this.status=status;
        this.bookedby=bookedby;
        this.Journey_date=date;
    }

    // public Ticket(int no,String name,Station from,Station to,long pnr,ArrayList<Passenger> passengers,String status,String bookedby,Date date) {
    //     train_no=no;
    //     train_name=name;
    //     this.from=from;
    //     this.to=to;
    //     this.pnr = pnr;
    //     // this.passengers_count = passengers_count;
    //     // this.payment = payment;
    //     this.passengers = passengers;
    //     this.status=status;
    //     this.bookedby=bookedby;
    //     this.Journey_date=date;
    // }
}







// package TrainTicketBookingProject.Booking;

// import java.util.ArrayList;
// import TrainTicketBookingProject.persons.*;

// public class Ticket{
//     public  long pnr_no;
//     public  int passengers_count;
//     // public static ArrayList<Payment> payments=new ArrayList<Payment>();
//     public static void addpayment(Payment i){
//         payments.add(i);
//     }
//     public Ticket(ArrayList<Payment> i) {
//         payments = i;
//     }

//     public static void setPayments(ArrayList<Payment> i) {
//         payments = i;
//     }
//     // public static ArrayList<Passenger> passengers=new ArrayList<Passenger>();
//     public Ticket(ArrayList<Passenger> i) {
//         passengers = i;
//     }

//     public static void addpassenger(ArrayList<Passenger> i){
//         passengers.add(i);
//     }
//     public Ticket(long pnr,int count){
//         pnr_no=pnr;
//         passengers_count=count;
//     }
    
//     public static long gerpnr(Ticket i){
//         return i.pnr_no;
//     }
//     public static int getpassengerscount(Ticket i){
//         return i.passengers_count;
//     }
// }