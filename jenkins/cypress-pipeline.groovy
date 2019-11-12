def meteorRunner = "meteor"
def cypressLabel = "cypress"

def stageClousre = {
  stage("stage ${it}") {
    steps {
      echo "${it}"
      sh 'date'
    }
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
            tests["kokokokokok ${i}"] = stageClousre(i)
          }

          parallel tests
        }
      }
    }
  }
}

