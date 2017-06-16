package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.Course;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.CourseContract;

/**
 * Created by ss14 on 2017/6/16.
 */

public class CoursePresenterImpl implements CourseContract.Presenter {


    private CourseContract.View view;
    public CoursePresenterImpl (CourseContract.View view){

        this.view = view;

    }


    @Override
    public void getCoursesByUsername(String token, String username) {
        ApiManager.getInstance().getCommonApi().getCoursesByUsername(token,username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Course>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String err = (e.getMessage()==null)?"Get Groups failed":e.getMessage();
                        Log.e("error:",err);
                    }

                    @Override
                    public void onNext(List<Course> courses) {
                        Log.d("groupSize",courses.size()+"");
                        view.showCourses(courses);
                    }
                });
    }
}
