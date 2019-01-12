package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/16/2016.
 */

public class issue_bookResponse {

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
        return "addbookResponse{" +
                "success='" + success + '\'' +
                '}';
    }
}
