package TrainTicketBookingProject.Train;
public class Station{
    public String name;
    public String code;
    public int distance;
    public Station(String code,String name,int i){
        this.code=code;
        this.name=name;
        distance=i;
    }
    public Station(String code,String name){
        this.code=code;
        this.name=name;
    }
    // public String getname(){
    //     return name;
    // }
    // public String getcode(){
    //     return code;
    // }
    // public int getdistance(){
    //     return distance;
    // }
}