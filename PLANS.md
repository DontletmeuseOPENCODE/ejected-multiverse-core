# Ejected Multiverse — Horror Mod dla Minecraft

> Liminal / uncanny horror inspirowany Backrooms, The Stanley Parable i
> SCP. Multi-wymiarowy rdzeń (stąd `multiverse-core` w nazwie), w którym
> gracz wpada do "wyrzuconych" warstw rzeczywistości. Każdy wymiar to
> zamknięta, znajomo-obca przestrzeń z własnymi regułami.

**Stack:** NeoForge 1.21.x · Java 21 · Gradle (Kotlin DSL) · Mojmap mappings

---

## 0. Zasady projektu

- **Klimat > zawartość.** Lepiej 3 wymiary z silnym horror vibe niż 15 pustych.
- **Asset-last.** Pisz logikę i struktury danych najpierw; modele i tekstury to ostatnia mila.
- **Deterministyczny horror.** seedowalne RNG, powtarzalne anomalie — gracz ma czuć, że "to się dzieje naprawdę".
- **Brak jump-scare'ów jako głównej mechaniki.** Atmosfera, dźwięk, perspektywa, światło.
- **Wszystko tunable z datapacków.** Trudność, czasy respawnu, listy mobów, reguły wymiarów.

---

## 1. Fazy rozwoju (roadmap)

Każda faza kończy się kompilowalnym, grywalnym buildem. Faza N+1 zaczyna się
dopiero po `git tag phase-N` i smoke-teście ręcznym.

### ✅ Faza 0 — Fundament *(ten commit)*
- [x] `PLANS.md` z roadmapą
- [x] Porządki w repo (usunięcie śmieciowego `package.json` / `node_modules`)
- [ ] `.gitignore` dla projektu MC (build/, .gradle/, run/, *.log, *.iml, .idea/)

### 🚧 Faza 1 — Szkielet projektu (1-2 dni)
- [ ] `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties` (NeoForge 1.21.x, Java 21)
- [ ] `gradle/wrapper/` (gradle-wrapper.jar + .properties)
- [ ] `src/main/java/com/dontletmeuseopencode/ejectedmultiverse/EjectedMultiverse.java` (główna klasa moda)
- [ ] `src/main/resources/META-INF/mods.toml`, `pack.mcmeta`
- [ ] Smoke test: `./gradlew build` → BUILD SUCCESSFUL; klient i serwer startują

### 🚧 Faza 2 — Multi-wymiarowy rdzeń (3-5 dni)
- [ ] Rejestr wymiarów (`DimensionRegistry`) + JSON-owy datapack API
- [ ] Bazowy `EjectedDimension` — flat, generowany z seeded noise, mgła, custom sky
- [ ] `PortalItem` + `PortalBlock` — wejście do wymiaru (użycie jak ender pearl na bloku)
- [ ] Sanity check: przejście do wymiaru i z powrotem bez crasha

### 🚧 Faza 3 — Liminalny wymiar #1: "The Yellow Hallway" (1-2 tyg)
- [ ] Generacja: nieskończone korytarze w żółtych tapeżach (proceduralne, seeded)
- [ ] Biomy: `yellow_hallway`, `poolroom`, `dark_corridor`
- [ ] Custom sky (gradient żółty→beż), brak słońca, ambient particles (kurz)
- [ ] Custom ambient soundscape (loop szelest + odległe kroki)
- [ ] Light level: nigdy > 7, moby hostile spawn tylko w cieniu

### 🚧 Faza 4 — Pierwszy mob: "The Watcher" (3-5 dni)
- [ ] Encja humanoid-stretched, AI: stoi nieruchomo, podąża wzrokiem, znika po zbliżeniu
- [ ] Renderer: stretched model + migoczący shader
- [ ] Dźwięk: skrzypienie podłogi przy ruchu gracza w promieniu 16 kratek
- [ ] Spawn rule: tylko w `yellow_hallway`, night-time, daleko od gracza

