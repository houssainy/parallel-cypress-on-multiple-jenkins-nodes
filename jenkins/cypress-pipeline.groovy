/**
 * A script to define a parallel cypress test.
 * This script is designed to be used in pipelineJob and to be imported
 * using scriptPath directive in seed job definition.
 *  @see https://jenkinsci.github.io/job-dsl-plugin/#path/pipelineJob-definition-cpsScm-scriptPath
 */
def meteorRunner = 'meteor'

evaluate(new File("./helpers/install-yarn.groovy"))

println(answer)
println(installYarn)

cypressLabel = "cypress"
// TODO(@houssainy) numberOfInstances should be dynamically fetched using AWS APIs
numberOfInstances = 3

// Declirative Pipeline Script
pipeline {
  agent none // Agents will be specified in the parallel stages

  stages {
    // First stage is to run db scripts to re-import the test datasets
    stage("Re-import Database") {
      steps {
        script {
          parallel getBuildStages()
        }
      }
    }
    // Second stage is to run cypress tests using --parallel
    stage("Run Cypress tests") {
      steps {
        script {
          parallel getCypressTestStages()
        }
      }
    }
  }
}

//
// Helper functions
//

/**
 * return n stages directives where n is defined by
 * `numberOfInstances` variable.
 *
 * Each stage run db reimport scripts on cyprss${i} node.
 */
def getBuildStages() {
  def buildStages = [:]
  for (int i = 1; i <= numberOfInstances; i++) {
    int currentIndex = i

    buildStages["Agent - ${currentIndex}"] = {
      node("${cypressLabel}${currentIndex}") {
        stage("Agent - ${currentIndex}") {
          checkout scm
          sh "cd \${WORKSPACE}"
          sh """
            meteor npm install -g yarn

            meteor yarn install || error=true

            #Fail the build if there was an error
            if [ \$error ]
            then
              rm -f yarn.lock
              meteor yarn install --skip-integrity-check
            fi
          """
          sh "export PATHABLE_NEXT_HOME=\${WORKSPACE}"
          sh "./scripts/db/reimport.sh staging test-data datasets"
          sh "./scripts/db/fix-community-domains.sh staging"
        }
      }
    }
  }

  return buildStages
}

/**
 * return n stages directives where n is defined by
 * `numberOfInstances` variable.
 *
 * Each stage installs the dependencies in jenkins workspace
 * then it runs the cypress test using --parallel flag.
 *
 * Nodes selection is done by Jenkins where all the stages
 * are just targeting the nodes with label `cypress` only.
 */
def getCypressTestStages() {
  def cypressStages = [:]
  for (int i = 1; i <= numberOfInstances; i++) {
    int currentIndex = i

    cypressStages["Agent - ${currentIndex}"] = {
      node("${cypressLabel}") {
        stage("Agent - ${currentIndex}") {
          sh "meteor yarn cypress:run --parallel"
        }
      }
    }
  }

  return cypressStages
}
