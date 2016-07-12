# ProjectTwoBigData

Istruzioni preliminari per MongoDB:
Importare l'input prima di eseguire i Job. {Open dataSet dal sito governativo statunitense. On-Time Performances} 
(DB "airplaneDB" - Collezione "input") Comando qui di seguito:
sudo mongoimport -d airplaneDB -c input --type csv --file XXX.csv --headerline 

Nota: Eseguire i Job in serie, con l'ordinamento indicato:

1) routesJob
2) routesJobDistinct
3) meanDelayStandardDeviationJob
4) ghostFlightJob
5) markersJob
6) carrierDelayJob

