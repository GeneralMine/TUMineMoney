package ga.tumgaming.tumine.tuminemoney;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import ga.tumgaming.tumine.tuminemoney.listeners.BlockBreakListener;
import ga.tumgaming.tumine.tuminemoney.listeners.ClickListener;
import ga.tumgaming.tumine.tuminemoney.listeners.JoinListener;
import ga.tumgaming.tumine.tuminemoney.listeners.BankCommand;
import ga.tumgaming.tumine.tuminemoney.util.*;

public class BankAccountManager
{
	private Config config;
	
	public BankAccountManager(Config config)
	{
		this.config = config;
	}
	
	public long getUnchangedMoneyFromPlayer(Player recipient)
	{
		checkIfPlayerHasBankAccount(recipient);
		return config.get(recipient.getUniqueId().toString());
	}
	
	public int[] getChangedMoneyFromPlayer(Player recipient)
	{
		int[] ergebnis = new int[4];
		long money = getUnchangedMoneyFromPlayer(recipient);
		
		if((int)(money/Math.pow(64, 3)) > 0)
		{
			ergebnis[0] = (int) ((int) money/Math.pow(64, 3));
			money = (long) (money % Math.pow(64, 3));
		}
		else
		{
			ergebnis[0] = 0;
		}
		
		if((int)(money/Math.pow(64, 2)) > 0)
		{
			ergebnis[1] = (int) ((int) money/Math.pow(64, 2));
			money = (long) (money % Math.pow(64, 2));
		}
		else
		{
			ergebnis[1] = 0;
		}
		
		if((int)(money/64) > 0)
		{
			ergebnis[2] = (int) money/64;
			money = money % 64;
		}
		else
		{
			ergebnis[2] = 0;
		}
		
		ergebnis[3] = (int) money;
		
		return ergebnis;
	}
	
	public long countCurrentMoney(Player recipient)
	{
		long ergebnis = 0;
		ItemStack[] inventar = recipient.getInventory().getContents();
		
        for (ItemStack item : inventar) 
        {
            if (item.getType() == Material.GOLD_INGOT) 
            {
                ergebnis += item.getAmount() * Math.pow(64, 3);
            }
            
            if (item.getType() == Material.GOLD_NUGGET) 
            {
                ergebnis += item.getAmount() * Math.pow(64, 2);
            }
            
            if (item.getType() == Material.IRON_INGOT) 
            {
                ergebnis += item.getAmount() * 64;
            }
            
            if (item.getType() == Material.IRON_NUGGET) 
            {
                ergebnis += item.getAmount();
            }
        }
		
		return ergebnis;
	}
	
	public void deleteAllCurrencyFromPlayer(Player recipient)
	{
		recipient.getInventory().remove(Material.GOLD_INGOT);
		recipient.getInventory().remove(Material.GOLD_NUGGET);
		recipient.getInventory().remove(Material.IRON_INGOT);
		recipient.getInventory().remove(Material.IRON_NUGGET);
	}
	
	public long withdrawUnchangedMoneyFromPlayer(Player recipient, long amount)
	{
		checkIfPlayerHasBankAccount(recipient);
		if((long) config.get(recipient.getUniqueId().toString()) < amount)
		{
			return -1; //Insufficient funds
		}
		config.set(recipient.getUniqueId().toString(), (long) config.get(recipient.getUniqueId().toString()) - amount);
		return amount;
	}
	
	public void depositUnchangedMoneyFromPlayer(Player recipient, long amount)
	{
		checkIfPlayerHasBankAccount(recipient);
		config.set(recipient.getUniqueId().toString(), (long) config.get(recipient.getUniqueId().toString()) + amount);
	}
	
	public boolean checkIfPlayerHasBankAccount(Player recipient)
	{
		if(config.get(recipient.getUniqueId().toString()) == null)
		{
			long money = 0;
			config.set(recipient.getUniqueId().toString(), money);
			return false;
		}
		return true;
	}
}
