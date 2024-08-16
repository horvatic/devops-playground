node {
    docker.image('horvatic/fbuild').inside {
        stage('checkout') {
            checkout scm
        }
        stage('groovy test') {
            def hello = load("${env.WORKSPACE}/hello.groovy")
            println hello.getHello()
        }
        stage('build') {
            sh 'fbuild'
            stash includes: '**/main-linux-release', name: 'app'
            stash includes: '**/build/linux/release/*.so', name: 'dll' 
        }
        stage ('deploy') {
            def util = load("${env.WORKSPACE}/deploy.groovy")
            util.deploy()
        }
    }
}