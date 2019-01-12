package parsing;

/**
 * Created by shubham on 12/19/2016.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/16/2016.
 */

public class returnbookResponse {

    @SerializedName("success")
    private String success="";

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "returnbookResponse{" +
                "success='" + success + '\'' +
                '}';
    }

}
