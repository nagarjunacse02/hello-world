def call(String dockerHubUsername, String imageName) { 
  docker login -u 'gorantla.nagarjuna@slkgroup.com' fisdemo1.jfrog.io -p 'Admin@123'
  docker tag ${imageName} 'fisdemo1.jfrog.io/demo-fis-docker-docker-local/${imageName}:latest'
  docker push 'fisdemo1.jfrog.io/demo-fis-docker-docker-local/${imageName}:latest'
}
