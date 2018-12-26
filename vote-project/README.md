vote-project - Design and implement a JBoss Maven project named vote-project as a 4-tire enterprise application. 
========================
Author: Mir Mohammed Mehdi
Level: Beginner  
Technologies: JSF, EJB, MDB, Bootstrap, Restful Service, logging, Hadoop, SQL
Summary: The projects trains and predicts the data as to which political party the user would vote for in future.  
Target Product: WildFly  
Source: <https://github.com/wildfly/quickstart/>  

What is it?
-----------

The application does the K-Means clustering and prediction using Hadoop. 

When you deploy the vote-project application, 3 users are automatically created:

Username		|	Password	|	Role
mir					mehdi			1 - (Admin)
faiza				zuhair			2 - (Developer)
jdoe				doe				3 - (General)


To test this example:

#Admin User : 

1. Enter username as 'mir' and password as 'mehdi' to land into the admin page.
2. Note that the passwords are encrypted in the database.
3. In the admin page, Enter any username present in the database and new role number and click the change button to change the role of that user in the database.

#Developer User:

#Prerequisites:
#Steps to execute this program

1. Export the kmeans-hd project as jar file with the name - kmeans-hd.jar.
2. Make sure that the input files-"voterun1.txt" and "centroidrun1.txt" are in /ec folder of hadoop file system.
-----------------------------------------------------------------------------------
#centroidrun1.txt:

9,38,91,92,74,55,84,90,91,43,14,58,65,64,92,64,democrat 0
91,62,9,8,26,45,16,10,9,57,14,79,35,36,8,64,republican  1
-----------------------------------------------------------------------------------------
1. Deploy ec-mdb project in the server.
2. Enter username as 'faiza' and password as 'zuhair' to land into the developer page.

#Note
Change the path of the kmeans-hd.jar in accordance to your local machine in the code- SBSingleton class of ec-mdb project.
Change the path of ECJMSProducer.jar in accordance to your local machine in the code- ManagedBEanModelDao class of vote-project project

#Create Cluster:
a. Enter path in HDFS of input file, say for example -"/ec" and the output path of the final cluster to be stored in HDFS, say for example -'/result' to call the kmeans-hd project via MDB and EJB  which would determine the final cluster model and save it in HDFS in the path fed previously, in our example - "/result".

#Save Cluster in SQL DB:
a. Open cmd and run the following command to get the list of files in the result directory.
				
				"hadoop fs -ls /result"
				
b. The following output will be displayed
				
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:52 /result/171075392385778
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:53 /result/171112516889708
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:53 /result/171147275338829
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:54 /result/171182929056852
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:54 /result/171216776361689
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:55 /result/171250621303936
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:56 /result/171285529439372
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:56 /result/171318297225648
			drwxr-xr-x   - MIR supergroup          0 2018-12-06 15:57 /result/171352105251061
			
c. Open the last folder created, in this example it is -
			
				"hadoop fs -ls /result/17135*"
				
d. The following output will be displayed.
			
			Found 2 items
			-rw-r--r--   1 MIR supergroup          0 2018-12-06 15:57 /result/171352105251061/_SUCCESS
			-rw-r--r--   1 MIR supergroup        595 2018-12-06 15:57 /result/171352105251061/part-00000
			
e.  Run the following command to get the result.

			"hadoop fs -cat /result/171352105251061/part-00000"
			
f. Enter the above path of the final cluster - "/result/171352105251061/part-00000".
g. Enter the name of the class with which you wish to save the bin file in the SQL DB,  say "vote".
h. Press "Save the Model" button to save the cluster in the database.

#Calculate the accuracy of the algorithm:

a. Enter the path of the actual input file in the HDFS. In our example it is - 
			
			"/ec/voterun1.txt"
b. Enter the class name of the cluster in the DB - "vote".

c.  Click "Calculate Accuracy" button to calculate the accuracy of the kmeans algorithm implemented.
 
#General User:

1. Enter username as 'jdoe' and password as 'doe' to land into the general page.
2. Grade a party based on various parameters out of 100 and give the modelclassname that was given in the developer page.
3. Press the submit button to predict the chances of the user voting a particular party from the trained dataset.

#About button in Home Page:

1. Click this button to call a webservice which would get the data from the vote-rs project.

#Note:
The projects ec-mdb,vote-project,vote-rs should be deployed in the wildfly for complete execution of the project.
Change the path of the kmeans-hd.jar in accordance to your local machine in the code- SBSingleton class of ec-mdb project.
Change the path of ECJMSProducer.jar in accordance to your local machine in the code- ManagedBEanModelDao class of vote-project project