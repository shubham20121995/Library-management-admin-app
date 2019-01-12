package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/23/2016.
 */

public class reissuebookRequest {

    @SerializedName("registration_no")
    private String registration_no = "";
    @SerializedName("book_id")
    private String book_id = "";
    @SerializedName("return_date")
    private String return_date = "";

    public reissuebookRequest(String registration_no, String book_id, String return_date ) {
        this.registration_no = registration_no;
        this.book_id = book_id;
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

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }



    @Override
    public String toString() {
        return "reissuebookRequest{" +
                "registration_no='" + registration_no + '\'' +
                ", book_id='" + book_id + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }


}
