package software.nju.tssclient.presenter.contract;

import software.nju.tssclient.model.entity.QuestionResult;

/**
 * Created by ss14 on 2017/6/22.
 */

public interface QuestionAnalysisContract {

    interface View {

        void showAnalysis(QuestionResult analysis);


    }

    interface Presenter{

        void getQuestionAnalysisByThreeIds(String token, int assignId , int stdId, int questionId);

    }

}
