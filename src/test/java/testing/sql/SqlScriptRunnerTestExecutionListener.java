package testing.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * {@link AbstractTestExecutionListener} impl that run the Sql queries using .
 * {@link DatabaseClient}, unfortunately methods are blocking.
 *
 */
@Component
public class SqlScriptRunnerTestExecutionListener
        extends AbstractTestExecutionListener {

    @Autowired
    DatabaseClient databaseClient;

    private final Logger logger = LoggerFactory.getLogger(SqlScriptRunnerTestExecutionListener.class);

    @Override
    public void beforeTestClass(TestContext testContext) {
        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        SqlScript sqlScriptMetadata = testContext.getTestMethod().getAnnotation(SqlScript.class);

        if (sqlScriptMetadata == null) {
            logger.info("Method does not contain SqkScript annotation. Skipped");
            return;
        }

        String[] locations = sqlScriptMetadata.beforeTestExecutionLocations();

        executeSqlScripts(locations);
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        SqlScript sqlScriptMetadata = testContext.getTestMethod().getAnnotation(SqlScript.class);

        if (sqlScriptMetadata == null) {
            logger.info("Method does not contain SqkScript annotation. Skipped");
            return;
        }

        String[] locations = sqlScriptMetadata.afterTestExecutionLocations();

        executeSqlScripts(locations);
    }

    private void executeSqlScripts(String[] locations) {
        Flux.fromArray(locations)
                .doOnNext(location -> logger.info("Executing sql script from the given location: {}", location))
                .map(SqlScriptRunnerTestExecutionListener::resolveSqlScript)
                .flatMap(script -> databaseClient.sql(script).then())
                .collectList()
                .block();
    }

    private static String resolveSqlScript(String location) {
        ClassPathResource resource = new ClassPathResource(location);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
