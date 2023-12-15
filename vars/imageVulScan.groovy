def call() {
  // Scan all vuln levels
                sh 'curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3'
                sh 'chmod 700 get_helm.sh'
                sh './get_helm.sh'
                sh 'helm repo add aquasecurity https://aquasecurity.github.io/helm-charts/'
                sh 'helm repo update'
                sh 'helm search repo trivy'
                //sh 'helm install my-trivy aquasecurity/trivy'
               sh 'trivy -h'
               sh 'mkdir -p reports'
               sh 'sudo apt-get -y install tree'
               sh 'trivy image nagarjunacse02/test-image.${BUILD_ID}  --output report.html || true'
               sh 'tree'
               sh 'ls -lrth'
              // publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'reports', reportFiles: 'index.html', reportName: 'Trivy Scan', reportTitles: 'Trivy Scan', useWrapperFileDirectly: true])
}
