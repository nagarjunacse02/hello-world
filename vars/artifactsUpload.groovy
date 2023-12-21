def call(String dockerHubUsername, String imageName) { 
  rtUpload (
                serverId: 'fisdemo',
                spec: '''{
                      "files": [
                        {
                          "pattern": "*${dockerHubUsername}/${imageName}",
                           "target": "fis-demo-dockerhub-docker/"
                        }
                    ]
                }'''
            )
}
