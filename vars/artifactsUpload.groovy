def call(String jfrogArtifactory, String imageName) {
  sh "docker login -u gorantla.nagarjuna@slkgroup.com fisdemo1.jfrog.io -p Admin@123"
  sh "docker tag youtube-5 fisdemo1.jfrog.io/demo-fis-docker-docker-local/youtube-5:latest"
  sh "docker push fisdemo1.jfrog.io/demo-fis-docker-docker-local/youtube-5:latest"
}
  
  //withCredentials([usernamePassword(credentialsId: 'artifact_creds', passwordVariable: 'pw', usernameVariable: 'un')]) {
    //sh "docker login -u $un fisdemo1.jfrog.io -p $pw"
    //sh "docker login -u gorantla.nagarjuna@slkgroup.com fisdemo1.jfrog.io -p Admin@123"
    //sh "docker tag ${imageName} ${jfrogArtifactory}/${imageName}:latest"
    //sh "docker push ${jfrogArtifactory}/${imageName}:latest"

  /*withDockerRegistry(credentialsId: 'artifact_creds', url: 'https://fisdemo1.jfrog.io') {
    sh "docker tag ${imageName} ${jfrogArtifactory}/${imageName}:latest"
    sh "docker push ${jfrogArtifactory}/${imageName}:latest"
  }*/
