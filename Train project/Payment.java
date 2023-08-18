package TrainTicketBookingProject.Booking;
public class Payment{
    public long card_no;
    public int ccv;
    public int exp_month;
    public int exp_year;
    public String card_holdername;
    public String holder_mobileno;
    public Payment(long card_no,int ccv,int exp_month,int exp_year,String card_holdername,String mobile){
        this.card_no=card_no;
        this.ccv=ccv;
        this.exp_month=exp_month;
        this.exp_year=exp_year;
        this.card_holdername=card_holdername;
        holder_mobileno=mobile;
    }
}