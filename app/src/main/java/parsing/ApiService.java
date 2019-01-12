package parsing;

import parsing.addbookRequest;
import parsing.addbookResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by shubham on 12/16/2016.
 */

public interface ApiService {

    @POST("/library/add_book.php")
    Call<addbookResponse> getLogin(@Body addbookRequest addbookrequest);

    @POST("/library/delete_book.php")
    Call<delete_bookResponse> getLogin2(@Body delete_bookRequest delete_bookrequest);

    @POST("/library/issue_book.php")
    Call<issue_bookResponse> getLogin3(@Body issue_bookRequest issue_bookrequest);

    @POST("/library/return_book.php")
    Call<returnbookResponse> getLogin4(@Body returnbookRequest returnbookrequest);

    @POST("/library/search_book.php")
    Call<searchbookResponse> getLogin5(@Body searchbookRequest searchbookrequest);

    @POST("/library/update_book.php")
    Call<updatebookResponse> getLogin6(@Body updatebookRequest updatebookrequest);

    @POST("/library/all_books.php")
    Call<all_books_response> getLogin7(@Body all_books_request allBooksRequestRequest);

    @POST("/library/re_issue_book.php")
    Call<reissuebookResponse> getLogin8(@Body reissuebookRequest reissuebookrequest);

    @POST("/library/books_issued.php")
    Call<books_issued_response> getLogin9(@Body books_issued_request booksIssuedRequest);

    @POST("/library/all_books_issued.php")
    Call<all_issueResponse> getLogin10(@Body all_issueRequest all_issuerequest);

    @POST("/library/display_reserved_books.php")
    Call<display_reserved_booksResponse> getLogin11(@Body display_reserved_booksRequest display_reserved_booksrequest);

}
