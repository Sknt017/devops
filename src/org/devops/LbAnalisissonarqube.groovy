package org.devops

class LbAnalisissonarqube {
    static void analizarCodigo(steps, Map parametros = [:]) {
        steps.sh 'echo Analizando código con SonarQube...'
        //withSonarQubeEnv('ServerSonarqube') {
                        sonar =  "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=analisisTermometro \
                            -Dsonar.projectName=analisisTermometro \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml".execute().text
        //            }

        // Lógica de análisis de SonarQube aquí
    }
}
