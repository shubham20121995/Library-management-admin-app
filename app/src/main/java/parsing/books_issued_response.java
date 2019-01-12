package parsing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubham on 12/9/2016.
 */

public class books_issued_response {

    @SerializedName("books")
    private ArrayList<Books> books = new ArrayList<>();
    @SerializedName("success")
    private String success="";

    @Override
    public String toString() {
        return "books_issued_response{" +
                "books=" + books +
                ", success='" + success + '\'' +
                '}';
    }

    public ArrayList<Books> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Books> books) {
        this.books = books;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}


