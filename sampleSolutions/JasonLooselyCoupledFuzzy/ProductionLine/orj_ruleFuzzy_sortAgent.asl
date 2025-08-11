// Agent sortAgent in project productionLine

/* Initial beliefs and rules */


//isit(Is) :- reverseBack(Is,S1) & not(reverseBack(_,S2) & S2>S1).



isitRed(Is) :- colorRed(Is,S1) & not(colorRed(_,S2) & S2>S1).

isitGreen(Is) :- colorGreen(Is,S1) & not(colorGreen(_,S2) & S2>S1).

isitBlue(Is) :- colorBlue(Is,S1) & not(colorBlue(_,S2) & S2>S1).



dropped(false).
shredEndSent(false).
count_time(0).
reverse_triggered(false).

colorDecided(false).


/* Initial goals */

//!status.

/* Plans */

planCounter(0). 

!samplecolor.



																																
												
+!samplecolor: true <- sampleColorRule;  .wait(1);  -+planCounter(0);  !decidecolorF;  !samplecolor.


//-!samplecolor <- .print("Sample Fail Plan"); .wait(1000); !samplecolor. // .drop_all_intentions; 




+!decidecolorF: (isitRed(high) & isitGreen(low) & isitBlue(medium))   <-   .print(" $$$$$ RED 1 -HIGH- $$$$$");  .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);  .  
+!decidecolorF: (isitRed(high) & isitGreen(medium) & isitBlue(medium))    <-   .print(" $$$$$ RED 2 -HIGH- $$$$$"); .wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);  .wait(1);  . 
+!decidecolorF: (isitRed(high) & isitGreen(high) & isitBlue(low))    <-   .print("$$$$$ RED 3 -HIGH- $$$$$");.wait(1);     ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);    .wait(1);   . 
+!decidecolorF: (isitRed(medium) & isitGreen(high) & isitBlue(low))    <-   .print(" $$$$$ RED 7 -MEDIUM- $$$$$");.wait(1);      ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1); . 
+!decidecolorF: (isitRed(medium) & isitGreen(medium) & isitBlue(low))    <-   .print(" $$$$$ RED 4 -MEDIUM- $$$$$");.wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1);  . 
+!decidecolorF: (isitRed(medium) & isitGreen(veryhigh) & isitBlue(low))    <-   .print(" $$$$$ RED 9 -MEDIUM- $$$$$");.wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1);  .
+!decidecolorF: (isitRed(high) & isitGreen(veryhigh) & isitBlue(low))    <-   .print(" $$$$$ RED 10 -HIGH- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);  .wait(1);  .
//mor
+!decidecolorF: (isitRed(medium) & isitGreen(medium) & isitBlue(medium))    <-   .print(" $$$$$ SpoiledRed 4 -MEDIUM- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("SpoiledRed",CC);.wait(1);   . // Karistirma noktasi
+!decidecolorF: (isitRed(medium) & isitGreen(high) & isitBlue(medium))    <-   .print("$$$$$ SpoiledRed 5 -MEDIUM- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("SpoiledRed",CC);.wait(1);   . 
+!decidecolorF: (isitRed(high) & isitGreen(high) & isitBlue(medium))      <-   .print("$$$$$ SpoiledRed 2 -HIGH- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("SpoiledRed",CC);.wait(1);   . //?? Karistirma noktasi Kirmizi
+!decidecolorF: (isitRed(medium) & isitGreen(medium) & isitBlue(high))      <-   .print("$$$$$ SpoiledRed 7 -MEDIUM- $$$$$ ");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);  . 
+!decidecolorF: (isitRed(high) & isitGreen(medium) & isitBlue(low))      <-   .print("$$$$$ SpoiledRed 3 -HIGH- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (isitRed(medium) & isitGreen(veryhigh) & isitBlue(medium))      <-   .print("$$$$$ SpoiledRed 13 -MEDIUM- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (isitRed(medium) & isitGreen(veryhigh) & isitBlue(high))      <-   .print("$$$$$ SpoiledRed 14 -MEDIUM- $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(veryhigh) & isitBlue(high))      <-   .print("$$$$$ SpoiledRed 15 -MEDIUM- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
// FOR Light Green 
+!decidecolorF: (isitRed(medium) & isitGreen(ultramedium) & isitBlue(medium))      <-   .print("####################### LIGHT GREEN 1 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(medium) & isitGreen(ultralow) & isitBlue(medium))      <-   .print("####################### LIGHT GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(medium) & isitGreen(ultrahigh) & isitBlue(medium))      <-   .print("####################### LIGHT GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Light Green",CC); .wait(1);   .




+!decidecolorF: (isitRed(medium) & isitGreen(ultramedium) & isitBlue(high))      <-   .print("####################### LIGHT GREEN 4 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(medium) & isitGreen(ultrahigh) & isitBlue(high) )     <-   .print("####################### LIGHT GREEN 4.1 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(ultramedium) & isitBlue(medium))      <-   .print("####################### LIGHT GREEN 5 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(ultramedium) & isitBlue(high))      <-   .print("####################### LIGHT GREEN 6 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(ultrahigh) & isitBlue(medium))      <-   .print("####################### LIGHT GREEN 7 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(ultrahigh) & isitBlue(high) )     <-   .print("####################### LIGHT GREEN 8 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (isitRed(high) & isitGreen(high) & isitBlue(high))      <-   .print("LIGHT GREEN 9 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC); .wait(1); .
// Middle Green
+!decidecolorF: (isitRed(low) & isitGreen(ultralow) & isitBlue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(ultralow) & isitBlue(medium) )      <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC); .wait(1);   .

+!decidecolorF: (isitRed(low) & isitGreen(ultralow) & isitBlue(medium) )     <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 3 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .

+!decidecolorF: (isitRed(low) & isitGreen(ultralow) & isitBlue(medium) )     <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 3 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .


+!decidecolorF: (isitRed(low) & isitGreen(veryhigh) & isitBlue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(veryhigh) & isitBlue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(veryhigh) & isitBlue(high) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .
// Dark Green
+!decidecolorF: (isitRed(low) & isitGreen(ultralow) & isitBlue(high) )     <-   .print("KKKKKKKKKK BLUE $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);saveResult("Blue",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(high) & isitBlue(low) )     <-   .print("KKKKKKKKKK DARK GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Dark Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(high) & isitBlue(medium) )     <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);      saveResult("Dark Green",CC);.wait(1);   .
+!decidecolorF: (isitRed(low) & isitGreen(veryhigh) & isitBlue(low))      <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);      saveResult("Dark Green",CC); .wait(1); .

//

//





//-!decidecolorF: true <-.print("deciceFail");!samplecolor.// .print(" !!!!!!!!!!!!!!!!!! No new Color!!!!!!!!!!!!!!!!!!! ");







				
					
+!toPush(Ph):Ph==2 <-.send(pushAgent,achieve,pushForce(PV)); !samplecolor. //!samplecolor.//!shredstart; .send([pushAgent],achieve,push). // go to samplecolor.


+!toBuild: true <- .send(buildAgent,achieve,build); !samplecolor. //.send([buildAgent],achieve,build). // go to samplecolor.

						

