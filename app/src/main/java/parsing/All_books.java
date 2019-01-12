package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/9/2016.
 */

public class All_books {
    @SerializedName("book_id")
    private String book_id="";

    @SerializedName("author")
    private String author="";

    @SerializedName("book_name")
    private String book_name;

    @SerializedName("publication")
    private String publication;

    @SerializedName("edition")
    private String edition;

    @SerializedName("no_of_copies")
    private String no_of_copies;

    @SerializedName("date_pur")
    private String date_pur;

    @SerializedName("price")
    private String price;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
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

    @Override
    public String toString() {
        return "All_books{" +
                "book_id='" + book_id + '\'' +
                ", author='" + author + '\'' +
                ", book_name='" + book_name + '\'' +
                ", publication='" + publication + '\'' +
                ", edition='" + edition + '\'' +
                ", no_of_copies='" + no_of_copies + '\'' +
                ", date_pur='" + date_pur + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


}
