# aksy-demo-project

## Question-1 Result
```bash
<<< ANSWER 1 >>>
---------------------------------------------------
Allianz A.S. Insurance
Turkiye Sigorta A.S. Insurance
Unknown
Unknown
Unknown
Unknown
```

## Question-2 Result
```bash
<<< ANSWER 2 >>>
---------------------------------------------------
Optional1
```

## Question-3 Result
```bash
<<< ANSWER 3 >>>
---------------------------------------------------
Her Şehirde Yaşayan Kişi Sayısı: {İstanbul=3, Bursa=1, İzmir=1, Ankara=2}
Her Şehirde Yaş Ortalaması: {İstanbul=41.333333333333336, Bursa=22.0, İzmir=32.0, Ankara=21.5}
Şehir ve Yaş Grubuna Göre Kişiler: {İstanbul={ORTA=[Burak, Ece], YAŞLI=[Ahmet]}, Bursa={GENÇ=[Ali]}, İzmir={ORTA=[Can]}, Ankara={GENÇ=[Ayşe, Nehir]}}
Kişi İsimleri ve Email'leri: {Nehir=nehir.com, Burak=burak.com, Ahmet=ahmet.com, Ece=ece.com, Ali=ali.com}
```

## Question-4 Result
#### BEFORE REFACTORING
```bash
public void processMsgWeaponStatus(MsgWeaponStatus msg) {
    if (msg.getLevelStatus() == LevelStatus.LEVEL1) {
        if (msg.getWarningCode != 0) {  // MefOutRangeException -- Checked
            if (mapError.getWarning(EnumErrorType.WARNING) != null && mapError.getWarning(EnumErrorType.WARNING)) {
                if (mapErrorProcessor.getProcessor(EnumProcessor.WARNING) != null) {
                    mapErrorProcessor.getProcessor(EnumProcessor.WARNING).processWarning(msg.getWarningCode);
                }
            }
        }
    }
}
```

#### AFTER REFACTORING
```bash
public void processMsgWeaponStatus(MsgWeaponStatus msg) {
    Optional.ofNullable(msg.getLevelStatus() == LevelStatus.LEVEL1 && msg.getWarningCode() != 0 &&
            mapError.getWarning(EnumErrorType.WARNING) != null && mapError.getWarning(EnumErrorType.WARNING)
            ? mapErrorProcessor.getProcessor(EnumProcessor.WARNING)     // Ustteki if sartlarinin hepsi true ise
            : null)                                                     // Ustteki if sartlarinin hepsi true degilse null dondur
        .ifPresent(processor -> processor.processWarning(msg.getWarningCode()));    // Ustteki optional deger null degilse; bu islemi yap
}
```