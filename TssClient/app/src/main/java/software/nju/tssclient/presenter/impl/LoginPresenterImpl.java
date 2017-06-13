package software.nju.tssclient.presenter.impl;

import android.util.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.dto.LoginUser;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.model.service.ApiManager;
import software.nju.tssclient.presenter.contract.LoginContract;

/**
 * Created by ss14 on 2017/6/12.
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginContract.View loginView;


    public LoginPresenterImpl(LoginContract.View loginView){

        if(loginView==null){
            throw new IllegalArgumentException("loginview must not be null");
        }

        this.loginView = loginView;
    }

    @Override
    public void getUserInfo(final String username, String password) {

        LoginUser user = new LoginUser(username,password);
        ApiManager.getInstance().getCommonApi().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("logError", e.getMessage());

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d("user",user.getName());
                        System.out.println(user.getName());
                        loginView.showUserInfo(user);
                    }
                });




    }
}
