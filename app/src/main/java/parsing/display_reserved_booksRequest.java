package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shu on 19/02/2017.
 */
public class display_reserved_booksRequest {

    @SerializedName("verify")
    private String verify = "";

    public display_reserved_booksRequest(String verify) {
        this.verify = verify;

    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    @Override
    public String toString() {
        return "all_books_request{" +
                "verify='" + verify + '\'' +
                '}';
    }

}
