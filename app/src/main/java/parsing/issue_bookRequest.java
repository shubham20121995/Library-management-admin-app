package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/16/2016.
 */

public class issue_bookRequest {

    @SerializedName("registration_no")
    private String registration_no = "";
    @SerializedName("book_id")
    private String book_id = "";
    @SerializedName("date_of_issue")
    private String date_of_issue = "";
    @SerializedName("return_date")
    private String return_date = "";


    public issue_bookRequest(String registration_no, String book_id, String date_of_issue, String return_date ) {
        this.registration_no = registration_no;
        this.book_id = book_id;
        this.date_of_issue = date_of_issue;
        this.return_date = return_date;

    }
    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
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

    @Override
    public String toString() {
        return "issue_bookRequest{" +
                "registration_no='" + registration_no + '\'' +
                ", book_id='" + book_id + '\'' +
                ", date_of_issue='" + date_of_issue + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }


}
