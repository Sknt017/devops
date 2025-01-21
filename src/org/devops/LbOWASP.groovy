package org.devops

class LbOWASP {
    static String analizarProyecto(steps) {
        def networkName = 'jenkinsOwasp'
        def imageName = 'zaproxy/zap-stable'
        def zapContainerName = 'owaspimagen-container'
        def imageExists = sh(returnStdout: true, script: "docker images -q $imageName" ).trim()
        if(imageExists.isEmpty()){
            sh "docker pull $imageName"
        } else {
            echo "La imagen ${imageName} ya existe. descartando descarga..."
        }
        sh "docker stop ${zapContainerName} || true"
        sh "docker rm ${zapContainerName} || true "
        sh "docker run -d --name ${zapContainerName} --network=${networkName} ${imageName}"
        def dockerHubUsername = 'davidruiz212'
        def crudspringbootImageName = 'termometroAPI'
        def crudspringbootContainerName = 'termometro-container'
        sh "docker stop ${crudspringbootContainerName} || true"
        sh "docker rm ${crudspringbootContainerName} || true"
        sh "docker run -d --name ${crudspringbootContainerName} --network=${networkName} -p 3500:8080 --user root ${dockerHubUsername}/${crudspringbootImageName}"
        def targetURL = "http://${steps.env.iphost}:3500"
        sh "docker run --rm -v fullscancrudspringboot:/zap/wrk/:rw --user root --network=${networkName} -t $imageName zap-full-scan.py -t ${targetURL} -r fullScanReport.html -I"
        sh "docker stop ${zapContainerName}"
        sh "docker rm ${zapContainerName}"
        sh "docker stop ${crudspringbootContainerName}"
        sh "docker rm ${crudspringbootContainerName}"
    }
}
