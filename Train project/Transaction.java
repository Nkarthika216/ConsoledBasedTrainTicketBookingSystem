package TrainTicketBookingProject.Booking;

import java.time.LocalDateTime;

public class Transaction{
    public String id;
    public String getId() {
        return id;
    }
    public double amount;
    public double getAmount() {
        return amount;
    }
    public LocalDateTime time;
    public LocalDateTime getTime() {
        return time;
    }
    public Transaction(String id, double amount, LocalDateTime time) {
        this.id = id;
        this.amount = amount;
        this.time = time;
    }
    /*      cancel logic for both cancel
                            try{
                                BufferedReader br=new BufferedReader(new FileReader("Tickets.csv"));
                                String ticket="";
                                while(!((ticket=br.readLine())==null)){
                                    if(!(ticket.contains(""+cancel_pnr)))
                                    continue;
                                    else{
                                        String ticketDetails[]=ticket.split(",");
                                        if(ticketDetails[7].equalsIgnoreCase(mail)){
                                        trainName=ticketDetails[1];
                                        cancelFrom=ticketDetails[3];
                                        cancelTo=ticketDetails[4];
                                        ticketStatus=ticketDetails[6];
                                        travelDate=ticketDetails[5];
                                        cancelPassengers=new ArrayList<String>(Arrays.asList(Arrays.split(ticketDetails[8].trim().split(" "))));
                                        }
                                        else if(ticketDetails[6].equalsIgnoreCase("Cancelled"))
                                        System.out.println("Ticket already cancelled...");
                                        else
                                        System.out.println("You can't able to cancel this ticket...");
                                        break;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("Error: while reading tickets for cancell a ticket");
                            }
     */
}