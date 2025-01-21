package org.devops

class LbDeployDocker{
    static void ejecutarContenedor(steps){
        def dockerHubUsername = 'davidruiz212'
        def imageName = 'termometroAPI'
        def containerName = 'termometro-container'
        def containerExists = sh(script: "docker ps -a --filter name=${containerName} --format {{.Names}}",
        returnStdout: true).trim()
        if (containerExists){
            echo "el contenedor ${containerName} ya existe. descartando..."
        } else {
            sh "docker stop ${containerName} || true"
            sh "docker rm ${containerName} || true"
            sh "docker run -d -p 3000:8080 --name ${containerName} ${dockerHubUsername}/${imageName}"
        }
    }
}