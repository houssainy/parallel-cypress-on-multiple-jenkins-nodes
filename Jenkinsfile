pipeline {
    agent any
    stages {
        stage('Install yarn') {
            steps {
                sh 'npm i yarn -g'
            }
        }       
        stage('run server') {
          steps {
            sh 'yarn start'
          }
        }
        stage('Parallel stages') {
	  parallel {
            stage('Run A') {
              steps {
                sh 'yarn test-parallel'
              }
            }
	    stage('Run B') {
              steps {
               sh 'yarn test-parallel'
              }
            }
          }
	}

    }
}

