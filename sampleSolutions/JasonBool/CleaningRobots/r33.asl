// mars robot 1

/* Initial beliefs */

//batteryPower(-1).
//vacuumBag(-1). 
//dirtIntensity(-1).




at(P) :- pos(P,X,Y) & pos(r3,X,Y).


finished(P) :- pos(P,X,Y) & pos(r3,0,0).

/* Initial goal */

!check(slots).  // disabled for now.

/* Plans */

//+!check(slots) : true // not garbage(r3)
//   <- next(slotRev);
//      !check(slots).
//+!check(slots).


+!check(slots) : not garbage(r3) 
   <- next(slotRev);
     ?pos(r3,X,Y); .print("X: ",X,"Y:",Y);
      !check(slots).



//+!check(slots) : not garbage(r3) & finished(P) // FOR no-gui
 //  <- ?pos(r3,X,Y); .print("X: ",X,"Y:",Y);
 //     .send(r2,tell,moveCount(r3,2)).        





+!check(slots) : garbage(r3) & (not pos(r3,8,7) | continue(r3,true)[source(r1)])  & vacuumBagFull(r3,K) & K==empty &  batteryCharge(r3,BR3) & BR3==full
   <-.print("Slot R3-1"); startTimePeriod;next(slotRev);checkStatusBool;-+planCounter(0);!arrangeVacuumPower1;!check(slots).				
   
   
   
+!check(slots) : (garbage(r3) & pos(r3,8,7) & ((vacuumBagFull(r3,M) & M==empty) | ( batteryCharge(r3, BR3) & BR3==full))       )
 <-  .print("Slot R3-2"); .send(r1,tell,continue(r1,true)); .send(r2,tell,moveCount(r3,1)).    //   R3 can move further, yet arrives it has finished its area.
  
  
   
 +!check(slots) : (garbage(r3)  & (vacuumBagFull(r3,M) & M==full) | ( batteryCharge(r3, BR3) & BR3==depleted))
 <-  .print("Slot R3-3"); .send(r1,tell,continue(r1,true)); .send(r2,tell,moveCount(r3,2)).    //  R3 cannot move any further.

   
   
   

//+!check(slots)<-.print("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRruns R3").


+continue(r3,true)[source(r1)] : not .desire(check(slots))            
   <- .print("  MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMessage from R1");!check(slots).  



/*

+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(min))  <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(mid))  <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(max))  <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(min))  <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(mid))  <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(max))  <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(min))  <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(max))  <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(min))  <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(mid))  <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(max))  <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(min))  <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(mid))  <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(max))  <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(min))  <- .print("RULE 16"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(max))  <- .print("RULE 18"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(min))  <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(mid))  <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(max))  <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(min))  <- .print("RULE 22"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(mid))  <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(max))  <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(min))  <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(max))  <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.


*/










/*

+!arrangeVacuumPower1: (batteryPower(min) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower2.
+!arrangeVacuumPower2: (batteryPower(min) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower3.
+!arrangeVacuumPower3: (batteryPower(min) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower4.
+!arrangeVacuumPower4: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower5.
+!arrangeVacuumPower5: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower6.
+!arrangeVacuumPower6: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower7.
+!arrangeVacuumPower7: (batteryPower(min) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower8.
+!arrangeVacuumPower8: (batteryPower(min) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower9.
+!arrangeVacuumPower9: (batteryPower(min) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower10.
+!arrangeVacuumPower10: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower11.
+!arrangeVacuumPower11: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower12.
+!arrangeVacuumPower12: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower13.
+!arrangeVacuumPower13: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower14.
+!arrangeVacuumPower14: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower15.
+!arrangeVacuumPower15: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(min))   <- .print("RULE 16"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(max)) <- .print("RULE 18"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(min))  <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(mid)) <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(min) & dirtIntensity(max)) <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(min))  <- .print("RULE 22"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(mid))  <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(max))   <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(min))   <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(max) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.

*/







// 15-27

