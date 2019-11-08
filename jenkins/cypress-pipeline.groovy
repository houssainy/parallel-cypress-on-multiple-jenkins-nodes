def meteorRunner = "meteor"
def cypressLabel = "cypress"


def map = [
        Bob  : 42,
        Alice: 54,
        Max  : 33
]

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      parallel {
        node {
            map.each { entry ->
                stage (entry.key) {
                    timestamps{
                        echo "$entry.value"
                    }
                }
            }
        }
      }
    }
  }
}

