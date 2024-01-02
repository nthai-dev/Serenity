package integration.step.common;

import net.serenitybdd.core.steps.Instrumented;
import net.thucydides.core.annotations.Step;

public class Stepper {

    private static volatile Stepper _instance;

    private static Stepper getInstance() {
        if(_instance == null) {
            synchronized (Stepper.class) {
                if(_instance == null) {
                    _instance = Instrumented.instanceOf(Stepper.class).newInstance();
                }
            }
        }
        return _instance;
    }

    @Step("{0}")
    public void doStep(String name, Action action) {
        try {
            action.run();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Step("{0}")
    public <T> void doStep(String name, ActionWithResult<T> action, ResultHolder<T> holder) {
        try {
            holder.setResult(action.run());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void step(String name, Action action) {
        getInstance().doStep(name, action);
    }

    public static <T> T step(String name, ActionWithResult<T> action) {
        ResultHolder<T> holder = new ResultHolder<>();
        getInstance().doStep(name, action, holder);
        return holder.getResult();
    }

    public interface Action {

        void run() throws Exception;

    }

    public interface ActionWithResult<T> {

        T run() throws Exception;

    }

    public interface ActionWithParam<T, R> {
        T run(R... params) throws Exception;
    }

    public static class ResultHolder<T> {
        private T result;

        public void setResult(T result) {
            this.result = result;
        }

        public T getResult() {
            return result;
        }
    }
}
