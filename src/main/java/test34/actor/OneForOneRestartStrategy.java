package test34.actor;


import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.japi.Function;
import scala.PartialFunction;
import scala.concurrent.duration.Duration;

/**
 * Created by chin on 11/17/16.
 */
public class OneForOneRestartStrategy extends OneForOneStrategy {


    public OneForOneRestartStrategy() {
        super(-1, Duration.Inf(), new Function<Throwable, Directive>(){

            @Override
            public Directive apply(Throwable param) throws Exception {

                if (param instanceof Exception)
                    return SupervisorStrategy.restart();
                else
                    return SupervisorStrategy.stop();

            }
        });
    }

    public OneForOneRestartStrategy(PartialFunction<Throwable, Directive> decider) {
        super(decider);
    }

    @Override
    public Object productElement(int n) {
        return null;
    }

    @Override
    public int productArity() {
        return 0;
    }

    @Override
    public boolean canEqual(Object that) {
        return false;
    }

    @Override
    public PartialFunction<Throwable, Directive> decider() {
        return null;
    }
}
