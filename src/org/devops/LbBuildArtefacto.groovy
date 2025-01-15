package org.devops

class LbBuildArtefacto {
    static void construirArtefacto(steps, String command) {
        //steps.sh 'echo Construyendo artefacto...'
        // Lógica de construcción del artefacto aquí
        steps.sh command
    }
}
