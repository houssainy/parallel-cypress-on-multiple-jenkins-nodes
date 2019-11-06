pipeline {
    agent any
    stages {
        stage('Install yarn') {
            steps {
                sh 'npm i yarn -g'
            }
        }       
        stage('Parallel stages') {
          steps {
            sh 'yarn start'
          }  
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

