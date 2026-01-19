Datamalli:

Sovelluksen keskiössä on Task-tietoluokka (data class), joka määrittelee yksittäisen tehtävän rakenteen. Se sisältää seuraavat tiedot:

id: Yksilöllinen tunniste.

title ja description: Tehtävän nimi ja tarkempi kuvaus.

priority: Tehtävän tärkeysaste.

dueDate: Eräpäivä, jonka perusteella lista voidaan järjestää.

done: Kertoo, onko tehtävä suoritettu.

2. Funktiot
   
Sovelluksen toiminnallisuus on toteutettu Kotlin-funktioilla, jotka käsittelevät tehtävälistoja:

addTask: Lisää uuden tehtäväolion olemassa olevan listan loppuun ja palauttaa päivitetyn listan.

toggleDone: Etsii listasta tehtävän ID:n perusteella ja kääntää sen done-tilan vastakkaiseksi.

filterByDone: Suodattaa listasta näkyviin vain ne tehtävät, joiden tila vastaa annettua parametria, esimerkiksi näytä vain tehdyt.

sortByDueDate: Järjestää listan tehtävät järjestykseen niiden päivämäärän perusteella.
