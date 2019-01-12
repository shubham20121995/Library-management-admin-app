package parsing;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 12/9/2016.
 */

public class books_issued_request {

    @SerializedName("registration_no")
    private String registrationNo = "";

    public books_issued_request(String registrationNo) {
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
