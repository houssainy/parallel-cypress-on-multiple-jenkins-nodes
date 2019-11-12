def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3

node("master") {
  stage('Build') {
    sh "ifconfig"
    git 'git@github.com:houssainy/parallel-cypress-on-multiple-jenkins-nodes.git'
    sh "meteor yarn install"
  }
}

node {
  stage('Run') {
    doDynamicParallelSteps()
  }
}

// def doBuildParallelSteps() {
//   def tests = [:]
//   for (int i = 1; i <= 3; i++) {
//     String x = i

//     tests["stage ${x}"] = {
//       stage("stage ${x}") {
//         sh 'ifconfig'

//       }
//     }
//   }
//   parallel tests
// }

def doDynamicParallelSteps(){
  def tests = [:]
  for (int i = 1; i <= 3; i++) {
    String x = i

    tests["stage ${x}"] = {
      node("cypress${x}") {
        stage("stage ${x}") {
          sh 'ifconfig'
          sh "meteor yarn test-parallel"
        }
      }
    }
  }
  parallel tests
}
