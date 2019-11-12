def meteorRunner = "meteor"
def cypressLabel = "cypress"

def stageClousre = {
  stage("${it}") {
    sh 'date'
  }
}

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      agent { label cypressLabel }

      steps {
        script {
          def tests = [:]
          for (int i = 0; i < 3; i++) {
            tests["${i}"] = stageClousre(i)
          }

          parallel tests
        }
      }
    }
  }
}

