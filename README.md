Currency Savings Calculator — Android App

Простий Android-додаток для розрахунку заощаджень у іноземній валюті (євро або долар) за рік. Додаток використовує курси валют на початок та кінець року, обчислює річний дохід, суму обміну, кількість придбаної валюти та кінцевий результат заощаджень.

Функціональність

Головний екран з кнопками Почати розрахунок та Вийти.

Введення місячного доходу (M) та відсотка (p: 0 < p < 1) для конвертації.

Вибір валюти (EUR або USD).

Розрахунки (всі 7 формул реалізовані в CurrencyCalculator.kt):

гіпотетичний річний дохід (Sy),

сума для обміну (Sc),

кількість придбаної валюти (W) з врахуванням інтерпольованих місячних курсів,

гривнева вартість придбаної валюти на кінець року (SH),

гривневий залишок (SL),

сума H = SH + SL,

сума заощадження R = H − Sy.

Показ результатів на окремому екрані з кнопкою Ок для повернення на початковий екран.

Підтримка портретної і альбомної орієнтацій (окремі layout-файли) та збереження стану при повороті екрана (onSaveInstanceState).

Навігація реалізована на базі Activity (через Intent / startActivityForResult).

Структура репозиторію
app/
├─ src/main/java/...
│  ├─ MainActivity.kt
│  ├─ Calculation1Activity.kt
│  ├─ Calculation2Activity.kt
│  ├─ ResultsActivity.kt
│  └─ CurrencyCalculator.kt
├─ src/main/res/layout/
│  ├─ activity_main.xml
│  ├─ activity_calculation1.xml
│  ├─ activity_calculation2.xml
│  └─ activity_results.xml
├─ src/main/res/layout-land/   (версії для альбомної орієнтації)
└─ AndroidManifest.xml
build.gradle.kts

Вимоги

Android Studio (рекомендовано остання стабільна версія)

JDK 17

compileSdk = 36 (у проєкті)

Gradle та Android Gradle Plugin сумісних версій (Android Studio запропонує необхідні налаштування)

Як запустити локально

Клонувати репозиторій:

git clone https://github.com/3Mira3/Laba1-PMP.git
cd Laba1-PMP


Відкрити в Android Studio: File → Open… → [папка проєкту]

Якщо IDE запитає — встановіть потрібні SDK/плагіни (compileSdk, platform tools тощо).

Створіть або виберіть AVD (емулятор) або підключіть реальний пристрій.

Запустіть додаток через кнопку Run ▶ у Android Studio.

Нотатки про реалізацію

Інтерфейс побудовано з використанням ConstraintLayout.

Видалено залежності Jetpack Compose (проект на Activity + XML).

Збереження стану та передача даних між Activity реалізовані через onSaveInstanceState та Intent/Bundle.

Для оптимальної коректної роботи при зміні орієнтації створені окремі layout-файли в res/layout-land/.

Посилання

Репозиторій: https://github.com/3Mira3/Laba1-PMP.git
