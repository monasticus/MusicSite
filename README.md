# MusicSite
Projekt zaliczeniowy kursu Java Developer: Web w CodersLab

## O aplikacji

Moim celem było podsumowanie i implementacja wiedzy zdobytej na kursie, szczególnie od strony Back-endu. 
W tym celu chciałem stworzyć aplikację, która da mi największe szanse, aby je wykorzystać. 
Stworzyłem więc aplikację podobną do portalu FilmWeb, lecz dotyczącą muzyki.
W moim założneiu aplikacja ta miała być miła użytkownikowi, miała poszerzać jego wiedzę i umożliwiać wymianę informacji.
MusicSite ma umożliwiać użytkownikowi ocenę utworów, albumów i wykonawców, oraz dzielenie się swoimi upodobaniami. 
W odróżnieniu od spotify ma skupiać się właśnie na tym, a nie na słuchaniu, 
a w odróżneiniu od Last.fm, jej celem nie jest sprawdzanie czego użytkownik słucha najwięcej. 
Mimo wszystko chciałem aby na stronie utworu znajdował się jego film z YouTube.
W docelowym stanie, oprócz podstawowych funkcjonalności jak zarządzanie zasobami, 
w których uczestniczyć może również użytkownik,
aplikacja miała wyświetlać na stronie głównej najczęściej polecane utwory; 
na stronie użytkownika miała wyświetlać wpisy znajomych oraz informacje o ich aktywności;
umożliwiać wysyłanie wiadomości między użytkownikami; 
na stronie albumów i wykonawców miały być zawarte informacje o nich oraz odsyłacze do innych portali: 
Wikipedia, Spotify, Last.fm, Facebook, WhoSampled.

## Wykorzystane języki i technologie
1. Java
   - Spring Context
   - Spring MVC
   - Spring Data
   - Hibernate
   - Hibernate Validator
   - Własne konwertery
   - Własny walidator
   - Własne wiadomości walidacyjne (ValidationMessages.properties)
   - Grupy walidacyjne
   - Filtry
   - Wzorzec Model-View-Controller
   - Transakcyjność
   - JavaMailSender
   - Klasy abstrakcyjne
   - Interfejsy
   - Wyrażenia regularne
2. Baza danych
3. JSP
   - Taglib JSTL
   - Taglib form
4. JavaScript
   - Eventy
5. HTML i CSS
   - Zasoby Bootstrap
   - Własne stylowanie


## Co udało się zrobić?
#### Aplikacja ma 3 tryby dostępu:
- zwykły użytkownik
- zalogowany użytkownik
- administrator

#### Aplikacja zawiera podstawowe elementy:
- Nagłówek
- Strona główna
- Strona Wykonawcy, albumu, utwóru
- Strona rankingów: wykonawców, albumów, utworów oraz kategorii.
- Wyszukiwarka
- Formularz dodawania: wykonawcy, albumu, utworu
- Formularz rejestracji oraz logowania

#### Dodatkowe elementy dostępne dla administratora:
- Formularz dodawania kategorii
- Zarządzanie użytkownikami
- Zarządzanie propozycjami (możliwość zatwierdzania lub odrzucania danych wprowadzania przez użytkowników)

##### Tryby dostępu
Zwykły użytkownik ma dostęp do przeglądania zasobów.\
Użytkownik zalogowany może dodawać obiekty do zasobów, ale nie bezpośrednio. Muszą być zatwierdzone przez administratora
Użytkownik zalogowany może również oceniać obiekty. Ocena zostaje zapisana w bazie danych i oczywiście wpływa na średnią obiektu.
Użytkownik zalogowany ma możliwość komentowania obiektów.
Użytkownik zalogowany ma dostęp do edycji swoich danych.
Administrator może usuwać użytkowników, zatwierdzać lub odrzucać propozycje. Podando może usuwać oraz edytować zatwierdzone wcześniej obiekty.

Zwykły użytkownik ma odcięty dostęp do opcji zalogowanego użytkownika oraz administratora.
Zalogowany użytkownik ma odcięty dostęp do opcji administatora, formularza zalogowania oraz rejestracji.
Administrator, jako zalogowany użytkownik ma odcięty dostęp do formularza zalogowania i rejestracji.

