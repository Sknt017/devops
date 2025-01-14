package org.devops
def testCoverage(){
    sh 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report'
}
def analisisSonar(){
    def scannerHome = tool 'sonnar-scanner'
    if(scannerHome){
        withSonarQubeEnv('sonar-scanner'){
            sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${gitName} \
            -Dsonar.projectName=${gitName} \
            -Dsonar.sources=src/main/java \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
        }
    } else {
        error 'Sonarqube Scanner not found'
    }
}