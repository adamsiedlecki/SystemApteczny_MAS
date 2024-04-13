pipeline {
    agent any
    options {
        ansiColor('xterm') // lub 'xterm' lub 'truecolor'
    }
    stages {
        stage('Code Checkout'){
            steps {
                checkout changelog: false, poll: false, scm: scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHubToken', url: 'https://github.com/adamsiedlecki/SystemApteczny_MAS.git']])
            }
        }
        stage('Build') {
            steps {
                echo "STARTING BUILD"
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Run Surefire Tests') {
            steps {
                echo "STARTING SUREFIRE"
                script {
                    def surefireResult = sh(script: 'mvn surefire:test', returnStatus: true)
                    if (surefireResult != 0) {
                        currentBuild.result = 'FAILED'
                        error "Surefire tests failed"
                    }
                }
            }
        }
        stage('Run Failsafe Tests') {
            steps {
                echo "STARTING FAILSAE"
                script {
                    sh "whoami"
                    def failsafeResult = sh(script: 'mvn failsafe:integration-test failsafe:verify', returnStatus: true)
                    echo "FAILSAFE RETURNED CODE: ${failsafeResult}"
                    if (failsafeResult != 0) {
                        currentBuild.result = 'FAILED'
                        error "Failsafe tests failed"
                    }
                }
            }
        }
        stage('Deploy to Host') {
                    steps {
                        sh 'mv target/apteka.jar apteka.jar'
                        script {
                            try {
                                sshPublisher(
                                    continueOnError: false,
                                    failOnError: true,
                                    publishers: [
                                        sshPublisherDesc(
                                            configName: 'JENK_DEPLOY_SSH_CONFIG', // Nazwa konfiguracji SSH w Jenkinsie
                                            verbose: true,
                                            transfers: [
                                                sshTransfer(
                                                    sourceFiles: "apteka.jar",
                                                    execTimeout: 60000, // Czas maksymalnego wykonania polecenia w milisekundach
                                                    flatten: true,
                                                    remoteDirectory: "/oms/pjatk/apteka" // Katalog docelowy na zdalnym hoście
                                                )
                                            ]
                                        )
                                    ]
                                )
                            } catch (Exception e) {
                                currentBuild.result = 'FAILURE'
                                println("Exception: ${e.message}")
                            }
                        }
                    }
                }
                stage('Rebuild Docker Compose') {
                    steps {
                        script {
                            sh 'pwd'
                            try {
                                sshPublisher(
                                    continueOnError: false,
                                    failOnError: true,
                                    publishers: [
                                        sshPublisherDesc(
                                            configName: 'JENK_DEPLOY_SSH_CONFIG', // Nazwa konfiguracji SSH w Jenkinsie
                                            verbose: true,
                                            transfers: [
                                                sshTransfer(
                                                    execCommand: "pwd && cd /home/adam/docker/pjatk && docker-compose up -d --build", // Polecenie do przebudowy docker-compose
                                                    execTimeout: 60000, // Czas maksymalnego wykonania polecenia w milisekundach
                                                    remoteDirectory: "/home/adam/docker/pjatk"
                                                )
                                            ]
                                        )
                                    ]
                                )
                            } catch (Exception e) {
                                currentBuild.result = 'FAILURE'
                                println("Exception: ${e.message}")
                            }
                        }
                    }
                }
                stage('Healthcheck') {
                    steps {
                        script {
                            sleep time: 30, unit: 'SECONDS'
                            def response = httpRequest url: 'https://apteka.asiedlecki.net/chart/temperature', timeout: 60

                            if (response.status == 200 && response.content.contains("body")) {
                                println("healthcheck success")
                            } else {
                                println("healthcheck failed")
                                currentBuild.result = 'FAILURE'
                            }
                        }
                    }
                }
    }
}