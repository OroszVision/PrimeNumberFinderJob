# Popis třídy PrimeNumberFinder
Třída PrimeNumberFinder je konzolová aplikace, která hledá prvočísla v Excelovém souboru. Aplikace funguje následovně:

# Vstup
Program přijímá jako argument cestu k Excel souboru (XLSX formát), který obsahuje data.
Zpracovává první list (Sheet 0) Excelu.
# Použití algoritmu Eratosthenova síta
Program využívá algoritmus Eratosthenova síta k předpočítání prvočísel do 1 000 000 (Tento rozsah lze v kódu změnit).
Prvočísla jsou uložena do množiny pro rychlé vyhledávání.
# Zpracování Excelu
Prochází každý řádek a kontroluje hodnotu ve druhé buňce (sloupec B).
Pokud je hodnota v buňce celé číslo a nachází se mezi prvočísly, je tato hodnota zalogována.
Pokud buňka obsahuje neplatná data, program zaloguje varování s konkrétní hodnotou.
# Logování
Program používá knihovnu Log4j pro logování.
Logy zahrnují informace o nalezených prvočíslech a varování pro neplatná data.
Struktura testů PrimeNumberFinderTest

# Testy jsou navrženy tak, aby pokryly různé scénáře a ověřily správné fungování aplikace:
## Testování validních prvočísel
Tento test ověřuje, zda jsou správně zalogována všechna prvočísla nalezená v Excel souboru.
## Testování neplatných dat
Test ověřuje, zda aplikace správně reaguje na neplatná data (např. text místo čísel) a zaznamenává příslušná varování.
## Testování nenumerických hodnot
Test kontroluje, zda aplikace správně zpracovává chyby při výskytu nenumerických hodnot v buňkách Excelu.
## Testování chybějícího souboru
Tento test zajišťuje, že aplikace zaloguje chybu, pokud není nalezen Excel soubor, a že je tato chyba správně ohlášena.
# Spuštění JAR souboru
Pokud máte již JAR soubor sestavený, aplikaci můžete spustit následujícím příkazem:
`java -jar <cesta_k_vasemu_JAR_souboru> <cesta_k_Excel_souboru>`

Například:
`java -jar out/artifacts/WorkTestPrimeNumbers_jar/WorkTestPrimeNumbers.jar src/main/resources/vzorek_dat.xlsx`
