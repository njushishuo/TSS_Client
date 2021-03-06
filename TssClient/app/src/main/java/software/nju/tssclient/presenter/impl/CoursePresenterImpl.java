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
                        String err = (e.getMessage()==null)?"Get Courses failed":e.getMessage();
                        Log.e("error:",err);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Course> courses) {
                        Log.d("courseSize",courses.size()+"");
                        if(courses.size()==0){
                            Course course = new Course();
                            course.setName("软件工程与计算1");
                            course.setId(1);
                            courses.add(course);
                        }
                        view.showCourses(courses);
                    }
                });
    }
}
