package org.devops

class LbBuildImagen {
    static void construirImagen(steps) {
        steps.sh 'echo Construyendo artefacto...'
        // Lógica de construcción del artefacto aquí
        def imageExists = steps.sh(script : "docker images -q crudspringboot-buildimagen",returnStdout: true).trim()
        if (imageExists){
            steps.sh "echo La imagen ya existe... descartando construccion..."
        }else{
            steps.sh 'docker build -t crudspringboot-buildimagen -f Dockerfile .'
            }
//        steps.sh command
//        return false
    }
}
