package org.devops

class LbPublicarDockerHub{
    static void publicarImagen(steps, String DOCKERHUB_USER, String DOCKERHUB_PASS){
        //def DOCKERHUB_PASS = DOCKERHUB_PASS
        //def DOCKERHUB_USER = DOCKERHUB_USER
        def dockerHubUsername = 'davidruiz212'
        def dockerHubTokenCredentialId = 'tokendockerhub'
        def imageExists = steps.sh(script: "docker images -q ${dockerHubUsername}/termometro",returnStdout: true).trim()

        if(imageExists){
            steps.sh 'echo La imagen ${dockerHubUsername}/termometro ya existe, descartando...'
        }else{
            steps.withCredentials([usernamePassword(credentialsId: dockerHubTokenCredentialId, passwordVariable: 'DOCKERHUB_PASS', usernameVariable: 'DOCKERHUB_USER')]){
            steps.sh "docker login --username ${DOCKERHUB_USER} --password ${DOCKERHUB_PASS}"
        }   
        steps.sh "docker tag termometro ${dockerHubUsername}/termometro"
        steps.sh "docker push ${dockerHubUsername}/termometro"
        }
    }
}
