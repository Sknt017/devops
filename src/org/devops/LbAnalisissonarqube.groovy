package org.devops

class LbAnalisissonarqube {
    static String analizarCodigo(Map parametros) {
        //steps.sh 'echo Analizando código con SonarQube...'
        //steps.sh 
            return "${parametros.sonarTool}/bin/sonar-scanner \
            -Dsonar.projectKey=analisisTermometro \
            -Dsonar.projectName=analisisTermometro \
            -Dsonar.sources=src/main/java \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
    }
}
