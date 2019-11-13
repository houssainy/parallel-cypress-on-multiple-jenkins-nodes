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
  agent { label "cypress6" } // Agents will be specified in the parallel stages

  stages {
    // First stage is to run db scripts to re-import the test datasets
    stage("Re-import Database") {
      steps {
        sh "echo \$HOME"
      }
    }
  }
}
