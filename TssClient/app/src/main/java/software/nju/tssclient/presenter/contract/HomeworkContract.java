package software.nju.tssclient.presenter.contract;

import java.util.List;

import software.nju.tssclient.model.entity.Homework;

/**
 * Created by ss14 on 2017/6/16.
 */

public interface HomeworkContract {

    interface View {
        void showHomework(List<Homework> homeworkList);
    }



    interface Presenter{
        void getHomeworkByCourseId(String token , int courseId);

    }
}
