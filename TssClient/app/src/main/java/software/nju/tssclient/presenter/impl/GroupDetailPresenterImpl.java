package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.Student;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.GroupDetailContract;

/**
 * Created by ss14 on 2017/6/15.
 */

public class GroupDetailPresenterImpl implements GroupDetailContract.Presenter {

    private GroupDetailContract.View view;

    public GroupDetailPresenterImpl ( GroupDetailContract.View view){
        this.view =view;
    }


    @Override
    public void getStudentsByGroupId(String token, int groupId) {
        ApiManager.getInstance().getTeacherApi().getStudentsByGroupId(token,groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Student>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String err = (e.getMessage()==null)?"Get Students failed":e.getMessage();
                        Log.e("error:",err);
                    }

                    @Override
                    public void onNext(List<Student> students) {
                        Log.d("studentsSize",students.size()+"");

                        view.showStudents(students);
                    }
                });
    }
}
