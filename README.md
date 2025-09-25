# WebAutomationJava
# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)

==========================================================================
Automation Setup – Prerequisite Document
==========================================================================
🔧 1. Install Eclipse IDE	2
  2. Install Java & Set JAVA_HOME Path	2
📦 3. Install Maven & Set Path	3
==========================================================================
🔧 1. Install Eclipse IDE
1.	Go to the official site: https://www.eclipse.org/downloads/
2.	Download “Eclipse IDE for Java Developers” (choose latest stable version).
3.	Extract the ZIP folder and double-click eclipse.exe to launch.
4.	Set your workspace folder (e.g., C:\AutomationWorkspace).

  2. Install Java & Set JAVA_HOME Path
Required: Java JDK (preferably  17)
✅ Steps:
1.	Download OR use OpenJDK
2.	Install JDK → Default location is usually:
C:\Program Files\Java\jdk-<version>
3.	Set Environment Variables:
o	Search for Environment Variables → Click Edit the system environment variables
o	Under System Variables, click New:
	Name: JAVA_HOME
	Value: C:\Program Files\Java\jdk-<version>
o	Edit Path → Add:
%JAVA_HOME%\bin
4.	Verify in Command Prompt:
•	java -version




📦 3. Install Maven & Set Path
Required for running automation test suites via command line, Jenkins, or CI/CD
✅ Steps:
1.	Download Maven ZIP:
https://maven.apache.org/download.cgi
2.	Extract it to:
C:\apache-maven-<version>
3.	Set Environment Variables:
o	System Variable → New:
	Name: MAVEN_HOME
	Value: C:\apache-maven-<version>
o	Edit Path → Add:
%MAVEN_HOME%\bin
4.	Verify in Command Prompt:
•	mvn -version