package cn.gaohaohao.hystrix;

import cn.gaohaohao.hystrix.command.CommandFacadeWithPrimarySecondary;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommandFacadeWithPrimarySecondaryTest {

    @Test
    public void primaryTest(){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            //将属性"primarySecondary.usePrimary"设置为true，则走PrimaryCommand；设置为false，则走SecondaryCommand
            ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", true);
            String s = new CommandFacadeWithPrimarySecondary(20).execute();
            assertEquals("responseFromPrimary-20", s);
        } finally {
            context.shutdown();
            ConfigurationManager.getConfigInstance().clear();
        }
    }

    @Test
    public void secondaryTest(){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            //将属性"primarySecondary.usePrimary"设置为true，则走PrimaryCommand；设置为false，则走SecondaryCommand
            ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", false);
            String s = new CommandFacadeWithPrimarySecondary(20).execute();
            assertEquals("responseFromSecondary-20", s);
        } finally {
            context.shutdown();
            ConfigurationManager.getConfigInstance().clear();
        }
    }
}
