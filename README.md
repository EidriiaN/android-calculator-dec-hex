

---

# Calculator Dec-Hex pentru Android

Acest proiect reprezintă un calculator robust, destinat platformei Android, care permite utilizatorilor să efectueze operațiuni aritmetice și conversii între numere în format decimal (baza 10) și hexadecimal (baza 16).

## Funcționalități principale

1. **Conversie între baze**: Ușor de utilizat pentru conversia rapidă a numerelor între formatul decimal și hexadecimal.
2. **Operațiuni Aritmetice**: Suport pentru efectuarea operațiunilor de bază, cum ar fi adunarea, scăderea și înmulțirea.
3. **Istoricul Operațiilor**: Utilizatorii pot vizualiza un istoric complet al calculelor efectuate, stocate într-o bază de date locală SQLite.
4. **Trimiterea Istoricului**: O caracteristică adițională permite utilizatorilor să trimită istoricul operațiunilor printr-un e-mail.
5. **Mod Tematic**: Include un switch pentru schimbarea între modurile zi și noapte pentru confort vizual.
6. **Jurnalizarea Operațiilor**: Păstrează un jurnal detaliat al tuturor operațiunilor efectuate de utilizator.

## Instalare și utilizare

1. **Pregătirea**: Asigurați-vă că aveți Android Studio instalat pe mașina dvs.
2. **Acces**: Clonează repositoriu folosind `git clone [URL-repo]`.
3. **Deschidere**: Deschideți directorul proiectului în Android Studio.
4. **Rulare**: Pornește aplicația pe un emulator sau pe un dispozitiv Android real conectat.

## Structura Proiectului

- **MainActivity**: Punctul central de intrare în aplicație. Gestionează navigația între diferite fragmente.
- **HomeFragment**: Fragmentul principal pentru conversii și operații aritmetice.
- **EmailFragment**: Permite utilizatorilor să trimită istoricul operațiunilor prin e-mail.
- **HistoryFragment**: Afișează un istoric al operațiunilor efectuate de utilizator.
- **LogsFragment**: Furnizează un jurnal detaliat al operațiunilor pentru sesiuni anterioare.
- **MyDBHelper**: Ajută la gestionarea bazei de date SQLite pentru stocarea istoricului operațiunilor.

---
