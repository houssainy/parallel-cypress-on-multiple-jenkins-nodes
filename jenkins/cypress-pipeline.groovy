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
                npm i yarn -g
                rm -r node_modules/
                yarn install
                yarn test-parallel
              """     }
          }
          stage('Run B') {
            agent { label 'cypress2' }
            steps {
              sh """
                ifconfig
                npm i yarn -g
                rm -r node_modules/
                yarn install
                yarn test-parallel
              """
            }
          }
        }
      }
    }
}

