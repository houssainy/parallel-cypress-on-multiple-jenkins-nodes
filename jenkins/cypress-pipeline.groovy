def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3


node {
  stage('Run') {
    doDynamicParallelSteps()
  }
}

node("master") {
  stage('Build') {
        sh 'ifconfig'
  }
}

def doDynamicParallelSteps(){
  def tests = [:]
  for (int i = 1; i <= 3; i++) {
    String x = i

    tests["stage ${x}"] = {
      node("cypress${x}") {
        stage("stage ${x}") {
          sh 'ifconfig'
          echo "${x}"
        }
      }
    }
  }
  parallel tests
}
