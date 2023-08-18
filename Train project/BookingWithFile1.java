package TrainTicketBookingProject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import TrainTicketBookingProject.Booking.*;
import TrainTicketBookingProject.Train.*;
import TrainTicketBookingProject.persons.*;
import java.util.stream.*;

public class BookingWithFile1{
   static LinkedHashMap<String,User> userdetails=new LinkedHashMap<String,User>();
   static LinkedHashMap<String,String> logindetails=new LinkedHashMap<String,String>();
   static ArrayList<TrainDetails> trains=new ArrayList<TrainDetails>();
   static LinkedHashMap<Long,Account> accounts=new LinkedHashMap<Long,Account>();
   static LinkedHashMap<Long,Transaction> transactions=new LinkedHashMap<Long,Transaction>();
   static LinkedHashMap<Integer,Seat> allocatedseats=new LinkedHashMap<Integer,Seat>();
   static LinkedHashMap<Long,Ticket> ticketsList=new LinkedHashMap<Long,Ticket>();
//    static LinkedHashMap<TrainDetails,ArrayList<Ticket>> train_ticket=new LinkedHashMap<TrainDetails,ArrayList<Ticket>>();
   static LinkedHashMap<String,ArrayList<TrainDetails>> bookingsWithDate=new LinkedHashMap<String,ArrayList<TrainDetails>>();
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
        // System.out.println("helloooo....");
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));

        // trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("DG","dindigul",230),new Station("MS","chennai",270))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));

        // trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","tirunelveli",0),new Station("VPT","virudunagar",114),new Station("MDU","madurai",157),new Station("MS","chennai",245))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));

        // trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","kanyakumari",0),new Station("TEN","tirunelveli",90),new Station("MDU","madurai",240),new Station("MS","chennai",320))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));
      
        // try{
        //     File stationFile=new File("Station.csv");
        //     BufferedWriter bw=new BufferedWriter(new FileWriter("Station.csv"));
        //     bw.write("Id,Code,Name");
        //     bw.newLine();
        //     bw.flush();
        //     int id=1;
        //     for(TrainDetails train:trains){
        //         for(Station st:train.route){
        //             BufferedReader br=new BufferedReader(new FileReader("Station.csv"));
        //             String line="";
        //             while(!((line=br.readLine())==null)){
        //                 if(line.contains(st.name)){
        //                     br.close();
        //                     break;
        //                 }
        //             }
        //             if(line==null){
        //                 bw.write(""+id++ +","+st.code+","+st.name);
        //                 bw.newLine();
        //                 bw.flush();
        //             }
        //         }
        //     }
        //     bw.close();
        // }catch(Exception e){
        //     e.printStackTrace();
        //     System.out.println("Error: While writing station...");
        // }
        
        // File trainFile=new File("Trains.csv");
        // try{
        //     System.out.println("first");
        //     BufferedWriter bw=new BufferedWriter(new FileWriter("Trains.csv"));
        //     bw.write("Id,Number,Name,Route,Rate");
        //     bw.newLine();
        //     int trainId=0;
        //     for(TrainDetails train:trains){
        //       System.out.println("second");
        //         trainId++;
        //         String route="";
        //         File trainFolder=new File(train.name);
        //         trainFolder.mkdir();
        //         int count=0;
        //         for(Station st:train.route){
        //             System.out.println("third");
        //             count++;
        //             BufferedReader br=new BufferedReader(new FileReader("Station.csv"));
        //             String line="";
        //             while(!((line=br.readLine())==null)){
        //                 System.out.println("fourth");
        //                 if((line).contains(st.name)){
        //                     System.out.println("fivth");
        //                     String arr[]=line.split(",");
        //                     route+=arr[0]+"_"+st.distance;
        //                     if(count!=(train.route).size())
        //                     route+="#";
        //                 }
        //             }
        //             br.close();
        //         }
        //         int num=0;
        //         // String chId="";
        //         int number=0;
        //         // try{
        //             File coachlistFile=new File(train.name+"\\"+"CoachList.csv");
        //              System.out.println(coachlistFile.createNewFile());
        //             int trainCoachId=0;
        //         BufferedWriter bWriter=new BufferedWriter(new FileWriter(train.name+"\\"+"CoachList.csv"));
        //         bWriter.write("Id,Name,Coach type,Seat count");
        //         bWriter.newLine();
        //         bWriter.flush();
        //         for(Coach ch:train.coachs){
        //             num++;
        //                 bWriter.write(num+","+ch.name+","+ch.class_type+","+ch.seat_count);
        //                 bWriter.newLine();
        //                 bWriter.flush();
        //         }
        //         bWriter.close();
        //         bw.write(trainId+","+train.number+","+train.name+","+route+","+train.rate);
        //         bw.newLine();
        //     }
        //     bw.flush();
        //     bw.close();
        // }catch(Exception e){
        //     System.out.println("Error: while writing traindetails");
        //     e.printStackTrace();
        // }
        // userdetails.put("karthi@gmail.com",new User("karthi","karthi@gmail.com","Nkarthi@123","9952857934"));
        // accounts.put(9952857934L,new Account("139501000048773",20000,"SBI","Surandai","Pasupathi",new Payment(9952857934L,876,9,2024,"Pasupathi","9952857934")));
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));
        // logindetails.put("karthi@gmail.com","Nkarthi@123");
        
