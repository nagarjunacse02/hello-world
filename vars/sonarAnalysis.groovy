def call() {
  withSonarQubeEnv('sonar-server') {
        sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.host.url=http://172.203.233.93:9000 -Dsonar.projectName=favPlayList -Dsonar.projectKey=favPlayList -Dsonar.token=sqa_afd1f2a998809c2a3506faa46cedcb8ef1fe06d6 '''
    }
}
