# ProjectTwoBigData

1) routesJob

Istruzioni per MongoDB:
Importare l'input prima di eseguire il Job. (Collezione "input") Comando qui di seguito:
sudo mongoimport -d airplaneDB -c input --type csv --file XXX.csv --headerline 

Effetti:
Dopo l'esecuzione del Job risulterà una nuova collezione chiamata "routes" con i risultati.
