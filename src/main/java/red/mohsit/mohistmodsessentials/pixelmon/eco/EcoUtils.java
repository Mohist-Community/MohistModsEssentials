package red.mohsit.mohistmodsessentials.pixelmon.eco;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.EconomyEvent;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import java.util.UUID;
import org.bukkit.Bukkit;

/**
 * @Author Mgazul
 * @create 2019/10/14 16:10
 */
public class  EcoUtils {

    public static PlayerPartyStorage getPixelmonBankAccount(String playerName) {
        UUID uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId();
        return getPixelmonBankAccount(uuid);
    }

    public static PlayerPartyStorage getPixelmonBankAccount(UUID uuid) {
        return Pixelmon.storageManager.getParty(uuid);
    }

    public static boolean changeMoney(PlayerPartyStorage pps, int change) {
        int oldBalance = pps.getMoney();
        EconomyEvent.transactionType type = (change < 0) ? EconomyEvent.transactionType.withdraw : EconomyEvent.transactionType.deposit;
        EconomyEvent.PreTransactionEvent preEvent = new EconomyEvent.PreTransactionEvent(pps.getPlayer(), type, oldBalance, change);
        if (!Pixelmon.EVENT_BUS.post(preEvent)) {
            int newBalance = oldBalance + preEvent.change;
            if (pps.getMoney() != newBalance) {
                pps.setMoney(newBalance);
                if (pps.getMoney() < 0) {
                    pps.setMoney(0);
                }
                if (pps.getPlayer() != null) {
                    pps.setMoney(pps.getMoney());
                }
            }
            Pixelmon.EVENT_BUS.post(new EconomyEvent.PostTransactionEvent(pps.getPlayer(), type, oldBalance, pps.getMoney()));
            return true;
        }
        return false;
    }
}
