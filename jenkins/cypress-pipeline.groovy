def meteorRunner = "meteor"
def cypressLabel = "cypress"

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      parallel {

        for (i = 0; i <3; i++) {
          stage("Agent - ${i}") {
            agent { label cypressLabel }
            steps {
              sh "cd \${WORKSPACE}"
              sh "export PATHABLE_NEXT_HOME=\${WORKSPACE}"
              sh "./scripts/db/reimport.sh staging test-data datasets"
              sh "./scripts/db/fix-community-domains.sh staging"
              sh "${meteorRunner} yarn cypress:run"
            }
          }
        }
      }
    }
  }
}

