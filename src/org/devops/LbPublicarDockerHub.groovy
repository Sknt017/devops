package org.devops

class LbPublicarDockerHub{
    static void publicarImagen(steps){
        def dockerHubUsername = 'davidruiz212'
        def dockerHubTokenCredentialId = 'tokendockerhub'
        def imageExists = steps.sh(script: "docker images -q ${dockerHubUsername}/termometro",returnStdout: true).trim()

        if(imageExists){
            steps.sh 'echo La imagen ${dockerHubUsername}/termometro ya existe, descartando...'
        }else{
            withCredentials([usernamePassword(credentialsId: dockerHubTokenCredentialId, passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]){
            steps.sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }   
        sh "docker tag termometro ${dockerHubUsername}/termometro"
        sh "docker push ${dockerHubUsername}/termometro"
        }
    }
}
