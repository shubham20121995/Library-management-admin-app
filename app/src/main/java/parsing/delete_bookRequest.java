package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/16/2016.
 */

public class delete_bookRequest {
    @SerializedName("book_id")
    private String book_id = "";
    @SerializedName("no_of_copies")
    private String no_of_copies = "";

    public delete_bookRequest(String book_id,String no_of_copies) {
        this.book_id = book_id;
        this.no_of_copies = no_of_copies;

    }
    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
    }



    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "delete_bookRequest{" +
                "book_id='" + book_id + '\'' +
                '}';
    }


}
