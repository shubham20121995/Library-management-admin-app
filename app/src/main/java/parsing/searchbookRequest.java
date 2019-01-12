package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/19/2016.
 */

public class searchbookRequest {

    @SerializedName("book_id")
    private String book_id = "";


    public searchbookRequest(String book_id) {
        this.book_id = book_id;
    }
}
