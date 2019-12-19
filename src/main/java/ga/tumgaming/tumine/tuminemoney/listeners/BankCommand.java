package ga.tumgaming.tumine.tuminemoney.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ga.tumgaming.tumine.tuminemoney.BankAccountManager;
import ga.tumgaming.tumine.tuminemoney.util.Config;

public class BankCommand implements CommandExecutor {
	protected Config config;
	
	public BankCommand(Config config) 
	{
		this.config = config;
	}

	@EventHandler
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (commandSender instanceof Player) {
			Player npc = (Player) commandSender;
			if(args[0] == null)
			{
				Bukkit.getConsoleSender().sendMessage("Error: no arguments found");
			}
			Player recipient = Bukkit.getPlayer(args[0]);
			if(recipient == null)
			{
				Bukkit.getConsoleSender().sendMessage("Error: no bank recipient found");
			}
			else
			{
				BankAccountManager manager = new BankAccountManager(config);
				Inventory bankInv = Bukkit.createInventory(null, 27, recipient.getName() + "'s Account!");
				ItemStack balance = new ItemStack(Material.WRITTEN_BOOK);
				ItemMeta meta = balance.getItemMeta();
				meta.setDisplayName("Account balance");
				int[] accountMoney = manager.getChangedMoneyFromPlayer(recipient);
				List<String> lore = new ArrayList<>();
				lore.add("Gold Ingots: "+accountMoney[0]);
				lore.add("Gold Nuggets: "+accountMoney[1]);
				lore.add("Iron Ingots: "+accountMoney[2]);
				lore.add("Iron Nuggets: "+accountMoney[3]);
				meta.setLore(lore);
				balance.setItemMeta(meta);
				
				ItemStack depositGI = new ItemStack(Material.GOLD_INGOT);
				ItemMeta depositGIMeta = depositGI.getItemMeta();
				depositGIMeta.setDisplayName("Deposit 1 Gold Ingot");
				depositGI.setItemMeta(depositGIMeta);
				
				ItemStack depositGN = new ItemStack(Material.GOLD_NUGGET);
				ItemMeta depositGNMeta = depositGN.getItemMeta();
				depositGNMeta.setDisplayName("Deposit 1 Gold Nugget");
				depositGN.setItemMeta(depositGNMeta);
				
				ItemStack depositII = new ItemStack(Material.IRON_INGOT);
				ItemMeta depositIIMeta = depositII.getItemMeta();
				depositIIMeta.setDisplayName("Deposit 1 Iron Ingot");
				depositII.setItemMeta(depositIIMeta);
				
				ItemStack depositIN = new ItemStack(Material.IRON_NUGGET);
				ItemMeta depositINMeta = depositIN.getItemMeta();
				depositINMeta.setDisplayName("Deposit 1 Iron Nugget");
				depositIN.setItemMeta(depositINMeta);
				
				ItemStack depositALL = new ItemStack(Material.DIAMOND);
				ItemMeta depositALLMeta = depositALL.getItemMeta();
				depositALLMeta.setDisplayName("Deposit All");
				depositALL.setItemMeta(depositALLMeta);
				
				ItemStack withdrawGI = new ItemStack(Material.GOLD_INGOT);
				ItemMeta withdrawGIMeta = withdrawGI.getItemMeta();
				withdrawGIMeta.setDisplayName("Withdraw 1 Gold Ingot");
				withdrawGI.setItemMeta(withdrawGIMeta);
				
				ItemStack withdrawGN = new ItemStack(Material.GOLD_NUGGET);
				ItemMeta withdrawGNMeta = withdrawGN.getItemMeta();
				withdrawGNMeta.setDisplayName("Withdraw 1 Gold Nugget");
				withdrawGN.setItemMeta(withdrawGNMeta);
				
				ItemStack withdrawII = new ItemStack(Material.IRON_INGOT);
				ItemMeta withdrawIIMeta = withdrawII.getItemMeta();
				withdrawIIMeta.setDisplayName("Withdraw 1 Iron Ingot");
				withdrawII.setItemMeta(withdrawIIMeta);
				
				ItemStack withdrawIN = new ItemStack(Material.IRON_NUGGET);
				ItemMeta withdrawINMeta = withdrawIN.getItemMeta();
				withdrawINMeta.setDisplayName("Withdraw 1 Iron Nugget");
				withdrawIN.setItemMeta(withdrawINMeta);
				
				ItemStack withdrawALL = new ItemStack(Material.DIAMOND);
				ItemMeta withdrawALLMeta = withdrawALL.getItemMeta();
				withdrawALLMeta.setDisplayName("Withdraw All");
				withdrawALL.setItemMeta(withdrawALLMeta);
				
				ItemStack depositGlass = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				
				ItemStack withdrawGlass = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
				
				bankInv.setItem(13, balance);
				bankInv.setItem(0, depositGlass);
				bankInv.setItem(1, depositGlass);
				bankInv.setItem(2, depositGI);
				bankInv.setItem(3, depositGN);
				bankInv.setItem(4, depositII);
				bankInv.setItem(5, depositIN);
				bankInv.setItem(6, depositALL);
				bankInv.setItem(7, depositGlass);
				bankInv.setItem(8, depositGlass);
				bankInv.setItem(18, withdrawGlass);
				bankInv.setItem(19, withdrawGlass);
				bankInv.setItem(20, withdrawGI);
				bankInv.setItem(21, withdrawGN);
				bankInv.setItem(22, withdrawII);
				bankInv.setItem(23, withdrawIN);
				bankInv.setItem(24, withdrawALL);
				bankInv.setItem(25, withdrawGlass);
				bankInv.setItem(26, withdrawGlass);
				
				recipient.openInventory(bankInv);
			}
		}
		return false;
	}

}
