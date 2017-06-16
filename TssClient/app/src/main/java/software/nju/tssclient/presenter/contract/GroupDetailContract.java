package software.nju.tssclient.presenter.contract;

import java.util.List;

import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.Student;

/**
 * Created by ss14 on 2017/6/14.
 */

public interface GroupDetailContract {

    interface View {

        void showStudents(List<Student> students);

    }

    interface Presenter{

        void getStudentsByGroupId(String token , int groupId);

    }
}
