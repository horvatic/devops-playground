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
                    sh 'ssh -o StrictHostKeyChecking=no produser@cprod.libvirt uptime'
                    sh 'ssh -v produser@cprod.libvirt'
                    sh 'scp HelloWorld.exe produser@cprod.libvirt:/home/produser/HelloWorld.exe'
                }
            }
        }
    }
}