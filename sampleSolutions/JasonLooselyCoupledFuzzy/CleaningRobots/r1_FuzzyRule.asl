// mars robot 1

/* Initial beliefs */

//isitbatteryPower(-1).
//isitvacuumBag(-1). 
//isitdirtIntensity(-1).





isitbatteryPower(Is) :- isitbatteryPower(Is,S1) & not(isitbatteryPower(_,S2) & S2>S1).

isitvacuumBag(Is) :- isitvacuumBag(Is,S1) & not(isitvacuumBag(_,S2) & S2>S1).

isitdirtIntensity(Is) :- isitdirtIntensity(Is,S1) & not(isitdirtIntensity(_,S2) & S2>S1).


at(P) :- pos(P,X,Y) & pos(r1,X,Y).

/* Initial goal */

!check(slots).

/* Plans */

+!check(slots) : not garbage(r1)
   <- next(slot);
      !check(slots).

+!check(slots) : not garbage(r1) & finished(P) // FOR no-gui
   <- ?pos(r1,X,Y); .print("X: ",X,"Y:",Y);
      .send(r2,tell,moveCount(r1,2));.send(r2,tell,moveCount(r3,2)).  


+!check(slots) : garbage(r1) & ((not pos(r1,6,7)) | continue(r1,true)[source(r3)]) & vacuumBagFull(r1,M) & M==empty & batteryCharge(r1, BR1) & BR1==full
   <- .print("Slot R1-1");startTimePeriod;next(slot);checkStatusRule; -+planCounter(0); !arrangeVacuumPower1;   !check(slots).


+!check(slots) : (  garbage(r1) & pos(r1,6,7) & ((vacuumBagFull(r1,ML) & ML==full) | ( batteryCharge(r1, BR1) & BR1==depleted))  ) 
 <-  .print("Slot R1-4"); .send(r3,tell,continue(r3,true)); .send(r2,tell,moveCount(r1,2)).  //;  R1 can NOT move further, yet arrives right on the 6,7 and has finished its area.


+!check(slots) : (  garbage(r1) & pos(r1,6,7) & ((vacuumBagFull(r1,MM) & MM==empty) | ( batteryCharge(r1, BR1) & BR1==full))  ) 
 <-  .print("Slot R1-2"); .send(r3,tell,continue(r3,true)); .send(r2,tell,moveCount(r1,1)).  //;  R1 can move further, yet arrives it has finished its area.





+!check(slots) : (		garbage(r1) & (vacuumBagFull(r1,MK) & MK==full) | ( batteryCharge(r1, BR1) & BR1==depleted)		) 
 <-  .print("Slot R1-3");.send(r3,tell,continue(r3,true)); .send(r2,tell,moveCount(r1,2)).  //;    R1 cannot move any further.
 
 






// .print("Slot R3-2"); ?moveCount(r1,MCR1); MCR1+1; -+moveCount(r3,MCR1); .send(r1,tell,continue(r1,true)); .send(r2,tell,moveCount(r3,_)).    

//+!check(slots)<-.print("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRruns R1").


+continue(r1,true)[source(r3)] : not .desire(check(slots))      
   <- .print(" MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM Message from R3");!check(slots).  





/*
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(mid))  <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(max))  <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(min))  <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(mid))  <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(max))  <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(min))  <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(max))  <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(mid))  <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(max))  <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(min))  <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(mid))  <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(max))  <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(min))  <- .print("RULE 16"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(max))  <- .print("RULE 18"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(mid))  <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(max))  <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(min))  <- .print("RULE 22"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(mid))  <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(max))  <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(min))  <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(max))  <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
*/

/*
+!arrangeVacuumPower1: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower2.
+!arrangeVacuumPower2: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower3.
+!arrangeVacuumPower3: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower4.
+!arrangeVacuumPower4: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower5.
+!arrangeVacuumPower5: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower6.
+!arrangeVacuumPower6: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower7.
+!arrangeVacuumPower7: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower8.
+!arrangeVacuumPower8: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower9.
+!arrangeVacuumPower9: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower10.
+!arrangeVacuumPower10: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower11.
+!arrangeVacuumPower11: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower12.
+!arrangeVacuumPower12: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower13.
+!arrangeVacuumPower13: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower14.
+!arrangeVacuumPower14: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower15.
+!arrangeVacuumPower15: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(min))   <- .print("RULE 16"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(max)) <- .print("RULE 18"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(mid)) <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(max)) <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(min))  <- .print("RULE 22"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(mid))  <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(max))   <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(min))   <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
*/




