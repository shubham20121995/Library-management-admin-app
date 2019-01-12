package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/23/2016.
 */

public class reissuebookResponse {

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
        return "reissuebookResponse{" +
                "success='" + success + '\'' +
                '}';
    }
}
