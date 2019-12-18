package ga.tumgaming.tumine.tuminemoney.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListeners implements Listener 
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		String title = event.getView().getTitle();
		if(title.endsWith("'s Account"))
		{
			event.setCancelled(true);
			if(current == null)
			{
				return;
			}
			switch(current.getItemMeta().getDisplayName())
			{
				case "Account balance":
					break;
					
				case "Deposit":
					//TODO
					
				case "Withdraw":
					//TODO
			}
		}
	}
}
