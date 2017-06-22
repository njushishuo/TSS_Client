package software.nju.tssclient.presenter.impl;

import android.util.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.AssignmentAnalysis;
import software.nju.tssclient.model.entity.QuestionResult;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.QuestionAnalysisContract;

/**
 * Created by ss14 on 2017/6/22.
 */

public class QuestionAnalysisPresenterImpl implements QuestionAnalysisContract.Presenter {

    private QuestionAnalysisContract.View view;

    public  QuestionAnalysisPresenterImpl(QuestionAnalysisContract.View view){
        this.view = view;
    }


    @Override
    public void getQuestionAnalysisByThreeIds(String token, final int assignId, int stdId, final int questionId) {
        ApiManager.getInstance().getStudentApi().getAssignmentAnalysisBy(token,assignId,stdId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AssignmentAnalysis>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String err = (e.getMessage()==null)?"Get Analysis failed":e.getMessage();
                        Log.e("error:",err);
                    }

                    @Override
                    public void onNext(AssignmentAnalysis assignmentAnalysis) {
                        QuestionResult [] questionResults = assignmentAnalysis.getQuestionResults();
                        QuestionResult questionResult=null;
                        for(int i=0;i<questionResults.length;i++){
                            if(questionResults[i].getQuestionId()==questionId){
                                questionResult = questionResults[i];
                                break;
                            }
                        }
                        view.showAnalysis(questionResult);
                    }
                });
    }
}
