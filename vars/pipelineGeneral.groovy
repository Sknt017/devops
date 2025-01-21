import org.devops.LbBuildImagen
import org.devops.LbDeployDocker
import org.devops.LbOWASP
import org.devops.LbPublicarDockerHub
def call(){
    def build = new org.devops.LbBuildImagen()
    def publish = new org.devops.LbPublicarDockerHub()
    def deploy = new org.devops.LbDeployDocker()
    def OWASP = new org.devops.LbOWASP()
    pipeline {    
        agent any    
        stages {
            stage('Checkout') {
                steps {
                    git url: "${env.GIT_URL_1}"
                }
            }
            stage('Build Application') {
                steps {
                    sh 'mvn clean package'
                }
            }
            stage('Build Image') {
                steps {
                    script{
                        build.construirImagen(this)
                        // def imageExists = sh(script : "docker images -q termometroAPI",returnStdout: true).trim()
                        // if (imageExists){
                        //     echo "La imagen ya existe... descartando construccion..."
                        // }else{
                        //     sh 'docker build -t termometroAPI -f Dockerfile .'
                        // }
                    }
                }
            }
            stage('Publish Image') {
                steps {
                    script{
                            publish.publicarImagen(this)
                        }
                    }

                }
            
            stage('Run Container') {
                steps {
                    script{
                            deploy.ejecutarContenedor(this)
                        // def dockerHubUsername = 'davidruiz212'
                        // def imageName = 'termometroAPI'
                        // def containerName = 'termometro-container'
                        // def containerExists = sh(script: "docker ps -a --filter name=${containerName} --format {{.Names}}",
                        // returnStdout: true).trim()
                        // if (containerExists){
                        //     echo "el contenedor ${containerName} ya existe. descartando..."
                        // } else {
                        //     sh "docker stop ${containerName} || true"
                        //     sh "docker rm ${containerName} || true"
                        //     sh "docker run -d -p 3000:8080 --name ${containerName} ${dockerHubUsername}/${imageName}"
                        // }
                    }
                }
            }
            stage('OWASP Analisis') {
                steps {
                    script {
                            OWASP.analizarProyecto(this)

                        // def networkName = 'jenkinsOwasp'
                        // def imageName = 'zaproxy/zap-stable'
                        // def zapContainerName = 'owaspimagen-container'
                        // def imageExists = sh(returnStdout: true, script: "docker images -q $imageName" ).trim()
                        // if(imageExists.isEmpty()){
                        //     sh "docker pull $imageName"
                        // } else {
                        //     echo "La imagen ${imageName} ya existe. descartando descarga..."
                        // }
                        // sh "docker stop ${zapContainerName} || true"
                        // sh "docker rm ${zapContainerName} || true "
                        // sh "docker run -d --name ${zapContainerName} --network=${networkName} ${imageName}"
                        // def dockerHubUsername = 'davidruiz212'
                        // def crudspringbootImageName = 'termometroAPI'
                        // def crudspringbootContainerName = 'termometro-container'
                        // sh "docker stop ${crudspringbootContainerName} || true"
                        // sh "docker rm ${crudspringbootContainerName} || true"
                        // sh "docker run -d --name ${crudspringbootContainerName} --network=${networkName} -p 3500:8080 --user root ${dockerHubUsername}/${crudspringbootImageName}"
                        // def targetURL = "http://${env.iphost}:3500"
                        // sh "docker run --rm -v fullscancrudspringboot:/zap/wrk/:rw --user root --network=${networkName} -t $imageName zap-full-scan.py -t ${targetURL} -r fullscancrudspringboot.html -I"
                        // sh "docker stop ${zapContainerName}"
                        // sh "docker rm ${zapContainerName}"
                        // sh "docker stop ${crudspringbootContainerName}"
                        // sh "docker rm ${crudspringbootContainerName}"
                    }
                }
            }
        }
    }
}