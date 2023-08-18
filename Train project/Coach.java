package TrainTicketBookingProject.Train;

import java.util.ArrayList;

public class Coach{
    public String name;
    public String class_type;
    public int seat_count;
    // int station_count;
    public ArrayList<Seat> seats=new ArrayList<Seat>();
    public Coach(String name, String class_type) {
        this.name = name;
        this.class_type = class_type;
    }
    //new const
    public Coach(String name,String class_type,int seat_count){
        this.name=name;
        this.class_type=class_type;
        this.seat_count=seat_count;
        // for(int k=1;k<=seat_count;k++){
        //    seats.add(new Seat(""+k,class_type,name,class_type,station_count));
        // }
        // this.station_count=station_count;
    }
}