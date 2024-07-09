pipeline {
    agent { docker { image 'horvatic/fbuild' } }
    stages {
        stage('build') {
            steps {
                sh 'fbuild'
                stash includes: '**/main-linux-release', name: 'app'
                stash includes: '**/build/**', name: 'dll' 
            }
        }
        stage ('deploy') {
            steps{
                sshagent(credentials : ['cprod-login']) {
                    unstash 'app'
                    unstash 'dll'
                    sh '''
                    [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                    ssh-keyscan -t ed25519 cprod.libvirt >> ~/.ssh/known_hosts
                    ssh produser@cprod.libvirt
                    scp main-linux-release produser@cprod.libvirt:/home/produser/
                    scp -r build produser@cprod.libvirt:/home/produser/build
                    '''
                }
            }
        }
    }
}
