MVVM-malli (Model-View-ViewModel):
MVVM on arkkitehtuurimalli, jonka tarkoituksena on erottaa sovelluksen käyttöliittymä ja liiketoimintalogiikka toisistaan.

Model: Sisältää sovelluksen datan ja tietomallit (esim. Task.kt).

View: Käyttöliittymäkerros (Compose), joka näyttää datan käyttäjälle. Se ei sisällä logiikkaa, vaan reagoi ViewModelin tarjoamaan tilaan.

ViewModel: Toimii välittäjänä Modelin ja View'n välillä. Se hakee datan ja muokkaa sitä sekä pitää yllä sovelluksen tilaa.

Miksi se on hyödyllinen Composessa?

-  Compose on suunniteltu toimimaan tilan (State) ympärillä. Kun ViewModelissa oleva tila muuttuu, UI piirtyy automaattisesti uudelleen.

-  Koodi on helpompi testata ja ylläpitää, kun käyttöliittymän koodi ei ole sekaisin laskentalogiikan kanssa.

- ViewModel säilyttää datan esimerkiksi näytön kääntämisen yhteydessä, jolloin käyttäjän syöttämä tieto ei katoa.

StateFlow:

- StateFlow on tilan seurantaan tarkoitettu tietovirta (Flow), joka säilyttää aina uusimman arvon ja lähettää sen kaikille tilaajilleen.

- Stateflow olemassaoleva ja pitää arvonsa, vaikka kukaan ei aktiivisesti kuuntelisi sitä juuri sillä hetkellä.

- UI-kerroksessa StateFlow muunnetaan Compose-yhteensopivaksi tilaksi käyttämällä collectAsState()-funktiota. Tämä varmistaa, että jokainen muutos ViewModelin datassa päivittää näytön välittömästi.