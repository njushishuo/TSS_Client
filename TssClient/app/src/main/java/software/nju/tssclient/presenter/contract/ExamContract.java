package software.nju.tssclient.presenter.contract;

import java.util.List;

import software.nju.tssclient.model.entity.Exam;

/**
 * Created by ss14 on 2017/6/16.
 */

public interface ExamContract {

    interface View {
        void showExam(List<Exam> examList);
    }

    interface Presenter{
        void getExamByCourseId(String token , int courseId);
    }
}
