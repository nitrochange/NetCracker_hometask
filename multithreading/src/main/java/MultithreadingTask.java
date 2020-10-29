import java.util.concurrent.*;

public class MultithreadingTask {
    public static void main(String[] args) {
        String[] words = TEXT.split("\\W+");

        Counter counter = new Counter();

        Integer total = 0;

        //для каждой строки надо вернуть её длину
        for (String word : words) {
            try {
                total += counter.getLength(word).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //завершаем работу executorService
        counter.executorService.shutdown();

        //expected output 344
        System.out.println(total);
    }

    private static class Counter {
        private ExecutorService executorService = Executors.newFixedThreadPool(10);

        /**
         * Counts characters in a word
         * @param word
         * @return
         */
        public Future<Integer> getLength(final String word) {
            return executorService.submit(new Callable<>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 */
                public Integer call() {
                    return word.length();
                }
            });
        }
    }

    private static final String TEXT = "All modern operating systems support concurrency both via processes and threads. Processes are instances of programs which typically run independent to each other, e.g. if you start a java program the operating system spawns a new process which runs in parallel to other programs. Inside those processes we can utilize threads to execute code concurrently, so we can make the most out of the available cores of the CPU";
}