### 🚧 Faza 5 — Mechanika "Sanity" (1-2 tyg)
- [ ] Player capability `Sanity` (0-100), per-gracz, persystentna
- [ ] Spadki: długi pobyt w wymiarze, ciemność, patrzenie na Watchera
- [ ] Efekty niskiego sanity: screen distortion (shader), odwrócone sterowanie, szept w słuchawkach
- [ ] Regeneracja: wychodząc z wymiaru, w świetle, po przespanej nocy
- [ ] HUD: pasek sanity obok paska zdrowia (tylko w wymiarach Ejected)

### 🚧 Faza 6 — Wymiary 2 & 3 + boss (2-3 tyg)
- [ ] Wymiar "The Office" (SCP-173-ish) — biurowe kafle, migające światła, mob The Walker
- [ ] Wymiar "The Stairwell" (nieskończone schody w górę) — sanity drain
- [ ] Boss: "The Archivist" w jednym z wymiarów, fight z logiką "nie patrz na niego"
- [ ] Loot: klucze do zamkniętych pokojów, notatki (lore w postaci książek MC)

### 🚧 Faza 7 — Polishing & release (1 tydzień)
- [ ] Datapack config: trudność, listy mobów, sanity tune
- [ ] README z instrukcją, screenshotami, licencją
- [ ] CurseForge pack (screenshoty, opis, wymagania, dependencies)
- [ ] Smoke test na 3 różnych seedach
- [ ] Tag `v0.1.0-alpha`

---

## 2. Struktura docelowa katalogów

```
ejected-multiverse-core/
├── PLANS.md                    ← ten plik
├── README.md
├── LICENSE                     ← MIT lub GPL-3, decyzja przed fazą 7
├── .gitignore
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradle/
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/com/dontletmeuseopencode/ejectedmultiverse/
│   │   │   ├── EjectedMultiverse.java
│   │   │   ├── core/           ← rejestry, capability, sieć pakietów
│   │   │   ├── dimension/      ← kod specyficzny dla wymiarów
│   │   │   ├── entity/         ← moby (Watcher, Walker, Archivist)
│   │   │   ├── block/
│   │   │   ├── item/
│   │   │   ├── worldgen/       ← chunk generator, biomy, features
│   │   │   ├── client/         ← render, HUD, shadery
│   │   │   └── data/           ← sanity events, datagen
│   │   └── resources/
│   │       ├── META-INF/mods.toml
│   │       ├── assets/ejectedmultiverse/   ← tekstury, modele, dźwięki
│   │       └── data/ejectedmultiverse/      ← datapacki (wymiary, looty)
│   └── test/                   ← testy logiki (sanity, wymiary, RNG)
└── docs/
    ├── DESIGN.md               ← głębszy design doc (lore, mechaniki)
    └── CONTRIBUTING.md
```

---

## 3. Decyzje do podjęcia w trakcie

| Temat | Kiedy | Domyślna |
|---|---|---|
| Licencja | Faza 7 | MIT |
| Port z Fabric na NeoForge? | Po fazie 5 | Zostajemy NeoForge |
| Multiplayer safety | Faza 4 | Cały horror singleplayer; w MP tylko ambience |
| Compat z ShadersMod / Iris | Faza 7 | Iris-compatible shadery |
| Polski vs angielski w grze | Faza 5 | Angielski (CurseForge audience) |

---

## 4. Jak pracujemy (workflow)

- **Commity:** konwencja `phase-N: krótki opis` (np. `phase-2: add portal block registration`).
- **Każda faza = 1 PR / 1 seria commitów + 1 tag.**
- **PRzed commitem:** `./gradlew build` musi przejść.
- **Każdy nowy feature ma conajmniej 1 test** (logika) lub 1 manualną ścieżkę (asset-heavy).

---

## 5. Ryzyka i mitygacje

| Ryzyko | Mitygacja |
|---|---|
| Liminalny klimat wymaga assetów których nie mam | Startujemy z proceduralną generacją; assety to fazy 3+ |
| Shadery / render tricki są kruche między wersjami MC | Trzymamy się publicznego API NeoForge; shadery optional |
| Scope creep (za dużo wymiarów / mobów) | Roadmap jest linearny; faza N+1 nie startuje bez review |
| Burnout solo dev | Faza 0+1 to fundament; potem iterujemy małymi krokami |
