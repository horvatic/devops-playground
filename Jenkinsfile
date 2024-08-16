node {
    docker.image('horvatic/fbuild').inside {
        stage('groovy test') {
            def hello = load("${env.WORKSPACE}/hello.groovy")
            hello.testHello()
        }
        stage('build') {
            sh 'fbuild'
            stash includes: '**/main-linux-release', name: 'app'
            stash includes: '**/build/linux/release/*.so', name: 'dll' 
        }
        stage ('deploy') {
            def util = load("${env.WORKSPACE}/deploy.groovy")
            util.deploy()
            deploy()
        }
    }
}