// 15-27
/*
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 1"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(mid))  <- .print("RULE 2"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); .
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(max))  <- .print("RULE 3"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(min))  <- .print("RULE 4"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(mid))  <- .print("RULE 5"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(max))  <- .print("RULE 6"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(min))  <- .print("RULE 7"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(mid))  <- .print("RULE 8"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(max))  <- .print("RULE 9"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(min))  <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(mid))  <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(max))  <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(min))   <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.
+!arrangeVacuumPower: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.

+!arrangeVacuumPower15: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower16.
+!arrangeVacuumPower16: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 16"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower17.
+!arrangeVacuumPower17: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower18.
+!arrangeVacuumPower18: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 18"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower19.
+!arrangeVacuumPower19: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower20.
+!arrangeVacuumPower20: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower21.
+!arrangeVacuumPower21: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower22.
+!arrangeVacuumPower22: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 22"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower23.
+!arrangeVacuumPower23: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower24.
+!arrangeVacuumPower24: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower25.
+!arrangeVacuumPower25: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower26.
+!arrangeVacuumPower26: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower27.
+!arrangeVacuumPower27: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);!arrangeVacuumPower.

*/

 
+!arrangeVacuumPower1: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower2.
+!arrangeVacuumPower2: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower3.
+!arrangeVacuumPower3: (isitbatteryPower(min) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower4.
+!arrangeVacuumPower4: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower5.
+!arrangeVacuumPower5: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower6.
+!arrangeVacuumPower6: (isitbatteryPower(min) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower7.
+!arrangeVacuumPower7: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower8.
+!arrangeVacuumPower8: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower9.
+!arrangeVacuumPower9: (isitbatteryPower(min) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower10.
+!arrangeVacuumPower10: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower11.
+!arrangeVacuumPower11: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower12.
+!arrangeVacuumPower12: (isitbatteryPower(mid) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower13.
+!arrangeVacuumPower13: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower14.
+!arrangeVacuumPower14: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower15.
+!arrangeVacuumPower15: (isitbatteryPower(mid) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower16.
+!arrangeVacuumPower16: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 16"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower17.
+!arrangeVacuumPower17: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower18.
+!arrangeVacuumPower18: (isitbatteryPower(mid) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 18"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower19.
+!arrangeVacuumPower19: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(min)) | true <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower20.
+!arrangeVacuumPower20: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(mid)) | true <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower21.
+!arrangeVacuumPower21: (isitbatteryPower(max) & isitvacuumBag(min) & isitdirtIntensity(max)) | true <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower22.
+!arrangeVacuumPower22: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(min)) | true <- .print("RULE 22"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower23.
+!arrangeVacuumPower23: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(mid)) | true <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower24.
+!arrangeVacuumPower24: (isitbatteryPower(max) & isitvacuumBag(mid) & isitdirtIntensity(max)) | true <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower25.
+!arrangeVacuumPower25: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(min)) | true <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower26.
+!arrangeVacuumPower26: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(mid)) | true <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1); !arrangeVacuumPower27.
+!arrangeVacuumPower27: (isitbatteryPower(max) & isitvacuumBag(max) & isitdirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR1("R1",CC);  .wait(1);.










//@lg[atomic]
//+garbage(r1) : not .desire(carry_to(r2))
//   <- !check(slots).


+!carry_to(R)
   <- // remember where to go back
      ?pos(r1,X,Y);
      -+pos(last,X,Y);

      // carry garbage to r2
      !take(garb,R);

      // goes back and continue to check
      !at(last);
      !check(slots).

+!take(S,L) : true
   <- !ensure_pick(S);
      !at(L);
      drop(S).

+!ensure_pick(S) : garbage(r1)
   <- pick(garb);
      !ensure_pick(S).
+!ensure_pick(_).

+!at(L) : at(L).
+!at(L) <- ?pos(L,X,Y);
           move_towards(X,Y);
           !at(L).
