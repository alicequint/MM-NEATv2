java -jar dist/MM-NEATv2.jar runNumber:%1 randomSeed:%1 base:torus trials:8 maxGens:300 mu:100 io:true netio:true mating:false fs:true task:edu.utexas.cs.nn.tasks.gridTorus.TorusEvolvedPredatorsVsStaticPreyTask log:AllPredVsStaticAllPreyTeam-Control saveTo:Control allowDoNothingActionForPredators:true torusPreys:5 torusPredators:3 staticPreyController:edu.utexas.cs.nn.gridTorus.controllers.PreyFleeAllPredatorsController PredsEatEachPreyQuickly:true PredatorMinimizeTotalTime:true predatorMinimizeDistance:true torusSenseTeammates:true