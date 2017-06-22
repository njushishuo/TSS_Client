package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.AssignmentScore;
import software.nju.tssclient.model.entity.Course;
import software.nju.tssclient.model.entity.QuestionScore;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.QuestionScoreContract;

/**
 * Created by ss14 on 2017/6/22.
 */

public class QuestionScorePresenterImpl implements QuestionScoreContract.Presenter {

    private QuestionScoreContract.View view;

    public QuestionScorePresenterImpl (QuestionScoreContract.View view){

        this.view =view;

    }


    @Override
    public void getQuestionScoreBy(String token, final int assignmentId, final int questionId) {
        ApiManager.getInstance().getTeacherApi().getScoreInfoByAssignmentId(token,assignmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AssignmentScore>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.getMessage());
                    }

                    @Override
                    public void onNext(AssignmentScore assignmentScore) {
                        QuestionScore [] questionScores = assignmentScore.getQuestionScores();
                        QuestionScore result=null;
                        for(int i=0;i<questionScores.length;i++){
                            if(questionScores[i].getQuestionInfo().getId()==questionId){
                                result = questionScores[i];
                                break;
                            }
                        }
                        view.showScoreGraph(result);
                    }
                });
    }
}
