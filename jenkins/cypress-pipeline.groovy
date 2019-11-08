def meteorRunner = "meteor"
def cypressLabel = "cypress"

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      parallel {
        stage("Agent - 1") {
          agent { label cypressLabel }
          steps {
            sh "cd \${WORKSPACE}"
            sh "export PATHABLE_NEXT_HOME=\${WORKSPACE}"
            sh "./scripts/db/reimport.sh staging test-data datasets"
            sh "./scripts/db/fix-community-domains.sh staging"
            sh "${meteorRunner} yarn cypress:run"
          }
        }
        stage("Agent - 2") {
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

