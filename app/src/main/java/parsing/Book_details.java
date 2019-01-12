package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/22/2016.
 */

public class Book_details {

    @SerializedName("registration_no")
    private String registration_no="";

    @SerializedName("return_date")
    private String return_date;
    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }



    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    @Override
    public String toString() {
        return "Book_details{" +
                "registration_no='" + registration_no + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }



}
