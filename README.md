## Stepwise gendiv-calculator for Nei's Gene Diversity index He (Nei 1972) 

The calculator is a web-based client/server application for dominant genetic data (such as AFLP), based on Google Web Toolkit (GWT). It automatically calculates the genetic diversity for k individuals from the input dataset, with k ranging from two to the maximal number of sampled individuals. It is also possible to set the number of used fragments (m) as well as the number of repititions (n).
  
  
  
### Google Web Toolkit (GWT)
The Google Web Toolkit is an application development framework, that enables the software engineer to write source code in Java language, which is automatically translated into html and javascript websites. This combines the advantages of class-based, object-oriented programming, with the light-weight execution of html/javascript-websites on client-side.
  
###  Architecture
The source-code of a GWT-application consists of four components, that determine how the framework will treat the source files, and where the compiled files will be executed. The first component, the client side source files, will be translated to html and javascript files, and executed as the website on the client's browser. The server component will be translated to java classes, and executed on the server by responding to the requests from the client-side code. In the shared component, data structures can be defined, that are used by the client as well as the server component, in order to avoid redundancies in definition. Finally the public component stores static files, like images and symbols. It is crucial for the software engineer to always consider the context of the specific components, while developing the classes.

### Client component
In the client component the source code is logically devided into view- and controller-elements. The view-elements defines all Graphical User Interface (GUI) items, i.e. text fields for data input, labels for data output, and buttons to enable the user to take action. The controller items define the logic of the application, how GUI-items should react to user interaction, and which requests should be sent to the server component.
The GenDiv-Calculator provides a table to show all previous calculations and a button to add new calculations. When the user presses the button to add a new calculation, a dialog is shown with the following inputs:
* Title of the calculation, which can be freely defined by the user, in order to later be able to identify the specific calculation
* Input data:  
	* dominant data (1/0) semicolon separated  
  * rows: Fragments  
  * column: Individuals
* Number of fragments (m)
* Number of repetitions (n) 

Once the dialog is confirmed, the new calculation will be sent to the server component for processing, and will appear at the list of calculations. In case of wrong data entry, the user can cancel a calculation while it is in the waiting queue. After a calculation has been processed, the status in the table changes, and the results can be shown in a pop-up window, activated via the according button. 
Data ouput:
* He values for each k summarised in one mean value (k from 2 to kmax = maximum number of individuals)

### Server component
On the server-side the calculation class is waiting for requests from the client-side. Once a new calcualtion is added by the user, the calculation data is sent via http-request to the server, where it is added to the calculation queue. A new thread is created, to be able to process the calculation in the background, without blocking the request-handler class. Once the calculation thread is finished, the result data is handed back to the request-handler class, where all calculation requests and results are stored in a data array.
The client side component is polling the server for the status of all calculations on a regular basis. Default value for polling was set to 3000ms, as a trade off between acceptable update frequency for the user compared to avoiding to overflow the server with requests. Based on a polling request from the client, the server will send the status of all calculations back to the client side, to correctly show the list of all calculation to the user.

### Deployment and improvement potentials
The server components are running as Java classes on the server operating system. As described above, each new calculation is processed in an individual thread. Each thread is given by the operating system to a processor core. Practical experience has shown that best performance was reached, when one core is left for server activities, and the rest of the cores is processing one calculation thread each. In order to achieve this setup, the user either needs to add calculations as he sees active calculations in the status table. However this requires constant attention of the user. To optimally use the processor ressources, a calculation queue was introduced, that allows the user to enter a high number of calculations, but new calculation threads are only started when a free processor thread is available. Therefore the total number of processor cores of the server, minus one core for the remaining server activities, is defined as number of calculation threads. The queue is checking on a regular basis for the number of running and available calculation threads. 
  
### Exemplary input data (35 individuals, 8 Fragments)
1;0;1;0;1;0;0;0;0;0;0;0;0;1;1;1;0;1;0;0;0;0;0;0;1;0;0;0;1;1;1;1;0;0;0  
1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1  
1;1;1;1;1;1;1;1;1;1;0;0;1;1;1;1;1;1;1;1;0;1;1;0;1;1;1;0;1;1;1;1;1;1;1  
1;0;1;0;1;0;1;1;1;1;1;1;1;1;1;0;1;1;1;1;1;0;1;1;1;0;1;1;1;1;1;1;1;1;1  
1;1;1;1;0;1;0;0;0;0;1;1;1;0;1;0;1;1;1;1;1;1;0;1;0;1;1;1;1;1;0;0;1;0;1  
1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1;1  
1;1;0;0;1;0;1;1;0;0;1;0;0;0;0;0;0;0;0;1;0;0;1;0;0;0;1;0;0;1;0;1;1;0;0  
0;0;0;0;1;0;1;0;0;0;0;0;0;1;0;0;0;0;1;1;0;0;1;0;0;1;1;0;0;1;0;0;1;0;0 
	
### Reference
Nei M (1972) Genetic distance between populations. Am Nat 106:283-292  
  
### Technical Info  
Based on Google Web Toolkit (GWT)  
Last tested version: GWT 2.7.0 with Java 8  
  
  

