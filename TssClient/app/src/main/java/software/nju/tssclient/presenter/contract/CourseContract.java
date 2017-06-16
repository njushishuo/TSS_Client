package software.nju.tssclient.presenter.contract;

import java.util.List;

import software.nju.tssclient.model.entity.Course;

/**
 * Created by ss14 on 2017/6/16.
 */

public interface CourseContract {

    interface View {

        void showCourses(List<Course> courses);

    }

    interface Presenter{

        void getCoursesByUsername(String token , String username);

    }
}
