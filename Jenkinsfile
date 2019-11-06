pipeline {
    agent any
    stages {
        stage('Install yarn') {
            steps {
                sh 'npm i yarn -g'
            }
        }
        stage('Install packages') {
            steps {
		sh 'rm -rf node_modules/'
                sh 'yarn install'
            }
        }
        
        stage('Run') {
          parallel {
            steps {
                sh 'yarn test'
            }
          }
	}

    }
}

