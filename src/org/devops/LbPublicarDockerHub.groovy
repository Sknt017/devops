package org.devops

class LbPublicarDockerHub{
    static void publicarImagen(steps){
        def dockerHubUsername = 'davidruiz212'        
        def imageExists = steps.sh(script: "docker images -q ${dockerHubUsername}/crudspringboot-buildimagen",returnStdout: true).trim()
        if(imageExists){
            steps.sh 'echo La imagen ${dockerHubUsername}/crudspringboot-buildimagen ya existe, descartando...'
        }else{               
        steps.sh "docker tag crudspringboot-buildimagen ${dockerHubUsername}/crudspringboot-buildimagen"
        steps.sh "docker push ${dockerHubUsername}/crudspringboot-buildimagen"
        }
    }
}
