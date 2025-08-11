

/* Initial beliefs and rules */

/* Initial goals */

buildStatus(0).
buildFree(true).




/* Plans */







+!build : true <- ?buildStatus(M); .print("M= ",M); K = M+1; -+buildStatus(K); .print("K=", K);!state.

+!state : buildStatus(L) & L ==1 <- .print("First Red Press",L). 

+!state : buildStatus(L) & L ==2 <-  .print("Second Red-Red Press" ,L);!eject.


+!eject : true <- .print("Eject"); -+buildStatus(0). //state_eject

