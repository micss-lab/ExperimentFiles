// Agent sortAgent in project productionLine

/* Initial beliefs and rules */


//isit(Is) :- reverseBack(Is,S1) & not(reverseBack(_,S2) & S2>S1).



//red(Is) :- colorRed(Is,S1) & not(colorRed(_,S2) & S2>S1).

//green(Is) :- colorGreen(Is,S1) & not(colorGreen(_,S2) & S2>S1).

//blue(Is) :- colorBlue(Is,S1) & not(colorBlue(_,S2) & S2>S1).



dropped(true).
shredEndSent(false).
count_time(0).
reverse_triggered(false).

colorDecided(false).

//colorValue(0).

/* Initial goals */

//!status.

/* Plans */

//!status.
planCounter(0).

!samplecolor.


+!status : dropped(false) <-  !status.
+!status : dropped(true) <-   .print("MESSAGE SORT - dropped"); !samplecolor.		

+!contsamplecolor: true<- -+colorDecided(false); !samplecolor.

//reverseMovements;
																																//; 
							//colorDecided(CDS)  & CDS==false					
+!samplecolor: true  <- .print(" ###### START Collecting #####"); sampleColorBool;  .wait(1);  -+planCounter(0);  !decidecolorF; !samplecolor.//  ?rgb(RR,GG,BB); .print(RR,GG,BB);  .print("---------Sampled Color Value =-------------", "R ", RR, "G ", GG, "B ",BB);  //  ?count_time(M); .print(M); New_time=M+1; -+count_time(New_time);;    // 



-!samplecolor <- .print("Sample Fail Plan"); !samplecolor. // .drop_all_intentions; 

//Fuzzified Plan decidecolorF

// Replace this "!colorBucket(2) " !toBuild for PushForce Experiment


