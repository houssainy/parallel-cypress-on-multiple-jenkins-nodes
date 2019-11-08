pipeline {
    agent none
    tools {nodejs "nodejs"}

    stages {
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            agent { label 'cypress1' }
            steps {
              sh """
                ifconfig
                sh npm i yarn -g
                sh rm -r node_modules/
                sh yarn install
                sh yarn test-parallel
              """     }
          }
          stage('Run B') {
            agent { label 'cypress2' }
            steps {
              sh """
                ifconfig
                sh npm i yarn -g
                sh rm -r node_modules/
                sh yarn install
                sh yarn test-parallel
              """
            }
          }
        }
      }
    }
}

