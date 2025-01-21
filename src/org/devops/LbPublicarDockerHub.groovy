package org.devops

class LbPublicarDockerHub{
    static void publicarImagen(steps){
        def dockerHubUsername = 'davidruiz212'
        def dockerHubTokenCredentialId = 'tokendockerhub'
        def imageExists = sh(script: "docker images -q ${dockerHubUsername}/termometroAPI",returnStdout: true).trim()
        if(imageExists){
            echo 'La imagen ${dockerHubUsername}/termometroAPI ya existe, descartando...'
        }else{
            withCredentials([usernamePassword(credentialsId: dockerHubTokenCredentialId, passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]){
            sh "docker login --username ${env.DOCKERHUB_USERNAME} --password ${env.DOCKERHUB_PASSWORD}"
        }   
        sh "docker tag termometroAPI ${dockerHubUsername}/termometroAPI"
        sh "docker push ${dockerHubUsername}/termometroAPI"
        }
    }
}
