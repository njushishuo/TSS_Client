package software.nju.tssclient.presenter.contract;


import software.nju.tssclient.model.entity.QuestionScore;

/**
 * Created by ss14 on 2017/6/22.
 */

public interface QuestionScoreContract {

    interface View {

        void showScoreGraph(QuestionScore questionScore);

    }

    interface Presenter{

        void getQuestionScoreBy(String token , int assignmentId , int questionId);

    }
}