/*
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(min)) <- .print("RULE 1"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(mid))  <- .print("RULE 2"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); .
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(min) & dirtIntensity(max))  <- .print("RULE 3"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(min))  <- .print("RULE 4"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(mid))  <- .print("RULE 5"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(max))  <- .print("RULE 6"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(min))  <- .print("RULE 7"); burnGarb(50,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(mid))  <- .print("RULE 8"); burnGarb(70,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(min) & vacuumBag(max) & dirtIntensity(max))  <- .print("RULE 9"); burnGarb(90,1); .wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(min))  <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(mid))  <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(max))  <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(min)) <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.
+!arrangeVacuumPower: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.

+!arrangeVacuumPower15: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower16.
+!arrangeVacuumPower16: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 16"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower17.
+!arrangeVacuumPower17: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower18.
+!arrangeVacuumPower18: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 18"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower19.
+!arrangeVacuumPower19: (batteryPower(max) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower20.
+!arrangeVacuumPower20: (batteryPower(max) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower21.
+!arrangeVacuumPower21: (batteryPower(max) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower22.
+!arrangeVacuumPower22: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 22"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower23.
+!arrangeVacuumPower23: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower24.
+!arrangeVacuumPower24: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower25.
+!arrangeVacuumPower25: (batteryPower(max) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower26.
+!arrangeVacuumPower26: (batteryPower(max) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower27.
+!arrangeVacuumPower27: (batteryPower(max) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);!arrangeVacuumPower.
*/






+!arrangeVacuumPower1: (batteryPower(min) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 1"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower2.
+!arrangeVacuumPower2: (batteryPower(min) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 2"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower3.
+!arrangeVacuumPower3: (batteryPower(min) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 3"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower4.
+!arrangeVacuumPower4: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 4"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower5.
+!arrangeVacuumPower5: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 5"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower6.
+!arrangeVacuumPower6: (batteryPower(min) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 6"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower7.
+!arrangeVacuumPower7: (batteryPower(min) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 7"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower8.
+!arrangeVacuumPower8: (batteryPower(min) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 8"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower9.
+!arrangeVacuumPower9: (batteryPower(min) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 9"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower10.
+!arrangeVacuumPower10: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 10"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower11.
+!arrangeVacuumPower11: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 11"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower12.
+!arrangeVacuumPower12: (batteryPower(mid) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 12"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower13.
+!arrangeVacuumPower13: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 13"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower14.
+!arrangeVacuumPower14: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 14"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower15.
+!arrangeVacuumPower15: (batteryPower(mid) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 15"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower16.
+!arrangeVacuumPower16: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 16"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower17.
+!arrangeVacuumPower17: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 17"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower18.
+!arrangeVacuumPower18: (batteryPower(mid) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 18"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower19.
+!arrangeVacuumPower19: (batteryPower(max) & vacuumBag(min) & dirtIntensity(min)) | true <- .print("RULE 19"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower20.
+!arrangeVacuumPower20: (batteryPower(max) & vacuumBag(min) & dirtIntensity(mid)) | true <- .print("RULE 20"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower21.
+!arrangeVacuumPower21: (batteryPower(max) & vacuumBag(min) & dirtIntensity(max)) | true <- .print("RULE 21"); burnGarb(90,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower22.
+!arrangeVacuumPower22: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(min)) | true <- .print("RULE 22"); burnGarb(50,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower23.
+!arrangeVacuumPower23: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(mid)) | true <- .print("RULE 23"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower24.
+!arrangeVacuumPower24: (batteryPower(max) & vacuumBag(mid) & dirtIntensity(max)) | true <- .print("RULE 24"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower25.
+!arrangeVacuumPower25: (batteryPower(max) & vacuumBag(max) & dirtIntensity(min)) | true <- .print("RULE 25"); burnGarb(70,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower26.
+!arrangeVacuumPower26: (batteryPower(max) & vacuumBag(max) & dirtIntensity(mid)) | true <- .print("RULE 26"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1); !arrangeVacuumPower27.
+!arrangeVacuumPower27: (batteryPower(max) & vacuumBag(max) & dirtIntensity(max)) | true <- .print("RULE 27"); burnGarb(100,1);.wait(1); ?planCounter(MM); CC = MM+1; -+planCounter(CC); saveResultR3("R3",CC);  .wait(1);.





  
 
+!carry_to(R)
   <- // remember where to go back
      ?pos(r3,X,Y);
      -+pos(last,X,Y);

      // carry garbage to r2
      !take(garb,R);

      // goes back and continue to check
      !at(last);
      !check(slots).
	  
+!take(S,L) : true
   <- !ensure_pick(S);
      !at(L);
      dropRev(S).

+!ensure_pick(S) : garbage(r3)
   <- pickRev(garb);
      !ensure_pick(S).
+!ensure_pick(_).

+!at(L) : at(L).
+!at(L) <- ?pos(L,X,Y);
           move_towardsRev(X,Y);
           !at(L).