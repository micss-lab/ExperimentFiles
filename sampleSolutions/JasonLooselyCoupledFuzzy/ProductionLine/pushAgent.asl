// Agent pushAgent in project productionLine

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */




+!push : true <- .wait(100); .print("The Product is Pushed away.").

//+!pushForce(X) : true <- .wait(100); pushMotorVal(X);.print("Actuating push motor : Brick is PUSHED by fuzzy Val"); .send(dropAgent,achieve,loop).



/* +done(M)[source(initAgent)] : true        // 1
   <- .print(" 1) DONE Message from ",initAgent,": ",M);
      -done(M).  

*/