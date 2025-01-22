package org.devops

class LbDeployDocker{
    static void ejecutarContenedor(steps){
        def dockerHubUsername = 'davidruiz212'
        def imageName = 'crudspringboot-buildimagen'
        def containerName = 'crudspringboot-buildimagen-container'
        def containerExists = steps.sh(script: "docker ps -a --filter name=${containerName} --format {{.Names}}",
        returnStdout: true).trim()
        if (containerExists){
            steps.sh "echo el contenedor ${containerName} ya existe. descartando..."
        } else {
            steps.sh "docker stop ${containerName} || true"
            steps.sh "docker rm ${containerName} || true"
            steps.sh "docker run -d -p 3000:8080 --name ${containerName} ${dockerHubUsername}/${imageName}"
        }
    }
}