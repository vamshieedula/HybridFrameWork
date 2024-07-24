pipeline{
    
    agent any
    
    tools{
   		 maven 'maven'
    }
    
   stages {
   
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
        	}
        }
        
        stage("Deploy to Dev"){
            steps{
                echo("Deploy to Dev")
            }
        }
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
        stage("Run regression test cases on QA"){
            steps{
                   catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vamshieedula/HybridFrameWork'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/test_regression.xml"
            	}
            }
        }
        
        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        stage('Publish Regression Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Regression Extent Report',
                    reportTitles: ''])
            }
        }
        
        stage("Deploy to stage"){
            steps{
                echo("deploy to stage")
            }
        }
        
         stage("Run sanity test cases on stage"){
            steps{
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/vamshieedula/HybridFrameWork'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/test_sanity.xml"
            	}
            }
        }
        
        stage('Publish Sanity Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'HTML Sanity Extent Report',
                    reportTitles: ''])
            }
        }
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
         
	}

}