package software.nju.tssclient.model.service;



import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import software.nju.tssclient.model.dto.LoginUser;
import software.nju.tssclient.model.entity.User;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface CommonApi {

    @POST("user/auth")
    Observable<User> login (@Body LoginUser user);

}
