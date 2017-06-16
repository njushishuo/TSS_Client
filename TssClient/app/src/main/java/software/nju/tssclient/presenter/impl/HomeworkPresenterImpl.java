package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.Homework;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.HomeworkContract;

/**
 * Created by ss14 on 2017/6/16.
 */

public class HomeworkPresenterImpl implements HomeworkContract.Presenter {

    private HomeworkContract.View view;

    public HomeworkPresenterImpl(HomeworkContract.View view){

        this.view = view;

    }

    @Override
    public void getHomeworkByCourseId(String token, int courseId) {
        ApiManager.getInstance().getCommonApi().getHomeworkByCourseId(token,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Homework>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(List<Homework> homeworkList) {
                        Log.d("homeworkSize",homeworkList.size()+"");
                        view.showHomework(homeworkList);
                    }
                });
    }
}
