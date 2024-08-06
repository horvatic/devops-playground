pipeline {
    agent { docker { image 'horvatic/fbuild' } }
    stages {
        stage('build') {
            steps {
                sh 'fbuild'
                stash includes: '**/main-linux-release', name: 'app'
                stash includes: '**/build/linux/release/libhello.so', name: 'libhello' 
                stash includes: '**/build/linux/release/libgoodbye.so', name: 'libgoodbye' 
            }
        }
        stage ('deploy') {
            steps{
                sshagent(credentials : ['cprod-login']) {
                    unstash 'app'
                    unstash 'libhello'
                    unstash 'libgoodbye'
                    sh '''
                    [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                    ssh-keyscan -t ed25519 cprod.libvirt >> ~/.ssh/known_hosts
                    ssh produser@cprod.libvirt "mkdir -p /home/produser/bin/"
                    scp main-linux-release produser@cprod.libvirt:/home/produser/
                    scp build/linux/release/libhello.so produser@cprod.libvirt:/home/produser/bin/
                    scp build/linux/release/libgoodbye.so produser@cprod.libvirt:/home/produser/bin/
                    '''
                }
            }
        }
    }
}