// try{
//     BufferedWriter paymentsWriter=new BufferedWriter(new FileWriter("Payments.csv"));
//     paymentsWriter.write("Card number,cvv,Expiry month,Expiry year,Card holder name,Phone number,Balance,Bank name,Branch name");
//     paymentsWriter.newLine();
//     paymentsWriter.flush();
//     for(Map.Entry<Long,Account> map:accounts.entrySet()){
//         Account account=map.getValue();
//         Payment payment=account.payment;
//         paymentsWriter.write(payment.card_no+","+payment.ccv+","+payment.exp_month+","+payment.exp_year+","+payment.card_holdername+","+payment.holder_mobileno+","+account.getBalance()+","+account.bankname+","+account.branch_name);
//         paymentsWriter.newLine();
//         paymentsWriter.flush();
//     }
//     paymentsWriter.close();
// }catch(Exception e){
//     System.out.println(e);
// }
        // loadAllCollections();
        // HashMap<String,User> userdetails=new HashMap<String,User>();
        // HashMap<String,String> logindetails=new HashMap<String,String>();
        // final ArrayList<TrainDetails> trains=new ArrayList<TrainDetails>();
        // HashMap<Long,Account> accounts=new HashMap<Long,Account>();
        // HashMap<Long,Transaction> transactions=new HashMap<Long,Transaction>();
        // HashMap<Integer,Seat> allocatedseats=new HashMap<Integer,Seat>();
        // HashMap<Long,Ticket> ticketsList=new HashMap<Long,Ticket>();
        // HashMap<TrainDetails,ArrayList<Ticket>> train_ticket=new HashMap<TrainDetails,ArrayList<Ticket>>();

        loadAllCollections();
        //command
        // HashMap<User,ArrayList<Ticket>> bookings=new HashMap<User,ArrayList<Ticket>>();
        // ArrayList<Payment> payments=new ArrayList<Payment>();
        // HashMap<TrainDetails,HashMap<String,HashMap<Integer,Seat>>> bookings_withdate=new HashMap<TrainDetails,HashMap<String,HashMap<Integer,Seat>>>();
        // HashMap<Integer,Long> passengerid_pnr=new HashMap<Integer,Long>();
        // accounts.put(8220128243L,new Account("139501000012345",10000,"IOB","Surandai","Kumar"));
        // accounts.put(9488057934L,new Account("139501000023456",15000,"KVB","Madurai","Raj"));
        // accounts.put(7708656243L,new Account("139501000081101",27000,"IOB","Chennai","Kani"));
        // System.out.println(4243361634L+324);
        int passenger_id=allocatedseats.size();int transaction_id=0; 
        long PNR=4243361634L+ticketsList.size();
        System.out.println("Ticketslist size:  "+ticketsList.size()+"   pnr:  "+PNR+"   allocatedseats size:  "+allocatedseats.size()+"   passenger id:  "+passenger_id);
        // trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("DG","dindigul",230),new Station("MS","chennai",270))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4))),2));

        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4)/*,new Coach("S2","Sleeper",4)*/)),2));
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",1,4,0,new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("MS","Chennai",250))),2));
        // trains.add(new TrainDetails(16102,"QLN EXPRESS",1,4,0,new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("DG","Dindigul",230),new Station("MS","Chennai",270))),2));
        // trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","Tirunelveli",0),new Station("VPT","Virudunagar",114),new Station("MDU","Madurai",157),new Station("MS","Chennai",245))),2));
        // trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","Kanyakumari",0),new Station("TEN","Tirunelveli",90),new Station("MDU","Madurai",240),new Station("MS","Chennai",320))),2));
        TrainDetails currentTrain=null;
        // trains.add(new TrainDetails(12662,"POTHIGAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","sengottai",0),new Station("TSI","tenkasi",10),new Station("SNKL","sankarankovil",45),new Station("SVKS","sivakasi",105),new Station("MDU","madurai",174),new Station("MS","chennai",250))),new ArrayList<Coach>(Arrays.asList(new Coach("S1","Sitting",4,6)/* ,new Coach("S2","Sleeper",4,6)*/)),2));

        // trains.add(new TrainDetails(16102,"QLN EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("SCT","Sengottai",0),new Station("TSI","Tenkasi",10),new Station("SNKL","Sankarankovil",45),new Station("SVKS","Sivakasi",105),new Station("MDU","Madurai",174),new Station("DG","Dindigul",230),new Station("MS","Chennai",270))),2));

        // trains.add(new TrainDetails(12632,"NELLAI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("TEN","Tirunelveli",0),new Station("VPT","Virudunagar",114),new Station("MDU","Madurai",157),new Station("MS","Chennai",245))),2));

        // trains.add(new TrainDetails(12634,"KANYAKUMARI EXPRESS",/*1,4,0,*/new ArrayList<Station>(Arrays.asList(new Station("CAPE","Kanyakumari",0),new Station("TEN","Tirunelveli",90),new Station("MDU","Madurai",240),new Station("MS","Chennai",320))),2));

        

        
        // try{
        // File user=new File("Users.csv");
        //     if(!user.exists()){
        //         user.createNewFile();
        //         BufferedWriter bw=new BufferedWriter(new FileWriter("Users.csv"));
        //         bw.write("Name,Mail id,Password,Phone number");
        //         bw.newLine();
        //         bw.write("karthi,karthi@gmail.com,Nkarthi@123,9952857934");
        //         bw.newLine();
        //         bw.flush();
        //         bw.close();
        //     }
        // }catch(Exception e){
        //     System.out.println("Error: while creating file user");
        // }


        // try{
        //     File stationFile=new File("Station.csv");
        //     BufferedWriter bw=new BufferedWriter(new FileWriter("Station.csv"));
        //     bw.write("Id,Code,Name");
        //     bw.newLine();
        //     bw.flush();
        //     int id=1;
        //     for(TrainDetails train:trains){
        //         for(Station st:train.route){
        //             BufferedReader br=new BufferedReader(new FileReader("Station.csv"));
        //             String line="";
        //             while(!((line=br.readLine())==null)){
        //                 if(line.contains(st.name)){
        //                     br.close();
        //                     break;
        //                 }
        //             }
        //             if(line==null){
        //                 bw.write(""+id++ +","+st.code+","+st.name);
        //                 bw.newLine();
        //                 bw.flush();
        //             }
        //         }
        //     }
        //     bw.close();
        // }catch(Exception e){
        //     e.printStackTrace();
        //     System.out.println("Error: While writing station...");
        // }


        //write coach list(one time)

        // try{
        //     File Coachs=new File("Coachs.csv");
        //     Coachs.createNewFile();
            
        //     int id=0;
        //     BufferedWriter bw=new BufferedWriter(new FileWriter("Coachs.csv"));
        //     bw.write("  Id  ,   Name  ,  Class type  ,  Seat count ");
        //     bw.newLine();
        //     bw.flush();
        //     int t=0;
        //     for(TrainDetails train:trains){
        //         int s=0;
        //         for(Coach ch:train.coachs){
        //     BufferedReader br=new BufferedReader(new FileReader("Coachs.csv"));
        //             String line="";
        //             while(((line=br.readLine())!=null)){
        //                     if(line.contains(ch.name+","+ch.class_type+","+ch.seat_count)){
        //                     break;
        //                 }
        //             }
        //             if(line==null){
        //                 bw.write(""+(++id)+","+ch.name+","+ch.class_type+","+ch.seat_count);
        //                 bw.newLine();
        //                 bw.flush();
        //             }
        //     br.close();
        //         }
        //     }
        //     bw.close();
        //  }catch(Exception e){
        //     System.out.println("Error: while writing coach");
        // }

        
        // //Train Details file(one time)
//         File trainFile=new File("Trains.csv");
//         try{
//             BufferedWriter bw=new BufferedWriter(new FileWriter("Trains.csv"));
//             bw.write("Id,Number,Name,Route,Coachs,Rate");
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
//                     File coachlistFile=new File(train.name+"\\"+"CoachList.csv");
//                     coachlistFile.createNewFile();
//                     int trainCoachId=0;
//                 BufferedWriter bWriter=new BufferedWriter(new FileWriter(train.name+"\\"+"CoachList.csv"));
//                 bWriter.write("Id,Name,Coach type");
//                 bWriter.newLine();
//                 bWriter.flush();
//                 for(Coach ch:train.coachs){
//                     num++;
//                     File coachFile=new File("Coachs.csv");
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
//                         }
//                 }
//                 bWriter.close();
//                 bw.write(trainId+","+train.number+","+train.name+","+route+","+chId+","+train.rate);
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
    //     try{
    //             File passengerFile=new File("Passengers.csv");
    //             passengerFile.createNewFile();
    //             BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("Passengers.csv"));
    //             passengerWriter.write("Id,Name,Date of birth,Status,Seat id,Berth,Coach name,Coach type,Travel,Seat status File");
    //             passengerWriter.newLine();
    //             passengerWriter.flush();
    //             passengerWriter.write("1,vidhya,19-07-2000,Booked,1,Lower,S1,Sitting,1#6,POTHIGAIEXPRESS\\21-07-2023\\LowerSeats.csv");
    //             passengerWriter.newLine();
    //             passengerWriter.flush();
    //             passengerWriter.close();
    //     }catch(Exception e){
    //         System.out.println("Error: while writing passengers...");
    //     }
    // try{
    //     File ticketFile=new File("Tickets.csv");
    //     ticketFile.createNewFile();
    //     BufferedWriter ticketWriter=new BufferedWriter(new FileWriter("Tickets.csv"));
    //     ticketWriter.write("Id(PNR),Train name,Train number,From,To,Travel date,Status,Booked by,Passengers,Trasaction id");
    //     ticketWriter.newLine();
    //     ticketWriter.flush();
    //     ticketWriter.close();
    // }catch(Exception e){
    //     e.printStackTrace();
    //     System.out.println("Error: while creating passengers file");
    // }

