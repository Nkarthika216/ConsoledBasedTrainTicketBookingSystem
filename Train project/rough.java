import java.util.*;
import java.io.*;
public class rough {
    public static void main(String args[]){
    try(BufferedReader ticketReader=new BufferedReader(new FileReader("Tickets.csv"))){
        File oldTicketFile=new File("Tickets.csv");
        String ticket=ticketReader.readLine();
        BufferedWriter ticketWriter=new BufferedWriter(new File("TempTicketFile.csv"));
        File newTicketFile=new File("TempTicketFile.csv");
        ticketWriter.write(ticket);
        ticketWriter.newLine();
        ticketWriter.flush();
        while((ticket=ticketReader.readLine())!=null){
            String ticketDetails[]=ticket.split(",");
            if(ticketDetails[0].equals(""+cancel_pnr)&&ticketDetails[6].equalsIgnoreCase("Booked")){
                ArrayList<String> cancelledPassengers=new ArrayList<String>(Arrays.asList(ticketDetails[8].trim().split(" ")));
                File oldPassengersFile=new File("Passengers.csv");
                BufferedReader passengeReader=new BufferedReader(new FileReader(oldPassengersFile));
                String passenger=passengeReader.readLine();
                File newPassengersFile=new File("TempPassengers.csv");
                BufferedWriter passengerWriter=new BufferedWriter(newPassengersFile);
                passengerWriter.write(passenger);
                passengerWriter.newLine();
                passengerWriter.flush();
                while((passenger=passengeReader.readLine())!=null){
                    String passengerDetails[]=passenger.split(",");
                    if(cancelledPassengers.contains(passengerDetails[0])&&passengerDetails[3].equals("Booked")){
                        // File oldSeatFile=null;
                        // if(passengerDetails[5].equals("null"))
                        // oldSeatFile=new File(ticketDetails[1]+"\\"+ticketDetails[5]+"\\WaitingListSeats.csv");
                        // else
                        // oldSeatFile=new File(ticketDetails[1]+"\\"+ticketDetails[5]+"\\"+passengerDetails[5]+"Seats.csv");
                        // BufferedReader seatReader=new BufferedReader(new FileReader(oldSeatFile));
                        // String seat=seatReader.readLine();
                        // File newSeatFile=new File(ticketDetails[1]+"\\"+ticketDetails[5]+"\\TempSeatsFile.csv");
                        // BufferedWriter seatWriter=new BufferedWriter(newSeatFile);
                        // seatWriter.write(seat);
                        // seatWriter.newLine();
                        // seatWriter.flush();
                        // while((seat=seatReader.readLine())!=null){
                        //     String seatDeatails[]=seat.split(",");
                        //     if(seatDeatails[0].equals(passengerDetails[4])){
                        //         int fromId=findStationId(ticketDetails[3]);
                        //         int toId=findStationId(ticketDetails[4]);
                        //         String newStatus="";
                        //         String previousStatus[]=seatDeatails[2].split("#");
                        //         for(int i=0;i<previousStatus.length;i++){
                        //             if(i>=fromId&&i<toId)
                        //             newStatus+="false";
                        //             else
                        //             newStatus+=previousStatus[i];
                        //         }
                        //         seatWriter.write(seatDeatails[0]+","+seatDeatails[1]+","+newStatus+","+seatDeatails[3]);
                        //         seatWriter.newLine();
                        //         seatWriter.flush();
                        //     }else{
                        //         seatWriter.write(seat);
                        //         seatWriter.newLine();
                        //         seatWriter.flush();
                        //     }
                        // }



                        // passenger writer new content
                        passengerWriter.write(passengerDetails[0]+","+passengerDetails[1]+","+passengerDetails[2]+",Cancelled,"+passengerDetails[4]+","+passengerDetails[5]);
                        passengerWriter.newLine();
                        passengerWriter.flush();
                    }else{
                        passengerWriter.write(passenger);
                        passengerWriter.newLine();
                        passengerWriter.flush();
                    }
                }
                passengeReader.close();
                passengerWriter.close();
                oldPassengersFile.delete();
                newPassengersFile.renameTo(oldPassengersFile);

                //ticket writer
                ticketWriter.write(ticketDetails[0]+","+ticketDetails[1]+","+ticketDetails[2]+","+ticketDetails[3]+","+ticketDetails[4]+","+ticketDetails[5]+",Cancelled,"+ticketDetails[7]+","+ticketDetails[8]);
                ticketWriter.newLine();
                ticketWriter.flush();
            }else{
                ticketWriter.write(ticket);
                ticketWriter.newLine();
                ticketWriter.flush();
            }
        }
        ticketReader.close();
        ticketWriter.close();
        oldTicketFile.delete();
        newTicketFile.renameTo(oldTicketFile);
    }
}
public static void shiftingAgainstCancel(String seatId,String berth,String trainName,String travelDate,String[] status){
    boolean hasChangeInRac=false;
    try{
        BufferedReader seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv"));
        String seat=seatReader.readLine();
        BufferedWriter seatWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv"));
        seatWriter.write(seat);
        seatWriter.newLine();
        seatWriter.flush();
        while((seat=seatReader.readLine())!=null){
            String seatDetails[]=seat.split(",");
            if()
        }

        if(!berth.equals("null"))
        hasChangeInRac=true;
        if(hasChangeInRac){
            BufferedReader racReader=new BufferedReader(trainName+"\\"+travelDate+"\\RacSeats.csv");
            String racSeat=racReader.readLine();
            BufferedWriter racSeatWriter=
        }
    }catch(Exception e){
        System.out.println(e);
    }
    // BufferedReader seatReader=null;
    // if(!berth.equals("null"))
    // seatReader=new BufferedReader(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv");
    // else
    // seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\WaitingListSeats.csv"));
}
}
