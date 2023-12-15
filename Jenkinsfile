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
  }
}
        
