package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/22/2016.
 */

public class updatebookResponse {
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
        return "updatebookResponse{" +
                "success='" + success + '\'' +
                '}';
    }


}
