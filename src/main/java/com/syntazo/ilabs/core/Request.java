package com.syntazo.ilabs.core;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/7/11
 * Time: 3:42 PM
 */
public class Request extends DataEnvelope {
    public Request(Message message, Object subject) {
        super(message, subject);
    }

    public class Reply extends FutureTask<Message> {
        public Reply(Callable<Message> attributeListCallable) {
            super(attributeListCallable);
        }

        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean isCancelled() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean isDone() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Message get() throws InterruptedException, ExecutionException {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Message get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
