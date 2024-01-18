pipeline{
    agent any
    tools{
        maven 'MAVEN'
    }
    stages{
        stage('Checkout'){
            steps{
               checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Osamak552/RD-Gold']])
        }
        }
        stage('Build maven project'){
            steps{
                bat "mvn clean install -DskipTests=true"
            }
        }
        stage('Test and Code Coverage') {
            steps {
                script {
                    bat 'mvn test jacoco:report'
                }
            }
            post {
                always {
                    jacoco(execPattern: '**/target/jacoco.exec')
                }
            }
        }
        stage('SonarQube'){
            steps{
            bat "mvn sonar:sonar"
        }
        }
    }
}