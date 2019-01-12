package parsing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubham on 12/27/2016.
 */

public class all_issueResponse {


    @SerializedName("books")
    private ArrayList<Books2> books = new ArrayList<Books2>();
    @SerializedName("success")
    private String success="";

    public ArrayList<Books2> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Books2> books) {
        this.books = books;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }



    @Override
    public String toString() {
        return "all_issueResponse{" +
                "books=" + books +
                ", success='" + success + '\'' +
                '}';
    }
}
