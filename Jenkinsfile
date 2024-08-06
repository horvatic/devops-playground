pipeline {
    agent { docker { image 'horvatic/fbuild' } }
    stages {
        stage('build') {
            steps {
                sh 'fbuild'
                stash includes: '**/main-linux-release', name: 'app'
                stash includes: '**/build/linux/release/*.so', name: 'dll' 
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
                    ssh produser@cprod.libvirt "mkdir -p /home/produser/bin/"
                    scp main-linux-release produser@cprod.libvirt:/home/produser/
                    scp build/linux/release/* produser@cprod.libvirt:/home/produser/bin/
                    '''
                }
            }
        }
    }
}
