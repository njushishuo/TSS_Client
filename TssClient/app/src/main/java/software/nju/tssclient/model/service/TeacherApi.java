package software.nju.tssclient.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import software.nju.tssclient.model.entity.Group;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface TeacherApi {

    @GET("group")
    Observable<List<Group>> getAllGroups (@Header("Authorization") String token);

}
