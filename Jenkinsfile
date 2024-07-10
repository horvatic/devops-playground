pipeline {
    agent { docker { image 'horvatic/fbuild' } }
    stages {
        stage('build') {
            steps {
                sh 'fbuild'
                sh './Out/HelloWorld.exe'
            }
        }
    }
}