# ProjectTwoBigData

Istruzioni preliminari per MongoDB:
Importare l'input prima di eseguire i Job. (DB "airplaneDB" - Collezione "input") Comando qui di seguito:
sudo mongoimport -d airplaneDB -c input --type csv --file XXX.csv --headerline 

Nota: Eseguire i Job in serie, con l'ordinamento indicato:

1) routesJob
2) markersJob
3) meanDelayStandardDeviationJob
4) 
