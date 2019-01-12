package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/27/2016.
 */

public class Books2 {

    @SerializedName("book_id")
    private String book_id="";

    @SerializedName("book_name")
    private String book_name="";



    @SerializedName("registration_no")
    private String registration_no="";

    @SerializedName("date_of_issue")
    private String date_of_issue;

    @SerializedName("return_date")
    private String return_date;





    @Override
    public String toString() {
        return "Books{" +
                "book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", date_of_issue='" + date_of_issue + '\'' +
                ", return_date='" + return_date + '\'' +


                '}';
    }



    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }
    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }



}
