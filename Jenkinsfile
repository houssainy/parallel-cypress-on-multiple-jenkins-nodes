pipeline {
    agent none
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
      stage('run server') {
        steps {
          sh 'yarn start &'
        }
      }
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            agent {
              label "Stage A"
            }
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            agent {
              label "Stage B"
            }
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

