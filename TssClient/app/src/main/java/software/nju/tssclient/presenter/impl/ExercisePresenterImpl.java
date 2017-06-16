package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.Exercise;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.ExerciseContract;

/**
 * Created by ss14 on 2017/6/16.
 */

public class ExercisePresenterImpl implements ExerciseContract.Presenter {

    private ExerciseContract.View view;

    public ExercisePresenterImpl(ExerciseContract.View view){
        this.view =view;
    }


    @Override
    public void getExerciseByCourseId(String token, int courseId) {
        ApiManager.getInstance().getCommonApi().getExerciseByCourseId(token,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Exercise>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String err = (e.getMessage()==null)?"Get Groups failed":e.getMessage();
                        Log.e("error:",err);
                    }

                    @Override
                    public void onNext(List<Exercise> exerciseList) {
                        Log.d("exerciseSize",exerciseList.size()+"");
                        view.showExercise(exerciseList);
                    }
                });
    }
}
