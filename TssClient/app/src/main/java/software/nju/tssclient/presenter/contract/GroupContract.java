package software.nju.tssclient.presenter.contract;

import java.util.List;

import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.User;

/**
 * Created by ss14 on 2017/6/13.
 */

public interface GroupContract {


    interface View {

        void showGroups(List<Group> groups);

    }

    interface Presenter{

        void getAllGroups(String username, String password);

    }

}
