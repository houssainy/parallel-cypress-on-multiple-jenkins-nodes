/**
 * A script to define a parallel cypress test.
 * This script is designed to be used in pipelineJob and to be imported
 * using scriptPath directive in seed job definition.
 *  @see https://jenkinsci.github.io/job-dsl-plugin/#path/pipelineJob-definition-cpsScm-scriptPath
 */
def meteorRunner = 'meteor'

cypressLabel = "cypress"
// TODO(@houssainy) numberOfInstances should be dynamically fetched using AWS APIs
numberOfInstances = 3

// Declirative Pipeline Script
pipeline {
  agent {label "cypress3"} // Agents will be specified in the parallel stages

  stages {
    // First stage is to run db scripts to re-import the test datasets
    stage("Re-import Database") {
      steps {
        sh "echo \$HOME"
        sh "meteor yarn install"
        sh "yarn test-parallel"
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
      node("master") {
        stage("Agent - ${currentIndex}") {

          sh "echo ${answer}"
          sh "echo ${installYarn}"
          sh "${installYarn}"
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
