package br.com.vnrg.ppauthserver;

import br.com.vnrg.ppauthserver.entities.Customer;
import br.com.vnrg.ppauthserver.entities.Pricing;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan("br.com.vnrg")
public class AuthServerApplication implements CommandLineRunner {

    private final RedisTemplate<String, String> template;
    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (this.process()) ;
    }

    @SneakyThrows
    private boolean process() {
        try {
            long t = System.currentTimeMillis();
            var mapper = new ObjectMapper();
            Customer c = new Customer();
            Pricing p = new Pricing();
            for (int i = 0; i < 100000; i++) {
                p.setPricingType("MB");
                p.setAsset("B3SA3");
                p.setValue(BigDecimal.valueOf(Math.random()).setScale(5, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).abs());
                c.setId("client-id-" + i);
                c.setPricing(p);
                // c.setTimestamp(Instant.now());
                // elasticsearchRestTemplate.save(c);
                // Customer search = this.elasticsearchRestTemplate.get(c.getId(), Customer.class);
                template.opsForValue().set(c.getId(), mapper.writeValueAsString(c));
            }
            log.info("cached loaded in (ms) {} ", (System.currentTimeMillis() - t));

            var o = mapper.readValue(this.template.opsForValue().get("client-id-1"), Customer.class);

            return false;

        } catch (Exception e) {
            Thread.sleep(30_000);
            log.error("error persisting cache", e);
            return true;
        }
    }
}
