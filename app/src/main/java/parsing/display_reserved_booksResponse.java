package parsing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shu on 19/02/2017.
 */
public class display_reserved_booksResponse {

    @SerializedName("books")
    private ArrayList<res_books> books = new ArrayList<>();

    @SerializedName("success")
    private String success="";

    public ArrayList<res_books> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<res_books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "all_books_response{" +
                "books=" + books +
                ", success='" + success + '\'' +
                '}';
    }



    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
