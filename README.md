Dicoogle Plugin - Sample 
-----------------------


Where should I start?
-----------------------


Run Dicoogle
---------------
1. Go to http://www.dicoogle.com/?page_id=67
2. Download the version of Dicoogle
3. Run the Dicoogle with: sh Dicoogle.sh or Dicoogle.bat. 
4. Is it running? You're ok! 


Use your own plugin
--------------------

You should use Netbeans to compile the project and maven is necessary. Then, the first class to look is RSIPluginSet. It is the place where the magic starts.

You can compile:

1. https://github.com/bioinformatics-ua/dicooglePluginSample.git
2. cd dicooglePluginSample 
3. ```$ mvn install```
4. copy target/dicooglePluginRestSample-1.0-SNAPSHOT-jar-with-dependencies.jar to the folder Plugins inside Dicoogle project.
5. Run Dicoogle with: sh Dicoogle.sh or Dicoogle.bat. 



Available plugins
-----------------------
- RSIIndex
- RSIWebService
- RSIJettyPlugin
- Sample HTML5 content and consuming web service: helps to build a web app
- RSIStorage
- RSIQuery


Web service plugin sample and Web App: 
--------------------------

To test the webservice plugin, you should go to Services and Enable Dicoogle Web Services.

- http://127.0.0.1:8080/sample/hello?uid=1111
- http://127.0.0.1:8080/dashboardSample
- http://127.0.0.1:6060/rsitest (restlet)



Platforms
----------------

- Windows
- Linux
- Mac OS X



