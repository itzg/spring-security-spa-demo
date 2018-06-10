package me.itzg.spalogindemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "github-client-id=ID",
        "github-client-secret=SECRET"
})
public class SpaDemoAppTests {

    @Test
    public void contextLoads() {
    }

}
