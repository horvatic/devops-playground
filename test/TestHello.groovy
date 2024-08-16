import org.junit.*
import com.lesfurets.jenkins.unit.BasePipelineTest

class TestHelloJob extends BasePipelineTest {
    @Override
    @Before
    void setUp() {
        super.setUp()
        helper.registerAllowedMethod('deploy', []) { args -> println 'hello' }
        helper.registerAllowedMethod('sh', [Map]) { args -> println 'hello' }
        binding.setVariable('env', [WORKSPACE:"./"])
    }
    @Test
    void shouldExecuteWithoutErrors() {
        loadScript('Jenkinsfile').execute()
        printCallStack()
        assertJobStatusSuccess()
    }
}
