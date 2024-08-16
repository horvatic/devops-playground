import org.junit.*
import com.lesfurets.jenkins.unit.BasePipelineTest

class TestJenkinsFile extends BasePipelineTest {
    @Override
    @Before
    void setUp() {
        super.setUp()
        helper.registerAllowedMethod('sh', [Map]) { args -> println 'hello' }
        binding.setVariable('env', [WORKSPACE:"./"])
    }
    @Test
    void shouldExecuteWithoutErrors() {
        loadScript('Jenkinsfile')
        assertJobStatusSuccess()
    }
}
