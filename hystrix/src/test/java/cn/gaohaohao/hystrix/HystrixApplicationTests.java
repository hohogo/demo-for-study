package cn.gaohaohao.hystrix;

import cn.gaohaohao.hystrix.command.CommandUsingSemaphoreIsolation;
import cn.gaohaohao.hystrix.command.GetOrderCircuitBreakerCommand;
import cn.gaohaohao.hystrix.command.GetOrderCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixThreadPool;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import com.netflix.hystrix.util.PlatformSpecific;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class HystrixApplicationTests {
    final static ConcurrentHashMap<String, HystrixThreadPool> threadPools = new ConcurrentHashMap<String, HystrixThreadPool>();

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        Future<List> future =new GetOrderCommand("hystrix-order").queue();
        List list = future.get();
        list.clear();
    }

    @Test
    void CommandUsingSemaphoreIsolation() throws ExecutionException, InterruptedException {
        Future<String> future =new CommandUsingSemaphoreIsolation(1).queue();
        String s = future.get();
        System.out.println(s);
    }

    @Test
    void testGetOrderCircuitBreakerCommand() throws InterruptedException {
        for(int i=0;i<25;i++){
            Thread.sleep(500);
            HystrixCommand<String> command = new GetOrderCircuitBreakerCommand("testCircuitBreaker");
            String result = command.execute();
            //本例子中从第11次，熔断器开始打开
            System.out.println("call times:"+(i+1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen());
            //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
        }
    }

    public ThreadPoolExecutor getThreadPool(final HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        ThreadFactory threadFactory = null;
        if (!PlatformSpecific.isAppEngineStandardEnvironment()) {
            threadFactory = new ThreadFactory() {
                protected final AtomicInteger threadNumber = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "hystrix-" + threadPoolKey.name() + "-" + threadNumber.incrementAndGet());
                    thread.setDaemon(true);
                    return thread;
                }

            };
        } else {
            threadFactory = PlatformSpecific.getAppEngineThreadFactory();
        }

        final int dynamicCoreSize = corePoolSize.get();
        final int dynamicMaximumSize = maximumPoolSize.get();

        if (dynamicCoreSize > dynamicMaximumSize) {
            System.err.println("Hystrix ThreadPool configuration at startup for : " + threadPoolKey.name() + " is trying to set coreSize = " +
                    dynamicCoreSize + " and maximumSize = " + dynamicMaximumSize + ".  Maximum size will be set to " +
                    dynamicCoreSize + ", the coreSize value, since it must be equal to or greater than the coreSize value");
            return new ThreadPoolExecutor(dynamicCoreSize, dynamicCoreSize, keepAliveTime.get(), unit, workQueue, threadFactory);
        } else {
            return new ThreadPoolExecutor(dynamicCoreSize, dynamicMaximumSize, keepAliveTime.get(), unit, workQueue, threadFactory);
        }
    }

}
