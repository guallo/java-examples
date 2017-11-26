package mypackage;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.naming.NameNotFoundException;

import org.openqa.selenium.support.ui.FluentWait;


public class Main {
    private static Integer counter = 0;
    
    public static void main(final String[] args) throws NameNotFoundException {
        String methodName = args[0];
        String[] specificArgs = Arrays.copyOfRange(args, 1, args.length);
        
        if (methodName.equals("fluentWait")) {
            Main.fluentWait(specificArgs);
        }
        else {
            throw new NameNotFoundException("invalid method name: " + methodName);
        }
    }
    
    public static void fluentWait(final String[] args) {
        double customTarget = Double.parseDouble(args[0]);
        long customTimeout = Long.parseLong(args[1]);
        long customPollingEvery = Long.parseLong(args[2]);
        
        FluentWait<Double> wait = new FluentWait<>(customTarget)
            .withTimeout(customTimeout, TimeUnit.SECONDS)
            .pollingEvery(customPollingEvery, TimeUnit.SECONDS)
            .ignoring(Throwable.class);
        
        Integer finalCounter = wait.until((final Double target) -> {
            Main.counter++;
            System.out.println("testing: " + Main.counter + " < " + target);
            return Main.counter < target ? null : Main.counter;
        });
        
        System.out.println("final counter: " + finalCounter);
    }
}
