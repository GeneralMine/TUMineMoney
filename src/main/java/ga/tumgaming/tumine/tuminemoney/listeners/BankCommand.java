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
	private Config config;
	
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
				ItemStack stack = new ItemStack(Material.WRITTEN_BOOK);
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName("Account balance");
				int[] balance = manager.getChangedMoneyFromPlayer(recipient);
				List<String> lore = new ArrayList<>();
				lore.add("Gold Ingots: "+balance[0]);
				lore.add("Gold Nuggets: "+balance[1]);
				lore.add("Iron Ingots: "+balance[2]);
				lore.add("Iron Nuggets: "+balance[3]);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				
				ItemStack stack2 = new ItemStack(Material.SPRUCE_SIGN);
				meta.setDisplayName("Withdraw");
				
				ItemStack stack3 = new ItemStack(Material.CHEST);
				meta.setDisplayName("Deposit");
				
				bankInv.setItem(4, stack);
				bankInv.setItem(10, stack2);
				bankInv.setItem(17, stack3);
				
				recipient.openInventory(bankInv);
			}
		}
		return false;
	}

}
