package software.nju.tssclient.presenter.impl;

import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.dto.LoginUser;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.GroupContract;
import software.nju.tssclient.util.TokenBuilder;

/**
 * Created by ss14 on 2017/6/13.
 */

public class GroupPresenterImpl implements GroupContract.Presenter {


    private GroupContract.View view;

    public GroupPresenterImpl(GroupContract.View view){

        if(view==null){
            throw new IllegalArgumentException("groupview must not be null");
        }
        this.view = view;

    }

    @Override
    public void getAllGroups(String username, String password) {

        ApiManager.getInstance().getTeacherApi().getAllGroups(TokenBuilder.getToke(username,password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Group>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String err = (e.getMessage()==null)?"Get Groups failed":e.getMessage();
                        Log.e("error:",err);
                    }

                    @Override
                    public void onNext(List<Group> groups) {
                        Log.d("groupSize",groups.size()+"");
                        view.showGroups(groups);
                    }
                });
    }
}
