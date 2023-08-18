package TrainTicketBookingProject.Train;

import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;

// import javax.sound.sampled.SourceDataLine;
// import javax.swing.text.html.StyleSheet;

import TrainTicketBookingProject.Booking.Ticket;
import TrainTicketBookingProject.persons.*;

public class TrainDetails {
    public LinkedList<Seat> ubList = new LinkedList<Seat>();
    public LinkedList<Seat> lbList = new LinkedList<Seat>();
    public LinkedList<Seat> mbList = new LinkedList<Seat>();
    public LinkedList<Seat> racList = new LinkedList<Seat>();
    public LinkedList<Seat> wList = new LinkedList<Seat>();
    public ArrayList<Ticket> TicketList = new ArrayList<Ticket>();
    public ArrayList<Coach> coachs = new ArrayList<Coach>();
    public ArrayList<Ticket> berth_expectors = new ArrayList<Ticket>();

    public ArrayList<Ticket> getTicketList() {
        return TicketList;
    }

    public int number;
    public String name;
    public int no_ofcoaches;
    public int total_seats;
    public int seatsPerCoach;
    public int upper;
    public int lower;
    public int middle;
    public ArrayList<Station> route;
    public ArrayList<Seat> bookedseatList = new ArrayList<Seat>();
    public int booked_seats;
    public double rate;
    public int rac = 0;
    public int waitinglist;
    public int rac_count = 0;
    public int station_count;

    public TrainDetails(){}

    public TrainDetails(int number, String name, double rate) {
        this.number = number;
        this.name = name;
        this.rate = rate;
    }

    // new
    public TrainDetails(int number, String name, ArrayList<Station> route, ArrayList<Coach> chs, double rate) {
        this.number = number;
        this.name = name;
        this.route = route;
        this.rate = rate;
        this.coachs = chs;
    
        // try{
        //     BufferedWriter lowerWriter=new BufferedWriter(new FileWriter(name+"\\LowerSeats.csv"));
        //     lowerWriter.write("Id,Number,Berth,Coach name,Class type,Status");
        //     lowerWriter.newLine();
        //     lowerWriter.flush();
        //     BufferedWriter middleWriter=new BufferedWriter(new FileWriter(name+"\\MiddleSeats.csv"));
        //     middleWriter.write("Id,Number,Berth,Coach name,Class type,Status");
        //     middleWriter.newLine();
        //     middleWriter.flush();
        //     BufferedWriter upperWriter=new BufferedWriter(new FileWriter(name+"\\UpperSeats.csv"));
        //     upperWriter.write("Id,Number,Berth,Coach name,Class type,Status");
        //     upperWriter.newLine();
        //     upperWriter.flush();
        //     BufferedWriter racWriter=new BufferedWriter(new FileWriter(name+"\\RacSeats.csv"));
        //     racWriter.write("Id,Number,Berth,Coach name,Class type,Status");
        //     racWriter.newLine();
        //     racWriter.flush();
        // }
        for (Coach ch : chs) {
            this.station_count = route.size();

            total_seats += ch.seat_count;
            for (int i = 1; i <= ch.seat_count; i++) {
                if (i <= ch.seat_count) {
                    ArrayList<Boolean> seat_status = new ArrayList<Boolean>();
                    for (int k = 0; k < station_count; k++) {
                        seat_status.add(false);
                    }
                    lbList.add(new Seat("" + i++, "Lower", ch.name, ch.class_type, seat_status));
                    ch.seats.add(new Seat("" + (i - 1), "Lower", ch.name, ch.class_type, seat_status));
                }
                if (i <= ch.seat_count) {
                    ArrayList<Boolean> seat_status = new ArrayList<Boolean>();
                    for (int k = 0; k < station_count; k++) {
                        seat_status.add(false);
                    }
                    mbList.add(new Seat("" + i++, "Middle", ch.name, ch.class_type, seat_status));
                    ch.seats.add(new Seat("" + (i - 1), "Middle", ch.name, ch.class_type, seat_status));
                }
                if (i <= ch.seat_count) {
                    ArrayList<Boolean> seat_status = new ArrayList<Boolean>();
                    for (int k = 0; k < station_count; k++) {
                        seat_status.add(false);
                    }
                    ubList.add(new Seat("" + i++, "Upper", ch.name, ch.class_type, seat_status));
                    ch.seats.add(new Seat("" + (i - 1), "Upper", ch.name, ch.class_type, seat_status));
                }
                if (i <= ch.seat_count) {
                    ArrayList<Boolean> seat_status = new ArrayList<Boolean>();
                    for (int k = 0; k < station_count; k++) {
                        seat_status.add(false);
                    }
                    racList.add(new Seat("" + i, "Rac", ch.name, ch.class_type, seat_status));
                    ch.seats.add(new Seat("" + i, "Rac", ch.name, ch.class_type, seat_status));
                    rac++;
                }
            }
        }
    }

    public int getavailableseats() {
        booked_seats = bookedseatList.size();
        if (booked_seats > total_seats)
            booked_seats = total_seats;
        return total_seats - booked_seats;
    }
}