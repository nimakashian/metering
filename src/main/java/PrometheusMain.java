import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;


public class PrometheusMain {

    private static double rand(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    public static void main(String[] args) {
        Counter counter = Counter.build().namespace("mynamespace").name("myname").help("This is my counter").register();
        Counter counter2 = Counter.build().name("a_b_c_d").help("help").register();
//        Gauge gauge = Gauge.build().namespace("mynamespace").name("myname").labelNames("mylabel").help("This is my gauge").register();
//        Histogram histogram = Histogram.build().namespace("java").name("my_histogram").help("This is my histogram").register();
//        Summary summary = Summary.build().namespace("java").name("my_summary").help("This is my summary").register();

        Thread bgThread = new Thread(() -> {
            while (true) {
                try {
                    counter.inc(rand(0, 5));
//                    gauge.set(rand(-5, 10));
//                    histogram.observe(rand(0, 5));
//                    summary.observe(rand(0, 5));


                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bgThread.start();

        try {

            HTTPServer server = new HTTPServer(9999);
            System.out.println("app started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
