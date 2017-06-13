package software.nju.tssclient.presenter.contract;

import software.nju.tssclient.model.entity.User;

/**
 * Created by ss14 on 2017/6/12.
 */

public interface LoginContract {

    interface View {

        void showUserInfo(User user);

    }

    interface Presenter{

        void getUserInfo(String username , String password);

    }



}
