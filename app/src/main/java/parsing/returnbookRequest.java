package parsing;

/**
 * Created by shubham on 12/19/2016.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/16/2016.
 */

public class returnbookRequest {

    @SerializedName("registration_no")
    private String registration_no = "";
    @SerializedName("book_id")
    private String book_id = "";


    public returnbookRequest(String registration_no, String book_id) {
        this.book_id = book_id;
        this.registration_no = registration_no;

    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    @Override
    public String toString() {
        return "returnbookRequest{" +
                "book_id='" + book_id + '\'' +
                ", registration_no='" + registration_no + '\'' +
                '}';
    }


}
