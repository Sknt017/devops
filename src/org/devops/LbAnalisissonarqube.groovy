package org.devops

class LbAnalisissonarqube {
    static void analizarCodigo(steps, Map parametros = [:]) {
        steps.sh 'echo Analizando código con SonarQube...'
        def scannerHome = tool 'SonarqubeScanner'
        withSonarQubeEnv('ServerSonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=analisisTermometro \
                            -Dsonar.projectName=analisisTermometro \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }

        // Lógica de análisis de SonarQube aquí
    }
}
