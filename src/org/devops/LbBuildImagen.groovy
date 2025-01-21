package org.devops

class LbBuildImagen {
    static void construirImagen(steps) {
        steps.sh 'echo Construyendo artefacto...'
        // Lógica de construcción del artefacto aquí
        def imageExists = steps.sh(script : "docker images -q termometroAPI",returnStdout: true).trim()
        if (imageExists){
            steps.echo "La imagen ya existe... descartando construccion..."
        }else{
            steps.sh 'docker build -t termometroAPI -f Dockerfile .'
            }
//        steps.sh command
//        return false
    }
}
