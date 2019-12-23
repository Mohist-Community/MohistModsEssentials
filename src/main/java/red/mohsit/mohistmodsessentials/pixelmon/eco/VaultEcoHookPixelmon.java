package red.mohsit.mohistmodsessentials.pixelmon.eco;

import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import java.util.ArrayList;
import java.util.List;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

/**
 * @Author Mgazul
 * @create 2019/10/14 16:02
 */
public class VaultEcoHookPixelmon extends AbstractEconomy {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "MoPixelmon";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return String.valueOf(v);
    }

    @Override
    public String currencyNamePlural() {
        return "";
    }

    @Override
    public String currencyNameSingular() {
        return "";
    }

    @Override
    public boolean hasAccount(String s) {
        return EcoUtils.getPixelmonBankAccount(s) != null;
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return this.hasAccount(s);
    }

    @Override
    public double getBalance(String s) {
        PlayerPartyStorage account = EcoUtils.getPixelmonBankAccount(s);
        return (account != null) ? account.getMoney() : 0.0;
    }

    @Override
    public double getBalance(String s, String s1) {
        return this.getBalance(s);
    }

    @Override
    public boolean has(String s, double v) {
        return this.getBalance(s) >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return this.has(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        PlayerPartyStorage account = EcoUtils.getPixelmonBankAccount(s);
        if (account == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "");
        }
        double balance = account.getMoney();
        if (v < 0.0) {
            return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "");
        }
        if (balance - v < 0.0) {
            return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "");
        }
        if (EcoUtils.changeMoney(account, (int)(v * -1.0))) {
            return new EconomyResponse(v, (double)account.getMoney(), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return this.withdrawPlayer(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        PlayerPartyStorage account = EcoUtils.getPixelmonBankAccount(s);
        if (account == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "");
        }
        double balance = account.getMoney();
        if (v < 0.0) {
            return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "");
        }
        if (EcoUtils.changeMoney(account, (int)v)) {
            return new EconomyResponse(v, (double)account.getMoney(), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(0.0, (double)account.getMoney(), EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return this.depositPlayer(s, v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return  new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "not support bank accounts!");
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<String>();
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return this.createPlayerAccount(s);
    }
}
