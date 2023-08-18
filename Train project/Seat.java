
package TrainTicketBookingProject.Train;
import java.util.*;
public class Seat{
    public String number;
    public String coachname;
    public String classtype;
    public ArrayList<Boolean> seat_status;
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String berth_type;
// public String status;
    // public String getBerth_type() {
    //     return berth_type;
    // }
    // public void setBerth_type(String berth_type) {
    //     this.berth_type = berth_type;
    // }
    // public String getberthtype(Seat i){
    //     return i.berth_type;
    // }
    // //new
    // public Seat(String no,String berth,String coachname,String classtype,int station_count){
    //     berth_type=berth;
    //     number=no;
    //     seat_status=new ArrayList<Boolean>(station_count);
    //     for(int i=0;i<station_count;i++){
    //         seat_status.add(false);
    //     }
    //     System.out.println(seat_status);
    //     this.coachname=coachname;
    //     this.classtype=classtype;
    // }
    //new const1
    // public Seat(int station_count){
    //     for(int i=0;i<station_count;i++){
    //         seat_status.add(false);
    //     }
    // }
    //new const2
    public Seat(String no,String berth,String coachname,String classtype,ArrayList<Boolean> seat_status){
        berth_type=berth;
        number=no;
        // seat_status=new ArrayList<Boolean>(station_count);
        // for(int i=0;i<station_count;i++){
        //     seat_status.add(false);
        // }
        // System.out.println(seat_status);
        this.coachname=coachname;
        this.classtype=classtype;
        this.seat_status=seat_status;
    }
    // public Seat(String number,String berth,String status,int from,int to){
    //     this.number=number;
    //     this.berth_type=berth;
    //     this.status=status;
    //     this.fromid=from;
    //     this.toid=to;
    // }
    //old
    public Seat(String no,String berth,Coach ch){
        berth_type=berth;
        number=no;
        coachname=ch.name;
        classtype=ch.class_type;
    }
    // for cancel
    public String seatId;
    public String status;
    public int fromid;
    public int toid;    
    public Seat(String seatId,String berth,String status,int fromid,int toid){
        this.seatId=seatId;
        this.berth_type=berth;
        this.status=status;
        this.fromid=fromid;
        this.toid=toid;
    }
}
