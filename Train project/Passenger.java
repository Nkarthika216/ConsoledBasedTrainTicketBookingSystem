package TrainTicketBookingProject.persons;
import java.util.Date;
import TrainTicketBookingProject.Booking.*;
import TrainTicketBookingProject.Train.*;
public class Passenger
{
    public String name;
    public  int age;
    public String dob;
    public  int id;
    public String status;
    public String preference;
    public String seatId;
/*
public int getPassenger_id(){
return id;}
public String getPassenger_name(){
return name;}
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
*/
public Seat seat;
//constructor for rough passenger details
    public Passenger(int id,String name,String dob,String preference,String seatId){
        this.id=id;
        this.name=name;
        this.dob=dob;
        this.preference=preference;
        this.seatId=seatId;
    }
    public Passenger(int id,String name,String dob,Seat i,String status){
        this.name=name;
        // this.age=age;
        this.dob=dob;
        this.id=id;
        this.seat=i;
        this.status=status;
    }
}