node {
    docker.image('horvatic/fbuild').inside {
        stage('groovy test') {
            def util = load("${env.WORKSPACE}/test.groovy")
            util.testHello()
        }
        stage('build') {
            sh 'fbuild'
            stash includes: '**/main-linux-release', name: 'app'
            stash includes: '**/build/linux/release/*.so', name: 'dll' 
        }
        stage ('deploy') {
            sshagent(credentials : ['cprod-login']) {
                unstash 'app'
                unstash 'dll'
                sh '''
                [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                ssh-keyscan -t ed25519 cprod.libvirt >> ~/.ssh/known_hosts
                ssh produser@cprod.libvirt "mkdir -p /home/produser/bin/"
                scp main-linux-release produser@cprod.libvirt:/home/produser/
                scp build/linux/release/*.so produser@cprod.libvirt:/home/produser/bin/
                '''
            }
        }
    }
}
