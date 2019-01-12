package parsing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubham on 12/19/2016.
 */

public class searchbookResponse {

    @SerializedName("success")
    private String success="";
    @SerializedName("book_name")
    private String book_name="";
    @SerializedName("author")
    private String author="";
    @SerializedName("publication")
    private String publication="";
    @SerializedName("edition")
    private String edition="";
    @SerializedName("no_of_copies")
    private String no_of_copies="";
    @SerializedName("date_pur")
    private String date_pur="";
    @SerializedName("price")
    private String price="";
    @SerializedName("reg_no")
    private ArrayList<Book_details> details = new ArrayList<>();

    public ArrayList<Book_details> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Book_details> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "searchbookResponse{" +
                "success='" + success + '\'' +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", publication='" + publication + '\'' +
                ", edition='" + edition + '\'' +
                ", no_of_copies='" + no_of_copies + '\'' +
                ", date_pur='" + date_pur + '\'' +
                ", price='" + price + '\'' +
                ", details=" + details +
                '}';
    }






    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getDate_pur() {
        return date_pur;
    }

    public void setDate_pur(String date_pur) {
        this.date_pur = date_pur;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
