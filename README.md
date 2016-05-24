# ProjectTwoBigData

1) routesJob

Istruzioni per MongoDB:
Prima di eseguire il Job ci deve essere il db chiamato "airplaneDB". All'interno deve esserci la collezione "input" con l'importazione del csv del dataset (ho testato con un solo mese), senza alcuna modifica; una seconda collezione chiamata "airports" deve contenere il dataset degli aeroporti, il quale dovrà contenere tutti i campi "iata_code" popolati. Cancellare quelli vuoti con una query in Mongo.

Effetti:
Dopo l'esecuzione del Job risulterà una terza collezione chiamata "routes" con i risultati.
