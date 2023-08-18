// My favourite query(search) 
// select * from (
//  SELECT
//         t1.trainid,
//         t1.stationid AS fromid,
//         t2.stationid AS toid,
//         t1.distance AS from_dt,
//         t2.distance AS to_dt,
//         t1.station_name AS from,
//         t2.station_name AS to,
//         t1.train_name,
//     t2.distance-t1.distance totaldistance,
//     t1.rate_per_km,
//     (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
//     FROM
//         (
//             SELECT
//                 trainid,
//                 stationid,
//                 distance,
//                 station.name AS station_name,
//                 code,
//                 train.name AS train_name,
//                 number,
//                 rate_per_km,
//                 SUM(distance) AS total_distance
//             FROM
//                 route
//             LEFT JOIN
//                 station ON stationid = station.id
//             LEFT JOIN
//                 train ON trainid = train.id
//             WHERE
//                 station.name = 'madurai'
//             GROUP BY
//                 trainid,
//                 stationid,
//                 distance,
//                 station.name,
//                 code,
//                 train.name,
//                 number,
//                 rate_per_km
//         ) t1
//     INNER JOIN
//         (
//             SELECT
//                 trainid,
//                 stationid,
//                 distance,
//                 station.name AS station_name,
//                 code,
//                 train.name AS train_name,
//                 number,
//                 rate_per_km,
//                 SUM(distance) AS total_distance
//             FROM
//                 route
//             LEFT JOIN
//                 station ON stationid = station.id
//             LEFT JOIN
//                 train ON trainid = train.id
//             WHERE
//                 station.name = 'chennai'
//             GROUP BY
//                 trainid,
//                 stationid,
//                 distance,
//                 station.name,
//                 code,
//                 train.name,
//                 number,
//                 rate_per_km
//         ) t2
//     ON
//         t1.trainid = t2.trainid) sq1 inner join (
//      SELECT trainid, COUNT(*) AS count
//        from(
//        select trainid,seatid,status
//        from( select *
//        from seat_status
//        where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
//        ) t1
//        inner join seat on seatid = seat.id
//        order by trainid
//        ) subquery
//        group by trainid) sq2 on sq1.trainid=sq2.trainid;
package DbOperations;

import TrainTicketBookingProject.Booking.*;
import TrainTicketBookingProject.persons.*;
import TrainTicketBookingProject.Train.*;

