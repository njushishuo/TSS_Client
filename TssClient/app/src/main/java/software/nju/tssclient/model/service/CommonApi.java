package software.nju.tssclient.model.service;



import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import software.nju.tssclient.model.entity.Course;
import software.nju.tssclient.model.pojo.LoginUser;
import software.nju.tssclient.model.entity.User;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface CommonApi {

    @POST("user/auth")
    Observable<Response<User>> login (@Body LoginUser user);


    @GET("user/{username}/course")
    Observable<List<Course>> getCoursesByUsername
            (@Header("Authorization") String token, @Path("username") String username);

}
