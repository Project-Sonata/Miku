package testing.spring.config;


import com.odeyalo.sonata.miku.support.converter.TrackDtoConverter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackageClasses = TrackDtoConverter.class)
public class MapStructBeansBootstrapConfiguration {
}
