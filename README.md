Tämä sovellus on Jetpack Composella toteutettu tehtävänhallintatyökalu, joka hyödyntää MVVM-arkkitehtuuria, jaettua tilaa ja navigointia kahden eri päänäkymän välillä.

Navigointi Jetpack Composessa:

- Navigointi Jetpack Composessa tarkoittaa siirtymistä sovelluksen eri näyttöjen (Composable-funktioiden) välillä ilman perinteisiä Activity-vaihtoja. Se perustuu sovelluksen sisäiseen reititykseen.

- NavController: Toimii navigoinnin keskusyksikkönä. Se pitää kirjaa näyttöjen historiasta ja suorittaa siirtymiset reittien välillä.

- NavHost: Säiliö, joka määrittelee sovelluksen navigointikaavion. Se yhdistää tietyt merkkijonoreitit (esim. home ja calendar) niitä vastaaviin näyttöihin.

- Toteutus: Sovelluksessa on käytössä kaksi pääreittiä: ROUTE_HOME ja ROUTE_CALENDAR. Käyttäjä voi siirtyä listanäkymästä kalenteriin yläpalkin ikonia painamalla ja palata takaisin listaan kalenterinäkymän painikkeella.

Arkkitehtuuri: MVVM ja jaettu tila:

- Sovellus noudattaa MVVM-mallia, joka erottaa käyttöliittymän ja liiketoimintalogiikan toisistaan.

- Yksi ViewModel, kaksi näyttöä: Sekä HomeScreen että CalendarScreen käyttävät samaa TaskViewModel-instanssia.

- Tilan jakaminen: ViewModel on alustettu NavHostin yläpuolella MainActivityssa, mikä estää sen uudelleenluomisen navigoinnin aikana.

- Datan synkronointi: Tehtävät on tallennettu ViewModeliin StateFlow-muodossa. Kun tehtävää muokataan tai se merkitään tehdyksi toisella näytöllä, muutos näkyy välittömästi toisella näytöllä collectAsState()-funktion ansiosta.

Näkymien toteutus:

- CalendarScreen: Tehtävät kalenterissa

Kalenterinäkymä tarjoaa vaihtoehtoisen tavan tarkastella tehtäviä:

- Ryhmittely: Tehtävät ryhmitellään niiden eräpäivän mukaan.

- Esitystapa: Jokainen päivämäärä näytetään omana otsikkonaan, jonka alla on lista kyseisen päivän tehtävistä. Tämä auttaa käyttäjää hahmottamaan tulevat määräajat selkeämmin.

AlertDialog:

- Kaikki tietojen syöttäminen ja muokkaaminen on keskitetty dialogeihin, jotta navigointirakenne pysyy yksinkertaisena:

- addTask: Uusi tehtävä lisätään kotiruudun kelluvan painikkeen (FAB) kautta avautuvassa AlertDialogissa. Käyttäjä syöttää nimen ja kuvauksen, jotka tallennetaan ViewModelin addTask-funktiolla.

- editTask: Olemassa olevaa tehtävää muokataan klikkaamalla sitä listasta, jolloin avautuu DetailDialog. Täällä voi päivittää tietoja (updateTask) tai poistaa tehtävän kokonaan (removeTask).