# ProjectTwoBigData

1) routesJob

Istruzioni per MongoDB:
Importare prima di eseguire il Job, l'input e gli aeroporti. Comandi qui di seguito: \n
sudo mongoimport -d airplaneDB -c airports --type csv --file airports.csv --headerline \n
sudo mongoimport -d airplaneDB -c input --type csv --file XXX.csv --headerline \n
(Non rinominare il DB o le due collezioni)

Effetti:
Dopo l'esecuzione del Job risulterà una terza collezione chiamata "routes" con i risultati.
