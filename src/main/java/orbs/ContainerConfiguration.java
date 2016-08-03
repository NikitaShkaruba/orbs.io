package orbs;

import orbs.models.beans.World;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class ContainerConfiguration {
    @Async
    @Scheduled(fixedRate=5000)
    public void timeTick() {
        world().timeTick();
    }

    @Bean
    @Scope(value = "singleton")
    public World world() {
        return new World();
    }
}
