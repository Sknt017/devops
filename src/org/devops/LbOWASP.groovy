package org.devops

class LbOWASP {
    static String analizarProyecto(steps) {
        //Descarga de OWASP ZAProxy
        def networkName = 'jenkinsOwasp'
        def imageName = 'zaproxy/zap-stable'
        def zapContainerName = 'owaspimagen-container'
        def imageExists = steps.sh(script: "docker images -q $imageName",returnStdout: true ).trim()
        if(imageExists.isEmpty()){
            steps.sh "docker pull $imageName"
        } else {
            steps.sh "echo La imagen ${imageName} ya existe. descartando descarga..."
        }
        steps.sh "docker stop ${zapContainerName} || true"
        steps.sh "docker rm ${zapContainerName} || true "
        //steps.sh "docker run -d --name ${zapContainerName} --network=${networkName} ${imageName}"
        def dockerHubUsername = 'davidruiz212'
        def crudspringbootImageName = 'termometro'
        def crudspringbootContainerName = 'termometro-container'
        //custom port config
        def crudspringbootContainerNamePort = "8081"
        steps.sh "docker stop ${crudspringbootContainerName} || true"
        steps.sh "docker rm ${crudspringbootContainerName} || true"
        steps.sh "docker run -d --name ${crudspringbootContainerName} --network=${networkName} -p ${crudspringbootContainerNamePort}:${crudspringbootContainerNamePort} --user root ${dockerHubUsername}/${crudspringbootImageName}"
        def targetURL = "http://${steps.env.iphost}:${crudspringbootContainerNamePort}"
        steps.sh "docker run --rm -v fullscancrudspringboot:/zap/wrk/:rw --user root --name ${zapContainerName} --network=${networkName} -t $imageName zap-full-scan.py -t ${targetURL} -r fullScanReport.html -I"
        steps.sh "docker stop ${zapContainerName}"
        steps.sh "docker rm ${zapContainerName}"
        steps.sh "docker stop ${crudspringbootContainerName}"
        steps.sh "docker rm ${crudspringbootContainerName}"
    }
}