import java.util.*;
import java.sql.*;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class TrainTicketBookingUtil{
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    public TrainTicketBookingUtil(){
        try{
        connection=DbConnection.getInstance().getConnection();    
    }catch(Exception e){
        e.printStackTrace();
    }
}
    public boolean signUp(User user){
        boolean signUpStatus=false;
        try{
        // connection=DbConnection.getInstance().getConnection();
        preparedStatement=connection.prepareStatement("SELECT mailid from users where mailid=?");
        preparedStatement.setString(1,user.mail_id);
        resultSet=preparedStatement.executeQuery();
        if(!resultSet.next()){
            preparedStatement=connection.prepareStatement("INSERT INTO passenger(name,date_of_birth) values(?,?) returning id");
            preparedStatement.setString(1,user.name);
            // Date date=new SimpleDateFormat("dd-MM-yyyy").parse(user.date_of_birth);
            // preparedStatement.setDate(2,new SimpleDateFormat("dd-MM-yyyy").parse(user.date_of_birth));
            // preparedStatement.setDate(2,Date.valueOf(user.date_of_birth));
            preparedStatement.setDate(2,java.sql.Date.valueOf(user.date_of_birth));
            resultSet=preparedStatement.executeQuery(); 
            int passengerId=-1;
            if(resultSet.next())
            passengerId=resultSet.getInt("id");
            // =resultSet.getInt("id");
            preparedStatement=connection.prepareStatement("INSERT INTO users(passengerid,mailid,password,mobile_number) values(?,?,?,?) returning id");
            preparedStatement.setInt(1, passengerId);
            preparedStatement.setString(2,user.mail_id);
            preparedStatement.setString(3,user.password);
            preparedStatement.setString(4,user.phone_no);
            resultSet=preparedStatement.executeQuery(); 
            int userid=-1;
            if(resultSet.next())
            userid=resultSet.getInt("id");
            preparedStatement=connection.prepareStatement("INSERT INTO wishlists(userid,passengerid) values(?,?)");
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, passengerId);
            preparedStatement.executeUpdate();
            signUpStatus=true;
        }
}catch(Exception e){
    e.printStackTrace();
}
finally{
    try{
    if(preparedStatement!=null)
    preparedStatement.close();
    if(resultSet!=null)
    resultSet.close();
}catch(Exception e){
    e.printStackTrace();
}
}
return signUpStatus;
    }


    public boolean login(String mail_id,String password){
        boolean login_status=false;
        try{
         preparedStatement=connection.prepareStatement("SELECT mailid,password from users where mailid=? and password=?");
         preparedStatement.setString(1, mail_id);
         preparedStatement.setString(2, password);   
         resultSet=preparedStatement.executeQuery();
         if(resultSet.next())
         login_status=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
            if(preparedStatement!=null)
            preparedStatement.close();
            if(resultSet!=null)
            resultSet.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        return login_status;    
    }

    // public void fill_station_index_in_route(ArrayList<TrainDetails> trains){
    // for(TrainDetails train:trains){
    //     int i=1;
    //     for(Station station:train.route){
    //         PreparedStatement preparedStatement=connection.prepareStatement("SELECT id FROM station WHERE name=?");
    //         preparedStatement.setString(1,station.name);
    //         ResultSet resultSet=preparedStatement.executeQuery();
    //         if(resultSet.next()){
    //             int stationid=resultSet.getInt("id");
    //             preparedStatement=connection.prepareStatement("INSERT INTO station_index_in_route values(?,?,?)");
    //             preparedStatement.setInt(1, );
    //         }
    //     }
    // }
    // }

//     // find ticket charge
//      SELECT
//        t1.trainid,
//        t1.stationid AS fromid,
//        t2.stationid AS toid,
//        t1.distance AS from_dt,
//        t2.distance AS to_dt,
//        t1.station_name AS from,
//        t2.station_name AS to,
//        t1.train_name,
//    t2.distance-t1.distance totaldistance,
//    t1.rate_per_km,
//    (t2.distance-t1.distance)*t1.rate_per_km as ticketcharge
//    FROM
//        (
//            SELECT
//                trainid,
//                stationid,
//                distance,
//                station.name AS station_name,
//                code,
//                train.name AS train_name,
//                number,
//                rate_per_km,
//                SUM(distance) AS total_distance
//            FROM
//                route
//            LEFT JOIN
//                station ON stationid = station.id
//            LEFT JOIN
//                train ON trainid = train.id
//            WHERE
//                station.name = 'madurai'
//            GROUP BY
//                trainid,
//                stationid,
//                distance,
//                station.name,
//                code,
//                train.name,
//                number,
//                rate_per_km
//        ) t1
//    INNER JOIN
//        (
//            SELECT
//                trainid,
//                stationid,
//                distance,
//                station.name AS station_name,
//                code,
//                train.name AS train_name,
//                number,
//                rate_per_km,
//                SUM(distance) AS total_distance
//            FROM
//                route
//            LEFT JOIN
//                station ON stationid = station.id
//            LEFT JOIN
//                train ON trainid = train.id
//            WHERE
//                station.name = 'chennai'
//            GROUP BY
//                trainid,
//                stationid,
//                distance,
//                station.name,
//                code,
//                train.name,
//                number,
//                rate_per_km
//        ) t2
//    ON
//        t1.trainid = t2.trainid;

// give date and from,to
{
    
}


    // public HashMap<TrainDetails,Integer> findAvailableTrains(String date,String from,String to){
    //     try {
    //         HashMap<TrainDetails,Integer> availableTrains=new HashMap<TrainDetails,Integer>();
    //         preparedStatement=connection.prepareStatement("SELECT seat.trainid,train.name,count(travel_date) from seat left join seat_status on seat.id=seatid left join train on train.id=seat.trainid where travel_date=? group by seat.trainid,train.name");
    //         preparedStatement.setDate(1,java.sql.Date.valueOf(date));
    //         resultSet=preparedStatement.executeQuery();
    //         if(resultSet=null){
    //             int count=resultSet.getInt("count");
    //             if(count==0){
    //                 // INSERT INTO seat_status (seatid, status, travel_date)
    //                 // SELECT seat.id, train_seatstatus.seatstatus, '2000-06-21'
    //                 // FROM seat
    //                 // RIGHT JOIN train_seatstatus ON seat.trainid = train_seatstatus.trainid;
    //                 preparedStatement.preparedStatement("INSERT INTO seat_status (seatid, status, travel_date)"+
    //                 "SELECT seat.id, train_seatstatus.seatstatus, date"+
    //                 "FROM seat"+
    //                 "RIGHT JOIN train_seatstatus ON seat.trainid = train_seatstatus.trainid");
    //                 preparedStatement.setDate(1,java.sql.Date.valueOf(date));
    //                 preparedStatement.executeUpdate();
    //                 ????
    //                 // to check array
    //                 // SELECT my_array
    //                 // FROM my_table
    //                 // WHERE array_slice(my_array, 2, 3) = ARRAY[true, false, false];

    //             }else{
    //                 while(resultSet.next()){
                                                    
    //                 }
    //                 // availableTrains.put()
    //             }
    //         }else{

    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         e.printStackTrace();
    //     }
    //     finally{
    //         try{
    //         if(preparedStatement!=null)
    //         preparedStatement.close();
    //         if(resultSet!=null)
    //         resultSet.close();
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    //     }
    //     return ;
    // }

// 
// 
    // SELECT trainid, COUNT(*) AS count
    //   from(
    //   select trainid,seatid,status
    //   from( select *
    //   from seat_status
    //   where travel_date='25-06-2000' and status[1:5] = array[false,false,false,false,false]::boolean[]
    //   ) t1
    //   inner join seat on seatid = seat.id
    //   order by trainid
    //   ) subquery
    //   group by trainid;
// 
// 
    

// write all trains details in database
//     public void insertTrainDetails(ArrayList<TrainDetails> trains){
//         try{
//         connection=DbConnection.getInstance().getConnection();
//             for(TrainDetails train:trains){
//         preparedStatement=connection.prepareStatement("INSERT INTO train(name,number,rate_per_km,total_seats) values(?,?,?,?) returning id");
//         preparedStatement.setString(1,train.name);
//         preparedStatement.setInt(2,train.number);
//         preparedStatement.setDouble(3,train.rate);
//         PreparedStatement.setInt(4,train.total_seats);
//         resultSet=preparedStatement.executeQuery();
//         int trainId=-1;
//         if(resultSet.next())
//         trainId=resultSet.getInt("id");
//         for(Station station:train.route){
//             preparedStatement=connection.prepareStatement("SELECT id from station where name=? and code=?");
//             preparedStatement.setString(1,station.name);
//             preparedStatement.setString(2,station.code);
//             resultSet=preparedStatement.executeQuery();
//             int stationid=-1;
//             if(resultSet.next()){
//                 stationid=resultSet.getInt("id");
//             }else{
//                 preparedStatement=connection.prepareStatement("INSERT INTO station(name,code) values(?,?) returning id");
//                 preparedStatement.setString(1,station.name);
//                 preparedStatement.setString(2,station.code);
//                 resultSet=preparedStatement.executeQuery();
//             if(resultSet.next())
//                 stationid=resultSet.getInt("id");
//             }
//             preparedStatement=connection.prepareStatement("INSERT INTO route(trainid,stationid,distance) values(?,?,?)");
//             preparedStatement.setInt(1, trainId);
//             preparedStatement.setInt(2, stationid);
//             preparedStatement.setInt(3,station.distance);
//             preparedStatement.executeUpdate();
//         }
//         Object[] seatStatus=null;
//         preparedStatement=connection.prepareStatement("SELECT count(*) from route where trainid=?");
//         preparedStatement.setInt(1,trainId);
//         resultSet=preparedStatement.executeQuery();
//         if(resultSet.next()){
//             seatStatus=new Object[resultSet.getInt("count")];
//             for(int i=0;i<seatStatus.length;i++)
//             seatStatus[i]=false;
//             preparedStatement=connection.prepareStatement("INSERT INTO train_seatstatus values(?,?)");
//             preparedStatement.setInt(1,trainId);
//             Array seatStatusArray = connection.createArrayOf("bool", seatStatus);
//             preparedStatement.setArray(2,seatStatusArray);
//             preparedStatement.executeUpdate();
//         }
//         // int a=null;
//                 // String query="INSERT INTO coach(trainid,name,class_type_id,number_of_seats) values";
//                 // String values="";
//                 for(Coach coach:train.coachs){
//                     int class_type_id=-1;
//                     preparedStatement=connection.prepareStatement("SELECT id from classtype where LOWER(type)=LOWER(?)");
//                     preparedStatement.setString(1,coach.class_type);
//                     resultSet=preparedStatement.executeQuery();
//                     if(resultSet.next())
//                     class_type_id=resultSet.getInt("id");
//                     else{
//                         preparedStatement=connection.prepareStatement("INSERT INTO classtype(type) values(?) returning id");
//                         preparedStatement.setString(1,coach.class_type);
//                         resultSet=preparedStatement.executeQuery();
//                         if(resultSet.next())
//                         class_type_id=resultSet.getInt("id");
//                     }
//                     int coachId=0;
//                     preparedStatement=connection.prepareStatement("INSERT INTO coach(trainid,name,class_type_id,number_of_seats) values(?,?,?,?) returning id");
//                     preparedStatement.setInt(1, trainId);
//                     preparedStatement.setString(2,coach.name);
//                     preparedStatement.setInt(3, class_type_id);
//                     preparedStatement.setInt(4,coach.seat_count);
//                     resultSet=preparedStatement.executeQuery();
//                     if(resultSet.next())
//                     coachId=resultSet.getInt("id");
//                     int berthId=0;
//                     int seatId=0;
//                     for(int i=1;i<=coach.seat_count;i++){
//                         if(i%4==1)
//                         berthId=1;
//                         else if(i%4==2)
//                         berthId=2;
//                         else if(i%4==3)
//                         berthId=3;
//                         else
//                         berthId=4;
//                         preparedStatement=connection.prepareStatement("INSERT INTO seat(number,trainid,coachid,berthid) values(?,?,?,?)");
//                         preparedStatement.setInt(1,i);
//                         preparedStatement.setInt(2,trainId);
//                         preparedStatement.setInt(3,coachId);
//                         preparedStatement.setInt(4,berthId);
//                         // resultSet=
//                         preparedStatement.executeUpdate();

//                         // if(resultSet.next()){
//                         //     seatId=resultSet.getInt("id");
//                         // }
//                     }
//                 }
//     }
// }catch(Exception e){
//     e.printStackTrace();
// }
// finally{
//     try {
//         if(preparedStatement!=null)
//         preparedStatement.close();
//         if(resultSet!=null)
//         resultSet.close();
//     } catch (Exception e) {
//         // TODO: handle exception
//         e.printStackTrace();
//     }
// // }
//     }
// }

}