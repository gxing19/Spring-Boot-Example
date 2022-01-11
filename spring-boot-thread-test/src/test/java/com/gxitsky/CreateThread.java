package com.gxitsky;

import java.util.concurrent.*;

public class CreateThread {
    private final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        CreateThread createThread = new CreateThread();
        String result = createThread.executionTask("task1");
        System.out.println(result);

    }

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };


                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if(future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }

            try {

                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }


}
