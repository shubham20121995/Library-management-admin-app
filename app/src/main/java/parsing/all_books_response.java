package parsing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubham on 12/9/2016.
 */

public class all_books_response {
    @SerializedName("books")
    private ArrayList<All_books> books = new ArrayList<>();

    @SerializedName("success")
    private String success="";

    public ArrayList<All_books> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<All_books> books) {
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
