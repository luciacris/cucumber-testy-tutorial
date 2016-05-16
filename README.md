# Cucumber Testy Tutorial

Integration Tests with [Testy](https://github.com/sdl/Testy) and [Cucumber](https://cucumber.io/)

## Running the Tests:

    mvn clean test -DfailIfNoTests=false
	
For Running specific Test add system parameter "test":
	 
	 mvn clean test -DfailIfNoTests=false -Dtest=LoginTest -Dbrowser=FIREFOX //daca se da din consola tipul browserului
	 mvn clean test -DfailIfNoTests=false -Dtest=AccountTest
	 mvn clean test -DfailIfNoTests=false -Dtest=TestyElementsTesty -Dapp.url=http://examples.sencha.com/extjs/6.0.2/examples/classic/view/data-view.html
	 //mai sus, daca app.url a fost pus in properties, atunci poti sa redefinesti in linia de comanda o proprietate din properties (ex. app.url) si asta are intaietate (se ignora ce e in properies)

