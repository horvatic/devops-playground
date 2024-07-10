pipeline {
    agent { docker { image 'horvatic/fbuild' } }
    stages {
        stage('build') {
            steps {
                sh 'fbuild'
                stash includes: '**/Out/HelloWorld.exe', name: 'app' 
            }
        }
        stage ('deploy') {
            steps{
                sshagent(credentials : ['cprod-login']) {
                    unstash 'app'
                    sh '''
                    [ -d ~/.ssh ] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                    ssh-keyscan -t rsa,dsa cprod.libvirt >> ~/.ssh/known_hosts
                    ssh -v produser@cprod.libvirt
                    scp HelloWorld.exe produser@cprod.libvirt:/home/produser/HelloWorld.exe
                    '''
                }
            }
        }
    }
}