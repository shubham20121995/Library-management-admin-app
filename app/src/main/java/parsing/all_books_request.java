package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/9/2016.
 */

public class all_books_request {
    @SerializedName("verify")
    private String verify = "";

    public all_books_request(String verify) {
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
