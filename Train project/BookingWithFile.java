// import
package TrainTicketBookingProject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import TrainTicketBookingProject.Booking.*;
// import TrainTicketBookingProject.Booking.Ticket;
// import TrainTicketBookingProject.Booking.Payment;
// import TrainTicketBookingProject.Booking.Transaction;
// import TrainTicketBookingProject.Booking.Account;

import TrainTicketBookingProject.Train.*;
import TrainTicketBookingProject.persons.*;
// import TrainTicketBookingProject.persons.User;
// import TrainTicketBookingProject.persons.Passenger;
// import TrainTicketBookingProject.Train.Seat;
// import TrainTicketBookingProject.Train.Station;
// import TrainTicketBookingProject.Train.TrainDetails;
// import TrainTicketBookingProject.Train.Coach;





public class BookingWithFile {
    static HashMap<String, User> userdetails = new HashMap<String, User>();
    static HashMap<String, String> logindetails = new HashMap<String, String>();
    static ArrayList<TrainDetails> trains = new ArrayList<TrainDetails>();
    static HashMap<Long, Account> accounts = new HashMap<Long, Account>();
    static HashMap<Long, Transaction> transactions = new HashMap<Long, Transaction>();
    static HashMap<Integer, Seat> allocatedseats = new HashMap<Integer, Seat>();
    static HashMap<Long, Ticket> ticketsList = new HashMap<Long, Ticket>();
    static HashMap<TrainDetails, ArrayList<Ticket>> train_ticket = new HashMap<TrainDetails, ArrayList<Ticket>>();
    static HashMap<Date, ArrayList<TrainDetails>> bookingsWithDate = new HashMap<Date, ArrayList<TrainDetails>>();

    public static boolean isValidDateformat(String date) {
        String regex = "^[0-9]{1,2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        return m.matches();
    }

    public static boolean isValidDate(String date) {
        boolean isValid = true;
        String[] arr = date.split("-");
        if (Integer.parseInt(arr[1]) < 1 || Integer.parseInt(arr[0]) < 1 || Integer.parseInt(arr[2]) < 1) {
            isValid = false;
        }
        // LocalDate
        // birthday=LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
        Year y = Year.of(Integer.parseInt(arr[2]));
        if (Year.isLeap(Integer.parseInt(arr[2])) && Integer.parseInt(arr[1]) == 2) {
            if (Integer.parseInt(arr[0]) > 29)
                isValid = false;
        } else if (!Year.isLeap(Integer.parseInt(arr[2])) && Integer.parseInt(arr[1]) == 2) {
            if (Integer.parseInt(arr[0]) > 28)
                isValid = false;
        } else if (Integer.parseInt(arr[1]) <= 12 && Integer.parseInt(arr[1]) >= 1) {
            if (Integer.parseInt(arr[1]) == 1 || Integer.parseInt(arr[1]) == 3 || Integer.parseInt(arr[1]) == 5
                    || Integer.parseInt(arr[1]) == 7 || Integer.parseInt(arr[1]) == 8 || Integer.parseInt(arr[1]) == 10
                    || Integer.parseInt(arr[1]) == 12) {
                if (Integer.parseInt(arr[0]) > 31)
                    isValid = false;
            } else if (Integer.parseInt(arr[1]) == 4 || Integer.parseInt(arr[1]) == 6 || Integer.parseInt(arr[1]) == 9
                    || Integer.parseInt(arr[1]) == 11) {
                if (Integer.parseInt(arr[0]) > 30)
                    isValid = false;
            }
        } else if (Integer.parseInt(arr[1]) > 12) {
            isValid = false;
        }
        // else
        // isValid=false;
        return isValid;
    }

    public static boolean isValidTravelDate(String date) {
        boolean isValid = false;
        String[] arr = date.split("-");
        LocalDate traveldate = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[0]));
        LocalDate today = LocalDate.now();
        LocalDate valid_date = today.plusMonths(3).plusDays(1);
        Period p = Period.between(today, traveldate);
        Period prd = Period.between(traveldate, valid_date);
        if (!p.isNegative() && !prd.isNegative() && (p.getDays() > 0 || p.getMonths() > 0)) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isValidDOB(String dob) {
        boolean isValid = false;
        String arr[] = dob.split("-");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[0]));
        Period p = Period.between(birthDate, LocalDate.now());
        if (!p.isNegative()) {
            if (p.getYears() > 5)
                isValid = true;
        }
        return isValid;
    }

    public static boolean isValidName(String name) {
        String regex = "[a-zA-Z]{2,}\\s*[\\D]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean isValidMobileNumber(String mobile_no) {
        String regex = "[0]?[+91]?[(]?[6-9][0-9]{2}[)]?[0-9]{7}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile_no);
        return m.matches();
    }

    public static boolean isValidMail(String mail) {
        String regex = "[a-zA-Z0-9][a-zA-Z0-9_[.]]+@{1}[a-zA-Z]{3,}[.[a-zA-Z]{2,}]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.matches();
    }

    public static boolean isValidPassword(String pwd) {
        String regex = "[A-Z]+[a-z]+[\\W]+[0-9]+{8,16}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    public static boolean isValidPnr(String pnr) {
        String regex = "[0-9]{10}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pnr);
        return m.matches();
    }

    public static boolean isValidPreference(String pref) {
        String regex = "[mMlLuU]{1}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pref);
        return m.matches();
    }

    public static boolean isValidCardNumber(String card_no) {
        String regex = "[0-9]{10}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(card_no);
        return m.matches();
    }

    public static void main(String args[])throws ParseException{
        trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));

        trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("DG","dindigul",230),new Station("MS","chennai",270))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));

        trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","tirunelveli",0),new Station("VPT","virudunagar",114),new Station("MDU","madurai",157),new Station("MS","chennai",245))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));

        trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","kanyakumari",0),new Station("TEN","tirunelveli",90),new Station("MDU","madurai",240),new Station("MS","chennai",320))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));
      

        
        accounts.put(9952857934L,new Account("139501000048773",20000,"SBI","Surandai","Pasupathi",new Payment(9952857934L,876,9,2024,"Pasupathi","9952857934")));
        // accounts.put(8220128243L,new Account("139501000012345",10000,"IOB","Surandai","Kumar"));
        // accounts.put(9488057934L,new Account("139501000023456",15000,"KVB","Madurai","Raj"));
        // accounts.put(7708656243L,new Account("139501000081101",27000,"IOB","Chennai","Kani"));

//         try{
//         File user=new File("Users.csv");
//             if(!user.exists()){
//                 user.createNewFile();
//                 BufferedWriter bw=new BufferedWriter(new FileWriter("Users.csv"));
//                 bw.write("Name,Mail id,Password,Phone number");
//                 bw.newLine();
//                 bw.write("karthi,karthi@gmail.com,Nkarthi@123,9952857934");
//                 bw.newLine();
//                 bw.flush();
//                 bw.close();
//             }
//         }catch(Exception e){
//             System.out.println("Error: while creating file user");
//         }


//         try{
//             File stationFile=new File("Station.csv");
//             BufferedWriter bw=new BufferedWriter(new FileWriter("Station.csv"));
//             bw.write("Id,Code,Name");
//             bw.newLine();
//             bw.flush();
//             int id=1;
//             for(TrainDetails train:trains){
//                 for(Station st:train.route){
//                     BufferedReader br=new BufferedReader(new FileReader("Station.csv"));
//                     String line="";
//                     while(!((line=br.readLine())==null)){
//                         if(line.contains(st.name)){
//                             br.close();
//                             break;
//                         }
//                     }
//                     if(line==null){
//                         bw.write(""+id++ +","+st.code+","+st.name);
//                         bw.newLine();
//                         bw.flush();
//                     }
//                 }
//             }
//             bw.close();
//         }catch(Exception e){
//             e.printStackTrace();
//             System.out.println("Error: While writing station...");
//         }


/*
//         //write coach list(one time)

//         try{
//             File Coachs=new File("Coachs.csv");
//             Coachs.createNewFile();
            
//             int id=0;
//             BufferedWriter bw=new BufferedWriter(new FileWriter("Coachs.csv"));
//             bw.write("  Id  ,   Name  ,  Class type  ,  Seat count ");
//             bw.newLine();
//             bw.flush();
//             int t=0;
//             for(TrainDetails train:trains){
//                 int s=0;
//                 for(Coach ch:train.coachs){
//             BufferedReader br=new BufferedReader(new FileReader("Coachs.csv"));
//                     String line="";
//                     while(((line=br.readLine())!=null)){
//                             if(line.contains(ch.name+","+ch.class_type+","+ch.seat_count)){
//                             break;
//                         }
//                     }
//                     if(line==null){
//                         bw.write(""+(++id)+","+ch.name+","+ch.class_type+","+ch.seat_count);
//                         bw.newLine();
//                         bw.flush();
//                     }
//             br.close();
//                 }
//             }
//             bw.close();
//          }catch(Exception e){
//             System.out.println("Error: while writing coach");
//         }

*/
        
//         // //Train Details file(one time)
//         File trainFile=new File("Trains.csv");
//         try{
//             BufferedWriter bw=new BufferedWriter(new FileWriter("Trains.csv"));
//             bw.write("Id,Number,Name,Route,Rate");
//             bw.newLine();
//             int trainId=0;
//             for(TrainDetails train:trains){
//                 trainId++;
//                 String route="";
//                 File trainFolder=new File(train.name);
//                 trainFolder.mkdir();
//                 int count=0;
//                 for(Station st:train.route){
//                     count++;
//                     BufferedReader br=new BufferedReader(new FileReader("Station.csv"));
//                     String line="";
//                     while(!((line=br.readLine())==null)){
//                         if((line).contains(st.name)){
//                             String arr[]=line.split(",");
//                             route+=arr[0]+"_"+st.distance;
//                             if(count!=(train.route).size())
//                             route+="#";
//                         }
//                     }
//                     br.close();
//                 }
//                 int num=0;
//                 String chId="";
//                 int number=0;
//                 // try{
//                     File coachlistFile=new File(train.name+"\\CoachList.csv");
//                     coachlistFile.createNewFile();
//                     int trainCoachId=0;
//                 BufferedWriter bWriter=new BufferedWriter(new FileWriter(train.name+"\\"+"CoachList.csv"));
//                 bWriter.write("Id,Coach name,Coach type,Seat count");
//                 bWriter.newLine();
//                 bWriter.flush();
//                 for(Coach ch:train.coachs){
//                     num++;
                        // bWriter.write(num,ch.name,ch.class_type,ch.seat_count);
                        // bWriter.newLine();
                        // bWriter.flush();
                        // chId+=num;
                        // if(num!=train.coachs.size())
                        // chId+="#";
//               /*      File coachFile=new File("Coachs.csv");
//                         BufferedReader br=new BufferedReader(new FileReader("Coachs.csv"));
//                         String line="";
//                         while(!((line=br.readLine())==null)){
//                             if(line.contains("Id"))
//                             continue;
//                             String arr[]=line.split(",");
//                             if(arr[1].equalsIgnoreCase(ch.name)&&arr[2].equalsIgnoreCase(ch.class_type)&&arr[3].equalsIgnoreCase(""+ch.seat_count)){
//                                 chId+=arr[0];
//                                 bWriter.write(""+(++trainCoachId)+","+(ch.class_type.equals("Sleeper")?"SL"+trainCoachId : "S"+trainCoachId)+","+arr[0]);
//                                 bWriter.newLine();
//                                 bWriter.flush();
//                                 if(num!=(train.coachs).size())
//                                 chId+="#";
//                             }
//                         }*/
//                 }
//                 bWriter.close();
//                 bw.write(trainId+","+train.number+","+train.name+","+route+","+train.rate);
//                 bw.newLine();
//             }
//             bw.flush();
//             bw.close();
//         }catch(Exception e){
//             System.out.println("Error: while writing traindetails");
//             e.printStackTrace();
//         }

//         File accountFile=new File("Accounts.csv");
//         try{
//             if(!(accountFile.exists()))
//             accountFile.createNewFile();
//             int accountId=0;
//             int paymentId=0;
//             BufferedWriter bw=new BufferedWriter(new FileWriter("Accounts.csv"));
//             BufferedWriter bWriter=new BufferedWriter(new FileWriter("Payments.csv"));
//             bw.write("Id,Account number,Bank name,Branch name,Account holder,Balance");
//             bw.newLine();
//             bw.flush();
//             bWriter.write("Id,Card number,Ccv,Expiry month,Expiry year,Mobile number,Account id");
//             bWriter.newLine();
//             bWriter.flush();
//             for(Map.Entry<Long,Account> map:accounts.entrySet()){
//                 // Long card_no=map.getKey();
//                 Account ac=map.getValue();
//                 // if(ac.payment!=null)
//                 // paymentId++;
//                 // try{
//                     BufferedReader br=new BufferedReader(new FileReader("Accounts.csv"));
//                     String line="";
//                     while(!((line=br.readLine())==null)){
//                         if(line.contains(ac.number)&&line.contains(ac.bankname)&&line.contains(ac.branch_name)&&line.contains(ac.holder_name)){
//                             br.close();
//                             break;
//                         }
//                     }
//                     if(line==null){
//                         br.close();
//                         bw.write(""+(++accountId)+","+ac.number+","+ac.bankname+","+ac.branch_name+","+ac.holder_name+","+ac.getBalance());
//                         bw.newLine();
//                         bw.flush();
//                         bWriter.write(""+(++paymentId+","+(ac.payment).card_no+","+(ac.payment).ccv+","+(ac.payment).exp_month+","+(ac.payment).exp_year+","+(ac.payment).holder_mobileno+","+accountId));
//                         bWriter.newLine();
//                         bWriter.flush();
//                     }else{
//                         bWriter.write(""+(++paymentId+","+(ac.payment).card_no+","+(ac.payment).ccv+","+(ac.payment).exp_month+","+(ac.payment).exp_year+","+(ac.payment).holder_mobileno+","+line.split(",")[0]));
//                         bWriter.newLine();
//                         bWriter.flush();
//                     }
//                     bw.close();
//                     bWriter.close();
//                 // }
//         }
//     }catch(Exception e){
//             e.printStackTrace();
//             System.out.println("Error : while writing account file");
//         }

