// Agent sample_agent in project networkScaler

/* Initial beliefs and rules */

/* Initial goals */



// ;startTimePeriod;next(slot);checkStatus;



!start.

/* Plans */

// +!decidecolorF1: (red(high) & green(low) & blue(medium)) |true   <-   .print(" $$$$$ RED 1 -HIGH- $$$$$");  .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);  !decidecolorF2.  

+!start: true <- .print("asd"); !startt.

+!startt : (currentWorkLoad(CWL) & CWL==0) |(arrivedTurn(AVV) & AVV==0) <-getWorkLoadBool;checkWorkLoadBool;  -+planCounter(0); ?arrivedWorkLoad(AWL); .print("Arrived Work Load= ", AWL); .wait(1); !arrangeResourceScale;!startt.
+!startt : currentWorkLoad(CWL) & CWL\==0 <-checkWorkLoadBool; -+planCounter(0); ?currentWorkLoad(CCWL); .print("Current Work Load= ", CCWL);.wait(1); !arrangeResourceScale;!startt.



+!arrangeResourceScale: (workLoad(low) & responseTime(good))   <- .print("RULE 1");  scaleFactor(-15); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (workLoad(low) & responseTime(ok))  <- .print("RULE 2");    scaleFactor(-10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (workLoad(low) & responseTime(bad)) <- .print("RULE 3");   scaleFactor(10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.



+!arrangeResourceScale: (workLoad(medium) & responseTime(good))  <- .print("RULE 4");  scaleFactor(-10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1); .
+!arrangeResourceScale: (workLoad(medium) & responseTime(ok))   <- .print("RULE 5"); scaleFactor(0); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1); .
+!arrangeResourceScale: (workLoad(medium) & responseTime(bad))  <- .print("RULE 6");     scaleFactor(10); consumeWorkLoad;.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1); .

+!arrangeResourceScale: (workLoad(high) & responseTime(good))  <- .print("RULE 7");  scaleFactor(0); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (workLoad(high) & responseTime(ok))   <- .print("RULE 8");    scaleFactor(10); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.
+!arrangeResourceScale: (workLoad(high) & responseTime(bad))  <- .print("RULE 9");   scaleFactor(15); consumeWorkLoad; .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResult("Red",CC);  .wait(1);.









/* 1-9
+!arrangeResourceScale1: (workLoad(low) & responseTime(good)) | true   <- .print("RULE 1");  scaleFactor(-15); consumeWorkLoad; !arrangeResourceScale2 .
+!arrangeResourceScale2: (workLoad(low) & responseTime(ok)) | true <- .print("RULE 2");    scaleFactor(-10); consumeWorkLoad; !arrangeResourceScale3.
+!arrangeResourceScale3: (workLoad(low) & responseTime(bad))| true <- .print("RULE 3");   scaleFactor(10); consumeWorkLoad; !arrangeResourceScale4 .



+!arrangeResourceScale4: (workLoad(medium) & responseTime(good)) | true  <- .print("RULE 4");    scaleFactor(-10); consumeWorkLoad; !arrangeResourceScale5 .
+!arrangeResourceScale5: (workLoad(medium) & responseTime(ok)) | true  <- .print("RULE 5");      scaleFactor(0); consumeWorkLoad; !arrangeResourceScale6.
+!arrangeResourceScale6: (workLoad(medium) & responseTime(bad)) | true <- .print("RULE 6");     scaleFactor(10); consumeWorkLoad;!arrangeResourceScale7.

+!arrangeResourceScale7: (workLoad(high) & responseTime(good)) | true <- .print("RULE 7");  scaleFactor(0); consumeWorkLoad; !arrangeResourceScale8.
+!arrangeResourceScale8: (workLoad(high) & responseTime(ok))  | true <- .print("RULE 8");    scaleFactor(10); consumeWorkLoad; !arrangeResourceScale9.
+!arrangeResourceScale9: (workLoad(high) & responseTime(bad)) | true <- .print("RULE 9");   scaleFactor(15); consumeWorkLoad;!startt.

*/

/*
-!arrangeResourceScale1 <- !arrangeResourceScale2.
-!arrangeResourceScale2 <- !arrangeResourceScale3.
-!arrangeResourceScale3 <- !arrangeResourceScale4.
-!arrangeResourceScale4 <- !arrangeResourceScale5.
-!arrangeResourceScale5 <- !arrangeResourceScale6.
-!arrangeResourceScale6 <- !arrangeResourceScale7.
-!arrangeResourceScale7 <- !arrangeResourceScale8.
-!arrangeResourceScale8 <- !arrangeResourceScale9.
*/





// normal behaviour
/* 
+!arrangeResourceScale: workLoad(low) & responseTime(good) <- .print("RULE 1");   scaleFactor(-15); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(low) & responseTime(ok) <- .print("RULE 2");     scaleFactor(-10); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(low) & responseTime(bad) <- .print("RULE 3");   scaleFactor(10); consumeWorkLoad;!start.



+!arrangeResourceScale: workLoad(medium) & responseTime(good) <- .print("RULE 4");    scaleFactor(-10); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(medium) & responseTime(ok) <- .print("RULE 5");        scaleFactor(0); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(medium) & responseTime(bad) <- .print("RULE 6");     scaleFactor(10); consumeWorkLoad;!start.

+!arrangeResourceScale: workLoad(high) & responseTime(good) <- .print("RULE 7");  scaleFactor(0); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(high) & responseTime(ok) <- .print("RULE 8");     scaleFactor(10); consumeWorkLoad;!start.
+!arrangeResourceScale: workLoad(high) & responseTime(bad) <- .print("RULE 9");   scaleFactor(15); consumeWorkLoad;!start.
*/







+!start : true <- .print("hello world.").