+!decidecolorF: (red(high) & green(low) & blue(medium))   <-   .print(" $$$$$ RED 1 -HIGH- $$$$$");  .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);  .  
+!decidecolorF: (red(high) & green(medium) & blue(medium))    <-   .print(" $$$$$ RED 2 -HIGH- $$$$$"); .wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);  .wait(1);  . 
+!decidecolorF: (red(high) & green(high) & blue(low))    <-   .print("$$$$$ RED 3 -HIGH- $$$$$");.wait(1);     ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);    .wait(1);   . 
+!decidecolorF: (red(medium) & green(high) & blue(low))    <-   .print(" $$$$$ RED 7 -MEDIUM- $$$$$");.wait(1);      ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1); . 
+!decidecolorF: (red(medium) & green(medium) & blue(low))    <-   .print(" $$$$$ RED 4 -MEDIUM- $$$$$");.wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1);  . 
+!decidecolorF: (red(medium) & green(veryhigh) & blue(low))    <-   .print(" $$$$$ RED 9 -MEDIUM- $$$$$");.wait(1);    ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC); .wait(1);  .
+!decidecolorF: (red(high) & green(veryhigh) & blue(low))    <-   .print(" $$$$$ RED 10 -HIGH- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Red",CC);  .wait(1);  .
//mor
+!decidecolorF: (red(medium) & green(medium) & blue(medium))    <-   .print(" $$$$$ SpoiledRed 4 -MEDIUM- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("SpoiledRed",CC);.wait(1);   . // Karistirma noktasi
+!decidecolorF: (red(medium) & green(high) & blue(medium))    <-   .print("$$$$$ SpoiledRed 5 -MEDIUM- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("SpoiledRed",CC);.wait(1);   . 
+!decidecolorF: (red(high) & green(high) & blue(medium))      <-   .print("$$$$$ SpoiledRed 2 -HIGH- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("SpoiledRed",CC);.wait(1);   . //?? Karistirma noktasi Kirmizi
+!decidecolorF: (red(medium) & green(medium) & blue(high))      <-   .print("$$$$$ SpoiledRed 7 -MEDIUM- $$$$$ ");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);  . 
+!decidecolorF: (red(high) & green(medium) & blue(low))      <-   .print("$$$$$ SpoiledRed 3 -HIGH- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (red(medium) & green(veryhigh) & blue(medium))      <-   .print("$$$$$ SpoiledRed 13 -MEDIUM- $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (red(medium) & green(veryhigh) & blue(high))      <-   .print("$$$$$ SpoiledRed 14 -MEDIUM- $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
+!decidecolorF: (red(high) & green(veryhigh) & blue(high))      <-   .print("$$$$$ SpoiledRed 15 -MEDIUM- $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("SpoiledRed",CC);.wait(1);   .
// FOR Light Green 
+!decidecolorF: (red(medium) & green(ultramedium) & blue(medium))      <-   .print("####################### LIGHT GREEN 1 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC);.wait(1);   .
+!decidecolorF: (red(medium) & green(ultralow) & blue(medium))      <-   .print("####################### LIGHT GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);  saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(medium) & green(ultrahigh) & blue(medium))      <-   .print("####################### LIGHT GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Light Green",CC); .wait(1);   .




+!decidecolorF: (red(medium) & green(ultramedium) & blue(high))      <-   .print("####################### LIGHT GREEN 4 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(medium) & green(ultrahigh) & blue(high) )     <-   .print("####################### LIGHT GREEN 4.1 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(high) & green(ultramedium) & blue(medium))      <-   .print("####################### LIGHT GREEN 5 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(high) & green(ultramedium) & blue(high))      <-   .print("####################### LIGHT GREEN 6 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(high) & green(ultrahigh) & blue(medium))      <-   .print("####################### LIGHT GREEN 7 $$$$$");.wait(1);?planCounter(MM); CC = MM+1; -+planCounter(CC);     saveResult("Light Green",CC); .wait(1);   .
+!decidecolorF: (red(high) & green(ultrahigh) & blue(high) )     <-   .print("####################### LIGHT GREEN 8 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Light Green",CC); .wait(1);   .

// Middle Green
+!decidecolorF: (red(low) & green(ultralow) & blue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(ultralow) & blue(medium) )      <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC); .wait(1);   .

+!decidecolorF: (red(low) & green(ultralow) & blue(medium) )     <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 3 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .

+!decidecolorF: (red(low) & green(ultralow) & blue(medium) )     <-   .print("ZZZZZZZZZZZZ  MIDDLE GREEN 3 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .


+!decidecolorF: (red(low) & green(veryhigh) & blue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(veryhigh) & blue(medium) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);    saveResult("Middle Green",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(veryhigh) & blue(high) )     <-   .print("ZZZZZZZZZZZZ MIDDLE GREEN 1 $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Middle Green",CC);.wait(1);   .
// Dark Green
+!decidecolorF: (red(low) & green(ultralow) & blue(high) )     <-   .print("KKKKKKKKKK BLUE $$$$$");.wait(1);  ?planCounter(MM); CC = MM+1; -+planCounter(CC);saveResult("Blue",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(high) & blue(low) )     <-   .print("KKKKKKKKKK DARK GREEN 2 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Dark Green",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(high) & blue(medium) )     <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);      saveResult("Dark Green",CC);.wait(1);   .
+!decidecolorF: (red(low) & green(veryhigh) & blue(low))      <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC);      saveResult("Dark Green",CC); .wait(1); .
+!decidecolorF: (red(low) & green(low) & blue(low))      <-   .print("KKKKKKKKKK DARK GREEN 3 $$$$$");.wait(1);   ?planCounter(MM); CC = MM+1; -+planCounter(CC);   saveResult("Dark Green",CC); .wait(1); .
//

//



-!decidecolorF: true <-    .print("deciceFail");  !samplecolor.// .print(" !!!!!!!!!!!!!!!!!! No new Color!!!!!!!!!!!!!!!!!!! ");





+!decideApplyReverse(Threshold) : Threshold> 5 <- .print("Reverse");  !decideRev.
+!decideApplyReverse(Threshold) : Threshold <= 5 <- .print("Not Reverse"); !samplecolor. 


//Fuzzified Plan decideRev
+!decideRev: isit(low)   & reverseBack(low,D1) & D1>0   <-   ?reverseBack(low,D1); .send(shredAgent,achieve,emergencyLocked); .send(shredAgent,achieve,shredend); .print("rev low");  reverseMovementsAction(275,1-D1);  .send(shredAgent,achieve,emergencyUnlocked);  !samplecolor.

+!decideRev: isit(medium)  & reverseBack(medium,D1) & D1>0 <-    ?reverseBack(medium,D1); .send(shredAgent,achieve,emergencyLocked); .send(shredAgent,achieve,shredend); .print("rev medium");   reverseMovementsAction(325,D1); .send(shredAgent,achieve,emergencyUnlocked);   !samplecolor.

+!decideRev: isit(high) & reverseBack(high,D1) & D1>0   <-   ?reverseBack(high,D1); .send(shredAgent,achieve,emergencyLocked);  .send(shredAgent,achieve,shredend); .print("rev  fast");     reverseMovementsAction(350,D1);  .send(shredAgent,achieve,emergencyUnlocked); !samplecolor.

-!decideRev: true <- .print(" #### Noise Value or Unrecognised ######",DD); !samplecolor.



+!colorbucket(F): F==5 <- .print("Sending to the Build Agent");!toBuild;+toBuild(true).

+!colorbucket(F): F\==5 & F\==0 <- .print("Sending to the Bucket and PushAgent",F);!toPush(F);+toPush(true).

+!colorbucket(F): F < 2 <- !samplecolor.


+dropped(M)[source(dropAgent)] : true
   <- .print("Message from ",dropAgent,": ",M);
      -msg(M);-+dropped(true);!status.
      
      
      
+buildFree(M)[source(buildAgent)] : true
   <- .print("Message from ",buildAgent,": ",M);
      -msg(M);.abolish(buildFree(_)); -+buildFree(M).
      

      
      
+!shredend: true <- .send(shredAgent,tell,shredEnd(true)).
+!shredstart: true <- .send(shredAgent,tell,shredEnd(false)).

				
					
+!toPush(Ph):  Ph==2 <-  goBluePosition;    .print("  A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop);  .send(pushAgent,achieve,pushForce(PV)); !samplecolor. //!samplecolor.      //  !shredstart; .send([pushAgent],achieve,push). // go to samplecolor.
+!toPushF(Ph,PV):  Ph==3 <-  goGreenPosition;   .print(" A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop);  .send(pushAgent,achieve,pushForce(PV)); !samplecolor .  //!samplecolor. // .send(pushAgent,achieve,push); .send(dropAgent,achieve,loop);
+!toPush(Ph):  Ph==4 <-  goYellowPosition;   .print("  A message should be sent to Push Agent."); -+colorDecided(false); .send(dropAgent,achieve,loop); .send(pushAgent,achieve,push); !samplecolor . //.send(pushAgent,achieve,push); !shredstart. //!samplecolor.
+!toPush(Ph):  Ph\==2 |Ph\==3 |Ph\==4 <-     .print("A message should be sent to Push Agent."); -+colorDecided(false); !samplecolor.

+!toBuild: buildFree(true) <-  goRedPosition;  .send(buildAgent,achieve,build); .print("A message should be sent to Build Agent."); -+colorDecided(false); !samplecolor.    //.send([buildAgent],achieve,build). // go to samplecolor.
+!toBuild: buildFree(false) <-  goYellowPosition;  .send(pushAgent,achieve,push); .print("Build is not free RED is discarded"); -+colorDecided(false); !samplecolor. 
						         

