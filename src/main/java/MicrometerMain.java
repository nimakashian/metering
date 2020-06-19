import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.util.concurrent.TimeUnit;

public class MicrometerMain {

    public static void main(String[] args) {

        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);



        Counter compositeCounter = prometheusRegistry.counter("counter");
        Timer compositeTimer = prometheusRegistry.timer("gauge");
        compositeTimer.record(10000, TimeUnit.MILLISECONDS);
        compositeCounter.increment();

        String response = prometheusRegistry.scrape();
        System.out.println(response);

        System.out.println("app started");
    }
}
