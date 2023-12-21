def call(String jfrogArtifactory, String imageName) { 
  sh "docker login -u gorantla.nagarjuna@slkgroup.com fisdemo1.jfrog.io -p Admin@123"
  sh "docker tag ${imageName} ${jfrogArtifactory}/${imageName}:latest"
  sh "docker push fisdemo1.jfrog.io/demo-fis-docker-docker-local/${imageName}:latest"
}
