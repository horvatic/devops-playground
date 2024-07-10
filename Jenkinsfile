pipeline {
    agent { docker { image 'golang:1.22.5-alpine3.20' } }
    stages {
        stage('build') {
            steps {
                sh 'go version'
            }
        }
    }
}