# GraphTheoryHungarianPairingMethod

Gráf párosítási algoritmus implementáció

### Párosítási eljárás
A párosítás során az algoritmus mohó módon működik,
azaz az kezdő pont kiválasztó stratégia az első megfelelő kiválasztása.
Alternáló út keresés során is ezen stratégia mentén történik az él keresés.

### Input
3 input mód:
- input könyvtár tartalomra :APPBASE_DIR//src/main/resources/data/ könytárban létező .json
- kiválasztás dialog-gal
- regisztrált src/main/java/ioprocessors/input/enums/TestInputFiles.java -ben regisztrált graph json-re

### Elvárt input adatszerkezet

```json
{
  "version" : 1.0, // double : jelenleg, ez egy konstans, későbbi bővítés formátum módosulás támogatása
  "description": "Teljes egyszerű párosítás lehetséges.", // String: tetszőleges szöveg, magyarázat a példához 
  "graph" : { // öszetett adatszerkezet : gráf definíció
    "vertex" : [1,2,3,4], // array : gráfban létező pontok
    "edge" : [// array : gráfban található élek megadása
      [1,4], // array : éldefinició, 2 elemű 
      [1,2],
      [3,4]
    ],
    "groups" : { // párosítási csoport definíció 
      "A" : [1,3], // key: csoport cimke, value: pontok melyek a csoportba tartoznak
      "B" : [2,4]
    },
    "pairing" : [ // már párosított élek megadása
      [1,4]
    ] 
  },
  "startGroup" : "A" // itt lehet megadni mely csoport ból induljon a párosítás
}
```

### Output
2 output fájl stuktúra létezik:
- ##### könig akadály találat esetén

```json
{
  "pairGraph": {
    "groups": {
      "A": [
        1,
        3
      ],
      "B": [
        2,
        4
      ]
    },
    "startGroup": "A",
    "edges": [
      {
        "label": "14",
        "source": 1,
        "destination": 4,
        "weight": 0.0
      },
      {
        "label": "12",
        "source": 1,
        "destination": 2,
        "weight": 0.0
      }
    ],
    "pairEdges": [
      {
        "label": "14",
        "source": 1,
        "destination": 4,
        "weight": 0.0
      }
    ],
    "pairlessVertexFromStartGroup": [
      3
    ]
  },
  "alternaloUt": {
    "startVertex": 3,
    "path": []
  }
}
```

pairlessVertexFromStartGroup: kiinduló csoportban lévő pár nélküli pontok a gráfban  
alternaloUt : ahol az alternáló út az a keresési út ahol abba mardt a keresés könig akadály miatt


- ##### helyesen párosított gráf output

```json
{
  "groups": {
    "A": [
      1,
      3
    ],
    "B": [
      2,
      4
    ]
  },
  "startGroup": "A",
  "edges": [
    {
      "label": "12",
      "source": 1,
      "destination": 2,
      "weight": 0.0
    },
    {
      "label": "14",
      "source": 1,
      "destination": 4,
      "weight": 0.0
    },
    {
      "label": "34",
      "source": 3,
      "destination": 4,
      "weight": 0.0
    }
  ],
  "pairEdges": [
    {
      "label": "12",
      "source": 1,
      "destination": 2,
      "weight": 0.0
    },
    {
      "label": "34",
      "source": 3,
      "destination": 4,
      "weight": 0.0
    }
  ],
  "pairlessVertexFromStartGroup": []
}
```


