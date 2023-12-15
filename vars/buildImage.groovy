def call() {
  sh 'docker build -t nagarjunacse02/test-image.${BUILD_ID} .'
  sh 'docker images'
}
