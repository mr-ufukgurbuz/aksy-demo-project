package org.havelsan.aksy.question4;


public class MainQuestion4 {

    public static void main(String[] args) {
        System.out.println("<<< ANSWER 4 >>>");
        System.out.println("---------------------------------------------------");
    }

    /* BEFORE REFACTORING
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
    }*/

    /* AFTER REFACTORING
    public void processMsgWeaponStatus(MsgWeaponStatus msg) {
        Optional.ofNullable(msg.getLevelStatus() == LevelStatus.LEVEL1 && msg.getWarningCode() != 0 &&
                mapError.getWarning(EnumErrorType.WARNING) != null && mapError.getWarning(EnumErrorType.WARNING)
                ? mapErrorProcessor.getProcessor(EnumProcessor.WARNING)     // Ustteki if sartlarinin hepsi true ise
                : null)                                                     // Ustteki if sartlarinin hepsi true degilse null dondur
            .ifPresent(processor -> processor.processWarning(msg.getWarningCode()));    // Ustteki optional deger null degilse; bu islemi yap
    }
    */

}