System.out.println("Accounts size:  "+accounts.size());
System.out.println("Trains size:  "+trains.size());
System.out.println("User size:  "+userdetails.size());
System.out.println("Login size:  "+logindetails.size());
System.out.println("Bookings  size:  "+bookingsWithDate.size());

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
                // System.out.println();
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
                System.out.println("User size:  "+userdetails.size());
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
                currentUser=userdetails.get(mail.toLowerCase());
                    loadUserBookedTickets(currentUser);
                    System.out.println();
                System.out.println("Login success...");
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
                    if(!bookingsWithDate.containsKey(dt))
                    bookingsWithDate.put(dt,addTrains(dt));
                    // else
                    // loadPreviousBookings(date);
                    System.out.println("From :");
                    in.nextLine();
                    String From=in.nextLine().toLowerCase();
                    System.out.println();
                    System.out.println("To  :");
                    String To=in.nextLine().toLowerCase();
                    // System.out.println("From :"+from+"  To: "+to);
                    HashMap<TrainDetails,Integer> availabletrainhash=availableTrains(dt,From,To);
                    System.out.println("Available train hash map size:  "+availabletrainhash.size());
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
                                                System.out.println("Short time available added:  "+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
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
                                // if(lt==1)
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
                                System.out.println("Enter your seat preference(M/L/U)");
                            String preference="";
                            loop=true;
                        while(loop){
                                preference=in.next();
                                loop=!isValidPreference(preference);
                                if(loop)
                                System.out.println("Enter a valid preference");
                        }
                            if(preference.equalsIgnoreCase("u")){
                                if(shorttime_availableseats.size()>0){
                                    System.out.println("STA"+shorttime_availableseats);
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.ubList.size()>0){
                                    System.out.println("UBA"+currentTrain.ubList);
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.mbList.size()>0){
                                    System.out.println("MBA"+currentTrain.mbList);
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.lbList.size()>0){
                                    System.out.println("LBA"+currentTrain.lbList);
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    System.out.println("RBA"+currentTrain.racList);
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
                                    currentSeat=new Seat("WL"+(currentTrain.wList.size()+1),"WaitingList","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                    currentTrain.waitinglist=currentTrain.wList.size();
                                }
                            }

                            if(preference.equalsIgnoreCase("m")){
                                if(shorttime_availableseats.size()>0){
                                    System.out.println("STA"+shorttime_availableseats);
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.mbList.size()>0){
                                    System.out.println("MBA"+currentTrain.mbList);
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.ubList.size()>0){
                                    System.out.println("UBA"+currentTrain.ubList);
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.lbList.size()>0){
                                    System.out.println("LBA"+currentTrain.lbList);
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    System.out.println("RBA"+currentTrain.racList);
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
                                    currentSeat=new Seat("WL"+(currentTrain.wList.size()+1),"WaitingList","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                    currentTrain.waitinglist=currentTrain.wList.size();
                                }
                             }

                            if(preference.equalsIgnoreCase("l")){
                                if(shorttime_availableseats.size()>0){
                                    System.out.println("STA"+shorttime_availableseats);
                                    currentSeat=shorttime_availableseats.get(0);
                                    shorttime_availableseats.remove(0);
                                }
                                else if(currentTrain.lbList.size()>0){
                                    System.out.println("LBA"+currentTrain.lbList);
                                    currentSeat=currentTrain.lbList.get(0);
                                    currentTrain.lbList.removeFirst();
                                }
                                else if(currentTrain.mbList.size()>0){
                                    System.out.println("MBA"+currentTrain.mbList);
                                    currentSeat=currentTrain.mbList.get(0);
                                    currentTrain.mbList.removeFirst();
                                }
                                else if(currentTrain.ubList.size()>0){
                                    System.out.println("UBA"+currentTrain.ubList);
                                    currentSeat=currentTrain.ubList.get(0);
                                    currentTrain.ubList.removeFirst();
                                }
                                else if(currentTrain.racList.size()>0){
                                    System.out.println("RBA"+currentTrain.racList);
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
                                    currentSeat=new Seat("WL"+(currentTrain.wList.size()+1),"WaitingList","---","---",seat_status);
                                    currentTrain.wList.add(currentSeat);
                                    currentTrain.waitinglist=currentTrain.wList.size();
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
                                        currentUser.addbookedtickets(new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,transaction_id,roughPassengerDetails,"booked",currentUser.mail_id,dt));
                                        currentTrain.TicketList.add(new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,transaction_id,roughPassengerDetails,"booked",currentUser.mail_id,dt));
                                        // train_ticket.put(currentTrain,currentTrain.TicketList);
                                        for(Passenger psr:roughPassengerDetails)
                                        currentTicket=new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,transaction_id,roughPassengerDetails,"booked",currentUser.mail_id,dt);
                                        ticketsList.put(PNR,new Ticket(currentTrain.number,currentTrain.name,fromStation,toStation,PNR,innerloopcount,transaction_id,roughPassengerDetails,"booked",currentUser.mail_id,dt));
                                        transactions.put((PNR),new Transaction(""+(PNR-4243361634L),ticketcost,LocalDateTime.now()));
                                        // }//for
                                        boolean isExpector=false;
                                        System.out.println();
                                    System.out.println("---------------------------------------------------------------------------------------------------"+
                                    "\nTrain number     : "+currentTicket.getTrain_no()+"                              Train name      :"+currentTicket.getTrain_name()+
                                    "\nPNR number       : "+currentTicket.getPnr()+"                         passengercount  : "+currentTicket.getPassengers_count()+
                                    "\nFrom             : "+currentTicket.getFrom().name+"                            To              : "+currentTicket.getTo().name+
                                    "\nDate of booking  : "+transactions.get(currentTicket.getPnr()).getTime()+"      Transaction id  : "+currentTicket.transaction_id+
                                    "\nJourney Date     : "+currentTicket.journeyDate+"       Booked by       : "+currentTicket.bookedby);
                                     System.out.println("*************************************************************************************************");
                                    ArrayList<Passenger>psr=currentTicket.getPassengers();
                                     System.out.println("Passenger     passenger     passenger     Seat     berth       Status       coach       class"+
                                                       "\n  id          name           DOB        number     type                    number       type");
                                                       System.out.println();
                                     for(Passenger v:psr){
                                        System.out.println(" "+v.id+"            "+v.name+"         "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                        +"     "+v.status+"        "+allocatedseats.get(v.id).coachname+"       "+allocatedseats.get(v.id).classtype); 
                                        // {
                                            // change the status of coach seats
                                            Seat passengerSeat=allocatedseats.get(v.id);
                                            if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                            CoachLoop:
                                        for(Coach coach:currentTrain.coachs){
                                            for(Seat coachSeat:coach.seats){
                                                if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                    coachSeat.seat_status=passengerSeat.seat_status;
                                                    break CoachLoop;
                                                }
                                            }
                                        }
                                    }else{
                                        for(Seat seat:currentTrain.wList){
                                            if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                seat.seat_status=passengerSeat.seat_status;
                                                break;
                                            }
                                        }
                                    }
                                        if(allocatedseats.get(v.id).berth_type.equalsIgnoreCase("Rac")||allocatedseats.get(v.id).berth_type.equals("WaitingList"))
                                        isExpector=true;
                                                for(int k=from_index;k<to_index;k++){
                                                    if(allocatedseats.get(v.id).seat_status!=null)
                                                    allocatedseats.get(v.id).seat_status.set(k,true);
                                                }
                                                passengerSeat=allocatedseats.get(v.id);
                                                if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                CoachLoop:
                                                for(Coach coach:currentTrain.coachs){
                                                    for(Seat coachSeat:coach.seats){
                                                        if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                            coachSeat.seat_status=passengerSeat.seat_status;
                                                            break CoachLoop;
                                                        }
                                                    }
                                                }
                                            }else{
                                                for(Seat seat:currentTrain.wList){
                                                    if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                        seat.seat_status=passengerSeat.seat_status;
                                                        break;
                                                    }
                                                }
                                            }
                                                if(!allocatedseats.get(v.id).number.contains("WL")){
                                                   Seat currentSeat=allocatedseats.get(v.id);
                                                        if(currentTrain.bookedseatList.size()>0){
                                                            int i=0;
                                                            for(i=0;i<currentTrain.bookedseatList.size();i++){
                                                                Seat seat=currentTrain.bookedseatList.get(i);
                                                                System.out.println(".Equals doubt:   "+seat.equals(currentSeat));
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
        
                                    }
                                }else{
                                    System.out.println("Invalid card details");
                                }
                            }
            
                            // if(isValidCard){
                                // DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");.format(formatter)
                              
                        // }
        
                        }else{
                            System.out.println("Train full....");
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
                        System.out.println();
                                    System.out.println("---------------------------------------------------------------------------------------------------"+
                                    "\nTrain number     : "+currentTicket.getTrain_no()+"                              Train name      :"+currentTicket.getTrain_name()+
                                    "\nPNR number       : "+currentTicket.getPnr()+"                         passengercount  : "+currentTicket.getPassengers_count()+
                                    "\nFrom             : "+currentTicket.getFrom().name+"                            To              : "+currentTicket.getTo().name+
                                    // "\nDate of booking  : "+transactions.get(currentTicket.getPnr()).getTime()+"      Transaction id  : "+currentTicket.transaction_id+
                                    "\nJourney Date     : "+currentTicket.journeyDate+"                         Booked by       : "+currentTicket.bookedby);
                                     System.out.println("*************************************************************************************************");
                                    ArrayList<Passenger>psr=currentTicket.getPassengers();
                                     System.out.println("Passenger     passenger     passenger     Seat     berth          Status       coach       class"+
                                                       "\n  id          name           DOB        number     type                    number       type");
                                                       System.out.println();
                                     for(Passenger v:psr){
                                        System.out.println(" "+v.id+"            "+v.name+"         "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                        +"     "+v.status+"        "+allocatedseats.get(v.id).coachname+"       "+allocatedseats.get(v.id).classtype); 
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
                        if(!bookingsWithDate.containsKey(cancelTicket.journeyDate))
                        bookingsWithDate.put(cancelTicket.journeyDate,addTrains(cancelTicket.journeyDate));
                        // for(Map.Entry<TrainDetails,ArrayList<Ticket>> tt:train_ticket.entrySet()){
                        //     if(tt.getKey().number==cancelTicket.train_no){
                        //         currentTrain=tt.getKey();
                        //         break;
                        //     }
                        // }
                        for(TrainDetails train:bookingsWithDate.get(cancelTicket.journeyDate)){
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
                    ArrayList<String> currentTrainStations=new ArrayList<String>();
                    for(Station st: currentTrain.route){
                    // System.out.println(st);
                    currentTrainStations.add(st.name);
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
                            // System.out.println("Cancel seat number1:  "+cancelseat.number+"  berth:  "+cancelseat.berth_type+"   coach name  :"+cancelseat.coachname+"   class type  :"+cancelseat.classtype+"  Status:  "+cancelseat.seat_status);
                            // System.out.println("From index:   "+currentTrain.route.indexOf(cancelTicket.from)+"   To index:  "+currentTrain.route.indexOf(cancelTicket.to));
                            // System.out.println("From  "+cancelTicket.from+"   To:  "+cancelTicket.to);
                            // System.out.println("Route:  "+currentTrain.route);
                         
                            for(int j=currentTrainStations.indexOf(cancelTicket.from.name);j<currentTrainStations.indexOf(cancelTicket.to.name);j++){
                                // System.out.println(currentTrain.route.get(j));
                                cancelseat.seat_status.set(j,false);
                                System.out.println("Changed....");
                            }
                            Seat passengerSeat=cancelseat;
                                        if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                CoachLoop:
                                                for(Coach coach:currentTrain.coachs){
                                                    for(Seat coachSeat:coach.seats){
                                                        if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                            coachSeat.seat_status=passengerSeat.seat_status;
                                                            break CoachLoop;
                                                        }
                                                    }
                                                }
                                            }else{
                                                for(Seat seat:currentTrain.wList){
                                                    if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                        seat.seat_status=passengerSeat.seat_status;
                                                        break;
                                                    }
                                                }
                                            }
                            System.out.println("Cancel seat number2:  "+cancelseat.number+"  berth:  "+cancelseat.berth_type+"   coach name  :"+cancelseat.coachname+"   class type  :"+cancelseat.classtype+"  Status:  "+cancelseat.seat_status);
                            System.out.println();
                            int from_index=0,to_index=0;
                            if(currentTrain.berth_expectors.size()>0){
                                //traverse berth expectors
                                for(Ticket ticket:currentTrain.berth_expectors){
                                    int k=0;
                                        for(k=currentTrainStations.indexOf(ticket.from.name);k<currentTrainStations.indexOf(ticket.to.name);k++){
                                            if(cancelseat.seat_status.get(k)==true)
                                            break;
                                            }
                                            if(k==currentTrainStations.indexOf(ticket.to.name)){
                                        for(Passenger psr:ticket.getPassengers()){
                                            if(allocatedseats.get(psr.id).berth_type.equals("Rac")&&allocatedseats.get(p.id).berth_type.equalsIgnoreCase("WaitingList"))
                                                continue;
                                            else if((allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("WaitingList"))&&p.id<psr.id){
                                                tempSeat=allocatedseats.get(psr.id);
                                                allocatedseats.put(psr.id,cancelseat);
                                                cancelseat=tempSeat;
                                                System.out.println("Cancel seat berth:  "+cancelseat.berth_type+"    number:  "+cancelseat.number);
                                        for(k=currentTrainStations.indexOf(ticket.from.name);k<currentTrainStations.indexOf(ticket.to.name);k++){
                                            allocatedseats.get(psr.id).seat_status.set(k,true);
                                        }
                                         passengerSeat=allocatedseats.get(psr.id);
                                            if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                    CoachLoop:
                                                    for(Coach coach:currentTrain.coachs){
                                                        for(Seat coachSeat:coach.seats){
                                                            if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                                coachSeat.seat_status=passengerSeat.seat_status;
                                                                break CoachLoop;
                                                            }
                                                        }
                                                    }
                                                }else{
                                                    for(Seat seat:currentTrain.wList){
                                                        if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                            seat.seat_status=passengerSeat.seat_status;
                                                            break;
                                                        }
                                                    }
                                                }

                                        for(int j=currentTrainStations.indexOf(ticket.from.name);j<currentTrainStations.indexOf(ticket.to.name);j++){
                                            cancelseat.seat_status.set(j,false);
                                        }
                                         passengerSeat=cancelseat;
                                            if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                    CoachLoop:
                                                    for(Coach coach:currentTrain.coachs){
                                                        for(Seat coachSeat:coach.seats){
                                                            if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                                coachSeat.seat_status=passengerSeat.seat_status;
                                                                break CoachLoop;
                                                            }
                                                        }
                                                    }
                                                }else{
                                                    for(Seat seat:currentTrain.wList){
                                                        if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                            seat.seat_status=passengerSeat.seat_status;
                                                            break;
                                                        }
                                                    }
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
                                        if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("WaitingList"))
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
                            // System.out.println("Cancel seat number1:  "+cancelseat.number+"  berth:  "+cancelseat.berth_type+"   coach name  :"+cancelseat.coachname+"   class type  :"+cancelseat.classtype+"  Status:  "+cancelseat.seat_status);
                            // System.out.println("From index:   "+currentTrain.route.indexOf(cancelTicket.from)+"   To index:  "+currentTrain.route.indexOf(cancelTicket.to));
                            // System.out.println("From  "+cancelTicket.from+"   To:  "+cancelTicket.to);
                            // System.out.println("Route:  "+currentTrain.route);
                         
                            for(int j=currentTrainStations.indexOf(cancelTicket.from.name);j<currentTrainStations.indexOf(cancelTicket.to.name);j++){
                                // System.out.println(currentTrain.route.get(j));
                                cancelseat.seat_status.set(j,false);
                                System.out.println("Changed....");
                            }
                            Seat passengerSeat=cancelseat;
                                        if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                CoachLoop:
                                                for(Coach coach:currentTrain.coachs){
                                                    for(Seat coachSeat:coach.seats){
                                                        if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                            coachSeat.seat_status=passengerSeat.seat_status;
                                                            break CoachLoop;
                                                        }
                                                    }
                                                }
                                            }else{
                                                for(Seat seat:currentTrain.wList){
                                                    if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                        seat.seat_status=passengerSeat.seat_status;
                                                        break;
                                                    }
                                                }
                                            }
                            System.out.println("Cancel seat number2:  "+cancelseat.number+"  berth:  "+cancelseat.berth_type+"   coach name  :"+cancelseat.coachname+"   class type  :"+cancelseat.classtype+"  Status:  "+cancelseat.seat_status);
                            System.out.println();
                            int from_index=0,to_index=0;
                            if(currentTrain.berth_expectors.size()>0){
                                //traverse berth expectors
                                for(Ticket ticket:currentTrain.berth_expectors){
                                    int k=0;
                                        for(k=currentTrainStations.indexOf(ticket.from.name);k<currentTrainStations.indexOf(ticket.to.name);k++){
                                            if(cancelseat.seat_status.get(k)==true)
                                            break;
                                            }
                                            if(k==currentTrainStations.indexOf(ticket.to.name)){
                                        for(Passenger psr:ticket.getPassengers()){
                                            if(allocatedseats.get(psr.id).berth_type.equals("Rac")&&allocatedseats.get(p.id).berth_type.equalsIgnoreCase("WaitingList"))
                                                continue;
                                            else if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("WaitingList")){
                                                tempSeat=allocatedseats.get(psr.id);
                                                allocatedseats.put(psr.id,cancelseat);
                                                cancelseat=tempSeat;
                                                System.out.println("Cancel seat berth:  "+cancelseat.berth_type+"    number:  "+cancelseat.number);
                                        for(k=currentTrainStations.indexOf(ticket.from.name);k<currentTrainStations.indexOf(ticket.to.name);k++){
                                            allocatedseats.get(psr.id).seat_status.set(k,true);
                                        }
                                         passengerSeat=allocatedseats.get(psr.id);
                                            if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                    CoachLoop:
                                                    for(Coach coach:currentTrain.coachs){
                                                        for(Seat coachSeat:coach.seats){
                                                            if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                                coachSeat.seat_status=passengerSeat.seat_status;
                                                                break CoachLoop;
                                                            }
                                                        }
                                                    }
                                                }else{
                                                    for(Seat seat:currentTrain.wList){
                                                        if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                            seat.seat_status=passengerSeat.seat_status;
                                                            break;
                                                        }
                                                    }
                                                }

                                        for(int j=currentTrainStations.indexOf(ticket.from.name);j<currentTrainStations.indexOf(ticket.to.name);j++){
                                            cancelseat.seat_status.set(j,false);
                                        }
                                         passengerSeat=cancelseat;
                                            if(!passengerSeat.berth_type.equalsIgnoreCase("WaitingList")){
                                                    CoachLoop:
                                                    for(Coach coach:currentTrain.coachs){
                                                        for(Seat coachSeat:coach.seats){
                                                            if(passengerSeat.number.equals(coachSeat.number)&&passengerSeat.berth_type.equalsIgnoreCase(coachSeat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(coachSeat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(coachSeat.classtype)){
                                                                coachSeat.seat_status=passengerSeat.seat_status;
                                                                break CoachLoop;
                                                            }
                                                        }
                                                    }
                                                }else{
                                                    for(Seat seat:currentTrain.wList){
                                                        if(passengerSeat.number.equals(seat.number)&&passengerSeat.berth_type.equalsIgnoreCase(seat.berth_type)&&passengerSeat.coachname.equalsIgnoreCase(seat.coachname)&&passengerSeat.classtype.equalsIgnoreCase(seat.classtype)){
                                                            seat.seat_status=passengerSeat.seat_status;
                                                            break;
                                                        }
                                                    }
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
                                        if(allocatedseats.get(psr.id).berth_type.equals("Rac")||allocatedseats.get(psr.id).berth_type.equals("WaitingList"))
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
                    for(Passenger p:cancelTicket.getPassengers()){
                        if(p.status.equalsIgnoreCase("cancelled"))
                        count++;
                    }
                    if(count==cancelTicket.passengers_count)
                    cancelTicket.status="cancelled";
                    
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
                    ArrayList<Ticket> bookedTickets=new ArrayList<Ticket>();
                    for(Map.Entry<Long,Ticket> map:ticketsList.entrySet()){
                        Ticket ticket=map.getValue();
                        if(ticket.bookedby.equalsIgnoreCase(currentUser.mail_id))
                        bookedTickets.add(ticket);
                        // currentUser.bookedtickets.add(ticket);
                    }
                    currentUser.bookedtickets=bookedTickets;
                    ArrayList<Ticket> booked=currentUser.getbookedtickets();
                    if(booked.size()<1)
                    System.out.println("No bookings found");
                    else {
                    for(Ticket ticket:booked){
                        System.out.println();
                                    System.out.println("---------------------------------------------------------------------------------------------------"+
                                    "\nTrain number     : "+ticket.getTrain_no()+"                              Train name      :"+ticket.getTrain_name()+
                                    "\nPNR number       : "+ticket.getPnr()+"                         passengercount  : "+ticket.getPassengers_count()+
                                    "\nFrom             : "+ticket.getFrom().name+"                            To              : "+ticket.getTo().name+
                                    // "\nDate of booking  : "+transactions.get(currentTicket.getPnr()).getTime()+"      Transaction id  : "+currentTicket.transaction_id+
                                    "\nJourney Date     : "+ticket.journeyDate+"                         Booked by       : "+ticket.bookedby);
                                     System.out.println("*************************************************************************************************");
                                    ArrayList<Passenger>psr=ticket.getPassengers();
                                     System.out.println("Passenger     passenger     passenger     Seat     berth          Status       coach       class"+
                                                       "\n  id          name           DOB        number     type                    number       type");
                                                       System.out.println();
                                     for(Passenger v:psr){
                                        System.out.println(" "+v.id+"            "+v.name+"         "+v.dob+"        "+allocatedseats.get(v.id).number+"        "+allocatedseats.get(v.id).berth_type
                                        +"     "+v.status+"        "+allocatedseats.get(v.id).coachname+"       "+allocatedseats.get(v.id).classtype); 
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
                 writeAllDetailsInFile();
                System.out.println("Thank you...");
                flag=false;
                break;
                default:
                System.out.println("Enter a valid number");
            }
        }
    }
    public static boolean isValidLogin(LinkedHashMap<String,String> logindetails,String mail,String pwd){
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
public static HashMap<TrainDetails,Integer> availableTrains(String date,String from,String to){
    // for(TrainDetails train:trains){
    //     for(Station st:train.route){
    //         // System.out.println("Train:  "+train.name+"   Station:  "+st.name);
    //     }
    // }
    ArrayList<TrainDetails> trains=bookingsWithDate.get(date);
    // System.out.println("TRAINS SIZE:  "+trains.size());
    // for(TrainDetails train:trains){
    //     for(Station st:train.route){
    //         System.out.println("Train2:  "+train.name+"   Station2:  "+st.name);
    //     }
    // }
    HashMap<TrainDetails,Integer> al=new HashMap<TrainDetails,Integer>();
    // System.out.println("11");
    // System.out.println("From:  "+from+"   To:  "+to);
for(TrainDetails train:trains){
    // System.out.println("22");
        int availableseats=0;
        int from_index=-1;
        int to_index=-1;
        // System.out.println("Route size:  "+train.route.size());
    for(Station station:train.route){
        // System.out.println("Station name:  "+station.name);
        // System.out.println("33");
           if(station.name.equalsIgnoreCase(from))
           from_index=train.route.indexOf(station);
           if(station.name.equalsIgnoreCase(to))
           to_index=train.route.indexOf(station);
        }
        System.out.println("From index:   "+from_index+"  to index:  "+to_index);
    if(from_index!=-1&&to_index!=-1&&to_index-from_index>=1){
        // System.out.println("44");
for(Coach coach:train.coachs){
    // System.out.println("55");
    for(Seat seat:coach.seats){
        // System.out.println("66");
        if(seat.seat_status.indexOf(true)!=-1){
            // System.out.println("77");
            int k=-1;
            for(k=from_index;k<to_index;k++){
                // System.out.println("88");
                if(seat.seat_status.get(k)==true)
                break;
            }
            if(k==to_index){
                // System.out.println("99");
                availableseats++;
            }
        }
    }
}
al.put(train,availableseats);
// System.out.println("AL size:  "+al.size());
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

    public static ArrayList<TrainDetails> addTrains(String date){
        ArrayList<TrainDetails> trainsList=trains;
        // new ArrayList<TrainDetails>();
        try{
                for(TrainDetails train:trainsList){
            if(new File(train.name+"\\"+date).exists()){
                for(Map.Entry<Long,Ticket> map:ticketsList.entrySet()){
                    boolean isBerthExpector=false;
                    Ticket ticket=map.getValue();
                    if(ticket.journeyDate.equals(date)&&ticket.train_name.equalsIgnoreCase(train.name)){
                        for(Passenger passenger:ticket.passengers){
                            if(passenger.seat.berth_type.equalsIgnoreCase("Rac")||passenger.seat.berth_type.equalsIgnoreCase("WaitingList"))
                            isBerthExpector=true;
                        }
                        if(isBerthExpector)
                        train.berth_expectors.add(ticket);
                    }
                }
                BufferedReader lowerSeatReader=new BufferedReader(new FileReader(train.name+"\\"+date+"\\LowerSeats.csv"));
                String lowerSeat=lowerSeatReader.readLine();
                while((lowerSeat=lowerSeatReader.readLine())!=null){
                    String lowerSeatDetails[]=lowerSeat.split(",");
                    if(lowerSeatDetails[4].contains("true")){
                        String status[]=lowerSeatDetails[4].split("#");
                        ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                        for(int i=0;i<status.length;i++){
                            statusList.add(Boolean.parseBoolean(status[i]));
                        }
                        Seat seat=new Seat(lowerSeatDetails[1],"Lower",lowerSeatDetails[2],lowerSeatDetails[3],statusList);
                        if(statusList.contains(true)){
                        train.bookedseatList.add(seat);
                        for(Seat lowerseat:train.lbList){
                            if(seat.number.equals(lowerseat.number)&&seat.berth_type.equalsIgnoreCase(lowerseat.berth_type)&&seat.coachname.equalsIgnoreCase(lowerseat.coachname)&&seat.classtype.equalsIgnoreCase(lowerseat.classtype)){
                                train.lbList.remove(lowerseat);
                                break;
                            }
                        }
                        System.out.println("bookedseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                    }
                        // else{
                        // train.lbList.add(seat);
                        // System.out.println("lowerseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        // }
                    }
                    for(Coach coach:train.coachs){
                        if(lowerSeatDetails[2].equals(coach.name)&&lowerSeatDetails[3].equalsIgnoreCase(coach.class_type)){
                            for(Seat seat:coach.seats){
                                if(seat.number.equals(lowerSeatDetails[1])&&seat.berth_type.equalsIgnoreCase("Lower")){
                                    String status[]=lowerSeatDetails[4].split("#");
                                    ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                                    for(int i=0;i<status.length;i++){
                                        statusList.add(Boolean.parseBoolean(status[i]));
                                    }
                                    seat.seat_status=statusList;
                                    // break;
                                }
                            }
                            // break;
                        }
                    }
                }
                lowerSeatReader.close();

                
                BufferedReader middleSeatReader=new BufferedReader(new FileReader(train.name+"\\"+date+"\\MiddleSeats.csv"));
                String middleSeat=middleSeatReader.readLine();
                while((middleSeat=middleSeatReader.readLine())!=null){
                    String middleSeatDetails[]=middleSeat.split(",");
                    if(middleSeatDetails[4].contains("true")){
                        String status[]=middleSeatDetails[4].split("#");
                        ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                        for(int i=0;i<status.length;i++){
                            statusList.add(Boolean.parseBoolean(status[i]));
                        }
                        
                        Seat seat=new Seat(middleSeatDetails[1],"Middle",middleSeatDetails[2],middleSeatDetails[3],statusList);
                        if(statusList.contains(true)){
                        train.bookedseatList.add(seat);
                        for(Seat middleseat:train.mbList){
                            if(seat.number.equals(middleseat.number)&&seat.berth_type.equalsIgnoreCase(middleseat.berth_type)&&seat.coachname.equalsIgnoreCase(middleseat.coachname)&&seat.classtype.equalsIgnoreCase(middleseat.classtype)){
                                train.mbList.remove(middleseat);
                                break;
                            }
                        }
                        System.out.println("bookedseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        }
                        // else{
                        // train.mbList.add(seat);
                        // System.out.println("middleseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        // }
                    }
                    for(Coach coach:train.coachs){
                        if(middleSeatDetails[2].equals(coach.name)&&middleSeatDetails[3].equalsIgnoreCase(coach.class_type)){
                            for(Seat seat:coach.seats){
                                if(seat.number.equals(middleSeatDetails[1])&&seat.berth_type.equalsIgnoreCase("Middle")){
                                    String status[]=middleSeatDetails[4].split("#");
                                    ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                                    for(int i=0;i<status.length;i++){
                                        statusList.add(Boolean.parseBoolean(status[i]));
                                    }
                                    seat.seat_status=statusList;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                middleSeatReader.close();

                
                BufferedReader upperSeatReader=new BufferedReader(new FileReader(train.name+"\\"+date+"\\UpperSeats.csv"));
                String upperSeat=upperSeatReader.readLine();
                while((upperSeat=upperSeatReader.readLine())!=null){
                    String upperSeatDetails[]=upperSeat.split(",");
                    if(upperSeatDetails[4].contains("true")){
                        String status[]=upperSeatDetails[4].split("#");
                        ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                        for(int i=0;i<status.length;i++){
                            statusList.add(Boolean.parseBoolean(status[i]));
                        }
                        
                        Seat seat=new Seat(upperSeatDetails[1],"Upper",upperSeatDetails[2],upperSeatDetails[3],statusList);
                        if(statusList.contains(true)){
                        train.bookedseatList.add(seat);
                        for(Seat upperseat:train.ubList){
                            if(seat.number.equals(upperseat.number)&&seat.berth_type.equalsIgnoreCase(upperseat.berth_type)&&seat.coachname.equalsIgnoreCase(upperseat.coachname)&&seat.classtype.equalsIgnoreCase(upperseat.classtype)){
                                train.ubList.remove(upperseat);
                                break;
                            }
                        }
                        System.out.println("bookedseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        }
                        // else{
                        // train.ubList.add(seat);
                        // System.out.println("upperseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        // }
                    }
                    for(Coach coach:train.coachs){
                        if(upperSeatDetails[2].equals(coach.name)&&upperSeatDetails[3].equalsIgnoreCase(coach.class_type)){
                            for(Seat seat:coach.seats){
                                if(seat.number.equals(upperSeatDetails[1])&&seat.berth_type.equalsIgnoreCase("Upper")){
                                    String status[]=upperSeatDetails[4].split("#");
                                    ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                                    for(int i=0;i<status.length;i++){
                                        statusList.add(Boolean.parseBoolean(status[i]));
                                    }
                                    seat.seat_status=statusList;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                upperSeatReader.close();

                
                BufferedReader racSeatReader=new BufferedReader(new FileReader(train.name+"\\"+date+"\\RacSeats.csv"));
                String racSeat=racSeatReader.readLine();
                while((racSeat=racSeatReader.readLine())!=null){
                    String racSeatDetails[]=racSeat.split(",");
                    if(racSeatDetails[4].contains("true")){
                        String status[]=racSeatDetails[4].split("#");
                        ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                        for(int i=0;i<status.length;i++){
                            statusList.add(Boolean.parseBoolean(status[i]));
                        }
                        Seat seat=new Seat(racSeatDetails[1],"Rac",racSeatDetails[2],racSeatDetails[3],statusList);
                        if(statusList.contains(true)){
                        train.bookedseatList.add(seat);
                        for(Seat racseat:train.racList){
                            if(seat.number.equals(racseat.number)&&seat.berth_type.equalsIgnoreCase(racseat.berth_type)&&seat.coachname.equalsIgnoreCase(racseat.coachname)&&seat.classtype.equalsIgnoreCase(racseat.classtype)){
                                train.racList.remove(racseat);
                                break;
                            }
                        }
                        System.out.println("bookedseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        }
                        // else{
                        // train.racList.add(seat);
                        // System.out.println("racseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                        // }
                    }
                    for(Coach coach:train.coachs){
                        if(racSeatDetails[2].equals(coach.name)&&racSeatDetails[3].equalsIgnoreCase(coach.class_type)){
                            for(Seat seat:coach.seats){
                                if(seat.number.equals(racSeatDetails[1])&&seat.berth_type.equalsIgnoreCase("Rac")){
                                    String status[]=racSeatDetails[4].split("#");
                                    ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                                    for(int i=0;i<status.length;i++){
                                        statusList.add(Boolean.parseBoolean(status[i]));
                                    }
                                    seat.seat_status=statusList;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                racSeatReader.close();

                BufferedReader waitinglistReader=new BufferedReader(new FileReader(train.name+"\\"+date+"\\WaitingListSeats.csv"));
                String waitingListSeat=waitinglistReader.readLine();
                while((waitingListSeat=waitinglistReader.readLine())!=null){
                    String seatDetails[]=waitingListSeat.split(",");
                    String status[]=waitingListSeat.split(",")[4].split("#");
                            // boolean arrBooleans[]=new boolean[status.length];
                            ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                            for(int i=0;i<status.length;i++){
                                statusList.add(Boolean.parseBoolean(status[i]));
                            }
                            Seat seat=new Seat(seatDetails[1],"WaitingList",seatDetails[2],seatDetails[3],statusList);
                    train.wList.add(seat);
                    // for(Seat seat:train.wList){
                    //     if(seat.number.equalsIgnoreCase(waitingListSeat.split(",")[1])){
                    //         String status[]=waitingListSeat.split(",")[4].split("#");
                    //         // boolean arrBooleans[]=new boolean[status.length];
                    //         ArrayList<Boolean> statusList=new ArrayList<Boolean>();
                    //         for(int i=0;i<status.length;i++){
                    //             statusList.add(Boolean.parseBoolean(status[i]));
                    //         }
                    //         seat.seat_status=statusList;
                    //         train.wList.add(seat);
                        System.out.println("waitinglistseat added..."+seat.number+"  "+seat.berth_type+"  "+seat.coachname+"  "+seat.classtype);
                    //         break;
                    //     }
                    // }
                }
                waitinglistReader.close();
            }else{
                if(!new File(train.name+"\\"+date).exists())
                new File(train.name+"\\"+date).mkdirs();
                System.out.println(new File(train.name+"\\"+date+"\\LowerSeats.csv").createNewFile());
                new File(train.name+"\\"+date+"\\MiddleSeats.csv").createNewFile();
                new File(train.name+"\\"+date+"\\UpperSeats.csv").createNewFile();
                new File(train.name+"\\"+date+"\\RacSeats.csv").createNewFile();
                new File(train.name+"\\"+date+"\\WaitingListSeats.csv").createNewFile();
            }
        }
    
    }catch(Exception e){
        e.printStackTrace();
    }
    return trainsList;

    }
    // public static void loadPreviousBookings(String date){
    //     for(TrainDetails train:trains){

    //     }
    // }
    
public static void writeAllDetailsInFile(){
    // write userdetails
    try{
        BufferedWriter userWriter=new BufferedWriter(new FileWriter("Users.csv"));
        userWriter.write("Name,Mail id,Password,Phone number");
        userWriter.newLine();
        userWriter.flush();
        for(Map.Entry<String,User> usermap:userdetails.entrySet()){
            User user=usermap.getValue();
            userWriter.write(user.name+","+user.mail_id+","+user.password+","+user.phone_no);
            userWriter.newLine();
            userWriter.flush();
        }
        userWriter.close();
    }catch(Exception e){
        e.printStackTrace();
    }

    // write  (from tickets to file)
    try{
        BufferedWriter ticketWriter=new BufferedWriter(new FileWriter("Tickets.csv"));
        ticketWriter.write("Id(PNR),Train name,Train number,From,To,Travel date,Status,Booked by,Passengers,Transaction id");
        ticketWriter.newLine();ticketWriter.flush();
        BufferedWriter passengerWriter=new BufferedWriter(new FileWriter("Passengers.csv"));
        passengerWriter.write("Id,Name,Date of birth,Status,Seat id,Berth,Coach name,Coach type,Travel,Seat status");
        passengerWriter.newLine();
        passengerWriter.flush();
        for(Map.Entry<Long,Ticket> ticketMap:ticketsList.entrySet()){
            Ticket ticket=ticketMap.getValue();
            String passengerIds="";
            for(Passenger psr:ticket.passengers)
            passengerIds+=psr.id+" ";
            ticketWriter.write(ticket.pnr+","+ticket.train_name+","+ticket.train_no+","+ticket.from.name+","+ticket.to.name+","+ticket.journeyDate+","+ticket.status+","+ticket.bookedby+","+passengerIds+","+ticket.transaction_id);
            ticketWriter.newLine();
            ticketWriter.flush();
            for(Passenger passenger:ticket.passengers){
                Seat seat=allocatedseats.get(passenger.id);
                passengerWriter.write(passenger.id+","+passenger.name+","+passenger.dob+","+passenger.status+","+seat.number+","+seat.berth_type+","+seat.coachname+","+seat.classtype+",-,"+ticket.train_name+"\\"+ticket.journeyDate+"\\"+seat.berth_type+"Seats.csv");
                passengerWriter.newLine();
                passengerWriter.flush();
            }
        }
        ticketWriter.close();
        passengerWriter.close();
    }catch(Exception e){
        e.printStackTrace();
    }

    try{
            for(Map.Entry<String,ArrayList<TrainDetails>> map:bookingsWithDate.entrySet()){
                for(TrainDetails train:map.getValue()){
                    BufferedWriter lowerWriter=new BufferedWriter(new FileWriter(train.name+"\\"+map.getKey()+"\\LowerSeats.csv"));
                    // if(lowerSeat!=null){
                    lowerWriter.write("Id,Number,Coach name,Coach type,Status");
                    lowerWriter.newLine();
                    lowerWriter.flush();
                // }
                    for(Coach coach:train.coachs)
                    for(Seat seat:coach.seats){
                        // boolean isBooked=false;
                        // for(Seat bookedSeat:train.bookedseatList){
                        //     if(seat.number.equals(bookedSeat.number)&&seat.berth_type.equalsIgnoreCase("Lower")&&seat.coachname.equalsIgnoreCase(bookedSeat.coachname)&&seat.classtype.equalsIgnoreCase(bookedSeat.classtype)){
                        //         isBooked=true;
                        //         String status="";
                        //         for(int i=0;i<seat.seat_status.size();i++){
                        //             status+=seat.seat_status.get(i);
                        //             if(i!=seat.seat_status.size()-1)
                        //             status+="#";
                        //         }
                        //         System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                        //         lowerWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                        //         lowerWriter.newLine();
                        //         lowerWriter.flush();
                        //         break;
                        //     }
                        // }
                        System.out.println(seat.berth_type);
                        if(seat.berth_type.equalsIgnoreCase("Lower")){
                            String status="";
                            for(int i=0;i<seat.seat_status.size();i++){
                                status+=seat.seat_status.get(i);
                                if(i!=seat.seat_status.size()-1)
                                status+="#";
                            }
                            System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            lowerWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            lowerWriter.newLine();
                            lowerWriter.flush();
                        }
                    }
                    lowerWriter.close();
                    

                    
                    BufferedWriter middleWriter=new BufferedWriter(new FileWriter(train.name+"\\"+map.getKey()+"\\MiddleSeats.csv"));
                    // if(lowerSeat!=null){
                    middleWriter.write("Id,Number,Coach name,Coach type,Status");
                    middleWriter.newLine();
                    middleWriter.flush();
                // }
                    for(Coach coach:train.coachs)
                    for(Seat seat:coach.seats){
                        // boolean isBooked=false;
                        // for(Seat bookedSeat:train.bookedseatList){
                        //     if(seat.number.equals(bookedSeat.number)&&seat.berth_type.equalsIgnoreCase("Middle")&&seat.coachname.equalsIgnoreCase(bookedSeat.coachname)&&seat.classtype.equalsIgnoreCase(bookedSeat.classtype)){
                        //         isBooked=true;
                        //         String status="";
                        //         for(int i=0;i<bookedSeat.seat_status.size();i++){
                        //             status+=bookedSeat.seat_status.get(i);
                        //             if(i!=bookedSeat.seat_status.size()-1)
                        //             status+="#";
                        //         }
                        //         System.out.println(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         middleWriter.write(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         middleWriter.newLine();
                        //         middleWriter.flush();
                        //         break;
                        //     }
                        // }
                        if(seat.berth_type.equalsIgnoreCase("Middle")){
                            String status="";
                            for(int i=0;i<seat.seat_status.size();i++){
                                status+=seat.seat_status.get(i);
                                if(i!=seat.seat_status.size()-1)
                                status+="#";
                            }
                            System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            middleWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            middleWriter.newLine();
                            middleWriter.flush();
                        }
                    }
                    middleWriter.close();

                    
                    BufferedWriter upperWriter=new BufferedWriter(new FileWriter(train.name+"\\"+map.getKey()+"\\UpperSeats.csv"));
                    // if(lowerSeat!=null){
                    upperWriter.write("Id,Number,Coach name,Coach type,Status");
                    upperWriter.newLine();
                    upperWriter.flush();
                // }
                    for(Coach coach:train.coachs)
                    for(Seat seat:coach.seats){
                        // boolean isBooked=false;
                        // for(Seat bookedSeat:train.bookedseatList){
                        //     if(seat.number.equals(bookedSeat.number)&&seat.berth_type.equalsIgnoreCase("Upper")&&seat.coachname.equalsIgnoreCase(bookedSeat.coachname)&&seat.classtype.equalsIgnoreCase(bookedSeat.classtype)){
                        //         isBooked=true;
                        //         String status="";
                        //         for(int i=0;i<bookedSeat.seat_status.size();i++){
                        //             status+=bookedSeat.seat_status.get(i);
                        //             if(i!=bookedSeat.seat_status.size()-1)
                        //             status+="#";
                        //         }
                        //         System.out.println(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         upperWriter.write(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         upperWriter.newLine();
                        //         upperWriter.flush();
                        //         break;
                        //     }
                        // }
                        if(seat.berth_type.equalsIgnoreCase("Upper")){
                            String status="";
                            for(int i=0;i<seat.seat_status.size();i++){
                                status+=seat.seat_status.get(i);
                                if(i!=seat.seat_status.size()-1)
                                status+="#";
                            }
                            System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            upperWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            upperWriter.newLine();
                            upperWriter.flush();
                        }
                    }
                    upperWriter.close();
                    
                    
                    BufferedWriter racWriter=new BufferedWriter(new FileWriter(train.name+"\\"+map.getKey()+"\\RacSeats.csv"));
                    // if(lowerSeat!=null){
                    racWriter.write("Id,Number,Coach name,Coach type,Status");
                    racWriter.newLine();
                    racWriter.flush();
                // }
                    for(Coach coach:train.coachs)
                    for(Seat seat:coach.seats){
                        // boolean isBooked=false;
                        // for(Seat bookedSeat:train.bookedseatList){
                        //     if(seat.number.equals(bookedSeat.number)&&seat.berth_type.equalsIgnoreCase("Rac")&&seat.coachname.equalsIgnoreCase(bookedSeat.coachname)&&seat.classtype.equalsIgnoreCase(bookedSeat.classtype)){
                        //         isBooked=true;
                        //         String status="";
                        //         for(int i=0;i<bookedSeat.seat_status.size();i++){
                        //             status+=bookedSeat.seat_status.get(i);
                        //             if(i!=bookedSeat.seat_status.size()-1)
                        //             status+="#";
                        //         }
                        //         System.out.println(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         racWriter.write(","+bookedSeat.number+","+bookedSeat.coachname+","+bookedSeat.classtype+","+status);
                        //         racWriter.newLine();
                        //         racWriter.flush();
                        //         break;
                        //     }
                        // }
                        if(seat.berth_type.equalsIgnoreCase("Rac")){
                            String status="";
                            for(int i=0;i<seat.seat_status.size();i++){
                                status+=seat.seat_status.get(i);
                                if(i!=seat.seat_status.size()-1)
                                status+="#";
                            }
                            System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            racWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                            racWriter.newLine();
                            racWriter.flush();
                        }
                    }
                    racWriter.close();
                    


                    BufferedWriter wlWriter=new BufferedWriter(new FileWriter(train.name+"\\"+map.getKey()+"\\WaitingListSeats.csv"));
                    wlWriter.write("Id,Number,Coach name,Coach type,Status");
                    wlWriter.newLine();
                    wlWriter.flush();
                    for(Seat seat:train.wList){
                        String status="";
                        for(int i=0;i<seat.seat_status.size();i++){
                            status+=seat.seat_status.get(i);
                            if(i!=seat.seat_status.size()-1)
                            status+="#";
                        }
                        System.out.println(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                        wlWriter.write(","+seat.number+","+seat.coachname+","+seat.classtype+","+status);
                        wlWriter.newLine();
                        wlWriter.flush();
                    }
                    wlWriter.close();
                }
            }
    }catch(Exception e){
        e.printStackTrace();
    }
   }
       
public static int loadAllCollections(){//// load all details....
    // load userdetails
   try{
    BufferedReader userReader=new BufferedReader(new FileReader("Users.csv"));
    String user=userReader.readLine();
    while((user=userReader.readLine())!=null){
        String userDetails[]=user.split(",");
        userdetails.put(userDetails[1],new User(userDetails[0],userDetails[1],userDetails[2],userDetails[3]));
        logindetails.put(userDetails[1],userDetails[2]);
    }
    userReader.close();
   }catch(Exception e){
    // System.out.println(e);
    e.printStackTrace();
   }

//load traindetails

try{
    BufferedReader trainReader=new BufferedReader(new FileReader("Trains.csv"));
    String train=trainReader.readLine();
    while((train=trainReader.readLine())!=null){
    ArrayList<Coach> coachArrayList=new ArrayList<Coach>();
    ArrayList<Station> stationArrayList=new ArrayList<Station>();

        String trainDetails[]=train.split(",");
        String stations[]=trainDetails[3].split("#");// stations id
        String distance[]=new String[stations.length];
        for(int i=0;i<stations.length;i++){
            distance[i]=stations[i].split("_")[1];
            stations[i]=stations[i].split("_")[0];
        }
        // System.out.println("Stations  :  "+Arrays.toString(stations)+"   Distance:  "+Arrays.toString(distance));
        stationLoop:
        for(int i=0;i<stations.length;i++){
            BufferedReader stationReader=new BufferedReader(new FileReader("Station.csv"));
            String station=stationReader.readLine();
        while((station=stationReader.readLine())!=null){
            // System.out.println(station+"Station read");
            String stationDetails[]=station.split(",");
            if(stationDetails[0].equals(stations[i])){
                // System.out.println("If...");
                stationArrayList.add(new Station(stationDetails[1],stationDetails[2],Integer.parseInt(distance[i])));
                // continue stationLoop;
                break;
            }
        }
        stationReader.close();
    }
    // String coachs[]=trainDetails[4].split("#");
    // coachLoop:
    // for(int i=0;i<coachs.length;i++){
        BufferedReader coachReader=new BufferedReader(new FileReader(trainDetails[2]+"\\CoachList.csv"));
        String coach=coachReader.readLine();
        while((coach=coachReader.readLine())!=null){
            String coachDetails[]=coach.split(",");
            // if(coachDetails[0].equals(coachs[i])){
                // coachArrayList.add(new Coach(coachDetails[1],coachDetails[2],Integer.parseInt(coachDetails[3])));
                coachArrayList.add(new Coach(coachDetails[1],coachDetails[2]));

                // continue coachLoop;
            // }
        }
        coachReader.close();
    // }
    // System.out.println("Station array:  "+stationArrayList+"   Coach arraylist:  "+coachArrayList);
        trains.add(new TrainDetails(Integer.parseInt(trainDetails[1]),trainDetails[2],stationArrayList,coachArrayList,Double.parseDouble(trainDetails[4])));
        // System.out.println("Train0"+trains.get(0));
    }
    trainReader.close();
}catch(Exception e){
    System.out.println(e);
    e.printStackTrace();
}

// load accountdetails

try{
    BufferedReader paymentReader=new BufferedReader(new FileReader("Payments.csv"));
    String payment=paymentReader.readLine();
    paymentLoop:
    while((payment=paymentReader.readLine())!=null){
        String paymentDetails[]=payment.split(",");
        BufferedReader accountReader=new BufferedReader(new FileReader("Accounts.csv"));
        String account=accountReader.readLine();
        while((account=accountReader.readLine())!=null){
            String accountDetails[]=account.split(",");
            if(accountDetails[0]==paymentDetails[6]){
                        accounts.put(Long.parseLong(paymentDetails[0]),new Account(paymentDetails[0],Double.parseDouble(accountDetails[5]),accountDetails[2],accountDetails[3],accountDetails[4],new Payment(Long.parseLong(paymentDetails[0]),Integer.parseInt(paymentDetails[1]),Integer.parseInt(paymentDetails[2]),Integer.parseInt(paymentDetails[3]),paymentDetails[4],paymentDetails[5])));
                accountReader.close();
                break;
            }
        }
    }
    paymentReader.close();
}catch(Exception e){
    System.out.println(e);
    e.printStackTrace();
}

// load allocated seats
int passengerCount=1;
try{
    BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
    // System.out.println("1234");
    String passenger=passengerReader.readLine();
    while((passenger=passengerReader.readLine())!=null){
        String passengerDetails[]=passenger.split(",");
        BufferedReader seatReader=new BufferedReader(new FileReader(passengerDetails[9]));
        String seat=seatReader.readLine();
        while((seat=seatReader.readLine())!=null){
            passengerCount++;
            // System.out.println("werer");
            String seatDetails[]=seat.split(",");
            if(passengerDetails[4].equalsIgnoreCase(seatDetails[1])&&passengerDetails[6].equalsIgnoreCase(seatDetails[2])&&passengerDetails[7].equalsIgnoreCase(seatDetails[3])){
            // System.out.println("saddffd");
            String  arr[]=seatDetails[4].split("#");
            ArrayList<Boolean> arrBooleans=new ArrayList<Boolean>();
            for(int i=0;i<arr.length;i++){
                arrBooleans.add(Boolean.parseBoolean(arr[i]));
            }
            allocatedseats.put(Integer.parseInt(passengerDetails[0]),new Seat(passengerDetails[4],passengerDetails[5],passengerDetails[6],passengerDetails[7],arrBooleans));
            }
        }
        seatReader.close();
    }
}catch(Exception e){
    e.printStackTrace();
}
// // System.out.println("Allocated seats:   "+allocatedseats.size());
// for(Map.Entry<Integer,Seat> map:allocatedseats.entrySet()){
//     Seat seat=map.getValue();
//     // System.out.println("Passenger id:  "+map.getKey());
//     // System.out.println("Seat number:  "+seat.number+"  seat berth:  "+seat.berth_type+"   coach:  "+seat.coachname+"  class:  "+seat.classtype+"  status:  "+seat.seat_status);
//     // System.out.println();
// }

// load ticketslist 

try{
    BufferedReader ticketReader=new BufferedReader(new FileReader("Tickets.csv"));
    String ticket=ticketReader.readLine();
    while((ticket=ticketReader.readLine())!=null){
        String ticketDetails[]=ticket.split(",");
        BufferedReader stationReader=new BufferedReader(new FileReader("Station.csv"));
        String station=stationReader.readLine();
        Station from=null;
        Station to=null;
        while((station=stationReader.readLine())!=null){
            String stationDetails[]=station.split(",");
            if(ticketDetails[3].equalsIgnoreCase(stationDetails[2]))
            from=new Station(stationDetails[1],stationDetails[2]);
            else if(ticketDetails[4].equalsIgnoreCase(stationDetails[2]))
            to=new Station(stationDetails[1],stationDetails[2]);
            if(from!=null&&to!=null)
            break;
        }
        stationReader.close();
        String passengerIds[]=ticketDetails[8].trim().split(" ");
        ArrayList<Passenger> passengers=new ArrayList<Passenger>();
        BufferedReader passengerReader=new BufferedReader(new FileReader("Passengers.csv"));
        String passenger=passengerReader.readLine();
        // PassengerLoop:
        for(String passengerid:passengerIds){
            while((passenger=passengerReader.readLine())!=null){
                String passengerDetails[]=passenger.split(",");
                if(passengerDetails[0].equals(passengerid)){
        BufferedReader seatReader=new BufferedReader(new FileReader(passengerDetails[9]));
        String seat=seatReader.readLine();
        ArrayList<Boolean> arrBooleans=new ArrayList<Boolean>();
        while((seat=seatReader.readLine())!=null){
            String seatDetails[]=seat.split(",");
            if(passengerDetails[4].equals(seatDetails[1])&&passengerDetails[6].equals(seatDetails[2])&&passengerDetails[7].equals(seatDetails[3])){
            String  arr[]=seatDetails[4].split("#");
            for(int i=0;i<arr.length;i++){
                arrBooleans.add(Boolean.parseBoolean(arr[i]));
            }
        }
        }
        seatReader.close();
                    passengers.add(new Passenger(Integer.parseInt(passengerDetails[0]),passengerDetails[1],passengerDetails[2],new Seat(passengerDetails[4],passengerDetails[5],passengerDetails[6],passengerDetails[7],arrBooleans),passengerDetails[3]));
                    // continue PassengerLoop;
                    break;
                }
            }
        }
        ticketsList.put(Long.parseLong(ticketDetails[0]),new Ticket(Integer.parseInt(ticketDetails[2]),ticketDetails[1],from,to,Long.parseLong(ticketDetails[0]),passengerIds.length,Long.parseLong(ticketDetails[9]),passengers,ticketDetails[6],ticketDetails[7],ticketDetails[5]));
    }
    ticketReader.close();
}catch(Exception e){
    e.printStackTrace();
}
return passengerCount;
}
public static void loadUserBookedTickets(User user){
    for(Map.Entry<Long,Ticket> map:ticketsList.entrySet()){
        Ticket ticket=map.getValue();
        if(ticket.bookedby.equalsIgnoreCase(user.mail_id)){
            user.bookedtickets.add(ticket);
        }
    }
}
}
    