def meteorRunner = "meteor"
def cypressLabel = "cypress"

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      steps {
        script {
            def tests = [:]
            for (int i = 0; i < 3; i++) {
                tests["${i}"] = {
                  stage("${i}") {
                      sh 'date'
                  }
                }
            }
            parallel tests
        }
      }
    }
  }
}

