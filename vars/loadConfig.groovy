def call() {
    return this
}

def loadNotificationConfigFromYaml() {
    def yamlContent = readYaml file: "${WORKSPACE}/config.yaml"
    return yamlContent.notificationConfig ?: [:]
}
