package software.nju.tssclient.presenter.contract;

import software.nju.tssclient.model.entity.ReadMe;

/**
 * Created by ss14 on 2017/6/22.
 */

public interface ReadMeContract {


    interface View {

        void showReadMe(ReadMe readMe);

    }

    interface Presenter{

        void getReadMeByThreeIds(String token, int assignId , int stdId, int questionId);

    }
}
