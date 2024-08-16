import org.junit.*
import static org.junit.Assert.*
import com.lesfurets.jenkins.unit.BasePipelineTest

class TestHello extends BasePipelineTest {
    @Override
    @Before
    void setUp() {
        super.setUp()
    }
    @Test
    void shouldExecuteWithoutErrors() {
        def result = loadScript('scripts/hello.groovy').getHello()
        assertTrue("Results was not Hello World, results where ${result}", "Hello World" == result)
    }
}
