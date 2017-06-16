package software.nju.tssclient.presenter.contract;

import java.util.List;
import software.nju.tssclient.model.entity.Exercise;

/**
 * Created by ss14 on 2017/6/16.
 */

public interface ExerciseContract {
    interface View {
        void showExercise(List<Exercise> exerciseList);
    }

    interface Presenter{
        void getExerciseByCourseId(String token , int courseId);
    }
}
