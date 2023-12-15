@Library('Jenkins_shared_library') _
def COLOR_MAP = [
    'FAILURE' : 'danger',
    'SUCCESS' : 'good'
]
pipeline {
  agent any
  stages {
    stage ('clean workspace') {
      steps {
        cleanWorkspace()
      }
    }
    stage ('source code checkout') {
        steps { 
           checkoutGit('https://github.com/nagarjunacse02/hello-world.git', 'master') 
        }
    }
  }
}
        
