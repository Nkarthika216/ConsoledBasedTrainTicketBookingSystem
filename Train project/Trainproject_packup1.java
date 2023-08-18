//Bookingutil
package TrainTicketBookingProject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import TrainTicketBookingProject.Booking.*;
import TrainTicketBookingProject.Train.*;
import TrainTicketBookingProject.persons.*;

public class Trainproject_packup1{
   static HashMap<String,User> userdetails=new HashMap<String,User>();
   static HashMap<String,String> logindetails=new HashMap<String,String>();
   static ArrayList<TrainDetails> trains=new ArrayList<TrainDetails>();
   static HashMap<Long,Account> accounts=new HashMap<Long,Account>();
   static HashMap<Long,Transaction> transactions=new HashMap<Long,Transaction>();
   static HashMap<Integer,Seat> allocatedseats=new HashMap<Integer,Seat>();
   static HashMap<Long,Ticket> ticketsList=new HashMap<Long,Ticket>();
   static HashMap<TrainDetails,ArrayList<Ticket>> train_ticket=new HashMap<TrainDetails,ArrayList<Ticket>>();
   static HashMap<Date,ArrayList<TrainDetails>> bookingsWithDate=new HashMap<Date,ArrayList<TrainDetails>>();
public static boolean isValidDateformat(String date){
    String regex="^[0-9]{1,2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{4}$";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(date);
    return m.matches();
}
public static boolean isValidDate(String date){
    boolean isValid=true;
    String[] arr=date.split("-");
    // System.out.println(arr[0]+"#"+arr[1]+"#"+arr[2]);
    if(Integer.parseInt(arr[1])<1||Integer.parseInt(arr[0])<1||Integer.parseInt(arr[2])<1){isValid=false;}
    // LocalDate birthday=LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    Year y=Year.of(Integer.parseInt(arr[2]));
    if(Year.isLeap(Integer.parseInt(arr[2]))&&Integer.parseInt(arr[1])==2){
        if(Integer.parseInt(arr[0])>29)
        isValid=false;
    }
    else if(!Year.isLeap(Integer.parseInt(arr[2]))&&Integer.parseInt(arr[1])==2){
        if(Integer.parseInt(arr[0])>28)
        isValid=false;
    }
    else if(Integer.parseInt(arr[1])<=12&&Integer.parseInt(arr[1])>=1){
    if(Integer.parseInt(arr[1])==1||Integer.parseInt(arr[1])==3||Integer.parseInt(arr[1])==5||Integer.parseInt(arr[1])==7||Integer.parseInt(arr[1])==8||Integer.parseInt(arr[1])==10||Integer.parseInt(arr[1])==12){
        if(Integer.parseInt(arr[0])>31)
        isValid=false;
    }
    else if(Integer.parseInt(arr[1])==4||Integer.parseInt(arr[1])==6||Integer.parseInt(arr[1])==9||Integer.parseInt(arr[1])==11){
        if(Integer.parseInt(arr[0])>30)
        isValid=false;
    }}
    else if(Integer.parseInt(arr[1])>12){
        isValid=false;
    }
    // else
    // isValid=false;
    return isValid;
}

public static String getPreference(String dob){
    String[] arr=dob.split("-");
    LocalDate dateOfBirth=LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    LocalDate today=LocalDate.now();
    Period period=Period.between(dateOfBirth, today);
    String preference="";
    if(period.getYears()>=50||period.getYears()<=12)
    preference="L";
    else if(period.getYears()>12&&period.getYears()<=35)
    preference="U";
    else
    preference="M";
    return preference;
}
public static boolean isValidTravelDate(String date){
    boolean isValid=false;
    String[] arr=date.split("-");
    // System.out.println(arr[0]+"#"+arr[1]+"#"+arr[2]);
    LocalDate traveldate=LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    LocalDate today=LocalDate.now();
    LocalDate valid_date=today.plusMonths(3).plusDays(1);
    Period p=Period.between(today,traveldate);
    Period prd=Period.between(traveldate, valid_date);
            if(!p.isNegative()&&!prd.isNegative()&&(p.getDays()>0||p.getMonths()>0)){
    isValid=true;
    }
    return isValid;
} 

public static boolean isValidDOB(String dob){
    boolean isValid=false;
    String arr[]=dob.split("-");
    LocalDate birthDate=LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
    Period p=Period.between(birthDate,LocalDate.now());
    if(!p.isNegative()){
        if(p.getYears()>5)
        isValid=true;
    }
    return isValid;
}
public static boolean isValidName(String name){
        String regex="[a-zA-Z]{2,}\\s*[\\D]";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(name);
        return m.matches();       
    }
public static boolean isValidMobileNumber(String mobile_no){
    String regex="[0]?[+91]?[(]?[6-9][0-9]{2}[)]?[0-9]{7}";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(mobile_no);
    return m.matches();   
}
public static boolean isValidMail(String mail){
    String regex="[a-zA-Z0-9][a-zA-Z0-9_[.]]+@{1}[a-zA-Z]{3,}[.[a-zA-Z]{2,}]+";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(mail);
    return m.matches();   
}
public static boolean isValidPassword(String pwd){
    String regex="[A-Z]+[a-z]+[\\W]+[0-9]+{8,16}";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(pwd);
    return m.matches();   
}
public static boolean isValidPnr(String pnr){
    String regex="[0-9]{10}";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(pnr);
    return m.matches(); 
}
public static boolean isValidPreference(String pref){
    String regex="[mMlLuU]{1}";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(pref);
    return m.matches(); 
}
public static boolean isValidCardNumber(String card_no){
    String regex="[0-9]{10}";
    Pattern p=Pattern.compile(regex);
    Matcher m=p.matcher(card_no);
    return m.matches(); 
}
    public static void main(String args[])throws ParseException{
        userdetails.put("karthi@gmail.com",new User("karthi","karthi@gmail.com","Nkarthi@123","9952857934"));

        logindetails.put("karthi@gmail.com","Nkarthi@123");
        accounts.put(9952857934L,new Account("139501000048773",20000,"SBI","Surandai","Pasupathi",new Payment(9952857934L,876,9,2024,"Pasupathi","9952857934")));
        // accounts.put(8220128243L,new Account("139501000012345",10000,"IOB","Surandai","Kumar"));
        // accounts.put(9488057934L,new Account("139501000023456",15000,"KVB","Madurai","Raj"));
        // accounts.put(7708656243L,new Account("139501000081101",27000,"IOB","Chennai","Kani"));
        long PNR=4243361634L;
        int passenger_id=1;int transaction_id=34720;
        trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));

        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",1,4,0,new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("MS","Chennai",250))),2));
        trains.add(new TrainDetails(16102,"QLN EXPRESS",1,4,0,new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("DG","Dindigul",230),new Station("MS","Chennai",270))),2));
        trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","Tirunelveli",0),new Station("VPT","Virudunagar",114),new Station("MDU","Madurai",157),new Station("MS","Chennai",245))),2));
        trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","Kanyakumari",0),new Station("TEN","Tirunelveli",90),new Station("MDU","Madurai",240),new Station("MS","Chennai",320))),2));
        TrainDetails currentTrain=null;
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4,6)/* ,new Coach("S2","Sleeper",4,6)*/)),2));

        // trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("DG","Dindigul",230),new Station("MS","Chennai",270))),2));

        // trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","Tirunelveli",0),new Station("VPT","Virudunagar",114),new Station("MDU","Madurai",157),new Station("MS","Chennai",245))),2));

        // trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","Kanyakumari",0),new Station("TEN","Tirunelveli",90),new Station("MDU","Madurai",240),new Station("MS","Chennai",320))),2));
      
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
                if(userdetails.containsKey(mail_id)){
                    // try{
                    //     BufferedReader br=new BufferedReader(new FileReader("Users.csv"));
                    //     String user="";
                    //     while((user=br.readLine())!=null){
                    //         String arr[]=user.split(",");
                    //         if(arr[0].equalsIgnoreCase(name)&&arr[1].equalsIgnoreCase(mail_id)&&arr[2].equalsIgnoreCase(password)&&arr[3].equalsIgnoreCase(phone_no)){
                    //             System.out.println("User already exists");
                    //             break;
                    //         }
                    //         else if(arr[1].equalsIgnoreCase(mail_id)&&(!arr[0].equalsIgnoreCase(name)||!arr[2].equalsIgnoreCase(password)||!arr[3].equalsIgnoreCase(phone_no))){
                    //             System.out.println("Try with another mail id");
                    //             break;
                    //         }
                    //     }
                    //        if(user==null){
                    //             try{
                    //                 BufferedWriter bw=new BufferedWriter(new FileWriter("Users.csv",true));
                    //                 bw.write(""+name+","+mail_id+","+password+","+phone_no);
                    //                 bw.newLine();
                    //                 bw.flush();
                    //                 bw.close();
                    //                 System.out.println("Successfully signed up");
                    //             }catch(Exception e){
                    //                 System.out.println("Error: while writing user details");
                    //             }
                    //         }
                        
                    // }catch(Exception e){
                    //     System.out.println("Error: while check user details");
                    // }
                //     System.out.println(new User(name,mail_id,password,phone_no));
                if(userdetails.get(mail_id).equals(new User(name,mail_id,password,phone_no))){
                System.out.println("User already exists");
                }
                else{
                System.out.println("Try with another mail id");
                }
                }
                else{
                //     try{
                //         BufferedWriter bw=new BufferedWriter(new FileWriter("Users.csv",true));
                //         bw.write(""+name+","+mail_id+","+password+","+phone_no);
                //         bw.newLine();
                //         bw.flush();
                //         bw.close();
                //     }catch(Exception e){
                //         System.out.println("Error: while writing user details");
                //     }
                userdetails.put(mail_id,new User(name,mail_id,password,phone_no));
                logindetails.put(mail_id,password);
                System.out.println("Successfully signed up....");
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
                currentUser=userdetails.get(mail.toLowerCase());
                System.out.println("User details:  "+userdetails);
                System.out.println("current user:  "+currentUser);
                boolean lp=true;
                while(lp){
                excp=true;
                byte ch=0;
                while(excp){
                    try{
                        System.out.println();
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
                    String dt="";
                    excp=true;
                    Date date=null;
                    while(excp){
                        try{
                        dt=in.next();
                        // excp=!(isValidDateformat(dt)&&isValidTravelDate(isValidDate(dt)));
                        excp=isValidDateformat(dt);
                        if(!excp){
                            System.out.println("Invalid date format\nEnter valid date format");
                            excp=true;
                            continue;
                        }
                        excp=isValidDate(dt);
                        if(!excp){
                            System.out.println("Invalid date\nEnter valid date");
                            excp=true;
                            continue;
                        }
                        excp=isValidTravelDate(dt);
                        if(!excp){
                            System.out.println("Journey date must be within 90 days from tomorrow\n\nEnter valid date");
                            excp=true;
                            continue;
                        }
                        if(excp){
                        date=new SimpleDateFormat("dd-MM-yyyy").parse(dt);
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


                    if(!bookingsWithDate.containsKey(date))
                    bookingsWithDate.put(date,addTrains());
                    System.out.println("From :");
                    in.nextLine();
                    String From=in.nextLine().toLowerCase();
                    System.out.println();
                    System.out.println("To  :");
                    String To=in.nextLine().toLowerCase();
                    // System.out.println("From :"+from+"  To: "+to);
                    HashMap<TrainDetails,Integer> availabletrainhash=availableTrains(date,From,To);
                    // System.out.println("Array length : "+al.size());
                    // for(TrainDetails i:al){
                    //     System.out.println(i.name);
                    // }
                    // System.out.println(al.size());
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
                                //collect short time booked seats 
                                for(Station station:currentTrain.route){
                                    if(station.name.equalsIgnoreCase(From)){
                                    from_index=currentTrain.route.indexOf(station);
                                    fromStation=station;
                                    }
                                    if(station.name.equalsIgnoreCase(To)){
                                    to_index=currentTrain.route.indexOf(station);
                                    toStation=station;
                                    }
                                    if(fromStation!=null&&toStation!=null)
                                    break;
                                }
                                for(Coach coach:currentTrain.coachs){
                                    for(Seat seat:coach.seats){
                                        if(seat.seat_status.indexOf(true)!=-1){
                                            int k=-1;
                                            for(k=from_index;k<to_index;k++){
                                                if(seat.seat_status.get(k)==true)
                                                break;
                                            }
                                            if(k==to_index){
                                                shorttime_availableseats.add(seat);
                                            }
                                        }
                                    }
                                }
                                int allowedcheck=0;
                                for(Coach coach:currentTrain.coachs){
                                    for(Seat seat:coach.seats){
                                        if(seat.seat_status.contains(false)){
                                        allowedcheck++;
                                        break;
                                        }
                                    }
                                }
                                if(allowedcheck>0&&currentTrain.waitinglist+1>currentTrain.rac_count){

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
                            // int loop_count=(pass_count%limit==0)?pass_count/limit:(pass_count/limit)+1;
                            // for(int n=0;n<loop_count;n++){//for
                                addpass=true;
                                int innerloopcount=0;
                                PNR++;
                                ArrayList<Passenger> roughPassengerDetails=new ArrayList<Passenger>();
                           while(addpass&&innerloopcount<limit){
                                lt++;
                                innerloopcount++;
                                System.out.println();
                                System.out.println("Enter passenger name");
                                if(lt==1)
                                in.nextLine();
                                name=in.nextLine();
                                // in.nextLine();
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
                                        // excp=!(isValidDateformat(dob)&&isValidDOB(dob)&&isValidDate(dob));
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
                                            excp=false;
                                        }
                                        if(excp){
                                            birthDate=new SimpleDateFormat("dd-MM-yyyy").parse(dob);
                                            excp=false;
                                        }
                                        // else{
                                        // }
                                        }catch(Exception e){
                                            System.out.println("Invalid date\n\nEnter valid date");
                                            excp=true;
                                        }
                                    }
                        Seat currentSeat=null;
                                adult_count++;
                                System.out.println();                                
                                // System.out.println("Enter your seat preference(M/L/U)");
                            String preference=getPreference(dob);
                        //     loop=true;
                        // while(loop){
                        //         preference=in.next();
                        //         loop=!isValidPreference(preference);
                        //         if(loop)
                        //         System.out.println("Enter a valid preference");
                        // }
                            if(preference.equalsIgnoreCase("u")){
                                if(shorttime_availableseats.size()>0){
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.ubList.size()>0){
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.mbList.size()>0){
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.lbList.size()>0){
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    currentTrain.rac++;
                                    currentSeat=currentTrain.racList.get(0);
                                    currentTrain.racList.removeFirst();
                                }
                                else
                                {
                                    ArrayList<Boolean> seat_status=new ArrayList<Boolean>();
                                    for(int k=0;k<currentTrain.station_count;k++){
                                        seat_status.add(false);
                                    }
                                    currentSeat=new Seat("WL"+(++currentTrain.waitinglist),"---","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                }
                            }

                            if(preference.equalsIgnoreCase("m")){
                                if(shorttime_availableseats.size()>0){
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.mbList.size()>0){
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.ubList.size()>0){
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.lbList.size()>0){
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    currentTrain.rac++;
                                    currentSeat=currentTrain.racList.get(0);
                                    currentTrain.racList.removeFirst();
                                }
                                else
                                {
                                    ArrayList<Boolean> seat_status=new ArrayList<Boolean>();
                                    for(int k=0;k<currentTrain.station_count;k++){
                                        seat_status.add(false);
                                    }
                                    currentSeat=new Seat("WL"+(++currentTrain.waitinglist),"---","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                }
                             }

                            if(preference.equalsIgnoreCase("l")){
                                if(shorttime_availableseats.size()>0){
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.lbList.size()>0){
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.mbList.size()>0){
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.ubList.size()>0){
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    currentTrain.rac++;
                                    currentSeat=currentTrain.racList.get(0);
                                    currentTrain.racList.removeFirst();
                                }
                                else
                                {
                                    ArrayList<Boolean> seat_status=new ArrayList<Boolean>();
                                    for(int k=0;k<currentTrain.station_count;k++){
                                        seat_status.add(false);
                                    }
                                    currentSeat=new Seat("WL"+(++currentTrain.waitinglist),"---","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                }
                            }
                            // }
                            allocatedseats.put(passenger_id,currentSeat);
                            roughPassengerDetails.add(new Passenger(passenger_id++,name,dob,currentSeat,"booked"));
                            if(lt==pass_count)
                            addpass=false;
                            }//while
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
                                //||!(accounts.containsKey(card_no))
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
                                if(accounts.containsKey(card_no)){
                                    Account account=accounts.get(card_no);
                                    Payment payment=account.payment;
                                    if(payment.ccv==ccv&&payment.exp_month==exp_month&&payment.exp_year==exp_year&&payment.card_holdername.equalsIgnoreCase(holdername)&&payment.holder_mobileno.equalsIgnoreCase(mobile)){
                                        isValidCard=true;
                                        System.out.println("Current user:  "+currentUser);
                                        System.out.println("Mail id:  "+currentUser.mail_id);
                                        System.out.println("Successfully Booked....");  
                                        transactions.put((PNR),new Transaction(""+transaction_id++,ticketcost,LocalDateTime.now()));
                                        currentUser.addbookedtickets(new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,new Payment(card_no, ccv, exp_month, exp_year, holdername,mobile),roughPassengerDetails,"booked",currentUser.mail_id,date));
                                        currentTrain.TicketList.add(new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,new Payment(card_no, ccv, exp_month, exp_year, holdername,mobile),roughPassengerDetails,"booked",currentUser.mail_id,date));
                                        train_ticket.put(currentTrain,currentTrain.TicketList);
                                        for(Passenger psr:roughPassengerDetails)
                                        currentTicket=new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,new Payment(card_no, ccv, exp_month, exp_year, holdername,mobile),roughPassengerDetails,"booked",currentUser.mail_id,date);
                                        ticketsList.put(PNR,new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,new Payment(card_no, ccv, exp_month, exp_year, holdername,mobile),roughPassengerDetails,"booked",currentUser.mail_id,date));
                                    // }//for
                                        boolean isExpector=false;
                                        System.out.println();
                                    System.out.println("---------------------------------------------------------------------------------------------------"+
                                    "\nTrain number     : "+currentTicket.getTrain_no()+"                              Train name      :"+currentTicket.getTrain_name()+
                                    "\nPNR number       : "+currentTicket.getPnr()+"                         passengercount  : "+currentTicket.getPassengers_count()+
                                    "\nFrom             : "+currentTicket.getFrom().name+"                            To              : "+currentTicket.getTo().name+
                                    "\nDate of booking  : "+transactions.get(currentTicket.getPnr()).getTime()+"      Transaction id  : "+transactions.get(currentTicket.getPnr()).getId()+
                                    "\nJourney Date     : "+currentTicket.Journey_date+"       Booked by       : "+currentTicket.bookedby);
                                     System.out.println("*************************************************************************************************");
                                    ArrayList<Passenger>psr=currentTicket.getPassengers();
                                     System.out.println("Passenger     passenger     passenger     Seat     berth       Status       coach       class"+
                                                       "\n  id          name           DOB        number     type                    number       type");
                                                       System.out.println();
                                     for(Passenger v:psr){
                                        System.out.println(" "+v.id+"            "+v.name+"         "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                        +"     "+v.status+"        "+allocatedseats.get(v.id).coachname+"       "+allocatedseats.get(v.id).classtype); 
                                        if(allocatedseats.get(v.id).berth_type.equalsIgnoreCase("Rac")||allocatedseats.get(v.id).berth_type.equals("---"))
                                        isExpector=true;
                                                for(int k=from_index;k<to_index;k++){
                                                    if(allocatedseats.get(v.id).seat_status!=null)
                                                    allocatedseats.get(v.id).seat_status.set(k,true);
                                                }
                                                if(!allocatedseats.get(v.id).number.contains("WL")){
                                                   Seat currentSeat=allocatedseats.get(v.id);
                                                        if(currentTrain.bookedseatList.size()>0){
                                                            int i=0;
                                                            for(i=0;i<currentTrain.bookedseatList.size();i++){
                                                                Seat seat=currentTrain.bookedseatList.get(i);
                                                            if(seat.equals(currentSeat))
                                                            break;
                                                    }
                                                    if(i==currentTrain.bookedseatList.size())
                                                    currentTrain.bookedseatList.add(allocatedseats.get(v.id));
                                                }
                                                else{
                                                    currentTrain.bookedseatList.add(allocatedseats.get(v.id));
                                                }
                                                }
                                    }
                                    if(isExpector)
                                    currentTrain.berth_expectors.add(currentTicket);
                                     System.out.println("-------------------------------------------------------------------------------------------------");
        
                                    }else
                                    System.out.println("Invalid card details");
                                }else{
                                    System.out.println("Invalid card details");
                                }
                            }
            
                            // if(isValidCard){
                                // DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");.format(formatter)
                              
                        // }
        
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
                    ArrayList<Passenger> cancelPassengers=new ArrayList<Passenger>(); 
                    Seat cancelseat=null;
                    Ticket cancelTicket=null;
                    if(currentUser.bookedtickets.size()>0){
                    excp=true;
                    long cancel_pnr=0;
                    byte count=0;
                    while(excp){
                        try{
                            count++;
                            System.out.println();
                            System.out.println("Enter your pnr number");
                            cancel_pnr=in.nextLong();
                            excp=!isValidPnr(""+cancel_pnr)||count==3;
                            if(excp)
                            System.out.println("Invalid pnr");
                        }catch(Exception e){
                            System.out.println("Invalid pnr");
                        }
                        }
                        
                        if(ticketsList.containsKey(cancel_pnr)){
                        cancelTicket=ticketsList.get(cancel_pnr);
                        // for(Map.Entry<TrainDetails,ArrayList<Ticket>> tt:train_ticket.entrySet()){
                        //     if(tt.getKey().number==cancelTicket.train_no){
                        //         currentTrain=tt.getKey();
                        //         break;
                        //     }
                        // }
                        for(TrainDetails train:bookingsWithDate.get(cancelTicket.Journey_date)){
                            if(cancelTicket.train_no==train.number){
                            currentTrain=train;
                            break;
                            }
                        }
                        if(cancelTicket.status.equalsIgnoreCase("Booked")){
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
                            cancelTicket.status="Cancelled";
                            passengers=cancelTicket.getPassengers();
                            for(Passenger p:passengers){
                            System.out.println("Berth expectors:  "+currentTrain.berth_expectors.size());
                            // System.out.println("First index ticket pnr:   "+currentTrain.berth_expectors.get(0).pnr);

                            if(p.status.equalsIgnoreCase("booked")){
                            p.status="cancelled";
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
                                            if(allocatedseats.get(psr.id).berth_type.equals("Rac")&&allocatedseats.get(p.id).berth_type.equalsIgnoreCase("WaitingList"))
                                                continue;
                                            else if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---")){
                                                tempSeat=allocatedseats.get(psr.id);
                                                allocatedseats.put(psr.id,cancelseat);
                                                cancelseat=tempSeat;
                                                System.out.println("Cancel seat berth:  "+cancelseat.berth_type+"    number:  "+cancelseat.number);
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
                            }//if

                            

                            }

                            currentUser.cancelledtickets.add(currentTicket);
                            System.out.println("Successfully cancelled");
                            
                            
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
                                                            if(allocatedseats.get(psr.id).berth_type.equals("Rac")&&allocatedseats.get(p.id).berth_type.equalsIgnoreCase("WaitingList"))
                                                                continue;
                                                            else if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("---")){
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
            }
                            lp=true;
                            break;
                        }else{
                System.out.println("Already cancelled...");
            }
                    }
                    else 
                    System.out.println("PNR not exist");
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
    public static boolean isValidLogin(HashMap<String,String> logindetails,String mail,String pwd){
        boolean login=false;
        // try{
        //     BufferedReader br = new BufferedReader(new FileReader("Users.csv"));
        //     String user="";
        //     while((user=br.readLine())!=null){
        //         String arr[]=user.split(",");
        //         if(arr[1].equalsIgnoreCase(mail)&&arr[2].equalsIgnoreCase(pwd))
        //         login=true;
        //     }
        // } catch (IOException e) {
        //     System.out.println("Error: while read user details");
        //     e.printStackTrace();
        // }
        if(logindetails.containsKey(mail.toLowerCase()))
        {
            if((logindetails.get(mail.toLowerCase())).equals(pwd))
                 login=true;
        }
        return login;
    }
    // public static ArrayList<TrainDetails> availableTrains(ArrayList<TrainDetails> trains,String from,String to){
    //     ArrayList<TrainDetails> al=new ArrayList<TrainDetails>();
    //     for(TrainDetails i:trains){
    //         ArrayList<Station> st=i.route;
    //         for(int k=0;k<st.size();k++){
    //             String name=st.get(k).name;
    //             if(name.equalsIgnoreCase(from)){
    //                 for(int l=k+1;l<st.size();l++){
    //                     name=st.get(l).name;
    //                     if(name.equalsIgnoreCase(to)){
    //                         // if(i.waitinglist<=i.rac_count)
    //                     al.add(i);
    //                     }
    //                 }
    //             }
    //         }
    //         }
    //     return al;
    // }

// //new available train
public static HashMap<TrainDetails,Integer> availableTrains(Date date,String from,String to){
    ArrayList<TrainDetails> trains=bookingsWithDate.get(date);
    HashMap<TrainDetails,Integer> al=new HashMap<TrainDetails,Integer>();
for(TrainDetails train:trains){
        int availableseats=0;
        int from_index=-1;
        int to_index=-1;
    for(Station station:train.route){
           if(station.name.equalsIgnoreCase(from))
           from_index=train.route.indexOf(station);
           if(station.name.equalsIgnoreCase(to))
           to_index=train.route.indexOf(station);
        }
    if(from_index!=-1&&to_index!=-1&&to_index-from_index>=1){
for(Coach coach:train.coachs){
    for(Seat seat:coach.seats){
        if(seat.seat_status.indexOf(true)!=-1){
            int k=-1;
            for(k=from_index;k<to_index;k++){
                if(seat.seat_status.get(k)==true)
                break;
            }
            if(k==to_index){
                availableseats++;
            }
        }
    }
}
al.put(train,availableseats);
}
}
return al;
}

    public static ArrayList<Double> getticketcharge(ArrayList<TrainDetails> al,String from,String to){
        ArrayList<Double> ticket_cost=new ArrayList<Double>();
        int x=0,y=0;
        for(TrainDetails i:al){
            ArrayList<Station> stations=i.route;
            for(Station k:stations){
                if(k.name.equalsIgnoreCase(from))
                x=k.distance;

                if(k.name.equalsIgnoreCase(to))
                y=k.distance;
            }
            ticket_cost.add((double)((y-x)*i.rate));
        }
        return ticket_cost;
    }
    public static boolean isadult(int age){
        boolean adult=false;
        if(age>5)
        adult=true;
        return adult;
    }
    public static double totalticketcost(int adults,double rate){
        return adults*rate;
    }
    public static ArrayList<TrainDetails> addTrains(){
        ArrayList<TrainDetails> trains=new ArrayList<TrainDetails>();
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4,6)/* ,new Coach("S2","Sleeper",4,6)*/)),2));
        trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));

        // trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("DG","Dindigul",230),new Station("MS","Chennai",270))),2));

        // trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","Tirunelveli",0),new Station("VPT","Virudunagar",114),new Station("MDU","Madurai",157),new Station("MS","Chennai",245))),2));

        // trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","Kanyakumari",0),new Station("TEN","Tirunelveli",90),new Station("MDU","Madurai",240),new Station("MS","Chennai",320))),2));
      return trains;
    }
}
    