##### Podstawowe elementy
###### Nagłówek
- zawiera logo strony, będące przyciskiem odsyłającym do strony głównej
- pasek nawigacji zawierający odsyłacze do rankinków oraz wyszukiwarkę
  - przed zalogowaniem zawiera opcję "zaloguj" z odsyłaczem do formularza logowania.
  - po zalogowaniu w miejscu "zaloguj" pojawia się imię użytkownika z odsyłaczem do edycji jego danych.
- gdy użytkownik jest zalogowany, zawiera przycisk do wylogowania.

###### Strona główna
 - Wyświetla na "karuzeli" 3 losowe utwory i daje możliwość odświeżenia strony
 
###### Strona wykonawcy, albumu, utworu
Wszystkie trzy w górnej sekcji zawierają podstawowe informacje: nazwę, kateogrie, ocenę, możliwość oceny.\
W przypadku albumu i utworu widnieje informacja o roku oraz wykonawcy; utwór zawiera jeszcze informację o albumie, z którego pochodzi.\
Sekcja dolna zawiera komentarze.

Sekcja środkowa w przypadku:
1. wykonawcy
   - zawiera zakładki do albumów oraz utworów, sortowanych od najnowszego do najstarzego.
2. albumu
   - zawiera listę utworów
   - gdy do albumu nie są przypisane żadne utwory, wyświetla przycisk do formularza jednoczesnego dodawania utworów do tego albumu.
   Użytkownik wybiera liczbę utworów, którą chce dodać i wpisuje jedynie ich nazwy i gatunki.
3. utworu
   - zawiera film YouTube
   
###### Strona rankingów
Strony wyświetlają posortowaną względem oceny listę obiektów.\
Ranking w opcji kategorii zawiera możliwość wyboru kategorii, do których jest przypisany chociaż jeden obiekt.

###### Wyszukiwarka
Wyszukiwarka wyszukuje wpisaną frazę wśród nazw obiektów, niezależnie od wielkości liter czy położenia frazy.

###### Formularze dodawania
Formularze zawierają elementy odpowiadające danym o obiekcie. Są zabezpieczone różnymi walidacjami, aby nie można było zbyt swobodnie manipulować danymi.\
M.in:
- wykonawca musi mieć nazwę, ale nie musi mieć imienia i nazwiska
- album musi mieć wykonawcę i rok, może mieć wiele gatunków
- utwór musi mieć wykonawcę, nie musi mieć albumu, do którego jest przypisany, ale jeśli jest do jakiegoś przypisany to wpisany rok musi się zgadzać z rokiem albumu.

###### Formularz rejestracji
Formularz rejestracji zawiera podstawowe pola jak email, nazwa, imię i hasło. 
Zabezpieczony jest walidacją unikalnego emailu, dostatecznie długiej liczby znaków hasła, 
czy potiwerdzenia hasła poprzez wpisanie go ponownie.\
Po wypełnieniu danych do użytkownika zostaje wysłany mail z potwierdzeniem rejestracji.
Jeśli email nie istnieje (aplikacja sprawdza to przy pomocy rzucanych wyjątków), 
użytkownik natychmiastowo zostaje wyrzucony z bazy. Przez właściwe zabezpiczenie i format linka potwierdzającego, 
użytkownik ma możliwosć potwierdzania tylko swojego konta i to tylko raz.

###### Formularz logowania
Formularz logowania sprawdza czy istnieje użytkownik o takim emailu i haśle, oraz sprawdza czy został on zatwierdzony. 
Jeśli istnieje, ale nie został zatweirdzony, wyświetla inofmrację o sprawdzeniu skryznki pocztowej. 
Jeśli dane są niepoprawne, informuje jedynie o tym, że email lub hasło są nieprawidłowe. 
Jeśli logowanie isę powiedzie, przenosi użytkownika na stronę główną aplikacji.


## Najbliżej planowane modyfikacje
- możliwość jednoczesnego dodawania wielu albumów do wykonawcy (analogiczna do możliwości dodawania wielu utworów do albumu).
- dodanie możliwości polecania oraz dodawania do ulubionych dla zalogowanych użytkowników.
- stworzenie panelu użytkownika
- możliwość dodawania do grupy znajomych
- możliwość wysyłania wiadomości i tworzenia postów, widocznych dla znajomych
- modyfikacja strony głównej aby wyświetlała najczęściej polecane utwory
- wprowadzenie klasyfikacji punktowej za aktywność użytkowników
