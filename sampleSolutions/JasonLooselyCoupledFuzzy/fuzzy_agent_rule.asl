// Agent sample_agent in project networkScaler

/* Initial beliefs and rules */

/* Initial goals */



// ;startTimePeriod;next(slot);checkStatus;



isitworkLoad(Is) :- workLoad(Is,S1) & not(workLoad(_,S2) & S2>S1).

isitresponseTime(Is) :- responseTime(Is,S1) & not(responseTime(_,S2) & S2>S1).




!start.

/* Plans */

+!start: true <- .print("asd"); !startt.

+!startt : (currentWorkLoad(CWL) & CWL==0) |(arrivedTurn(AVV) & AVV==0)<-getWorkLoadBool;checkWorkLoadRule; -+planCounter(0); ?arrivedWorkLoad(AWL); .print("Arrived Work Load= ", AWL); .wait(1);!arrangeResourceScale;!startt.
+!startt : currentWorkLoad(CWL) & CWL\==0  <-checkWorkLoadRule; -+planCounter(0); ?currentWorkLoad(CCWL); .print("Current Work Load= ", CCWL);.wait(1);  !arrangeResourceScale;!startt.




+!arrangeResourceScale: (isitworkLoad(low) & isitresponseTime(good)) <- .print("RULE 1");   scaleFactor(-15); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(low) & isitresponseTime(ok)) <- .print("RULE 2");     scaleFactor(-10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(low) & isitresponseTime(bad))  <- .print("RULE 3");   scaleFactor(10); consumeWorkLoad;  .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1); .

+!arrangeResourceScale: (isitworkLoad(medium) & isitresponseTime(good))  <- .print("RULE 4");    scaleFactor(-10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(medium) & isitresponseTime(ok))  <- .print("RULE 5");        scaleFactor(0); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(medium) & isitresponseTime(bad))  <- .print("RULE 6");     scaleFactor(10); consumeWorkLoad;  .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.

+!arrangeResourceScale: (isitworkLoad(high) & isitresponseTime(good))  <- .print("RULE 7");  scaleFactor(0); consumeWorkLoad;       .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(high) & isitresponseTime(ok))  <- .print("RULE 8");     scaleFactor(10); consumeWorkLoad;     .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (isitworkLoad(high) & isitresponseTime(bad))  <- .print("RULE 9");   scaleFactor(15); consumeWorkLoad;      .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.





/*
 * 

* 
 */







+!start : true <- .print("hello world.").
