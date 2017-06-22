package software.nju.tssclient.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;
import software.nju.tssclient.model.entity.AssignmentScore;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.Student;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface TeacherApi {

    @GET("group")
    Observable<List<Group>> getAllGroups (@Header("Authorization") String token);

    @GET("group/{groupId}/students")
    Observable<List<Student>> getStudentsByGroupId(@Header("Authorization") String token,@Path("groupId") int id);


    @GET("assignment/{assignmentId}/score")
    Observable<AssignmentScore> getScoreInfoByAssignmentId(@Header("Authorization") String token, @Path("assignmentId") int id);
}
