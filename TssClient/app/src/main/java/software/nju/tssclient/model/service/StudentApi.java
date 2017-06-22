package software.nju.tssclient.model.service;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;
import software.nju.tssclient.model.entity.AssignmentAnalysis;
import software.nju.tssclient.model.entity.ReadMe;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface StudentApi {

    @GET("assignment/{assignmentID}/student/{studentID}/question/{questionID}")
    Observable<ReadMe> getReadMeBy (@Header("Authorization")String token,
                                    @Path("assignmentID")int assignmentId, @Path("studentID") int stdId,
                                    @Path("questionID") int questionId);


    @GET("assignment/{assignmentID}/student/{studentID}/analysis")
    Observable<AssignmentAnalysis> getAssignmentAnalysisBy (@Header("Authorization")String token,
                                                            @Path("assignmentID")int assignmentId, @Path("studentID") int stdId);
}
