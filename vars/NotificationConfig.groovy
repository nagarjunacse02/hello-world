def sendTeamsNotification(webhookUrl, message) {
    // Send a notification to Microsoft Teams using webhookUrl and message
    teams([webhookUrl: {TEAM_A_TEAMS_WEBHOOK} , message: message])
}
