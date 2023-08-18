package TrainTicketBookingProject;
import java.util.*;
import java.io.*;
public class sample {
    public static void main(String args[]){
        try{
        String seatId="4";
        BufferedReader bufferedReader=new BufferedReader(new FileReader("KANYAKUMARI EXPRESS\\01-05-2023\\RacPassengers.csv"));
        // new BufferedWriter(new FileWriter("NELLAI EXPRESS\\30-04-2023\\RacPassengers.csv"));
        String racSeat=bufferedReader.readLine();
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("KANYAKUMARI EXPRESS\\01-05-2023\\RacPassengers.csv"));      
        bufferedWriter.write(racSeat);
        bufferedWriter.newLine();
        boolean bool=false;
    int count=0;
    while((racSeat=bufferedReader.readLine())!=null){
        System.out.println("rac seat:  "+racSeat);
        // System.out.println("RAC SEAT READ :   "+bufferedReader.readLine());
        count++;
        // if(count==1)
        // bufferedWriter=new BufferedWriter(new FileWriter("KANYAKUMARI EXPRESS\\04-05-2023\\RacPassengers.csv"));      

    System.out.println("read rac seat:  "+racSeat);
    System.out.println();
        if(racSeat.split(",")[0].equals(seatId)){
            System.out.println("1111");
            bufferedWriter.write(racSeat+"$"+(1)+"#"+0+"-"+2);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bool=true;
        }else{
            System.out.println("2222");
            System.out.println("rac list else");
            System.out.println();
            bufferedWriter.write(racSeat);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
    if(!bool){
        System.out.println("3333");
        System.out.println(seatId);
    // BufferedWriter racAppendWriter=new BufferedWriter(new FileWriter(currentTrain.name+ "\\" + dateInput+"\\RacPassengers.csv",true));
    bufferedWriter.write(seatId+","+(1)+"#"+0+"-"+2);
    System.out.println("Line 1");
    bufferedWriter.newLine();
    System.out.println("Line 2");
    bufferedWriter.flush();
    System.out.println("Line 3");
    }
    bufferedReader.close();
    bufferedWriter.close();
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
// // latest cancel(case 1) logic from bookingnewfile


//  // cancelTicket.status="Cancelled";
//  passengers=cancelTicket.getPassengers();
//  BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
 
//  for(Passenger p:passengers){
//  System.out.println("Berth expectors:  "+currentTrain.berth_expectors.size());
//  // System.out.println("First index ticket pnr:   "+currentTrain.berth_expectors.get(0).pnr);

//  if(p.status.equalsIgnoreCase("booked")){
//  p.status="cancelled";
//  cancelseat=allocatedseats.get(p.id);
//  for(int j=currentTrain.route.indexOf(cancelTicket.from);j<currentTrain.route.indexOf(cancelTicket.to);j++){
//      cancelseat.seat_status.set(j,false);
//  }
//  int from_index=0,to_index=0;
//  if(currentTrain.berth_expectors.size()>0){
//      //traverse berth expectors
//      for(Ticket ticket:currentTrain.berth_expectors){
//          int k=0;
//              for(k=currentTrain.route.indexOf(ticket.from);k<currentTrain.route.indexOf(ticket.to);k++){
//                  if(cancelseat.seat_status.get(k)==true)
//                  break;
//                  }
//                  if(k==currentTrain.route.indexOf(ticket.to)){
//              for(Passenger psr:ticket.getPassengers()){
//                  if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---")){
//                      tempSeat=allocatedseats.get(psr.id);
//                      allocatedseats.put(psr.id,cancelseat);
//                      cancelseat=tempSeat;
//                      System.out.println("Cancel seat berth:  "+cancelseat.berth_type+"    number:  "+cancelseat.number);
//              for(k=currentTrain.route.indexOf(ticket.from);k<currentTrain.route.indexOf(ticket.to);k++){
//                  allocatedseats.get(psr.id).seat_status.set(k,true);
//              }

//              for(int j=currentTrain.route.indexOf(ticket.from);j<currentTrain.route.indexOf(ticket.to);j++){
//                  cancelseat.seat_status.set(j,false);
//              }
//      }
//              }
//          }
//      }
     
//  if(!cancelseat.seat_status.contains(true)){
//  currentTrain.bookedseatList.remove(cancelseat);
//  if(cancelseat.berth_type.equals("Lower"))
//  currentTrain.lbList.addFirst(cancelseat);
//  if(cancelseat.berth_type.equals("Upper"))
//  currentTrain.ubList.addFirst(cancelseat);
//  if(cancelseat.berth_type.equals("Middle"))
//  currentTrain.mbList.addFirst(cancelseat);
//  if(cancelseat.berth_type.equals("Rac"))
//  currentTrain.racList.addFirst(cancelseat);
//             }
//      //update berth expectors
//      for(int j=0;j<currentTrain.berth_expectors.size();j++){
//          int isneedRemove=0;
//          for(Passenger psr:currentTrain.berth_expectors.get(j).getPassengers()){
//              if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---"))
//              {
//                  isneedRemove++;
//                  break;
//              }
//          }
//          if(isneedRemove==0){
//              currentTrain.berth_expectors.remove(j);
//              j--;
//          }
//      }
// }
// else{
//  if(!cancelseat.seat_status.contains(true)){
//      currentTrain.bookedseatList.remove(cancelseat);
//  if(cancelseat.berth_type.equals("Lower"))
//  currentTrain.lbList.addFirst(cancelseat);
//  if(cancelseat.berth_type.equals("Upper"))
//  currentTrain.ubList.addFirst(cancelseat);
//  if(cancelseat.berth_type.equals("Middle"))
//  currentTrain.mbList.addFirst(cancelseat);
//              }
// }
//  }//if
// }