package energy.viridis.exercise.error;

public class ExerciseException  extends  RuntimeException{


    public ExerciseException(String message) {
        super(message);
    }

    public ExerciseException(Throwable cause) {
        super(cause);
    }

    public ExerciseException(String message, Throwable cause) {
        super(message, cause);
    }

}
