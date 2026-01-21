Viikko1

Datamalli:

Sovelluksen keskiössä on Task-tietoluokka (data class), joka määrittelee yksittäisen tehtävän rakenteen. Se sisältää seuraavat tiedot:

id: Yksilöllinen tunniste.

title ja description: Tehtävän nimi ja tarkempi kuvaus.

priority: Tehtävän tärkeysaste.

dueDate: Eräpäivä, jonka perusteella lista voidaan järjestää.

done: Kertoo, onko tehtävä suoritettu.

Funktiot:
   
Sovelluksen toiminnallisuus on toteutettu Kotlin-funktioilla, jotka käsittelevät tehtävälistoja:

addTask: Lisää uuden tehtäväolion olemassa olevan listan loppuun ja palauttaa päivitetyn listan.

toggleDone: Etsii listasta tehtävän ID:n perusteella ja kääntää sen done-tilan vastakkaiseksi.

filterByDone: Suodattaa listasta näkyviin vain ne tehtävät, joiden tila vastaa annettua parametria, esimerkiksi näytä vain tehdyt.

sortByDueDate: Järjestää listan tehtävät järjestykseen niiden päivämäärän perusteella.

Viikko2:

Compose-tilanhallinta:

Sovellus hyödyntää Composen reagoivaa luonnetta käyttämällä mutableStateOf-tilaa ViewModelissa. 

- Kun käyttäjä tekee muutoksen (esim. painaa Checkboxia), kutsutaan ViewModelin funktiota.

- ViewModel päivittää sisäistä listaansa.

Koska lista on tarkkailtava tila (State), Compose huomaa muutoksen automaattisesti ja piirtää käyttöliittymän uudelleen vastaamaan uutta tilaa.

Miksi ViewModel on parempi kuin pelkkä remember:

ViewModelin käyttö tarjoaa useita etuja verrattuna siihen, että tila pidettäisiin pelkästään Composablen sisällä remember-muuttujassa:

- Elinkaaren hallinta: remember-muuttujat nollautuvat usein esimerkiksi konfiguraatiomuutoksen yhteydessä, mutta ViewModel säilyttää datan näissä tilanteissa.

- Vastuualueiden erottelu: Käyttöliittymäkoodi pysyy siistinä, kun monimutkainen logiikka ja datan käsittely siirretään ViewModeliin.

- Testattavuus: Sovelluslogiikkaa on helpompi testata automaattisilla testeillä, kun se ei ole sidottu suoraan käyttöliittymäkomponentteihin.
