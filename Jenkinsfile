pipeline {
    agent { docker { image 'node:6.3' } }
    stages {
        stage('Check node installed') {
            steps {
                sh 'node -v'
            }
        }
        stage('Install yarn') {
            steps {
                sh 'npm i yarn -g'
            }
        }
        stage('Install packages') {
            steps {
                sh 'yarn install'
            }
        }
        stage('Run') {
            steps {
                sh 'yarn run'
            }
        }


    }
}

