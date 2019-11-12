pipelineJob('jenkins-node-cypress-test') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('git@github.com:houssainy/parallel-cypress-on-multiple-jenkins-nodes.git')
          }
          branch('*/master')
        }
      }
      lightweight()
      scriptPath('./jenkins/cypress-pipeline.groovy')
    }
  }
}
