//package TrainTicketBookingProject;
package TrainTicketBookingProject.persons;
import java.util.ArrayList;

import TrainTicketBookingProject.persons.*;
import TrainTicketBookingProject.Booking.Ticket;
import TrainTicketBookingProject.Train.*;


public class User{
    public String date_of_birth;
    public User(){}
    public String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // String lastname;
    public String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String mail_id;
    public String getMail_id() {
        return mail_id;
    }
    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }
    public String phone_no;
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    public ArrayList<Ticket> bookedtickets=new ArrayList<Ticket>();
    public void setBookedtickets(ArrayList<Ticket> bkdtickets) {
        bookedtickets = bkdtickets;
    }
    public  ArrayList<Ticket> cancelledtickets=new ArrayList<Ticket>();
    public void addcancelledticket(Ticket i){
        cancelledtickets.add(i);
    }
    public ArrayList getcancelledtickets(){
        return cancelledtickets;
    }
    public User(String name,String email,String pwd,String ph_no){
        this.name=name;
        // lastname=lname;
        password=pwd;
        mail_id=email;
        phone_no=ph_no;
        }

        public User(String name,String date_of_birth,String mailId,String password,String mobileNumber){
            this.name=name;
            this.mail_id=mailId;
            this.password=password;
            this.phone_no=mobileNumber;
            this.date_of_birth=date_of_birth;
        }
    public void addbookedtickets(Ticket i){
        bookedtickets.add(i);
        // System.out.println("Method");
        // Passenger p=new Passenger();
        // ArrayList<Passenger> p=i.getPassengers();
        // System.out.println("outer for");
        // for(Passenger k:p){
        //     System.out.println("inner for");
        //    System.out.println(k.getPassenger_id());
        //    System.out.println(k.getAge());
        //     System.out.println(k.getPassenger_name());
        // }
    }
    public  ArrayList getbookedtickets(){
        return bookedtickets;
    }
}