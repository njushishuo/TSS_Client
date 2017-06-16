package software.nju.tssclient.presenter.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import software.nju.tssclient.model.pojo.LoginUser;
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

        final LoginUser user = new LoginUser(username,password);
        ApiManager.getInstance().getCommonApi().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("login error" , e.getMessage());
                        loginView.showError();

                    }

                    @Override
                    public void onNext(Response<User> userResponse) {
                        String jsonString = new Gson().toJson(userResponse);
                        JsonObject temp = new JsonParser().parse(jsonString).getAsJsonObject();

                        if(temp.has("body")){
                            JsonObject body = temp.getAsJsonObject("body");
                            if(body.has("id")){
                                loginView.showUserInfo(userResponse.body());
                            }
                        }
                    }
                });




    }
}
