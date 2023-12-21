def call(String jfrogArtifactory, String imageName) { 
  
  //withCredentials([usernamePassword(credentialsId: 'artifact_creds', passwordVariable: 'pw', usernameVariable: 'un')]) {
    //sh "docker login -u $un fisdemo1.jfrog.io -p $pw"
    sh "docker login -u gorantla.nagarjuna@slkgroup.com fisdemo1.jfrog.io -p Admin@123"
    sh "docker tag ${imageName} ${jfrogArtifactory}/${imageName}:latest"
    sh "docker push ${jfrogArtifactory}/${imageName}:latest"
  }
  /*withDockerRegistry(credentialsId: 'artifact_creds', url: 'https://fisdemo1.jfrog.io') {
    sh "docker tag ${imageName} ${jfrogArtifactory}/${imageName}:latest"
    sh "docker push ${jfrogArtifactory}/${imageName}:latest"
  }*/
 
}
