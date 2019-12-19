package ga.tumgaming.tumine.tuminemoney.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import ga.tumgaming.tumine.tuminemoney.BankAccountManager;
import ga.tumgaming.tumine.tuminemoney.util.Config;

public class InventoryListeners extends BankCommand implements Listener 
{
	public InventoryListeners(Config config) 
	{
		super(config);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		String title = event.getView().getTitle();
		if(title.endsWith("'s Account"))
		{
			event.setCancelled(true);
			if(current == null || current.getType().getKey().toString().endsWith("_glass_pane"))
			{
				return;
			}
			
			//TODO
			BankAccountManager manager = new BankAccountManager(config);
			switch(current.getItemMeta().getDisplayName())
			{
				case "Account balance":
					break;
					
				case "Deposit 1 Gold Ingot":
					if(player.getInventory().contains(Material.GOLD_INGOT))
					{
						manager.depositUnchangedMoneyFromPlayer(player, (long) (Math.pow(64, 3)));
						player.getInventory().getItem(player.getInventory().first(Material.GOLD_INGOT))
						.setAmount(player.getInventory().getItem(player.getInventory().first(Material.GOLD_INGOT)).getAmount() - 1);
					}
					else
					{
						manager.countCurrentMoney(player);
					}
					break;
				
				case "Deposit 1 Gold Nugget":
					if(player.getInventory().contains(Material.GOLD_NUGGET))
					{
						manager.depositUnchangedMoneyFromPlayer(player, (long) (Math.pow(64, 2)));
					}
					break;
					
				case "Deposit 1 Iron Ingot":
					if(player.getInventory().contains(Material.IRON_INGOT))
					{
						manager.depositUnchangedMoneyFromPlayer(player, 64);
					}
					break;
					
				case "Deposit 1 Iron Nugget":
					if(player.getInventory().contains(Material.IRON_NUGGET))
					{
						manager.depositUnchangedMoneyFromPlayer(player, 1);
					}
					break;
					
				case "Withdraw":
					//TODO
			}
		}
	}
}
