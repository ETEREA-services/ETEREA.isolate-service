package eterea.isolate.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.cloud.consul.enabled=false",
    "spring.cloud.discovery.enabled=false"
})
class IsolateServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
