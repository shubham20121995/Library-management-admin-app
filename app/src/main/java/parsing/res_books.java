package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shu on 19/02/2017.
 */
public class res_books {

    @SerializedName("book_id")
    private String book_id="";

    @SerializedName("registration_no")
    private String registration_no="";

    @SerializedName("reserved_date")
    private String reserved_date;

    @SerializedName("book_name")
    private String book_name;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Override
    public String toString() {
        return "res_books{" +
                "book_id='" + book_id + '\'' +
                ", registration_no='" + registration_no + '\'' +
                ", reserved_date='" + reserved_date + '\'' +
                '}';
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

    public String getReserved_date() {
        return reserved_date;
    }

    public void setReserved_date(String reserved_date) {
        this.reserved_date = reserved_date;
    }
}
