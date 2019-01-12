package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/27/2016.
 */

public class all_issueRequest {

    @SerializedName("registration_no")
    private String registrationNo = "";

    public all_issueRequest(String registrationNo) {
        this.registrationNo = registrationNo;

    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    @Override
    public String toString() {
        return "books_issued_request{" +
                "registrationNo='" + registrationNo + '\'' +
                '}';
    }


}