// //   write passenger file 
//         try{
//                 File passengerFile=new File("Passengers.csv");
//                 passengerFile.createNewFile();
//                 BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("Passengers.csv"));
//                 passengerWriter.write("Id,Name,Date of birth,Status,Seat id,Berth,Coach name,Coach type,Travel");
//                 passengerWriter.newLine();
//                 passengerWriter.flush();
//                 passengerWriter.close();
//         }catch(Exception e){
//             System.out.println("Error: while writing passengers...");
//         }
//     try{
//         File ticketFile=new File("Tickets.csv");
//         ticketFile.createNewFile();
//         BufferedWriter ticketWriter=new BufferedWriter(new FileWriter("Tickets.csv"));
//         ticketWriter.write("Id(PNR),Train name,Train number,From,To,Travel date,Status,Booked by,Passengers");
//         ticketWriter.newLine();
//         ticketWriter.flush();
//         ticketWriter.close();
//     }catch(Exception e){
//         System.out.println("Error: while creating passengers file");
//     }
        long PNR=4243361634L;
        int passenger_id=1;int transaction_id=34720;
        TrainDetails currentTrain=null;
        User currentUser=null;
        Ticket currentTicket=null;
        ArrayList<Passenger> passengers=new ArrayList<Passenger>();
        Scanner in=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            boolean excp=true;
            byte choice=0;
            while(excp){
            try{
                System.out.println();
            System.out.println("1.SignUp \n2.Login \n3.Exit \nEnter your choice");
            choice=in.nextByte();
            excp=false;
        }
            catch(Exception e){
                System.out.println("Invalid choice");
                in.nextLine();
            }
        }
            switch(choice){
                case 1:
                boolean  loop=false; 
                System.out.println();
                System.out.println("Enter your name");
                in.nextLine();
                String name=in.nextLine();
                loop=isValidName(name);
                while(!loop){
                   if(!loop){
               System.out.println("Enter a valid name");
               name=in.nextLine();
               loop=isValidName(name);
                   }}

                // String lname=in.nextLine();
                System.out.println();
                System.out.println("Enter your mail id");
                String mail_id=in.nextLine();
                loop=isValidMail(mail_id);
                // byte count_check=0;
                 while(!loop){
                    // count_check++;
                    if(!loop){
                System.out.println("Enter a valid mail id");
                mail_id=in.nextLine();
                loop=isValidMail(mail_id);
                    }}

                    mail_id=mail_id.toLowerCase();
                    System.out.println();
                System.out.println("Enter your password");
                String password=in.nextLine();
                loop=isValidPassword(password);
                while(!loop){
                   if(!loop){
               System.out.println("Enter a valid password");
               password=in.nextLine();
               loop=isValidPassword(password);
                   }}

                   System.out.println();
                System.out.println("Enter your phone number");
                String phone_no=in.nextLine();
                loop=isValidMobileNumber(phone_no);
                 while(!loop){
                    if(!loop){
                System.out.println("Enter a valid phone number");
                phone_no=in.nextLine();
                loop=isValidMobileNumber(phone_no);}
                }
                    try{
                        BufferedReader br=new BufferedReader(new FileReader("Users.csv"));
                        String user="";
                        while((user=br.readLine())!=null){
                            String arr[]=user.split(",");
                            if(arr[1].equalsIgnoreCase(mail_id)){
                                System.out.println("User already exists");
                                br.close();
                                break;
                            }
                        }
                           if(user==null){
                                    BufferedWriter bw=new BufferedWriter(new FileWriter("Users.csv",true));
                                    bw.write(""+name+","+mail_id+","+password+","+phone_no);
                                    bw.newLine();
                                    bw.flush();
                                    bw.close();
                                    System.out.println("Successfully signed up");
                            }
                    }catch(Exception e){
                        System.out.println("Error: while check user details");
                    }
                break;
                case 2:
                byte count_check=1;
                System.out.println();
                System.out.println("Enter your mail id");
                in.nextLine();
                String mail=in.nextLine();
                loop=isValidMail(mail);
                 while(!loop&&count_check<3){
                    count_check++;
                    if(!loop){
                System.out.println("Enter a valid mail id");
                mail=in.nextLine();
                loop=isValidMail(mail);
                    }
                }

                    System.out.println();
                    count_check=1;
                System.out.println("Enter your password");
                String pwd=in.nextLine();
                loop=isValidPassword(pwd);
                while(!loop&&count_check<3){
                    count_check++;
                   if(!loop){
               System.out.println("Enter a valid password");
               pwd=in.nextLine();
               loop=isValidPassword(pwd);
                   }
                }

                boolean login_status=isValidLogin(logindetails,mail,pwd);
                if(login_status)
                {
                    System.out.println();
                System.out.println("Login success...");
                currentUser=userdetails.get(mail);
                boolean lp=true;
                while(lp){
                excp=true;
                byte ch=0;
                while(excp){
                    try{
                        System.out.println();
                        System.out.println("1.search trains \n2.view PNR status \n3.cancel ticket \n4.view all bookings \n5.Logout  \nEnter your choice");
                        ch=in.nextByte();
                    excp=false;
                }
                    catch(Exception e){
                        System.out.println("Invalid choice");
                        in.nextLine();
                    }
                }

                switch(ch){
                    case 1:
                    System.out.println();
                    System.out.println("Journey date(dd-mm-yyyy)");
                    String dateInput="";
                    excp=true;
                    Date date=null;
                    while(excp){
                        try{
                            dateInput=in.next();
                        // excp=!(isValidDateformat(dt)&&isValidTravelDate(isValidDate(dt)));
                        System.out.println(dateInput+"in");
                        excp=isValidDateformat(dateInput);
                        if(!excp){
                            System.out.println("Invalid date format\nEnter valid date format");
                            excp=true;
                            continue;
                        }
                        excp=isValidDate(dateInput);
                        if(!excp){
                            System.out.println("Invalid date\nEnter valid date");
                            excp=true;
                            continue;
                        }
                        excp=isValidTravelDate(dateInput);
                        if(!excp){
                            System.out.println("Journey date must be within 90 days from tomorrow\n\nEnter valid date");
                            excp=true;
                            continue;
                        }
                        if(excp){
                        date=new SimpleDateFormat("dd-MM-yyyy").parse(dateInput);
                        excp=false;
                        }
                        // else{
                        //     System.out.println(" Journey date must be within 90 days from tomorrow\n\nEnter valid date");
                        // }
                        }catch(Exception e){
                            System.out.println("Invalid date\n\nEnter valid date");
                            excp=true;
                        }
                    }
                    System.out.println("From :");
                    in.nextLine();
                    String From=in.nextLine().toLowerCase();
                    System.out.println();
                    System.out.println("To  :");
                    String To=in.nextLine().toLowerCase();
                    int fromid=findStationId(From);
                    int toid=findStationId(To);
                    HashMap<TrainDetails,Integer> availabletrainhash=availableTrains(dateInput,From,To);
                    ArrayList<TrainDetails> al=new ArrayList<TrainDetails>();
                    if(availabletrainhash.size()>0){
                        Station fromStation=null;
                        Station toStation=null;
                        ArrayList<Seat> shorttime_availableseats=new ArrayList<Seat>();
                        byte num=0;
                        System.out.println();
                        System.out.println("Train id     Train number   "+"    Train name       "+"Available seats      "+"Ticket charge");
                            for(Map.Entry<TrainDetails,Integer> map:availabletrainhash.entrySet()){
                            num++;

                            int short_time_bookedseats=map.getValue();
                            currentTrain=map.getKey();
                            al.add(map.getKey());
                           System.out.println("   "+num+".       "+currentTrain.number+"       "+currentTrain.name+"                  "+(currentTrain.getavailableseats()+short_time_bookedseats)+"              Rs."+getticketcharge(al, From, To).get(num-1));
                        }
                        boolean book=true;
                       while(book){
                        excp=true;
                        byte id=0;
                while(excp){
                    try{
                        System.out.println();
                         System.out.println("To book tickets enter train id and enter 0 to exit \nAt a time only two bookings are allowed here");
                         id=in.nextByte();
                        excp=false;
                }
                    catch(Exception e){
                        System.out.println("Invalid choice");
                        in.nextLine();
                    }
                }
                    String seat_no="";String berth="";
                            if(id<=num){
                                if(id==0)
                                lp=false;
                                else if(id>0){
                                currentTrain=al.get(id-1);int limit=6;
                                int from_index=0,to_index=0;

                            boolean addpass=true;
                            int lt=0;int adult_count=0;
                            ArrayList<Passenger> rough=new ArrayList<Passenger>();
                            excp=true;
                            byte pass_count=0;
                    while(excp){
                        try{
                            System.out.println();
                            System.out.println("Enter passenger count(Each passenger should have age above 5)");
                            pass_count=in.nextByte();
                            if(pass_count>limit){
                            System.out.println("Invalid input");}
                            else  excp=false;
                    }
                        catch(Exception e){
                            System.out.println("Invalid input");
                            in.nextLine();
                        }
                    }
                                String preferenceList="";
                                addpass=true;
                                int innerloopcount=0;
                                PNR++;
                                ArrayList<Passenger> roughPassengerDetails=new ArrayList<Passenger>();
                           while(addpass&&innerloopcount<limit){
                                adult_count++;
                                lt++;
                                innerloopcount++;
                                System.out.println();
                                System.out.println("Enter passenger name");
                                in.nextLine();
                                name=in.nextLine();
                                    loop=isValidName(name);
                                    while(!loop){
                                    if(!loop){
                                System.out.println("Enter a valid name");
                                name=in.nextLine();
                                loop=isValidName( name);
                                    }}

                                    System.out.println();
                                    System.out.println("Enter date of birth(dd-mm-yyyy)");
                                    String dob="";
                                    excp=true;
                                    Date birthDate=null;
                                    int age=0;
                                    while(excp){
                                        try{
                                        dob=in.nextLine();
                                        excp=isValidDateformat(dob);
                                        if(!excp){
                                            System.out.println("Invalid date format\nEnter valid date format");
                                            excp=true;
                                            continue;
                                        }
                                        excp=isValidDate(dob);
                                        if(!excp){
                                            System.out.println("Invalid date\nEnter valid date");
                                            excp=true;
                                            continue;
                                        }
                                        excp=isValidDOB(dob);
                                        if(!excp){
                                            System.out.println("Birth year of the traveler should be less than 6 from the current year\n\nEnter valid date");
                                            excp=true;
                                            continue;
                                        }
                                        if(excp){
                                            birthDate=new SimpleDateFormat("dd-MM-yyyy").parse(dob);
                                            excp=false;
                                        }
                                        }catch(Exception e){
                                            System.out.println("Invalid date\n\nEnter valid date");
                                            excp=true;
                                        }
                                    }
                                System.out.println();                                
                                System.out.println("Enter your seat preference(M/L/U)");
                            String preference="";
                            loop=true;
                        while(loop){
                                preference=in.next();
                                loop=!isValidPreference(preference);
                                if(loop)
                                System.out.println("Enter a valid preference");
                        }
                        
                    preferenceList+=preference;
            
                    roughPassengerDetails.add(new Passenger(passenger_id++,name,dob,preference," "));
                            if(lt==pass_count)
                            addpass=false;
                            }//while
                            int fromStatusPosition=-1;
                            int toStatusPosition=-1;
                            try{
                                String train="";
                                BufferedReader trainReader=new BufferedReader(new FileReader("Trains.csv"));
                                while(!((train=trainReader.readLine())==null)){
                                    if(train.contains("Id"))
                                    continue;
                                    else{
                                        if(train.contains(currentTrain.name)){
                                            String route[]=train.split(",")[3].split("#");
                                            String stationId[]=new String[route.length];
                                            String distance[]=new String[route.length];
                                            for(int i=0;i<route.length;i++){
                                                stationId[i]=route[i].split("_")[0];
                                                if(stationId[i].equals(""+fromid))
                                                fromStatusPosition=i;
                                                if(stationId[i].equals(""+toid))
                                                toStatusPosition=i;
                                                if(fromStatusPosition!=-1&&toStatusPosition!=-1)
                                                break;
                                                // distance[i]=route[i].split("_")[1];
                                                // if(route.split("_"))
                                            }
                                            // for(int i=0;i<)
                                            break;
                                        }
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("Error: while find seat status position ");
                            }
                            double ticketcost=totalticketcost(adult_count, getticketcharge(al, From, To).get(id-1));
                            System.out.println();
                            System.out.println("Ticket charge:    "+ticketcost);
                            adult_count=0;
                                long card_no=0;
                                boolean isValidCard=false;
                                num=0;
                                while(!isValidCard&&num<3){
                                    num++;
                            excp=true;
                                while(excp){
                                try{
                                System.out.println();
                                System.out.println("Enter card number");
                                card_no=in.nextLong();
                                excp=!isValidCardNumber(""+card_no);
                                if(excp)
                                System.out.println("Invalid card number");
                                }
                                catch(Exception e){
                                System.out.println("Invalid card number");
                                in.nextLine();
                                }
                                }
                                System.out.println();
                                System.out.println("Enter ccv");
                                int ccv=0;
                                excp=true;
                                while(excp){
                                    try{
                                ccv=in.nextInt();
                                excp=!(ccv<1000&&ccv>=100);
                                if(excp)
                                System.out.println("Invalid input\nEnter valid input");
                                    }catch(Exception e){
                                        System.out.println("Invalid input\nEnter valid input");
                                    }
                                }
                                System.out.println();
                                System.out.println("Exp month");
                                int exp_month=0;
                                excp=true;
                                while(excp){
                                    try{
                                exp_month=in.nextInt();
                                excp=!(exp_month<=12&&exp_month>0);
                                if(excp)
                                System.out.println("Invalid month\nEnter valid month");
                                    }catch(Exception e){
                                        System.out.println("Invalid month\nEnter valid month");
                                    }
                            }
                                System.out.println();
                                System.out.println("Exp year");
                                excp=true;
                                int exp_year=0;
                                while(excp){
                                    try{
                                exp_year=in.nextInt();
                                excp=!(exp_year==2023||exp_year==2024||exp_year==2025);
                                if(excp)
                                System.out.println("Invalid year\nEnter valid year");
                                    }catch(Exception e){
                                        System.out.println("Invalid Year\nEnter valid year");
                                    }
                                }
                                System.out.println();
                                System.out.println("Card holder name");
                                excp=true;
                                String holdername="";
                                in.nextLine();
                                while(excp){
                                    try{
                                holdername=in.nextLine();
                                excp=!isValidName(holdername);
                                if(excp)
                                System.out.println("Invalid name\nEnter valid name");
                                    }catch(Exception e){
                                        System.out.println("Invalid name\nEnter valid name");
                                    }
                                }
                                System.out.println();
                                System.out.println("Enter your mobile number");
                                String mobile=in.nextLine();
                                loop=isValidMobileNumber(mobile);
                                while(!loop){
                                    if(!loop){
                                        System.out.println("Enter valid mobile number");
                                        mobile=in.nextLine();
                                        loop=isValidMobileNumber(mobile);
                                    }
                                }
                            
                                //  //is valid card details check from file
                              try{
                                // ????
                                BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
                                String passenger="";
                                int passengerId=0;
                                while(!((passenger=passengerReader.readLine())==null)){
                                    passengerId++;
                                }
                                passengerReader.close();
                                String type="";
                                String account="";
                                BufferedReader br=new BufferedReader(new FileReader("Payments.csv"));
                                String line="";
                                while(!((line=br.readLine())==null)){
                                    if(line.contains("Id")||!line.split(",")[1].equals(""+card_no))
                                    continue;
                                    String arr[]=line.split(",");
                                    if((""+card_no).equals(arr[1])&&ccv==Integer.parseInt(arr[2])&&
                                    exp_month==Integer.parseInt(arr[3])&&exp_year==Integer.parseInt(arr[4])&&mobile.equals(arr[5])){
                                        BufferedReader bReader=new BufferedReader(new FileReader("Accounts.csv"));
                                        while(!((account=bReader.readLine())==null)){
                                            if(account.contains("Id")||!account.split(",")[0].equals(line.split(",")[6]))
                                            continue;
                                            if(arr[6].equals(account.split(",")[0])){
                                            if(holdername.equalsIgnoreCase(account.split(",")[4])){
                                                br.close();
                                                bReader.close();
                                                isValidCard=true;
                                            break;
                                            }
                                        }
                                        }
                                    }
                                    if(isValidCard)
                                    break;
                                }
                                    ////here
                                        System.out.println("Account:   "+account);
                                        // try
                                        if(account!=null){
                                            //allocate seats here
                                            int count=0;
                                            // boolean hasSeat=false;
                                            String seatId=" ";
                                            String passengerList="";
                                            BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("Passengers.csv",true));
                                            
                                            BufferedReader ticketReader=new BufferedReader(new FileReader("Tickets.csv"));
                                            String ticket="";
                                            int ticketId=0;
                                            while(!((ticket=ticketReader.readLine())==null)){
                                                ticketId++;
                                            }
                                            ticketReader.close();
                                            int allocatedSeatsCount=0;
                                            String statusArr[]=null;
                                            //to find status array size for waiting list seat status creation
                                            try{
                                                BufferedReader trainReader=new BufferedReader(new FileReader("Trains.csv"));
                                                String train="";
                                                while((train=trainReader.readLine())!=null){
                                                    if(train.split(",")[2].equalsIgnoreCase(currentTrain.name)){
                                                       int length=(train.split(",")[3].split("#")).length;
                                                       statusArr=new String[length-1]; 
                                                    }
                                                }
                                                trainReader.close();
                                            }catch(Exception e){
                                                System.out.println("Error:  while find status array size");
                                            }
                                            while(count<5&&allocatedSeatsCount!=roughPassengerDetails.size()){
                                                allocatedSeatsCount=0;
                                                count++;
                                                for(Passenger psr:roughPassengerDetails){
                                                    if(!psr.seatId.equals(" "))
                                                    continue;
                                                    else if(count==1){
                                                        if(psr.preference.equalsIgnoreCase("M"))
                                                        type="Middle";
                                                        else if(psr.preference.equalsIgnoreCase("L"))
                                                        type="Lower";
                                                        else if(psr.preference.equalsIgnoreCase("U"))
                                                        type="Upper";
                                                        System.out.println("Passenger id before method:  "+psr.seatId);
                                                        psr.seatId=allocateSeat(currentTrain,type,dateInput,psr.seatId,fromStatusPosition,toStatusPosition,true/*,psr.id*/);
                                                        System.out.println("Psr.id:  "+psr.id);
                                                    }
                                                    else if(count==2){
                                                        if(psr.preference.equalsIgnoreCase("M"))
                                                        type="Upper";
                                                        else if(psr.preference.equalsIgnoreCase("L"))
                                                        type="Middle";
                                                        else if(psr.preference.equalsIgnoreCase("U"))
                                                        type="Middle";
                                                        psr.seatId=allocateSeat(currentTrain,type,dateInput,psr.seatId,fromStatusPosition,toStatusPosition,true/*,psr.id*/);
                                                        System.out.println("Psr.id:  "+psr.id);
                                                    }
                                                    else if(count==3){
                                                        if(psr.preference.equalsIgnoreCase("M"))
                                                        type="Lower";
                                                        else if(psr.preference.equalsIgnoreCase("L"))
                                                        type="Upper";
                                                        else if(psr.preference.equalsIgnoreCase("U"))
                                                        type="Lower";
                                                        psr.seatId=allocateSeat(currentTrain,type,dateInput,psr.seatId,fromStatusPosition,toStatusPosition,true/*,psr.id */);
                                                        System.out.println("Psr.id:  "+psr.id);
                                                    }
                                                    else if(count==4){
                                                        type="Rac";
                                                        psr.seatId=allocateSeat(currentTrain,"Rac",dateInput,psr.seatId,fromStatusPosition,toStatusPosition,true/*,psr.id */);
                                                        System.out.println("Psr.id:  "+psr.id);
                                                        
                                                        // rac passengers file .....

                                                        if(!psr.seatId.equals(" ")){
                                                            BufferedReader bufferedReader=new BufferedReader(new FileReader(currentTrain.name+ "\\" + dateInput+"\\RacPassengers.csv"));
                                                            String racSeat=bufferedReader.readLine();
                                                            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(currentTrain.name+ "\\" + dateInput+"\\RacPassengers.csv"));
                                                            bufferedWriter.write(racSeat);
                                                            bufferedWriter.newLine();
                                                            bufferedWriter.flush();
                                                            boolean bool=false;
                                                        while((racSeat=bufferedReader.readLine())!=null){
                                                            System.out.println("Rac seat:    "+racSeat);
                                                        System.out.println("Psr seat:  "+psr.seatId.trim());
                                                        System.out.println(racSeat.split(",")[0]);
                                                        System.out.println((racSeat.split(",")[0].trim()).equals(psr.seatId.trim()));//????
                                                            if((racSeat.split(",")[0].trim()).equals(psr.seatId.trim())){
                                                                System.out.println("IF block...");
                                                                String writeString=racSeat+"$"+(passengerId)+"#"+fromStatusPosition+"-"+toStatusPosition;
                                                                bufferedWriter.write(writeString);
                                                                bufferedWriter.newLine();
                                                                bufferedWriter.flush();
                                                                bufferedReader.readLine();
                                                                bool=true;
                                                            }else{
                                                                System.out.println("Else block....");
                                                                bufferedWriter.write(racSeat);
                                                                bufferedWriter.newLine();
                                                                bufferedWriter.flush();
                                                            }
                                                        }
                                                        // bufferedWriter.close();
                                                        bufferedReader.close();
                                                        if(!bool){
                                                        // BufferedWriter racAppendWriter=new BufferedWriter(new FileWriter(currentTrain.name+ "\\" + dateInput+"\\RacPassengers.csv",true));
                                                        bufferedWriter.write(psr.seatId+","+(passengerId)+"#"+fromStatusPosition+"-"+toStatusPosition);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.flush();
                                                        }
                                                        bufferedWriter.close();
                                                        }
                                                    }
                                                    else{
                                                        System.out.println("Count:  "+count);
                                                        type="WaitingList";
                                                        // BufferedReader seatFileReader=new BufferedReader(new FileReader(currentTrain.name+"\\"+dateInput+"\\"+"WaitingListSeats.csv"));
                                                        // BufferedWriter seatFileWriter=null;
                                                        // new BufferedWriter(new FileWriter(currentTrain.name+"\\"+dateInput+"\\"+"WaitingListSeats.csv"));
                                                        BufferedReader seatFileReader=new BufferedReader(new FileReader(currentTrain.name+"\\"+dateInput+"\\"+"WaitingListSeats.csv"));
                                                        String seat=seatFileReader.readLine();
                                                        System.out.println();
                                                        System.out.println("File name:    "+currentTrain.name+"\\"+dateInput+"\\"+"WaitingListSeats.csv");
                                                        System.out.println();
                                                        BufferedWriter seatFileWriter=new BufferedWriter(new FileWriter(currentTrain.name+"\\"+dateInput+"\\"+"WaitingListSeats.csv"));
                                                        seatFileWriter.write(seat);
                                                        seatFileWriter.newLine();
                                                        seatFileWriter.flush();
                                                            boolean bool=false;
                                                            int wlseatCount=1;
                                                            // String statusArr[]=seat.split(",")[1].split("#");
                                                            System.out.println("Status array:  "+Arrays.toString(statusArr));
                                                            // boolean hasSeat=false;
                                                            System.out.println(seat+"seathtsasdgtDT");
                                                            // System.out.println(seatFileReader.readLine()+"asdfghjkl");
                                                            AA:
                                                            while(!((seat=seatFileReader.readLine())==null)){
                                                                wlseatCount++;
                                                                if(!bool){
                                                                        System.out.println(seat+"seat");
                                                                     statusArr=seat.split(",")[1].split("#");
                                                                    System.out.println("Status array:  "+Arrays.toString(statusArr));
                                                                    int i=0;
                                                                    System.out.println(seat+"seat1");
                                                                    for(i=fromStatusPosition;i<toStatusPosition;i++){
                                                                        if(statusArr[i].equalsIgnoreCase("true")){
                                                                            seatFileWriter.write(seat);
                                                                            seatFileWriter.newLine();
                                                                            seatFileWriter.flush();
                                                                        continue AA;
                                                                        }
                                                                    }
                                                                    if(i==toStatusPosition){
                                                                        System.out.println(seat+"seat3");
                                                                        bool=true;
                                                                    psr.seatId=seat.split(",")[0];
                                                                    String status="";
                                                                    for(i=0;i<statusArr.length;i++){
                                                                        if(i>=fromStatusPosition&&i<toStatusPosition)
                                                                        status+="true";
                                                                        else
                                                                        status+=statusArr[i];
                                                                        if(i!=statusArr.length-1)
                                                                        status+="#";
                                                                    }
                                                                    seatFileWriter.write(psr.seatId+","+status);
                                                                    seatFileWriter.newLine();
                                                                    seatFileWriter.flush();
                                                                }
                                                            }
                                                        // }
                                                            else{
                                                                seatFileWriter.write(seat);
                                                                seatFileWriter.newLine();
                                                                seatFileWriter.flush();
                                                            }
                                                                // }
                                                            }//else
                                                        seatFileReader.close();
                                                        // seatFileWriter.close();
                                                        if(!bool){
                                                            String newSeatStatus="";
                                                            for(int k=0;k<statusArr.length;k++){
                                                                if(k>=fromStatusPosition&&k<toStatusPosition)
                                                                newSeatStatus+="true";
                                                                else
                                                                newSeatStatus+="false";
                                                                if(k!=statusArr.length-1)
                                                                newSeatStatus+="#";
                                                            }
                                                            // BufferedWriter bw=new BufferedWriter(new FileWriter(currentTrain.name+"\\"+dateInput+"\\WaitingListSeats.csv",true));
                                                            seatFileWriter.write("WL"+wlseatCount+","+newSeatStatus);
                                                            psr.seatId="WL"+wlseatCount;
                                                            seatFileWriter.newLine();
                                                            seatFileWriter.flush();
                                                        }
                                                        seatFileWriter.close();


                                                        String waitinglistSeat="";
                                                        BufferedReader bufferedReader=new BufferedReader(new FileReader(currentTrain.name+ "\\" + dateInput+"\\WaitingListPassengers.csv"));
                                                        waitinglistSeat=bufferedReader.readLine();
                                                        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(currentTrain.name+ "\\" + dateInput+"\\TempFile.csv"));
                                                        bufferedWriter.write(waitinglistSeat);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.flush();
                                                        boolean booln=false;
                                                        while((waitinglistSeat=bufferedReader.readLine())!=null){
                                                            System.out.println("Waitinglist seat:  "+waitinglistSeat);
                                                            System.out.println("Psr.seatid:  "+psr.seatId);
                                                            if(waitinglistSeat.split(",")[0].equals(psr.seatId)){
                                                                System.out.println("wl if block.....");
                                                                String correctString=waitinglistSeat+"$"+(passengerId)+"#"+fromStatusPosition+"-"+toStatusPosition;
                                                                bufferedWriter.write(correctString);
                                                                bufferedWriter.newLine();
                                                                bufferedWriter.flush();
                                                                // System.out.println("Wl if block read line:  "+bufferedReader.readLine());
                                                                booln=true;
                                                                // waitinglistSeat=bufferedReader.readLine();
                                                                // if(!waitinglistSeat.contains(psr.seatId)){
                                                                // bufferedWriter.write(waitinglistSeat);
                                                                // bufferedWriter.newLine();
                                                                // bufferedWriter.flush();
                                                                // }
                                                            }else{
                                                                System.out.println("Wl else block....");
                                                                bufferedWriter.write(waitinglistSeat);
                                                                bufferedWriter.newLine();
                                                                bufferedWriter.flush();
                                                            }
                                                        }
                                                        if(!booln){
                                                            System.out.println("New if block...");
                                                        bufferedWriter.write(psr.seatId+","+(passengerId)+"#"+fromStatusPosition+"-"+toStatusPosition);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.flush();
                                                        }
                                                        bufferedReader.close();
                                                        bufferedWriter.close();
                                                        File passengerFile=new File(currentTrain.name+ "\\" + dateInput+"\\WaitingListPassengers.csv");
                                                        passengerFile.delete();
                                                        File tempFile=new File(currentTrain.name+ "\\" + dateInput+"\\TempFile.csv");
                                                        tempFile.renameTo(passengerFile);

                                                     }//if(count==5)
                                                     if(!(psr.seatId.equals(" "))){
                                                        passengerList+=passengerId+" ";
                                                        // if(type.equalsIgnoreCase("Rac")||type.equalsIgnoreCase("WaitingList")){
                                                        //     BufferedReader berthExpectorsReader=new BufferedReader(new FileReader(currentTrain.name+"\\"+dateInput+"\\BerthExpectors.csv"));
                                                        //     String berthExpector=berthExpectorsReader.readLine();
                                                        //     BufferedWriter berthExpectorsWriter=new BufferedWriter(new FileWriter(currentTrain.name+"\\"+dateInput+"\\TempBerthExpectors.csv"));
                                                        //     berthExpectorsWriter.write(berthExpector);
                                                        //     berthExpectorsWriter.newLine();
                                                        //     berthExpectorsWriter.flush();
                                                        //     boolean hasAdd=false;
                                                        //     //  27-04-2023
                                                        //     while((berthExpector=berthExpectorsReader.readLine())!=null){
                                                        //         if(berthExpector.split(",")[0].equals(psr.seatId)){
                                                        //             hasAdd=true;
                                                        //             berthExpectorsWriter.write(berthExpector+"$"+passengerId+"#"+fromStatusPosition+"-"+toStatusPosition);
                                                        //             berthExpectorsWriter.newLine();
                                                        //             berthExpectorsWriter.flush();
                                                        //         }else if(!psr.seatId.contains("WL")&&berthExpector.split(",")[0].equalsIgnoreCase("WL1")&&!hasAdd){
                                                        //             hasAdd=true;
                                                        //             berthExpectorsWriter.write(psr.seatId+","+passengerId+"#"+fromStatusPosition+"-"+toStatusPosition);
                                                        //             berthExpectorsWriter.newLine();
                                                        //             berthExpectorsWriter.write(berthExpector);
                                                        //             berthExpectorsWriter.newLine();
                                                        //             berthExpectorsWriter.flush();
                                                        //         }else{
                                                        //             berthExpectorsWriter.write(berthExpector);
                                                        //             berthExpectorsWriter.newLine();
                                                        //             berthExpectorsWriter.flush();
                                                        //         }
                                                        //     }
                                                        //     berthExpectorsReader.close();
                                                        //         if(!hasAdd){
                                                        //     berthExpectorsWriter.write(psr.seatId+","+passengerId+"#"+fromStatusPosition+"-"+toStatusPosition);
                                                        //     berthExpectorsWriter.newLine();
                                                        //     berthExpectorsWriter.flush();
                                                        // }
                                                        //     // berthExpectorsWriter.close();
                                                        //     // new File(currentTrain.name+"\\"+dateInput+"\\BerthExpectors.csv").delete();
                                                        //     // new File(currentTrain.name+"\\"+dateInput+"\\TempBerthExpectors.csv").renameTo(new File(currentTrain.name+"\\"+dateInput+"\\BerthExpectors.csv"));
                                                        // }
                                                        System.out.println("While writing  passengers");
                                                        System.out.println("fRoM  :"+fromStatusPosition);
                                                        System.out.println("tO   :"+toStatusPosition);
                                                        BufferedReader coachListReader=new BufferedReader(new FileReader(currentTrain+"\\CoachList.csv"));
                                                        String coachList=coachListReader.readLine();
                                                        while((coachList=coachListReader.readLine())!=null){
                                                            // if(coachList.split(","))
                                                        }
                                                        passengerWriter.append(""+passengerId+++","+psr.name+","+psr.dob+",Booked,"+psr.seatId+","+type+","+((""+fromStatusPosition)+"#"+(""+toStatusPosition)));
                                                        passengerWriter.newLine();
                                                        passengerWriter.flush();
                                                     }
                                                     if(!psr.seatId.equals(" "))
                                                     allocatedSeatsCount++;
                                                }
                                                // System.out.println("Count:  "+count);
                                                // System.out.println("Allocate seat count:  "+allocatedSeatsCount);
                                                // System.out.println("Passenger list size:  "+roughPassengerDetails.size());
                                            }   
                                            passengerWriter.close();
                                            BufferedWriter ticketWriter=new BufferedWriter(new FileWriter("Tickets.csv",true));
                                            System.out.println("Passengerlist:  "+passengerList);
                                           System.out.println(""+(PNR+ticketId)+","+currentTrain.name+","+""+currentTrain.number+","+From+","+To+","+dateInput+","+"Booked,"+mail+","+passengerList);
                                            ticketWriter.write(""+(PNR+ticketId)+","+currentTrain.name+","+""+currentTrain.number+","+From+","+To+","+dateInput+","+"Booked,"+mail+","+passengerList); 
                                            ticketWriter.newLine();
                                            ticketWriter.flush();
                                            ticketWriter.close();
                                            }
                                //     }//no
                                //     break;
                                // }//no
                            }catch(Exception e){
                                System.out.println("Error: while checking card details");
                                e.printStackTrace();
                            }

                                }
                        book=false;
                        }
                            lp=true;
                            break;
                        }//if
                        else{
                        System.out.println("Invalid number");
                    }
                    }//if(waiting<racList.size())
                    }
                    else System.out.println("No trains found....");
                    break;
                    case 2:
                    excp=true;
                    long pnr=0;
                    while(excp){
                        try{
                            System.out.println();
                            System.out.println("Enter your pnr number");
                            pnr=in.nextLong();
                            excp=!isValidPnr(""+pnr);
                            if(excp)
                            System.out.println("Invalid pnr");
                        }catch(Exception e){
                            System.out.println("Invalid pnr");
                            in.nextLine();
                        }
                        }
                        if(ticketsList.containsKey(pnr)){
                        currentTicket=ticketsList.get(pnr);
                         System.out.println("---------------------------------------------------------------------------------------------------"+
                                        "\nTrain number     : "+currentTicket.getTrain_no()+"                             Train name      :"+currentTicket.getTrain_name()+
                                        "\nPNR number       : "+currentTicket.getPnr()+"                         passengercount  : "+currentTicket.getPassengers_count()+
                                        "\nFrom             : "+currentTicket.getFrom().name+"                        To              : "+currentTicket.getTo().name+
                                        "\nDate of booking  : "+transactions.get(currentTicket.getPnr()).getTime()+"         Transaction id  : "+transactions.get(currentTicket.getPnr()).getId());
                         System.out.println("Ticket status    :"+currentTicket.status+"                              Booked by  : "+currentTicket.bookedby+
                                        "\nJourney date     : "+currentTicket.Journey_date);
                                         System.out.println("*************************************************************************************************");
                                        ArrayList<Passenger>psr=currentTicket.getPassengers();
                                         System.out.println("Passenger     passenger     passenger     Seat      berth     status      coach       class"+
                                                           "\n  id          name           age        number     type                  number       type");
                                                           System.out.println();
                                         for(Passenger v:psr){
                                            System.out.println(" "+v.id+"            "+v.name+"        "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                            +"     "+v.status+"     "+allocatedseats.get(v.id).coachname+"     "+allocatedseats.get(v.id).classtype); 
                                        }
                                         System.out.println("-------------------------------------------------------------------------------------------------");
                    
                    }
                        else 
                        System.out.println("PNR not exist");
                       break;
                    case 3:
                    ArrayList<String> cancelPassengers=new ArrayList<String>(); 
                    Seat cancelseat=null;
                    Ticket cancelTicket=null;
                    int currentUserBookedTickets=0;
                    try{
                        String ticket="";
                        BufferedReader ticketReader=new BufferedReader(new FileReader("Tickets.csv"));
                        while(!((ticket=ticketReader.readLine())==null)){
                        String ticketDetails[]=ticket.split(",");
                        if(ticketDetails[6].equalsIgnoreCase("Booked")&&ticketDetails[7].equalsIgnoreCase(mail)){
                            currentUserBookedTickets++;
                        }
                        }
                        ticketReader.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    if(currentUserBookedTickets>0){
                    excp=true;
                    long cancel_pnr=0;
                    byte count=0;
                    while(excp&&count<3){
                        try{
                            count++;
                            System.out.println();
                            System.out.println("Enter your pnr number");
                            cancel_pnr=in.nextLong();
                            excp=!isValidPnr(""+cancel_pnr);
                            if(excp)
                            System.out.println("Invalid pnr");
                        }catch(Exception e){
                            System.out.println("Invalid pnr");
                        }
                        }
                        // find the pnr isExist
                        
                        try (BufferedReader ticketReader = new BufferedReader(new FileReader("Tickets.csv"))) {
                            String Ticket="";
                            boolean isExistPnr=false;
                            while((Ticket=ticketReader.readLine())!=null){
                                if(Ticket.split(",")[0].equals(""+cancel_pnr))
                                isExistPnr=true;
                            }
                            ticketReader.close();
                            // if(ticketsList.containsKey(cancel_pnr)){
                            // cancelTicket=ticketsList.get(cancel_pnr);
                            if(isExistPnr){
                            excp=true;
                            while(excp){
                                System.out.println();
                                System.out.println("1.Cancel ticket  \n2.Cancel particular passenger  \nEnter your choice");
                                try{
                                choice=in.nextByte();
                                excp=!(choice>0&&choice<=2);
                            }catch(Exception e){
                                System.out.println("Invalid choice");
                            }
               }
                            Seat tempSeat=null;
                            switch(choice){
                                case 1:
                                cancelPassengers.clear();
                                try {
                                    ArrayList<String> freeSeatStatus=new ArrayList<String>();
                                    String trainName="";
                                    int cancelFromIndex=-1;
                                    int cancelToIndex=-1;
                                    String ticketStatus="";
                                    String travelDate="";
                                    String cancelSeatId="";
                                    String cancelBerth="";
                                    BufferedReader br = new BufferedReader(new FileReader("Tickets.csv"));
                                    String ticket = "";
                                    boolean bool = false;
                                    // get cancel ticket details and train details 
                                    while (!((ticket = br.readLine()) == null)) {
                                        if (ticket.contains("" + cancel_pnr)) {
                                            String ticketDetails[] = ticket.split(",");
                                            if (ticketDetails[7].equalsIgnoreCase(mail)
                                                    && ticketDetails[6].equalsIgnoreCase("Booked")) {
                                                bool = true;
                                                trainName = ticketDetails[1];
                                                String cancelFrom = ticketDetails[3];
                                                String cancelTo = ticketDetails[4];
                                                ticketStatus = ticketDetails[6];
                                                travelDate = ticketDetails[5];
                                                cancelPassengers = new ArrayList<String>(
                                                        Arrays.asList(ticketDetails[8].trim().split(" ")));
                                                BufferedReader trainReader=new BufferedReader(new FileReader("Trains.csv"));
                                                String train="";
                                                while(!((train=trainReader.readLine())==null)){
                                                    if(train.contains(trainName)){
                                                        String stationIdAndDistance[]=train.split(",")[3].split("#");
                                                        String stationId[]=new String[stationIdAndDistance.length];
                                                        for(int i=0;i<stationIdAndDistance.length;i++)
                                                        stationId[i]=stationIdAndDistance[i].split("_")[0];
                                                        System.out.println("Station id:   "+Arrays.toString(stationId));
                                                        System.out.println("From station id:  "+findStationId(cancelFrom));
                                                        System.out.println("To station id:  "+findStationId(cancelTo));
                                                        cancelFromIndex=indexOfString(""+findStationId(cancelFrom),stationId);
                                                        cancelToIndex=indexOfString(""+findStationId(cancelTo),stationId);

                                                        trainReader.close();
                                                        break;
                                                    }
                                                }

                                            } else if (ticketDetails[6].equalsIgnoreCase("Cancelled"))
                                                System.out.println("Ticket already cancelled...");
                                            else
                                                System.out.println(
                                                        "You can't able to cancel this ticket...");
                                            break;
                                        }
                                    }
                                    br.close();

                                    // }
                                    // update the status of cancel ticket as cancelled
                                    if(bool) {
                                        // File originalTicketFile=new File("Tickets.csv");
                                        BufferedReader ticketFileReader = new BufferedReader(
                                                new FileReader("Tickets.csv"));
                                        // File tempFile=new File("Tempfile.csv");
                                        BufferedWriter bw=new BufferedWriter(new FileWriter("Tempfile.csv"));

                                        while (!((ticket = ticketFileReader.readLine()) == null)) {
                                            if (!(ticket.contains("" + cancel_pnr))) {
                                                bw.write(ticket);
                                                bw.newLine();
                                                bw.flush();
                                            } else {
                                                String ticketDetails[] = ticket.split(",");
                                                bw.write(ticketDetails[0] + "," + ticketDetails[1] + ","
                                                        + ticketDetails[2] + "," + ticketDetails[3]
                                                        + "," + ticketDetails[4] + ","
                                                        + ticketDetails[5] + "," + "Cancelled,"
                                                        + ticketDetails[7] + "," + ticketDetails[8]);
                                                bw.newLine();
                                                bw.flush();
                                            }
                                        }
                                        ticketFileReader.close();
                                        bw.close();
                                        System.out.println(new File("Tickets.csv").delete());
                                        System.out.println(new File("Tempfile.csv").renameTo(new File("Tickets.csv")));
                                        // originalTicketFile.delete();
                                        // tempFile.renameTo(originalTicketFile);

                                    //     // 26-04-2023

                                    //     BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
                                    //     String passenger=passengerReader.readLine();
                                    //     BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("New passengers.csv"));
                                    //     passengerWriter.write(passenger);
                                    //     passengerWriter.newLine();
                                    //     passengerWriter.flush();
                                    //     int cancelPassengerCount=0;
                                    //     CancelPassengerForEach:
                                    //     for(String cancelpsr:cancelPassengers){
                                    //         cancelPassengerCount++;
                                    //     while((passenger=passengerReader.readLine())!=null){
                                    //         String passengerDetails[]=passenger.split(",");
                                    //         if(passengerDetails[0].equals(cancelpsr)){
                                    //             passengerWriter.write(passengerDetails[0]+","+passengerDetails[1]+","+passengerDetails[2]+",Cancelled,"+passengerDetails[4]+","+passengerDetails[5]);
                                    //             passengerWriter.newLine();
                                    //             passengerWriter.flush();
                                    //             if(cancelPassengerCount!=cancelPassengers.size())
                                    //             continue CancelPassengerForEach;
                                    //         }else{
                                    //             passengerWriter.write(passenger);
                                    //             passengerWriter.newLine();
                                    //             passengerWriter.flush();
                                    //             }
                                    //     }
                                    // }
                                    // passengerReader.close();
                                    // passengerWriter.close();
                                    // new File("Passengers.csv").delete();
                                    // new File("New Passengers.csv").renameTo(new File("Passengers.csv"));
                                    // BufferedReader PassengerReader=new BufferedReader(new FileReader("Passengers.csv"));
                                    // String Passenger=PassengerReader.readLine();
                                    // ForEachCancelPassengers:
                                    // for(String cancelpsr:cancelPassengers){
                                    //     while((Passenger=PassengerReader.readLine())!=null){
                                    //         String passengerDetails[]=Passenger.split(",");
                                    //         if(passengerDetails[0].equals(cancelpsr)){
                                    //             cancelSeatId=passengerDetails[4];
                                    //             cancelBerth=passengerDetails[5];
                                    //         String berth="";
                                    //         if(passengerDetails[5].equalsIgnoreCase("null"))
                                    //         berth="WaitingList";
                                    //         else
                                    //         berth=passengerDetails[5];
                                    //         BufferedReader seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv"));
                                    //         String seat=seatReader.readLine();
                                    //         BufferedWriter seatWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\TempSeats.csv"));
                                    //         seatWriter.write(seat);
                                    //         seatWriter.newLine();
                                    //         seatWriter.flush();
                                    //         while((seat=seatReader.readLine())!=null){
                                    //             String seatDetails[]=seat.split(",");
                                    //             if(seatDetails[0].equals(passengerDetails[4])){
                                    //                 String status[]=null;
                                    //                 if(!berth.equalsIgnoreCase("WaitingList"))
                                    //                 status=seatDetails[2].split("#");
                                    //                 else
                                    //                 status=seatDetails[1].split("#");
                                    //                 String newStatus="";
                                    //                 System.out.println("Cancel from:  "+cancelFromIndex);
                                    //                 System.out.println("Cancel to:  "+cancelToIndex);
                                    //                 for(int i=0;i<status.length;i++){
                                    //                     if(i>=cancelFromIndex&&i<cancelToIndex)
                                    //                     newStatus+="false";
                                    //                     else
                                    //                     newStatus+=status[i];
                                    //                     if(i!=status.length-1)
                                    //                     newStatus+="#";
                                    //                 }
                                    //                 if(!berth.equalsIgnoreCase("WaitingList")){
                                    //                     String statusArr[]=newStatus.split("#");
                                    //                     BufferedReader berthExpectoReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\BerthExpectors.csv"));
                                    //                     String berthExpector=berthExpectoReader.readLine();
                                    //                     while((berthExpector=berthExpectoReader.readLine())!=null){
                                    //                         String passengerDetail[]=berthExpector.split(",")[1].split("$");
                                    //                         // String berthExpectorPassenger[]=new String[passengerDetatils.length];
                                    //                         String from[]=new String[passengerDetail.length];
                                    //                         String to[]=new String[passengerDetail.length];
                                    //                         for(int i=0;i<passengerDetail.length;i++){
                                    //                             from[i]=passengerDetail[i].split("#")[1].split("-")[0];
                                    //                             to[i]=passengerDetail[i].split("#")[1].split("-")[1];
                                    //                             passengerDetail[i]=passengerDetail[i].split("#")[0];
                                    //                             // String newSeatStatus="";
                                    //                             int k=0;
                                    //                             for(k=Integer.parseInt(from[i]);k<Integer.parseInt(to[i]);k++){
                                    //                                 if(statusArr[k].equalsIgnoreCase("true"))
                                    //                                 break;
                                    //                             }
                                    //                             if(k==Integer.parseInt(to[i])){
                                    //                                 // ????
                                    //                             }
                                    //                         }
                                    //                     }
                                    //                 // seatWriter.write(seatDetails[0]+","+seatDetails[1]+","+newStatus+","+seatDetails[3]);
                                    //                 }else
                                    //                 seatWriter.write(seatDetails[0]+","+newStatus);
                                    //                 seatWriter.newLine();
                                    //                 seatWriter.flush();
                                    //             }else{
                                    //                 seatWriter.write(seat);
                                    //                 seatWriter.newLine();
                                    //                 seatWriter.flush();
                                    //             }
                                    //         }
                                    //         seatReader.close();
                                    //         seatWriter.close();
                                    //         System.out.println("New"+new File(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv").delete());
                                    //         System.out.println("New"+new File(trainName+"\\"+travelDate+"\\TempSeats.csv").renameTo(new File(trainName+"\\"+travelDate+"\\"+berth+"Seats.csv")));
                                            
                                    //         if(!berth.equalsIgnoreCase("WaitingList")){
                                    //             BufferedReader racReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\RacSeats.csv"));
                                    //         }else???
                                            
                                            
                                    //         continue ForEachCancelPassengers;
                                    //         }
                                    //     }
                                    //     PassengerReader.close();
                                    // }

                                    // 28-04-2023
                                        // cancelPassengers(cancelPassengers, trainName, travelDate, cancelFromIndex, cancelToIndex);

                            //             // update cancel passenger status as cancelled
                                    
                                        // File ticketFile = new File("Tickets.csv");
                                        // ticketFile.delete();
                                        // tempFile.renameTo(ticketFile);
                                        // BufferedReader seatReader=new BufferedReader(new
                                        // FileReader(trainName+"\\"+travelDate+"\\"))
                                        File originalPassengerFile=new File("Passengers.csv");
                                        File tempPassengerFile=new File("tempFile.csv");
                                        BufferedReader passengerReader = new BufferedReader(new FileReader("Passengers.csv"));
                                        String passenger="";
                                        passenger=passengerReader.readLine();
                                        BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("tempFile.csv"));
                                        passengerWriter.write(passenger);
                                        passengerWriter.newLine();
                                        passengerWriter.flush();
                                        // find from to position
                                        BufferedReader trainReader = new BufferedReader(new FileReader("Trains.csv"));
                                        String status="";
                                        while(!((passenger=passengerReader.readLine())==null)){
                                            System.out.println("Passenger :  "+passenger);
                                            System.out.println("11");
                                            String passengerDetail[]=passenger.split(",");
                                            if(cancelPassengers.contains(passengerDetail[0])){
                                                System.out.println("12");
                                                System.out.println("Passenger write:  "+passengerDetail[0]+","+passengerDetail[1]+","+passengerDetail[2]+",Cancelled,"+passengerDetail[4]+","+passengerDetail[5]);
                                                passengerWriter.write(passengerDetail[0]+","+passengerDetail[1]+","+passengerDetail[2]+",Cancelled,"+passengerDetail[4]+","+passengerDetail[5]);
                                                passengerWriter.newLine();
                                                passengerWriter.flush();
                                                cancelSeatId=passengerDetail[4];
                                                cancelBerth=passengerDetail[5];
                                                //change the status of cancelled seat(status array)
                                                File originalSeatFile=new File(trainName+"\\"+travelDate+"\\"+cancelBerth+"Seats.csv");
                                                BufferedReader seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\"+cancelBerth+"Seats.csv"));
                                                // String seat="";
                                                String seat=seatReader.readLine();
                                                File tempSeatFile=new File(trainName+"\\"+travelDate+"\\TempSeats.csv");
                                                BufferedWriter seatWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\TempSeats.csv"));
                                                seatWriter.write(seat);
                                                seatWriter.newLine();
                                                seatWriter.flush();
                                                while(!((seat=seatReader.readLine())==null)){
                                                    status="";
                                                    System.out.println("13");
                                                    if(seat.split(",")[0].equals(cancelSeatId)){
                                                        System.out.println(14);
                                                        String seatDetails[]=seat.split(",");
                                                        String previousStatus[]=null;
                                                        if(!seat.split(",")[0].contains("WL"))
                                                        previousStatus=seatDetails[2].split("#");
                                                        else
                                                        previousStatus=seatDetails[1].split("#");
                                                        for(int i=0;i<previousStatus.length;i++){
                                                            System.out.println("15");
                                                            if(i>=cancelFromIndex&&i<cancelToIndex){
                                                                System.out.println("16");
                                                            status+="false";
                                                            freeSeatStatus.add("false");
                                                            }
                                                            else{
                                                                System.out.println("17");
                                                            status+=previousStatus[i];
                                                            freeSeatStatus.add(previousStatus[i]);
                                                            }
                                                            if(i!=previousStatus.length-1)
                                                            status+="#";
                                                        }
                                                        System.out.println("18");
                                                        if(!seat.split(",")[0].contains("WL")){
                                                        seatWriter.write(seatDetails[0]+","+seatDetails[1]+","+status+","+seatDetails[3]);
                                                        System.out.println("Seat:   "+seatDetails[0]+","+seatDetails[1]+","+status+","+seatDetails[3]);
                                                    }else
                                                        seatWriter.write(seatDetails[0]+","+status);
                                                        seatWriter.newLine();
                                                        seatWriter.flush();
                                                    }else{
                                                        System.out.println("19");
                                                        System.out.println("Seat:  "+seat);
                                                        seatWriter.write(seat);
                                                        seatWriter.newLine();
                                                        seatWriter.flush();
                                                    }
                                                }
                                                seatReader.close();
                                                seatWriter.close();
                                                originalSeatFile.delete();
                                                tempSeatFile.renameTo(originalSeatFile);
                                            }else{
                                                System.out.println("20");
                                                System.out.println("Passenger write:  "+passenger);
                                                passengerWriter.write(passenger);
                                                passengerWriter.newLine();
                                                passengerWriter.flush();
                                            }
                                        }
                                        passengerReader.close();
                                        passengerWriter.close();
                                        originalPassengerFile.delete();
                                        tempPassengerFile.renameTo(originalPassengerFile);
                                    // }
                                    // allocate cancelled seat to rac passenger or waitinglist passenger
                                    TreeMap<Integer,Seat> updatedPassengers=new TreeMap<Integer,Seat>();
                                    for(String psrId:cancelPassengers){
                                        System.out.println("21");
                                        updatedPassengers.put(Integer.parseInt(psrId),new Seat(cancelSeatId,cancelBerth,"Cancelled",cancelFromIndex,cancelToIndex));
                                    int i=0;
                                    int from_id[]=null;
                                    int to_id[]=null;
                                    try{
                                        System.out.println("22");
                                        String ChangedCancelSeat=null;
                                        String racPassenger="";
                                        if(!(cancelSeatId.contains("WL"))){
                                            System.out.println("23");
                                        BufferedReader racpassengersReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\RacPassengers.csv"));
                                        racPassenger=racpassengersReader.readLine();
                                        BufferedWriter racpassengersWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\RacPassengers.csv"));
                                        racpassengersWriter.write(racPassenger);
                                        racpassengersWriter.newLine();
                                        racpassengersWriter.flush();
                                        String statusArr[]=status.split("#");
                                        while((racpassengersReader.readLine())!=null){
                                            System.out.println("24");
                                            if(Integer.parseInt(racPassenger.split(",")[0])>Integer.parseInt(cancelSeatId)||!cancelBerth.equalsIgnoreCase("Rac")){
                                                // allocateSeat(currentTrain,"Rac",travelDate,cancelSeatId, cancelFromIndex, cancelToIndex)
                                                System.out.println("25");
                                                String passenger_travelplace[]=racPassenger.split("$");
                                                String passengerId[]=new String[passenger_travelplace.length];
                                                 from_id=new int[passenger_travelplace.length];
                                                 to_id=new int[passenger_travelplace.length];
                                                for( i=0;i<passenger_travelplace.length;i++){
                                                    System.out.println("26");
                                                    passengerId[i]=passenger_travelplace[i].split("#")[0];
                                                    from_id[i]=Integer.parseInt(passenger_travelplace[i].split("#")[1].split("-")[0]);
                                                    to_id[i]=Integer.parseInt(passenger_travelplace[i].split("#")[1].split("-")[1]);
                                                    int k=0;
                                                    for(k=from_id[i];k<to_id[i];k++){
                                                        if(statusArr[k].equals("true"))
                                                        break;
                                                    }
                                                    if(k==to_id[i]){
                                                        System.out.println("27");
                                                        if(ChangedCancelSeat==null)
                                                        allocateSeat(currentTrain,cancelBerth,travelDate,cancelSeatId,from_id[i],to_id[i],true);
                                                        else{
                                                        allocateSeat(currentTrain,"Rac",travelDate,cancelSeatId,from_id[i],to_id[i],true);
                                                        updatedPassengers.put(Integer.parseInt(passengerId[i]),new Seat(cancelSeatId,"Rac","Booked",from_id[i],to_id[i]));
                                                        }
                                                        allocateSeat(currentTrain,"Rac",travelDate,racPassenger.split(",")[0],from_id[i],to_id[i],false);
                                                        ChangedCancelSeat=racPassenger.split(",")[0];
                                                    }else
                                                        updatedPassengers.put(Integer.parseInt(passengerId[i]),new Seat(racPassenger.split(",")[0],"Rac","Booked",from_id[i],to_id[i]));

                                                    // if(k!=to_id[i]&&i==passenger_travelplace.length-1){
                                                    //     allocateSeat(currentTrain,cancelBerth,travelDate,cancelSeatId,from_id[i],to_id[i],true);
                                                    // }
                                                }
                                                if(ChangedCancelSeat!=null){
                                                    System.out.println("28");
                                                    cancelSeatId=ChangedCancelSeat;
                                                    ChangedCancelSeat=null;
                                                }
                                            }
                                            else{
                                                System.out.println("29");
                                                racpassengersWriter.write(racPassenger);
                                                racpassengersWriter.newLine();
                                                racpassengersWriter.flush();
                                            }
                                        }
                                        racpassengersWriter.close();
                                        }
                                        if(cancelSeatId.contains("WL")||racPassenger==null){
                                            System.out.println("30");
                                        BufferedReader waitinglistpassengersReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\WaitingListPassengers.csv"));
                                        String waitinglistPassenger=waitinglistpassengersReader.readLine();
                                        BufferedWriter waitinglistpassengersWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\TempWaitingListPassengers.csv"));
                                        waitinglistpassengersWriter.write(waitinglistPassenger);
                                        waitinglistpassengersWriter.newLine();
                                        waitinglistpassengersWriter.flush();
                                        String statusArr[]=status.split("#");
                                        while((waitinglistPassenger=waitinglistpassengersReader.readLine())!=null){
                                            System.out.println("31");
                                            int checkSeatId=-1;
                                            if(cancelSeatId.contains("WL"))
                                            checkSeatId=Integer.parseInt(cancelSeatId.split("L")[1]);
                                            else
                                            checkSeatId=Integer.parseInt(cancelSeatId);
                                            if(Integer.parseInt(waitinglistPassenger.split(",")[0].split("L")[1])>checkSeatId||!cancelSeatId.contains("WL")){
                                                // allocateSeat(currentTrain,"Rac",travelDate,cancelSeatId, cancelFromIndex, cancelToIndex)
                                                System.out.println("32");
                                                String passenger_travelplace[]=waitinglistPassenger.split("$");
                                                String passengerId[]=new String[passenger_travelplace.length];
                                                 from_id=new int[passenger_travelplace.length];
                                                 to_id=new int[passenger_travelplace.length];
                                                // String ChangedCancelSeat=null;
                                                for( i=0;i<passenger_travelplace.length;i++){
                                                    System.out.println("33");
                                                    passengerId[i]=passenger_travelplace[i].split("#")[0];
                                                    from_id[i]=Integer.parseInt(passenger_travelplace[i].split("#")[1].split("-")[0]);
                                                    to_id[i]=Integer.parseInt(passenger_travelplace[i].split("#")[1].split("-")[1]);
                                                    int k=0;
                                                    for(k=from_id[i];k<to_id[i];k++){
                                                        if(statusArr[k].equals("true"))
                                                        break;
                                                    }
                                                    if(k==to_id[i]){
                                                        System.out.println("34");
                                                        if(cancelSeatId.contains("WL")){
                                                            System.out.println("35");
                                                        allocateSeat(currentTrain,"WaitingList",travelDate,cancelSeatId,from_id[i],to_id[i],true);
                                                        updatedPassengers.put(Integer.parseInt(passengerId[i]),new Seat(cancelSeatId,null,"Booked",from_id[i],to_id[i]));
                                                        }
                                                        else
                                                        allocateSeat(currentTrain,"Rac",travelDate,cancelSeatId,from_id[i],to_id[i],true);

                                                        allocateSeat(currentTrain,"WaitingList",travelDate,waitinglistPassenger.split(",")[0],from_id[i],to_id[i],false);
                                                        ChangedCancelSeat=waitinglistPassenger.split(",")[0];
                                                    }else
                                                        updatedPassengers.put(Integer.parseInt(passengerId[i]),new Seat(waitinglistPassenger.split(",")[0],null,"Booked",from_id[i],to_id[i]));

                                                    // if(k!=to_id[i]&&i==passenger_travelplace.length-1){
                                                    //     allocateSeat(currentTrain,cancelBerth,travelDate,cancelSeatId,from_id[i],to_id[i],true);
                                                    // }
                                                }
                                                if(ChangedCancelSeat!=null){
                                                    System.out.println("36");
                                                    cancelSeatId=ChangedCancelSeat;
                                                    ChangedCancelSeat=null;
                                                }
                                            }
                                            // ????
                                        }
                                        waitinglistpassengersReader.close();
                                        waitinglistpassengersWriter.close();
                                        new File(trainName+"\\"+travelDate+"\\WaitingListPassengers.csv").delete();
                                        new File(trainName+"\\"+travelDate+"\\TempWaitingListPassengers.csv").renameTo(new File(trainName+"\\"+travelDate+"\\WaitingListPassengers.csv"));
                                    }
                                    if(ChangedCancelSeat!=null){
                                        System.out.println("37");
                                        if(ChangedCancelSeat.contains("WL"))
                                        allocateSeat(currentTrain,"WaitingList",travelDate,ChangedCancelSeat,from_id[i],to_id[i],false);
                                        else
                                        allocateSeat(currentTrain,"Rac",travelDate,ChangedCancelSeat,from_id[i],to_id[i],false);
                                    }
                                    // ??????......confirm aana rac wl passenger ah rac/wl passengers file la change pannanum....?????
                                    //     ?????
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                File originalRac=new File(trainName+"\\"+travelDate+"\\RacPassengers.csv");
                                BufferedReader racReader=new BufferedReader(new FileReader(originalRac));
                                // String racPassengerString="";
                                String racPassengerString="";
                                // racReader.readLine();
                                File tempRac=new File(trainName+"\\"+travelDate+"\\tempRac.csv");
                                tempRac.createNewFile();
                                BufferedWriter racWriter=new BufferedWriter(new FileWriter(tempRac));
                                // racWriter.write(racPassengerString);
                                // racWriter.newLine();
                                // racWriter.flush();
                                System.out.println("38");
                                while((racPassengerString=racReader.readLine())!=null){
                                    if(racPassengerString.contains("id")){
                                        racWriter.write(racPassengerString);
                                        racWriter.newLine();
                                        racWriter.flush();
                                    }else{
                                    System.out.println("39");
                                      String passenger_travelplace[]=racPassengerString.split("$");
                                                String passengerId[]=new String[passenger_travelplace.length];
                                                int from_id[]=new int[passenger_travelplace.length];
                                                int to_id[]=new int[passenger_travelplace.length];
                                                String psr="";
                                                for(Map.Entry<Integer,Seat> map:updatedPassengers.entrySet()){
                                                    System.out.println("40");
                                                    Seat seat=map.getValue();
                                                    System.out.println("key : "+map.getKey());
                                                    System.out.println("Value : "+seat.seatId+","+seat.berth_type);
                                                    if(seat.status.equalsIgnoreCase("Booked")&&seat.berth_type.equalsIgnoreCase("Rac")){
                                                        if(psr.equals(""))
                                                        psr+=map.getKey()+"#"+seat.fromid+"-"+seat.toid;
                                                        else
                                                        psr+="$"+map.getKey()+"#"+seat.fromid+"-"+seat.toid;
                                                    }
                                                }
                                                racWriter.write(racPassengerString.split(",")[0]+","+psr);
                                                racWriter.newLine();
                                                racWriter.flush();
                                            }
                                }
                                System.out.println("40 A");
                                racReader.close();
                                racWriter.close();
                                originalRac.delete();
                                tempRac.renameTo(originalRac);
                                System.out.println("40 B");
                                File oringinalWaitinglist=new File(trainName+"\\"+travelDate+"\\WaitingListPassengers.csv");
                                BufferedReader waitinglistReader=new BufferedReader(new FileReader(oringinalWaitinglist));
                                String waitinglistString="";
                                // waitinglistReader.readLine();
                                File tempWaitinglist=new File(trainName+"\\"+travelDate+"\\tempWaitingList.csv");
                                tempWaitinglist.createNewFile();
                                BufferedWriter waitinglistWriter=new BufferedWriter(new FileWriter(tempWaitinglist));
                                // System.out.println("Waiting list string :  "+waitinglistString);
                                // waitinglistWriter.write(waitinglistString);
                                // waitinglistWriter.newLine();
                                // waitinglistWriter.flush();
                                
                                
                                while((waitinglistString=waitinglistReader.readLine())!=null){
                                    if(waitinglistString.contains("id")){
                                        waitinglistWriter.write(waitinglistString);
                                        waitinglistWriter.newLine();
                                        waitinglistWriter.flush();
                                    }else{
                                    System.out.println("40 C");
                                      String passenger_travelplace[]=waitinglistString.split("$");
                                                String passengerId[]=new String[passenger_travelplace.length];
                                                int from_id[]=new int[passenger_travelplace.length];
                                                int to_id[]=new int[passenger_travelplace.length];
                                                String psr="";
                                                for(Map.Entry<Integer,Seat> map:updatedPassengers.entrySet()){
                                                    System.out.println("41");
                                                    Seat seat=map.getValue();
                                                    System.out.println("key2 : "+map.getKey());
                                                    System.out.println("Value2 : "+seat.seatId+","+seat.berth_type);
                                                    if(seat.status.equalsIgnoreCase("Booked")&&seat.berth_type.equals("WaitingList")){
                                                        System.out.println("41");
                                                        if(psr.equals(""))
                                                        psr+=map.getKey()+"#"+seat.fromid+"-"+seat.toid;
                                                        else
                                                        psr+="$"+map.getKey()+"#"+seat.fromid+"-"+seat.toid;
                                                    }
                                                }
                                                waitinglistWriter.write(waitinglistString.split(",")[0]+","+psr);
                                                waitinglistWriter.newLine();
                                                waitinglistWriter.flush();
                                            }
                                }
                                waitinglistReader.close();
                                waitinglistWriter.close();
                                oringinalWaitinglist.delete();
                                tempWaitinglist.renameTo(oringinalWaitinglist);
                            }
                                // }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println(
                                            "Error: while reading tickets for cancel a ticket");
                                }        
                                lp=true;
                                break;
                                case 2:
                                cancelPassengers.clear();
                                excp=true;
                                byte cancel_count=0;
                                while(excp){
                                    try{
                                        System.out.println();
                                    System.out.println("Enter cancellation count");
                                    cancel_count=in.nextByte();
                                    excp=(cancel_count>cancelTicket.passengers_count)||cancel_count<0;
                                    }catch(Exception e){
                                        System.out.println("Invalid input");
                                    }
                                }
                                excp=true;
                                int cancel_id=0;
                                count=0;
                                if(cancelTicket.bookedby.equals(currentUser.mail_id)){
                                for(int i=1;i<=cancel_count;i++){
                                while(excp){
                                    try{
                                        System.out.println();
                                        System.out.println("Enter passenger id");
                                        cancel_id=in.nextInt();
                                        for(Passenger p:cancelTicket.getPassengers()){
                                            if(p.id==cancel_id)
                                            {
                                                if(p.status.equalsIgnoreCase("booked")){
                                                p.status="Cancelled";
                                                count++;
                                                cancelseat=allocatedseats.get(p.id);
                                                for(int j=currentTrain.route.indexOf(cancelTicket.from);j<currentTrain.route.indexOf(cancelTicket.to);j++){
                                                    cancelseat.seat_status.set(j,false);
                                                }
                                                int from_index=0,to_index=0;
                                                if(currentTrain.berth_expectors.size()>0){
                                                    //traverse berth expectors
                                                    for(Ticket ticket:currentTrain.berth_expectors){
                                                        int k=0;
                                                            for(k=currentTrain.route.indexOf(ticket.from);k<currentTrain.route.indexOf(ticket.to);k++){
                                                                if(cancelseat.seat_status.get(k)==true)
                                                                break;
                                                                }
                                                                if(k==currentTrain.route.indexOf(ticket.to)){
                                                            for(Passenger psr:ticket.getPassengers()){
                                                                if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---")){
                                                                    tempSeat=allocatedseats.get(psr.id);
                                                                    allocatedseats.put(psr.id,cancelseat);
                                                                    cancelseat=tempSeat;
                                                            for(k=currentTrain.route.indexOf(ticket.from);k<currentTrain.route.indexOf(ticket.to);k++){
                                                                allocatedseats.get(psr.id).seat_status.set(k,true);
                                                            }
                                                            for(int j=currentTrain.route.indexOf(ticket.from);j<currentTrain.route.indexOf(ticket.to);j++){
                                                                cancelseat.seat_status.set(j,false);
                                                            }
                                                    }
                                                            }
                                                        }
                                                    }
                                                    
                                                if(!cancelseat.seat_status.contains(true)){
                                                currentTrain.bookedseatList.remove(cancelseat);
                                                if(cancelseat.berth_type.equals("Lower"))
                                                currentTrain.lbList.addFirst(cancelseat);
                                                if(cancelseat.berth_type.equals("Upper"))
                                                currentTrain.ubList.addFirst(cancelseat);
                                                if(cancelseat.berth_type.equals("Middle"))
                                                currentTrain.mbList.addFirst(cancelseat);
                                                if(cancelseat.berth_type.equals("Rac"))
                                                currentTrain.racList.addFirst(cancelseat);
                                                           }
                                                    //update berth expectors
                                                    for(int j=0;j<currentTrain.berth_expectors.size();j++){
                                                        int isneedRemove=0;
                                                        for(Passenger psr:currentTrain.berth_expectors.get(j).getPassengers()){
                                                            if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---"))
                                                            {
                                                                isneedRemove++;
                                                                break;
                                                            }
                                                        }
                                                        if(isneedRemove==0){
                                                            currentTrain.berth_expectors.remove(j);
                                                            j--;
                                                        }
                                                    }
                                            }
                                            else{
                                                if(!cancelseat.seat_status.contains(true)){
                                                    currentTrain.bookedseatList.remove(cancelseat);
                                                if(cancelseat.berth_type.equals("Lower"))
                                                currentTrain.lbList.addFirst(cancelseat);
                                                if(cancelseat.berth_type.equals("Upper"))
                                                currentTrain.ubList.addFirst(cancelseat);
                                                if(cancelseat.berth_type.equals("Middle"))
                                                currentTrain.mbList.addFirst(cancelseat);
                                                            }
                                                // if(cancelseat.berth_type.equals("Rac"))
                                                // currentTrain.racList.add(cancelseat);
                                                // if(cancelseat.berth_type.equals("---"))
                                                // currentTrain.waitinglist--;
                                            }
                                        }
                                                break;}
                                        }
                                        
                                        if(count==cancel_count)
                                        excp=false;
                                        else if(count==0)
                                        System.out.println("Invalid passenger id");
                                    }catch(Exception e){
                                        System.out.println("Invalid input");
                                        in.nextLine();
                                    }
                                }
                            }
                            System.out.println("Successfully cancelled...");

                                count=0;     
               for(Passenger p:currentTicket.getPassengers()){
                            if(p.status.equalsIgnoreCase("cancelled"))
                            count++;
               }
               if(count==currentTicket.passengers_count)
               currentTicket.status="cancelled";
               
            }
            else {
               System.out.println();
            System.out.println("You can not cancel this ticket");
            }
                                lp=true;
                                break;
                            }
               }
               else 
               System.out.println("PNR not exist");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }
                else 
                System.out.println("No booking found...");
                    break;
                    case 4:
                    ArrayList<Ticket> booked=currentUser.getbookedtickets();
                    if(booked.size()<1)
                    System.out.println("No bookings found");
                    else {
                    for(Ticket k:booked){
                        System.out.println();
                                         System.out.println("---------------------------------------------------------------------------------------------------"+
                                        "\nTrain number     : "+k.getTrain_no()+"                              Train name      :"+k.getTrain_name()+
                                        "\nPNR number       : "+k.getPnr()+"                                   passengercount  : "+k.getPassengers_count()+
                                        "\nFrom             : "+k.getFrom().name+"                                  To              : "+k.getTo().name+
                                        "\nDate of booking  : "+transactions.get(k.getPnr()).getTime()+"       Transaction id  : "+transactions.get(k.getPnr()).getId()+
                                        "\nJourney date     : "+k.Journey_date+"                                Booked by      :"+k.bookedby+
                                        "\nTicket status    : "+k.status);
                                         System.out.println("*************************************************************************************************");
                                        ArrayList<Passenger>psr=k.getPassengers();
                                         System.out.println("Passenger     passenger     passenger     Seat      berth          Status      coach       class"+
                                                           "\n  id          name           age        number     type                       name         type");
                                                           System.out.println();
                                         for(Passenger v:psr){
                                            System.out.println(" "+v.id+"            "+v.name+"        "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                           +"      "+v.status+"     "+allocatedseats.get(v.id).coachname+"     "+allocatedseats.get(v.id).classtype); 
                                        }
                                         System.out.println("-------------------------------------------------------------------------------------------------");
                    }//for
                }//else
                lp=true;
                    break;
                    case 5:
                    lp=false;
                    break;
                    default:
                    System.out.println("Invalid number");
                }
                }
            }
                else 
                System.out.println("Invalid login details...");
                break;
                case 3:
                System.out.println("Thank you...");
                flag=false;
                break;
                default:
                System.out.println("Enter a valid number");
            }
        }
    }

    public static boolean isValidLogin(HashMap<String, String> logindetails, String mail, String pwd) {
        boolean login = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Users.csv"));
            String user = "";
            while ((user = br.readLine()) != null) {
                String arr[] = user.split(",");
                if (arr[1].equalsIgnoreCase(mail) && arr[2].equalsIgnoreCase(pwd)) {
                    login = true;
                    br.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: while read user details");
            e.printStackTrace();
        }
        return login;
    }

    public static boolean isadult(int age) {
        boolean adult = false;
        if (age > 5)
            adult = true;
        return adult;
    }

    public static double totalticketcost(int adults, double rate) {
        return adults * rate;
    }

    public static ArrayList<TrainDetails> addTrains() {
        ArrayList<TrainDetails> trains = new ArrayList<TrainDetails>();
        trains.add(new TrainDetails(12662, "POTHIGAI EXPRESS",
                /* 1,4,0, */new ArrayList<Station>(
                        Arrays.asList(new Station("SCT", "sengottai", 0), new Station("TSI", "tenkasi", 10),
                                new Station("SNKL", "sankarankovil", 45), new Station("SVKS", "sivakasi", 105),
                                new Station("MDU", "madurai", 174), new Station("MS", "chennai", 250))),
                new ArrayList<Coach>(Arrays.asList(new Coach("S1", "Sitting", 4)/* ,new Coach("S2","Sleeper",4) */)),
                2));

        trains.add(new TrainDetails(16102, "QLN EXPRESS",
                /* 1,4,0, */new ArrayList<Station>(Arrays.asList(new Station("SCT", "sengottai", 0),
                        new Station("TSI", "tenkasi", 10), new Station("SNKL", "sankarankovil", 45),
                        new Station("SVKS", "sivakasi", 105), new Station("MDU", "madurai", 174),
                        new Station("DG", "dindigul", 230), new Station("MS", "chennai", 270))),
                new ArrayList<Coach>(Arrays.asList(new Coach("S1", "Sitting", 4))), 2));

        trains.add(
                new TrainDetails(12632, "NELLAI EXPRESS",
                        /* 1,4,0, */new ArrayList<Station>(Arrays.asList(new Station("TEN", "tirunelveli", 0),
                                new Station("VPT", "virudunagar", 100), new Station("MDU", "madurai", 160),
                                new Station("MS", "chennai", 240))),
                        new ArrayList<Coach>(Arrays.asList(new Coach("S1", "Sitting", 4))), 2));

        trains.add(
                new TrainDetails(12634, "KANYAKUMARI EXPRESS",
                        /* 1,4,0, */new ArrayList<Station>(Arrays.asList(new Station("CAPE", "kanyakumari", 0),
                                new Station("TEN", "tirunelveli", 90), new Station("MDU", "madurai", 240),
                                new Station("MS", "chennai", 320))),
                        new ArrayList<Coach>(Arrays.asList(new Coach("S1", "Sitting", 4))), 2));

        return trains;
    }

    public static int findStationId(String station) {
        int stationId = -1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Station.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                if (arr[2].equalsIgnoreCase(station)) {
                    stationId = Integer.parseInt(arr[0]);
                    br.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationId;
    }

    public static void createFreeTrain(String date) {
        try {
            System.out.println("Create free train method");
            BufferedReader trainReader = new BufferedReader(new FileReader("Trains.csv"));
            String train = "";
            while (!((train = trainReader.readLine()) == null)) {
                System.out.println("Train:   "+train);
                if (train.contains("Id"))
                    continue;
                File dateFile = new File(train.split(",")[2] + "\\" + date);
                System.out.println(dateFile.exists());
                if (!dateFile.exists()) {
                    System.out.println(dateFile.mkdirs());
                    File racPassengers = new File(train.split(",")[2] + "\\" + date+"\\RacPassengers.csv");
                    racPassengers.createNewFile();
                    BufferedWriter racpassengersWriter = new BufferedWriter(new FileWriter(train.split(",")[2] + "\\" + date+"\\RacPassengers.csv"));
                    racpassengersWriter.write("Seat id,Passenger id");
                    racpassengersWriter.newLine();
                    racpassengersWriter.flush();
                    racpassengersWriter.close();
                    File waitinglistPassengers = new File(train.split(",")[2] + "\\" + date+"\\WaitingListPassengers.csv");
                    waitinglistPassengers.createNewFile();
                    BufferedWriter wlpassengersWriter = new BufferedWriter(new FileWriter(train.split(",")[2] + "\\" + date+"\\WaitingListPassengers.csv"));
                    wlpassengersWriter.write("Seat id,Passenger id");
                    wlpassengersWriter.newLine();
                    wlpassengersWriter.flush();
                    wlpassengersWriter.close();
                    int seatId = 1;

                    int statusArrSize = train.split(",")[3].split("#").length;
                    String status = "";
                    for (int i = 1; i < statusArrSize; i++) {
                        status += "false";
                        if (i != statusArrSize - 1)
                            status += "#";
                    }
                    BufferedWriter lowerWriter = new BufferedWriter(
                            new FileWriter(train.split(",")[2] + "\\" + date + "\\LowerSeats.csv"));
                    lowerWriter.write("Id,Number,Coach name,Coach type,Status");
                    lowerWriter.newLine();
                    lowerWriter.flush();
                    BufferedWriter middleWriter = new BufferedWriter(
                            new FileWriter(train.split(",")[2] + "\\" + date + "\\MiddleSeats.csv"));
                    middleWriter.write("Id,Number,Coach name,Coach type,Status");
                    middleWriter.newLine();
                    middleWriter.flush();
                    BufferedWriter upperWriter = new BufferedWriter(
                            new FileWriter(train.split(",")[2] + "\\" + date + "\\UpperSeats.csv"));
                    upperWriter.write("Id,Number,Coach name,Coach type,Status");
                    upperWriter.newLine();
                    upperWriter.flush();
                    BufferedWriter racWriter = new BufferedWriter(
                            new FileWriter(train.split(",")[2] + "\\" + date + "\\RacSeats.csv"));
                    racWriter.write("Id,Number,Coach name,Coach type,Status");
                    racWriter.newLine();
                    racWriter.flush();
                    BufferedWriter waitinglistWriter = new BufferedWriter(
                            new FileWriter(train.split(",")[2] + "\\" + date + "\\WaitingListSeats.csv"));
                    waitinglistWriter.write("Id,Status");
                    waitinglistWriter.newLine();
                    waitinglistWriter.flush();
                    BufferedWriter berthExpectors=new BufferedWriter(new FileWriter(train.split(",")[2] + "\\" + date + "\\BerthExpectors.csv"));
                    berthExpectors.write("Seat id,Passenger id");
                    berthExpectors.newLine();
                    berthExpectors.flush();
                    berthExpectors.close();
                    BufferedReader coachListReader = new BufferedReader(
                            new FileReader(train.split(",")[2] + "\\CoachList.csv"));
                    String coach = "";
                    while (!((coach = coachListReader.readLine()) == null)) {
                        if (coach.contains("Id"))
                            continue;
                            String coachDetails[]=coach.split(",");
                        String coachTypeId = coach.split(",")[2];
                        String coachId = coach.split(",")[0];
                        BufferedReader bReader = new BufferedReader(new FileReader("Coachs.csv"));
                        String coachType = "";
                        while (!((coachType = bReader.readLine()) == null)) {
                            if (coachType.contains("Id"))
                                continue;
                            if (coach.split(",")[0].equals(coachTypeId)) {
                                int seatCount = Integer.parseInt(coachType.split(",")[3]);
                                for (int i = 1; i <= seatCount; i++) {
                                    if (i <= seatCount) {
                                        lowerWriter.write("" + seatId++ + "," + i++ + "," + status + "," + coachId);
                                        lowerWriter.newLine();
                                        lowerWriter.flush();
                                    }
                                    if (i <= seatCount) {
                                        middleWriter.write("" + seatId++ + "," + i++ + "," + status + "," + coachId);
                                        middleWriter.newLine();
                                        middleWriter.flush();
                                    }
                                    if (i <= seatCount) {
                                        upperWriter.write("" + seatId++ + "," + i++ + "," + status + "," + coachId);
                                        upperWriter.newLine();
                                        upperWriter.flush();
                                    }
                                    if (i <= seatCount) {
                                        racWriter.write("" + seatId++ + "," + i++ + "," + status + "," + coachId);
                                        racWriter.newLine();
                                        racWriter.flush();
                                    }
                                }
                                lowerWriter.close();
                                middleWriter.close();
                                upperWriter.close();
                                racWriter.close();
                                waitinglistWriter.close();
                            }
                        }
                        bReader.close();
                    }
                    coachListReader.close();
                }
            }
            trainReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: while creating free trains...");
        }
    }

    public static HashMap<TrainDetails, Integer> availableTrains(String date, String from, String to) {
        createFreeTrain(date);
        int fromId = findStationId(from);
        int toId = findStationId(to);
        HashMap<TrainDetails, Integer> al = new HashMap<TrainDetails, Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Trains.csv"));
            String line = "";
            int train = 0;
            while (!((line = br.readLine()) == null)) {
                int available = 0;

                train++;
                if (line.contains("Id"))
                    continue;
                String arr[] = line.split(",");
                String coachId[] = arr[4].split("#");
                String stationId[] = new String[arr[3].split("#").length];
                String distanceId[] = new String[arr[3].split("#").length];
                String stdtID[] = arr[3].split("#");
                for (int i = 0; i < arr[3].split("#").length; i++) {
                    if (!(arr[3].split("#")[i]).contains("_"))
                        break;
                    stationId[i] = (arr[3].split("#")[i]).split("_")[0];
                    distanceId[i] = (arr[3].split("#")[i]).split("_")[1];
                }

                int from_index = indexOfString("" + fromId, stationId);
                int to_index = indexOfString("" + toId, stationId);

                if (from_index < to_index && from_index != -1 && to_index != -1) {
                    System.out.println("Train name:  "+arr[2]);
                    BufferedReader middleReader = new BufferedReader(
                            new FileReader(arr[2] + "\\" + date + "\\MiddleSeats.csv"));
                    String middleSeat = "";
                    while (!((middleSeat = middleReader.readLine()) == null)) {
                        System.out.println("Middle seat:   "+middleSeat);
                        if (middleSeat.contains("Id"))
                            continue;
                        String status[] = middleSeat.split(",")[2].split("#");
                        System.out.println("Status array:  "+Arrays.toString(status));
                        int i = 0;
                        for (i = from_index; i < to_index; i++) {
                            if (status[i].equals("true"))
                                break;
                        }
                        if (i == to_index)
                            available++;
                    }
                    middleReader.close();

                    BufferedReader lowerReader = new BufferedReader(
                            new FileReader(arr[2] + "\\" + date + "\\LowerSeats.csv"));
                    String lowerSeat = "";
                    while (!((lowerSeat = lowerReader.readLine()) == null)) {
                        if (lowerSeat.contains("Id"))
                            continue;
                        String status[] = lowerSeat.split(",")[2].split("#");
                        int i = 0;
                        for (i = from_index; i < to_index; i++) {
                            if (status[i].equals("true"))
                                break;
                        }
                        if (i == to_index)
                            available++;
                    }
                    lowerReader.close();

                    BufferedReader upperReader = new BufferedReader(
                            new FileReader(arr[2] + "\\" + date + "\\UpperSeats.csv"));
                    String upperSeat = "";
                    while (!((upperSeat = upperReader.readLine()) == null)) {
                        if (upperSeat.contains("Id"))
                            continue;
                        String status[] = upperSeat.split(",")[2].split("#");
                        int i = 0;
                        for (i = from_index; i < to_index; i++) {
                            if (status[i].equals("true"))
                                break;
                        }
                        if (i == to_index)
                            available++;
                    }
                    upperReader.close();

                    BufferedReader racReader = new BufferedReader(
                            new FileReader(arr[2] + "\\" + date + "\\RacSeats.csv"));
                    String racSeat = "";
                    while (!((racSeat = racReader.readLine()) == null)) {
                        if (racSeat.contains("Id"))
                            continue;
                        String status[] = racSeat.split(",")[2].split("#");
                        int i = 0;
                        for (i = from_index; i < to_index; i++) {
                            if (status[i].equals("true"))
                                break;
                        }
                        if (i == to_index)
                            available++;
                    }
                    racReader.close();
                    al.put(new TrainDetails(Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[5])), available);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:  While finding  available trains");
        }
        return al;
    }

    public static ArrayList<Double> getticketcharge(ArrayList<TrainDetails> al, String from, String to) {
        ArrayList<Double> ticket_cost = new ArrayList<Double>();
        int x = 0, y = 0;
        try {
            String line = "";
            double ticketcost = 0;
            for (TrainDetails train : al) {
                BufferedReader br = new BufferedReader(new FileReader("Trains.csv"));
                while (!((line = br.readLine()) == null)) {
                    if (line.contains(train.name) && line.contains("" + train.number)) {
                        String arr[] = (line.split(",")[3]).split("#");
                        String stationId[] = new String[arr.length];
                        String distance[] = new String[arr.length];
                        for (int i = 0; i < arr.length; i++) {
                            stationId[i] = arr[i].split("_")[0];
                            distance[i] = arr[i].split("_")[1];
                        }
                        ticketcost = (Integer.parseInt(distance[indexOfString("" + findStationId(to), stationId)])
                                - Integer.parseInt(distance[indexOfString("" + findStationId(from), stationId)]))
                                * train.rate;
                        break;
                    }
                }
                br.close();
                ticket_cost.add(ticketcost);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: while reading trains to get ticket charge ");
        }
        return ticket_cost;
    }

    public static int indexOfString(String str, String[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(str)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String allocateSeat(TrainDetails currentTrain, String type, String dateInput,String seatId,
            int fromStatusPosition, int toStatusPosition,boolean changeSeatStatus/*,int passengerId*/) {
                // String seatId=" ";
                System.out.println("Allocate method seatId");
        // boolean hasSeat=false;
        System.out.println("allocate seat method");
        System.out.println("From    "+fromStatusPosition);
        System.out.println("To     "+toStatusPosition);
        String ans = " ";
        try {
            BufferedReader seatFileReader = new BufferedReader(
                    new FileReader(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv"));
                    String seat = "";
                    seat=seatFileReader.readLine();        
            BufferedWriter seatFileWriter= new BufferedWriter(
                new FileWriter(currentTrain.name + "\\" + dateInput + "\\TempSeats.csv"));
                seatFileWriter.write(seat);
                seatFileWriter.newLine();
                seatFileWriter.flush();
            boolean bool=false;
            aa:
            while((seat=seatFileReader.readLine())!=null){
                if (!seat.contains("Id")){
                    // System.out.println("Seat:   "+seat);
                    String arr[] = seat.split(",");
                    // System.out.println("ARR:  "+Arrays.toString(arr));
                    String statusArr[] = seat.split(",")[2].split("#");
                    // System.out.println("Status Arr:  "+Arrays.toString(statusArr));
                    int i = 0;
                    for (i = fromStatusPosition; i < toStatusPosition; i++) {
                        if (statusArr[i].equalsIgnoreCase("true")) {
                            seatFileWriter.write(seat);
                            // System.out.println("Write line:  "+seat);
                            seatFileWriter.newLine();
                            seatFileWriter.flush();
                            continue aa;
                        }
                    }
                    if (!bool) {
                        bool=true;
                        String correctLine = "";
                        seatId = seat.split(",")[0];
                        String status = "";
                        for (i = 0; i < statusArr.length; i++) {
                            if (i >=fromStatusPosition&&i<toStatusPosition)
                                status +=changeSeatStatus ;
                            else
                                status +=statusArr[i];
                            if (i != statusArr.length - 1)
                                status += "#";
                        }
                        ans = arr[0];
                        System.out.println(Arrays.toString(arr));
                        // if(arr[4].equals("+"))
                        // correctLine = arr[0] + "," + arr[1] + "," + status + "," + arr[3]+","+passengerId+"#"+fromStatusPosition+"-"+toStatusPosition;
                        // else
                        // correctLine = arr[0] + "," + arr[1] + "," + status + "," + arr[3]+","+arr[4]+"$"+passengerId+"#"+fromStatusPosition+"-"+toStatusPosition;
                        
                        correctLine = arr[0] + "," + arr[1] + "," + status + "," + arr[3];
                        
                        seatFileWriter.write(correctLine);
                        seatFileWriter.newLine();
                        seatFileWriter.flush();
                }
                // else{
                //     seatFileWriter.write(seat);
                //     seatFileWriter.newLine();
                //     seatFileWriter.flush();
                // }
            }
            // else{
            //     // seatFileWriter = new BufferedWriter(
            //     //     new FileWriter(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv"));
            //     seatFileWriter.write(seat);
            //     seatFileWriter.newLine();
            //     seatFileWriter.flush();
            // }
        }
        seatFileReader.close();
        seatFileWriter.close();
            // aa: while (!((seat = seatFileReader.readLine()) == null)) {
            //     if (seat.contains("Id"))
            //         continue;
            //     else {
            //         String arr[] = seat.split(",");
            //         String statusArr[] = seat.split(",")[2].split("#");
            //         int i = 0;
            //         for (i = fromStatusPosition; i < toStatusPosition; i++) {
            //             if (statusArr[i].equalsIgnoreCase("true")) {
            //                 continue aa;
            //             }
            //         }
            //         if (i == toStatusPosition) {
            //             seatFileReader.close();
            //             String correctLine = "";
            //             seatId = seat.split(",")[0];
            //             String status = "";
            //             for (i = 0; i < toStatusPosition; i++) {
            //                 if (i < fromStatusPosition)
            //                     status += statusArr[i];
            //                 else
            //                     status +=changeSeatStatus;
            //                 if (i != toStatusPosition - 1)
            //                     status += "#";
            //             }
            //             ans = arr[0];
            //             correctLine = arr[0] + "," + arr[1] + "," + status + "," + arr[3];

            //             File tempFile = new File(currentTrain.name + "\\" + dateInput + "\\TempFile.csv");
            //             tempFile.createNewFile();
            //             File originalFile = new File(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv");
            //             BufferedReader originalFileReader = new BufferedReader(
            //                     new FileReader(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv"));
            //             BufferedWriter tempFileWriter = new BufferedWriter(
            //                     new FileWriter(currentTrain.name + "\\" + dateInput + "\\TempFile.csv"));
            //             String line = "";
            //             while (!((line = originalFileReader.readLine()) == null)) {
            //                 if (line.split(",")[0].equals(seatId)) {
            //                     tempFileWriter.write(correctLine);
            //                     tempFileWriter.newLine();
            //                     tempFileWriter.flush();
            //                 } else {
            //                     tempFileWriter.write(line);
            //                     tempFileWriter.newLine();
            //                     tempFileWriter.flush();
            //                 }
            //             }
            //             originalFileReader.close();
            //             tempFileWriter.close();
            //             System.out.println("File delete:   " + originalFile.delete());
            //             System.out.println("File rename:   " + tempFile.renameTo(originalFile));
            //             break aa;
            //         }
            //     }
            // }

            new File(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv").delete();
            new File(currentTrain.name + "\\" + dateInput + "\\TempSeats.csv").renameTo(new File(currentTrain.name + "\\" + dateInput + "\\" + type + "Seats.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
    // public static void cancelPassengers(ArrayList<String> cancelPassengers,String trainName,String travelDate,int fromStatusPosition,int toStatusPosition){
    //  try{
    //     // for(Passenger passenger:cancelPassengers){
    //         boolean hasChangeInRac=false;
    //         System.out.println("Cancel method....");
    //         System.out.println("A1");
    //         BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
    //         String passenger=passengerReader.readLine();
    //         System.out.println("A2");
    //         BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("TempFile.csv"));
    //         System.out.println("A3");
    //         passengerWriter.write(passenger);
    //         System.out.println("A4");
    //         passengerWriter.newLine();
    //         passengerWriter.flush();
    //         while((passenger=passengerReader.readLine())!=null){
    //             System.out.println("first");
    //             String passengerDetails[]=passenger.split(",");
    //             if(cancelPassengers.contains(passengerDetails[0])){
    //                 passengerWriter.write(passengerDetails[0]+","+passengerDetails[1]+","+passengerDetails[2]+",Cancelled,"+passengerDetails[4]+","+passengerDetails[5]);
    //                 passengerWriter.newLine();
    //                 passengerWriter.flush();
    //                 System.out.println(passengerDetails[5]);
    //                 if(!passengerDetails[5].equalsIgnoreCase("null")){
    //                     hasChangeInRac=true;
    //                     System.out.println("aaaa");
    //                     BufferedReader seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\"+passengerDetails[5]+"Seats.csv"));
    //                     String seat=seatReader.readLine();
    //                     BufferedWriter seatWriter=new BufferedWriter(new FileWriter("TempSeats.csv"));
    //                     seatWriter.write(seat);
    //                     seatWriter.newLine();
    //                     seatWriter.flush();
    //                     while((seat=seatReader.readLine())!=null){
    //                         System.out.println("bbbb");
    //                         String seatDetails[]=seat.split(",");
    //                         if(seatDetails[0].equals(passengerDetails[4])){
    //                             System.out.println("cccc");
    //                             String newStatus="";
    //                             String seatStatus[]=seatDetails[2].split("#");
    //                             for(int i=0;i<seatStatus.length;i++){
    //                                 System.out.println("ddd");
    //                                 if(i>=fromStatusPosition&&i<toStatusPosition)
    //                                 newStatus+="false";
    //                                 else
    //                                 newStatus+=seatStatus[i];
    //                                 if(i!=seatStatus.length-1)
    //                                 newStatus+="#";
    //                             }
    //                             String newPassengers=" ";
    //                             String oldPassengers[]=seatDetails[4].split("$");
    //                             String fromid[]=new String[oldPassengers.length];
    //                             String toid[]=new String[oldPassengers.length];
    //                             for(int i=0;i<oldPassengers.length;i++){
    //                                 System.out.println("eeee");
    //                                 fromid[i]=oldPassengers[i].split("#")[1].split("-")[0];
    //                                 toid[i]=oldPassengers[i].split("#")[1].split("-")[1];
    //                                 oldPassengers[i]=oldPassengers[i].split("#")[0];
    //                                 if(!cancelPassengers.contains(oldPassengers[i])){
    //                                     System.out.println("ffff");
    //                                     if(newPassengers.equals(" "))
    //                                     newPassengers=oldPassengers[i]+"#"+fromid[i]+"-"+toid[i];
    //                                     else
    //                                     newPassengers+="$"+oldPassengers[i]+"#"+fromid[i]+"-"+toid[i];
    //                                 }
    //                             }
    //                             System.out.println("gggg");
    //                             seatWriter.write(seatDetails[0]+","+seatDetails[1]+","+newStatus+","+seatDetails[3]+","+newPassengers);
    //                             seatWriter.newLine();
    //                             seatWriter.flush();
    //                         }else{
    //                             seatWriter.write(seat);
    //                             seatWriter.newLine();
    //                             seatWriter.flush();
    //                         }
    //                     }
    //                     seatReader.close();
    //                     seatWriter.close();
    //                     new File(trainName+"\\"+travelDate+"\\"+passengerDetails[5]+"Seats.csv").delete();
    //                     new File("TempSeats.csv").renameTo(new File(trainName+"\\"+travelDate+"\\"+passengerDetails[5]+"Seats.csv"));
    //                     shiftingProcess(hasChangeInRac,seatDetails[0]);
    //                 }else{
    //                     // waiting list
    //                     hasChangeInRac=false;//unwanted
    //                     System.out.println("aaaa");
    //                     BufferedReader seatReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\WaitingListSeats.csv"));
    //                     String seat=seatReader.readLine();
    //                     BufferedWriter seatWriter=new BufferedWriter(new FileWriter("TempSeats.csv"));
    //                     seatWriter.write(seat);
    //                     seatWriter.newLine();
    //                     seatWriter.flush();
    //                     while((seat=seatReader.readLine())!=null){
    //                         System.out.println("bbbb");
    //                         String seatDetails[]=seat.split(",");
    //                         if(seatDetails[0].equals(passengerDetails[4])){
    //                             System.out.println("cccc");
    //                             String newStatus="";
    //                             String seatStatus[]=seatDetails[1].split("#");
    //                             for(int i=0;i<seatStatus.length;i++){
    //                                 System.out.println("ddd");
    //                                 if(i>=fromStatusPosition&&i<toStatusPosition)
    //                                 newStatus+="false";
    //                                 else
    //                                 newStatus+=seatStatus[i];
    //                                 if(i!=seatStatus.length-1)
    //                                 newStatus+="#";
    //                             }
    //                             String newPassengers=" ";
    //                             String oldPassengers[]=seatDetails[2].split("$");
    //                             String fromid[]=new String[oldPassengers.length];
    //                             String toid[]=new String[oldPassengers.length];
    //                             for(int i=0;i<oldPassengers.length;i++){
    //                                 System.out.println("eeee");
    //                                 fromid[i]=oldPassengers[i].split("#")[1].split("-")[0];
    //                                 toid[i]=oldPassengers[i].split("#")[1].split("-")[1];
    //                                 oldPassengers[i]=oldPassengers[i].split("#")[0];
    //                                 if(!cancelPassengers.contains(oldPassengers[i])){
    //                                     System.out.println("ffff");
    //                                     if(newPassengers.equals(" "))
    //                                     newPassengers=oldPassengers[i]+"#"+fromid[i]+"-"+toid[i];
    //                                     else
    //                                     newPassengers+="$"+oldPassengers[i]+"#"+fromid[i]+"-"+toid[i];
    //                                 }
    //                             }
    //                             System.out.println("gggg");
    //                             seatWriter.write(seatDetails[0]+","+newStatus+","+newPassengers);
    //                             seatWriter.newLine();
    //                             seatWriter.flush();
    //                         }else{
    //                             seatWriter.write(seat);
    //                             seatWriter.newLine();
    //                             seatWriter.flush();
    //                         }
    //                     }
    //                     seatReader.close();
    //                     seatWriter.close();
    //                     new File(trainName+"\\"+travelDate+"\\WaitingListSeats.csv").delete();
    //                     new File("TempSeats.csv").renameTo(new File(trainName+"\\"+travelDate+"\\WaitingListSeats.csv"));
    //                     shiftingProcess(hasChangeInRac,seatDetails[0]);
    //                 }
    //             }else{
    //                 passengerWriter.write(passenger);
    //                 passengerWriter.newLine();
    //                 passengerWriter.flush();
    //             }
    //         }
    //         passengerReader.close();
    //         passengerWriter.close();
    //     // }
    //  }catch(Exception e){
    //     // System.out.println(e);
    //     e.printStackTrace();
    //  } 
    // }
    // public static String shiftingProcess(boolean hasChangeInRac,String seatId){
    //     String ans="";
    //     if(hasChangeInRac){
    //         BufferedReader racReader=new BufferedReader(new FileReader(trainName+"\\"+travelDate+"\\RacSeats.csv"));
    //         String racSeat=racReader.readLine();
    //         BufferedWriter racWriter=new BufferedWriter(new FileWriter(trainName+"\\"+travelDate+"\\TempRacSeats.csv"));
    //         racWriter.write(racSeat);
    //         racWriter.newLine();
    //         racWriter.flush();
    //         while((racSeat=racReader.readLine())!=null){
    //             if(Integer.parseInt(seatId)<Integer.parseInt(racSeat.split(",")[0])&&passengerDetails[5].equalsIgnoreCase("Rac")||!roughPassengerDetails[5].equalsIgnoreCase("Rac")){

    //             }
    //         }
    //     }
    // }
}
